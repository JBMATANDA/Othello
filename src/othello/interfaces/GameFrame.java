/*
 GameFrame är en instans av en javafx.scene.layout.BorderPane och finns uppe så 
 länge programmet körs. Den innehåller, bland annat, en instans av GameBoard samt 
 knapparna ”Nytt parti” och ”Avsluta”.
 */
package othello.interfaces;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import othello.GameGridProperty;
import participants.GetMove;

public class GameFrame{


    private final GameBoard board = new GameBoard();
    private final BorderPane pane = new BorderPane();
    private final VBox vBox; 
    private final Button btEnd = new Button("End Game");
    private final Button btNew = new Button("New Game");
    

    public GameFrame(Stage primaryStage, GameGridProperty gridProperty, GetMove getMove) {        
        Scene scene = new Scene(pane, 600, 500);
        primaryStage.setTitle("Othello");
        primaryStage.setScene(scene);
        board.setGetMove(getMove);
        vBox = getVBox();
        updateView(gridProperty.getValue());
        bindViewToModel(gridProperty);
        //DrawnDialog draw = new DrawnDialog(primaryStage);
    }
    
    public void bindViewToModel(GameGridProperty gridProperty){
        gridProperty.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> updateView(newValue)));
    }
    
    private void updateView(int[][] gameGrid){
        System.out.println("\n\tUPDATING VIEW\t\n");
        pane.setCenter(board.createBoard(gameGrid)); 
        pane.setLeft(vBox);
    }
    
    private VBox getVBox() {
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(15, 15, 15, 15));
        vBox.setStyle("-fx-background-color: darkgreen");
        vBox.getChildren().add(btNew);
        vBox.getChildren().add(btEnd);
//        btNew.setOnAction();
//        btEnd.setOnAction();
        
        return vBox;
    }
    
    
    
    public void gameOver(){
        new DrawnDialog(new Stage());
    }
    public void gameOver(String winner){
        new WinnerDialog(new Stage(), winner);
    }
    
    

 

    //trycker man på NewGame skall spelplanen rensas och starta på nytt.

    class NewGamehandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            

        }
    }

    //Trycker man på EndGame skall spelet Avsluta.

    class EndGamehandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

        }
    }
}
