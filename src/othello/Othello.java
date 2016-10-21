/*
Othello är spelets huvudklass och (eftersom spelet är en JaxaFX applikation) 
ärver från javafx.application.Application.
 */
package othello;

import javafx.application.Application;
import javafx.stage.Stage;
import participants.*;

/**
 *
 * @author Group 4
 * 
 */
public class Othello/* extends Application*/{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        GameManager gm = new GameManager();
        gm.run();
    }
}