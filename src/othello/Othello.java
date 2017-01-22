/*
Othello är spelets huvudklass och (eftersom spelet är en JaxaFX applikation) 
ärver från javafx.application.Application.

pg.lost - I am a destroyer
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
        

        Application.launch(args);
    }
    
    @Override
    
    
    /*När spelet börjar, utan att man startat en match, så körs en demo mellan
    två ai, precis som på gamla arkadspel. Detta var något vi la in mest för 
    att det var kul.
    */
    
    public void start(Stage primaryStage){
        
        GameManager gm = new GameManager(new LocalComputerPlayer("Demo Player 1",1), new LocalComputerPlayer("Demo Player 2",2));
        GameFrame frame = new GameFrame(primaryStage, gm.getGameGridProperty(), gm.getGetMove());
        gm.setFrame(frame);
        gm.run();
        primaryStage.show();
    }
}

 
