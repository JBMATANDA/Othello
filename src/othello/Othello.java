/*
Othello är spelets huvudklass och (eftersom spelet är en JaxaFX applikation) 
ärver från javafx.application.Application.
 */
package othello;

import othello.interfaces.GameBoard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import participants.*;

/**
 *
 * @author Group 4
 * 
 */
public class Othello extends Application{
    
    GameBoard board = new GameBoard();
    private boolean wakeful = true;
    
    /**
     * Here the thread that holds the board is created
     */
    @Override public void start(Stage primaryStage) {
        
     new Thread(new Runnable() {
         
      @Override public void run() {
          
        try {
            
          while (wakeful) {
            
            Platform.runLater(new Runnable() {
                
              @Override public void run() {
                board.initBoard(primaryStage);
                
                 //Debbuging line...                 
                System.out.println("Board's thread ID number: " + Thread.currentThread().getId());
              }
            });
            
            Thread.sleep(1000);
          }
        }
        catch (InterruptedException ex) {  }
      }
    }).start();
  }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Application.launch(args);
    }
}