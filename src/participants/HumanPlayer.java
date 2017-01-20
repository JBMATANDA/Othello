/*
Då nästa drag begärs från GameManager väntar man tills användaren utfört draget.
 */
package participants;

import java.util.Scanner;
import javafx.beans.property.ObjectProperty;
import othello.Move;

/**
 *
 * @author S153298
 */
public class HumanPlayer extends Player{
    Scanner in;
    
    
    public HumanPlayer(String name, int markerId){
        super(name, markerId);
        in = new Scanner(System.in);
    }

    @Override
    public void getMove(Move[] moveList, ObjectProperty<Move> playerMadeMove) {


        new Thread(new MoveMaker(moveList, playerMadeMove)).start();
        

    }
    
    private class MoveMaker implements Runnable{

        private Move[] moveList;
        private ObjectProperty<Move> playerMadeMove;
        
        public MoveMaker(Move[] moveList, ObjectProperty<Move> playerMadeMove){
            this.moveList = moveList;
            this.playerMadeMove = playerMadeMove;
        }
        
        @Override
        public void run() {
            printMoveList(moveList);
            Move move;
            System.out.println("Player " + getName() + " Enter x");
            int x = in.nextInt();
            System.out.println("Player " + getName() + " Enter y");
            int y = in.nextInt();
            
            move = new Move(x, y, getMarkerId());
            
            this.playerMadeMove.set(move);
        }
        
    }
}