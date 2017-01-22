/*
Då nästa drag begärs från GameManager väntar man tills användaren utfört draget.
 */
package participants;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void getMove(Move[] moveList, ObjectProperty<Move> playerMadeMove, GetMove getMove) {
        
        new Thread(new MoveMaker(moveList, playerMadeMove, getMove)).start();
        
    }
    
    private class MoveMaker implements Runnable{

        private Move[] moveList;
        private ObjectProperty<Move> playerMadeMove;
        private GetMove getMove;
        
        public MoveMaker(Move[] moveList, ObjectProperty<Move> playerMadeMove, GetMove getMove){
            this.moveList = moveList;
            this.playerMadeMove = playerMadeMove;
            this.getMove = getMove;
        }
        
        @Override
        public void run() {
            try {
                printMoveList(moveList);
                Move move;
                
                
                int[] moveInt = GetMove.getMove().take();
                int x = moveInt[0];
                int y = moveInt[1];
                
                move = new Move(x, y, getMarkerId());
                
                this.playerMadeMove.set(move);
            } catch (InterruptedException ex) {
                Logger.getLogger(HumanPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}