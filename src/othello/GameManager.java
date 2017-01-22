/*
 GameManager är den som administrerar pågående parti. GameManagerns uppgift är enkel.
 Den skall gång på gång, omväxlande, be de två spelarna att ange sitt nästa drag. 
 Den skall också tala om för spelarna om de vunnit eller gjort ett felaktigt drag.
 Eftersom GameManagern inte skall veta vilken typ av spelare den har att göra 
 med skall dynamisk bindning användas vid anrop av spelarnas operationer.
 */
package othello;

import java.util.Scanner;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import othello.interfaces.GameFrame;
import participants.GetMove;
import participants.Player;

/**
 *
 * @author S153298
 */
public class GameManager {

    private GameGrid board;
    private Scanner in;
    private Player[] playerList;

    private GameFrame frame;

    private ObjectProperty<Move> playerMadeMove = new SimpleObjectProperty<>(); //Property som vi skickar med varje gång en spelare ska göra ett move (dvs vi anropar getmove) i getmove så har vi en set, vilket aktiverar vår lyssnare i run()
    private ObjectProperty<int[]> humanClick = new SimpleObjectProperty<>();
    private GetMove getMove = new GetMove();
    
    private int currentPlayer = 0;

    /*
     TODO
     - Run new turn
     - Select next player
     - Prompt new move until one is legal
     - Make move on board
     - Run match that makes new turns until someone wins or loses
     - New turn
     - Continue making new turn when a move is succesfully made in the GameGrid (board).
    
     - Run method gameOver when someone lost or won or draw etc.
            
     */
    public GameManager(Player white, Player black) {
        board = new GameGrid();

        in = new Scanner(System.in);
        playerList = new Player[2];
        playerList[0] = white;
        playerList[1] = black;
        addTheObjectPropertyListener();
    }

    public void run() {
        //board.printBoard();
        playerList[currentPlayer].getMove(board.getLegalMoves(playerList[currentPlayer]), playerMadeMove, getMove);
    }

    public GameGridProperty getGameGridProperty() {
        return board.gridProperty();
    }
    public GetMove getGetMove(){
        return getMove;
    }

    public void setFrame(GameFrame frame) {
        this.frame = frame;
    }

    private void addTheObjectPropertyListener() {
        this.playerMadeMove.addListener(e -> {                                  //varje gång getMove anropas så aktiveras set(se player-klassen), vilket lyssnaren ser.
            Move move = this.playerMadeMove.get();                              //Inuti får vi ett move-objekt genom get (som fick det från set)
            if (!board.isLegalMove(move)) {
                //System.out.println("Move was not legal! Try again.");
                playerList[currentPlayer].getMove(board.getLegalMoves(playerList[currentPlayer]), playerMadeMove, getMove);       //ifall det inte gick så anropar vi getMove, vilket anropar set, som tar oss tillbaka till lyssnaren
            } else {
                board.addToGrid(move);
                //board.printBoard();
                advanceTurn();
            }
        });
    }

    private void advanceTurn() {
        if (!board.checkForLegalMoves()) {
            gameOver();
            System.exit(0);
        }
        nextTurn();
        if (!(board.checkForLegalMoves(playerList[currentPlayer]))) {
            nextTurn();
        }
        playerList[currentPlayer].getMove(board.getLegalMoves(playerList[currentPlayer]), playerMadeMove, getMove);
    }

    private void nextTurn() {
        currentPlayer = (currentPlayer + 1) % 2;
    }

    private void gameOver() {
        //System.out.println("Player 1: " + board.getScore(1));
        //System.out.println("Player 2: " + board.getScore(2));
        //System.out.println("Winner: " + board.getWinner());
        int winner = board.getWinner();

        if (winner == 0) {
            frame.gameOver();
        } else {
            frame.gameOver(playerList[winner - 1].getName());
        }
        
        while(true){}
    }

}
