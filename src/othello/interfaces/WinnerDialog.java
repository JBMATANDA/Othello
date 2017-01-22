/*
 Denna klassen utgör en dialogruta som informerar om en spelare har vunnit.
 Spelaren skall tilldelas med namn. Dialogrutan innehåller också en ”Ok” knapp 
 som stänger dialogrutan.
 */
package othello.interfaces;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author S153298
 */
public class WinnerDialog {

    Alert alert;

    public WinnerDialog(Stage primaryStage, String winner) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(winner + " won");

        alert.showAndWait();

    }
}
