/*
Denna klassen utgör en liknande dialogruta som informerar om att spelet blev oavgjort.
 */
package othello.interfaces;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author S153298
 */
public class DrawnDialog {
    
    Alert alert;
    
    public DrawnDialog(Stage primaryStage){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("It was a draw");
        
        alert.showAndWait();
        
    }
    
}
