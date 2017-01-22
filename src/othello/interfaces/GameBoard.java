package othello.interfaces;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import participants.GetMove;

public class GameBoard {

    private Cell[][] cell;
    private GridPane pane;
    private int size = 8;
    private GetMove getMove;

    public GameBoard() {
        cell = new Cell[8][8];
        pane = new GridPane();
    }

    //Skapa spelbärdan.. Påminelse till Bosco Använd GridPane Istället


    public GridPane createBoard(int[][] grid) {

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {

                Cell square = new Cell(column, row, grid[column][row]);
                square.setOnMouseClicked(e -> square.theClick(getMove));
                pane.add(cell[column][row] = square, column, row);
            }
        }
        return pane;
    }
    
    public void setGetMove(GetMove getMove){
        this.getMove = getMove;
    }

    class Cell extends Pane {

        private int row, column;
        private int colour = 0;

        public Cell(int column, int row, int colour) {
            this.column = column;
            this.row = row;
            this.setStyle("-fx-border-color: black");
            this.setPrefSize(1500, 1500);

            switch (colour) {
                case 1: {
                    this.getChildren().add(new DrawChip().drawWhiteChip());
                    break;
                }
                case 2: {
                    this.getChildren().add(new DrawChip().drawBlackChip());
                    break;
                }
            }
        }

        private void theClick(GetMove getMove) {
            try {
                int[] coordinatesXY = {this.column, this.row};
                //System.out.println(coordinatesXY[0] + "  " + coordinatesXY[1]);
                
                //GetMove.getMove().put(this.column, this.row);
                getMove.put(this.column, this.row);
                
                //return coordinatesXY;
            } catch (InterruptedException ex) {
                Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    static class DrawChip {

        public Circle drawBlackChip() {

            Circle chip = new Circle(30, 30, 15);
            chip.setStroke(Color.WHITE);
            chip.setFill(Color.BLACK);

            return chip;
        }

        public Circle drawWhiteChip() {

            Circle chip = new Circle(30, 30, 15);
            chip.setStroke(Color.BLACK);
            chip.setFill(Color.WHITE);

            return chip;
        }



    }

}
