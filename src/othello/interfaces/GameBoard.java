package othello.interfaces;

import javafx.application.Application;
import javafx.event.*; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class GameBoard {

    private Pane[][] cell;
    private Label status;
    private GridPane pane;

    public GameBoard(){
        cell = new Pane[8][8];
        status = new Label("Black's turn to play");
        pane = new GridPane();
    }

    //Skapa spelbärdan.. Påminelse till Bosco Använd GridPane Istället

    public void createBoard() {

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                pane.add(cell[row][column] = new Cell(), column, row);
            
                if (column % 2 == 0) {
                    cell[row][column].setPrefSize(2000, 2000);
                    cell[row][column].setMinSize(200, 200);
                    cell[row][column].setStyle("-fx-background-color: black;");
                } else {
                    cell[row][column].setMinSize(200, 200);
                    cell[row][column].setPrefSize(2000, 2000);
                    cell[row][column].setStyle("-fx-background-color: white;");
                }
            }
        }
    }
    
    public GridPane getGameBoard(){
        return this.pane;
    }
    
    class Cell extends Pane{
        private int row, column;
         
        public Cell(){        
           this.column = column; 
           this.row = row; 
           this.setStyle("-fx-border-color: black");
            this.setPrefSize(500,500);
        }
    public Ellipse setPiece(int markerID){
        Ellipse piece = new Ellipse(this.getWidth()/2,
                this.getHeight()/2, this.getWidth()/2 - 10,
                this.getHeight()/2 - 10);
                
        piece.radiusXProperty().bind(
                this.widthProperty().divide(2).subtract(10));
        piece.radiusYProperty().bind(
                this.heightProperty().divide(2).subtract(10));
        piece.centerXProperty().bind(
                this.widthProperty().divide(2));
        piece.centerYProperty().bind(
                this.heightProperty().divide(2));
        switch(markerID){
            case 1: 
                piece.setFill(Color.BLACK);
                piece.setStroke(Color.WHITE);
                break; 
          case 2: 
              piece.setFill(Color.WHITE);
              piece.setStroke(Color.BLACK);
              break; 
          default: 
               piece.setFill(Color.TRANSPARENT);
              piece.setStroke(Color.TRANSPARENT);
              break; 
        } 
        return piece;
    } 
    
    class Cell extends Pane{
        
        public Cell(){
            setStyle("-fx-border-color: black"); 
            this.setPrefSize(2000,2000); 
            this.setOnMouseClicked(e -> handleMouseClick()); 
        }
    }
    private void handleMouseClick(){
        
    }
    }
}
