/*
Othello är spelets huvudklass och (eftersom spelet är en JaxaFX applikation) 
ärver från javafx.application.Application.
 */
package othello;

import participants.*;


/**
 *
 * @author S153298
 */
public class Othello{
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GameManager gm = new GameManager(new ComputerPlayer("John", 1), new ComputerPlayer("Carlos", 2));
        gm.run();
    }
}