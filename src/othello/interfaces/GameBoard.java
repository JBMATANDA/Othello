/*
GameBoard kan t.ex. vara av typen javafx.scene.layout.Pane och utgör den grafiska
representationen av spelplanen och används för att presentera den aktuella ställningen.
GameBoard fångar dessutom upp användarens (HumanPlayer) val av ruta då denne gör ett drag.
Observera att trots att gränssnittsobjekten ritar spelplanen och brickorna i den,
så är det inte deras uppgift att hålla reda på den aktuella ställningen. När 
spelplanen skall ritas om hämtas all information från GameGriden.
 */
package othello.interfaces;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene; 
import javafx.stage.Stage; 

public class GameBoard{
  
    
    public void createBoard(){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

      
                String color;

                if ((i + j) % 2 == 0) {
                    color = "FORESTGREEN";
                } else {
                    color = "GREEN";
                }
            }
        }
    }
}
