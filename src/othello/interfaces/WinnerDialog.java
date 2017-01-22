/*
 Denna klassen utgör en dialogruta som informerar om en spelare har vunnit.
 Spelaren skall tilldelas med namn. Dialogrutan innehåller också en ”Ok” knapp 
 som stänger dialogrutan.
 */
package othello.interfaces;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author S153298
 */
public class WinnerDialog {

    Alert alert;

    public WinnerDialog(String winner) {
        Platform.runLater(() -> {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(winner + " won");

            alert.showAndWait();
        });
    }
}
