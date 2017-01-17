/*
Othello är spelets huvudklass och (eftersom spelet är en JaxaFX applikation) 
ärver från javafx.application.Application.
 */
package othello;

import participants.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.application.Application; 
import othello.interfaces.GameFrame;


/**
 *
 * @author S153298
 */
public class Othello extends Application{
    //Just testing
    
    
    /**
     * @param args the command line arguments
     */
    
    @Override
    public void start(Stage primaryStage){
        GameFrame frame = new GameFrame(primaryStage);
         primaryStage.show();
    }
    
    public static void main(String[] args) {
      
      Application.launch(args);
    }
}

 
