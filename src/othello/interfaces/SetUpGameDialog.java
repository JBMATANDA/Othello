
package othello.interfaces;

import participants.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import othello.GameManager;


public class SetUpGameDialog {

    Player player1, player2;
    GameManager gameManager = new GameManager(player1,player2);
    
    Button startGameButton = new Button("Start Game");
    PlayerSelectionBox playerOneSelection;
    PlayerSelectionBox playerTwoSelection;
    private final Stage stage;
    private TextField playerOneName;
    private TextField playerTwoName;

    public SetUpGameDialog(Stage stage) {
        this.stage = stage;
    }

    public void setUpGameDialogFrame() {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Game Modes");
        pane.setTop(getTextField());
        
        playerOneSelection = new PlayerSelectionBox(1);
        playerTwoSelection = new PlayerSelectionBox(2);
        
        pane.setLeft(playerOneSelection);
        pane.setRight(playerTwoSelection);
        pane.setCenter(startGameButton);
        stage.show();

        setupNewGameButtonActions();
    }
    
    public FlowPane getTextField() {
        FlowPane fpane = new FlowPane();
        fpane.setPadding(new Insets(11, 12, 13, 14));
        fpane.setHgap(5);
        fpane.setVgap(5);
        
        this.playerOneName = new TextField();
        this.playerTwoName = new TextField();
        
        fpane.getChildren().addAll(new Label("Player 1: WHITE "), playerOneName);
        fpane.getChildren().addAll(new Label("Player 2: BLACK "), playerTwoName);
        return fpane;
    }

    private void setupNewGameButtonActions() {
        startGameButton.setOnAction(e -> {
            
            Player player1 = playerOneSelection.getPlayerFromSelection(playerOneName.getText());
            Player player2 = playerTwoSelection.getPlayerFromSelection(playerTwoName.getText());
            
            GameManager gm = new GameManager(player1, player2);
            GameFrame frame = new GameFrame(this.stage, gm.getGameGridProperty(), gm.getGetMove());
            gm.setFrame(frame);
            gm.run();
        });
    }
}
