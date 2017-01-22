package othello.interfaces;

import javafx.application.Application;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class GameBoard {

    private Cell[][] cell;
    private Label status;
    private GridPane pane;
    private int size = 8;

    public GameBoard() {
        cell = new Cell[8][8];
        status = new Label("Black's turn to play");
        pane = new GridPane();
    }

    //Skapa spelbärdan.. Påminelse till Bosco Använd GridPane Istället


    public GridPane createBoard(int[][] grid) {

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {

                Cell square = new Cell(column, row, grid[column][row]);
                square.setOnMouseClicked(e -> square.theClick());
                pane.add(cell[column][row] = square, column, row);
            }
        }
        return pane;
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

        private int[] theClick() {
            int[] coordinatesXY = {this.column, this.row};
            System.out.println(coordinatesXY[0] + "  " + coordinatesXY[1]);
            return coordinatesXY;
        }

        private Ellipse setPiece(int markerID) {
            Ellipse piece = new Ellipse(this.getWidth() / 2,
                    this.getHeight() / 2, this.getWidth() / 2 - 10,
                    this.getHeight() / 2 - 10);

            piece.radiusXProperty().bind(
                    this.widthProperty().divide(2).subtract(10));
            piece.radiusYProperty().bind(
                    this.heightProperty().divide(2).subtract(10));
            piece.centerXProperty().bind(
                    this.widthProperty().divide(2));
            piece.centerYProperty().bind(
                    this.heightProperty().divide(2));
            switch (markerID) {
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

        public void highLightSquare(int x, int y, GridPane gridPane) {

            StackPane highSquare = new StackPane();

            highSquare.setStyle("-fx-border-color: " + "BLUE" + ";");
            gridPane.add(highSquare, x, y);

        }

        public Circle fixBoardSize() {

            Circle fixSize;
            fixSize = new Circle(40, 40, 20);

            return fixSize;
        }

        /* This method shows a node's info 
         public Node getNode(final int row, final int column, GridPane gridPane) {

         Node result = null;

         ObservableList <Node> children = gridPane.getChildren();

         for (Node node : children) {

         if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
         result = node;
         break;
         }
         }
         return result;
         }   */
    }

}
