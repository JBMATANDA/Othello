/*
Klassen Player är basklass för HumanPlayer, LocalComputerPlayer och 
RemoteComputerPlayer. Klassen innehåller attributen namn och markör-id samt 
en abstrakt metod för att begära beräkning av nästa drag. 
Namnet sätts i dialogrutan SetUpGameDialog och används i 
WinnerDialog. Markör-id anger om spelaren har vita eller svarta brickor.
 */
package participants;

import javafx.beans.property.ObjectProperty;
import othello.Move;

/**
 *
 * @author S153298
 */
public  abstract class Player {
    
    private String name;
    private int markerId;

    public Player(String name, int markerId){
        this.name = name;
        this.markerId = markerId;
    }
    /**
     * Prompts the player to make a move, which is then stored in the ObjectProperty
     * and can be accessed through get().
     * Also calls the set() method, which is good for listeners.
     * @param moveList
     * @param playerMadeMove 
     */
    
    public abstract void getMove(Move[] moveList, ObjectProperty<Move> playerMadeMove, GetMove getMove);
    
    public String getName(){
        return name;
    }
    public int getMarkerId(){
        return markerId;
    }
    
    protected void setName(String name){
        this.name = name;
    }
    protected void setMarkerId(int markerId){
        this.markerId = markerId;
    }
    protected void printMoveList(Move[] moveList){
        for(int i = 0; i < moveList.length; i++){
            System.out.println("X: " + moveList[i].getX() + " Y: " + moveList[i].getY());
        }
    }
}
