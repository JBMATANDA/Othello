/*
Denna klass beräknar nästa drag utifrån den information som finns i klassen GameGrid.
Som du säkert inser är den besvärligaste metoden att konstruera en intelligent datorspelare.
För att inte fastna på detta nu, är det tillåtet att låta datorn vara hur dum som helst,
dvs. den genererar helt enkelt ett slumpmässigt tillåtet drag från en lista med alla möjliga 
tillåtna drag (istället för att beräkna ett drag från GameGrid).
Vi kommer att kika på hur man skapar smartare datorspelare i kursen intelligenta och lärande system.
 */
package participants;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import othello.Move;

/**
 *
 * @author S153298
 */
public class LocalComputerPlayer extends Player{
    Random rng = new Random();
    
    public LocalComputerPlayer(String name, int markerId){
        super(name, markerId);
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
            //printMoveList(moveList);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(LocalComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            int selectedMove = rng.nextInt(moveList.length);
            System.out.println(moveList[selectedMove].getX()+ " " + moveList[selectedMove].getY());
            playerMadeMove.set(moveList[selectedMove]);
        }
    }
}