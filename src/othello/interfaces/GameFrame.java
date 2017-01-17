/*
 GameFrame är en instans av en javafx.scene.layout.BorderPane och finns uppe så 
 länge programmet körs. Den innehåller, bland annat, en instans av GameBoard samt 
 knapparna ”Nytt parti” och ”Avsluta”.
 */
package othello.interfaces;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class GameFrame {

    GameBoard board = new GameBoard();

    public GameFrame(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setCenter(board.getGameBoard());
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Othello");
        primaryStage.setScene(scene);
        pane.setLeft(getVBox());
        pane.setCenter(board.getGameBoard()); 
        primaryStage.show();
    }

    private VBox getVBox() {
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(15, 15, 15, 15));
        vBox.setStyle("-fx-background-color: darkgreen");
        vBox.getChildren().add(new Button("New Game"));
        vBox.getChildren().add(new Button("End Game"));
        return vBox;
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
