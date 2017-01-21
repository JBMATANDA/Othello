/*
Othello är spelets huvudklass och (eftersom spelet är en JaxaFX applikation) 
ärver från javafx.application.Application.
 */
package othello;

import javafx.application.Application;
import javafx.stage.Stage;
import othello.interfaces.GameFrame;
import participants.*;


/**
 *
 * @author S153298
 */
public class Othello extends Application{
    //Just testing
    
    
    /**
     * @param args the command line arguments
     */
    

    
    public static void main(String[] args) {
        

        //DatabaseManager.addClient2(6, "2325.45", 5487768);
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        
        GameManager gm = new GameManager(new HumanPlayer("John",1), new LocalComputerPlayer("Carlos",2));
        GameFrame frame = new GameFrame(primaryStage, gm.getGameGridProperty());
        gm.setFrame(frame);
        gm.run();
        primaryStage.show();
    }
}

 
