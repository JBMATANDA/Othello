/*
GameGrid är den klass som innehåller partiets tillstånd (exempelvis en matris).
När en spelare utför ett drag kommer detta att registreras i GameGriden. 
Då GameBoard ritar om spelplanen hämtas informationen om partiets aktuella 
ställning från denna klass.
Er uppgift blir nu att konstruera och testa dessa klasser. Ett spel avslutas 
när hela spelplanen är fylld med brickor eller när ingen av spelarna kan 
utföra ett drag. I båda fallen är det spelaren med mest brickor i sin egen
färg som har vunnit.
 */
package othello;

import java.util.ArrayList;

public class GameGrid {
    private int[][] grid;
    
    public GameGrid(){
        grid = new int[8][8];
        initializeBoard();
    }
    
    /**
     * Initializes the game grid by setting all the squares to empty
     * except the 4 middle ones, just like Othello.
     */
    private void initializeBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                grid[i][j] = 0;
            }
        }
        grid[3][4] = 2;
        grid[4][3] = 2;
        grid[3][3] = 1;
        grid[4][4] = 1;
    }
    /**
     * returns the grid.
     * @return 
     */
    public int[][] getGridStatus(){
        return grid;
    }
    
    public void printboard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println("x: " + i + "  y: " + j + "  " + grid[i][j]);
            }
        }
    }
    public void printboardSkipZero(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] != 0)
                System.out.println("x: " + i + "  y: " + j + "  " + grid[i][j]);
            }
        }
    }
    public void add(Move move){
        add(move.getX(), move.getY(), move.getId());
    }
    
    public void add(int x, int y, int value){
        if(grid[x][y] != 0){
            System.out.println("Square is taken");
            return;
        }
        if (isLegalMove(value, x ,y))
            addToGrid(value, x, y);
        else System.out.println("LOL NOPE");
    }
    
    public boolean isLegalMove(Move move){
        return(isLegalMove(move.getId(),move.getX(),move.getY()));

    }
    
    public boolean isLegalMove(int player, int x, int y){
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isLegalMoveRecursive(player, x+i, y+j, i, j))
                    return true;
            }
        }
        return false;
    }
    private boolean isLegalMoveRecursive(int colour, int x, int y, int xChange, int yChange){
        if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0))
            return false;
        if (grid[x][y] == 0)
            return false;
        if ((grid[x][y] == colour)){
            if (grid[x-xChange][y-yChange] != 0)
                return true;
            else return false;
        }  
        return (isLegalMoveRecursive(colour, x+xChange, y+yChange, xChange, yChange));
    }
        private void addToGrid(int player, int x, int y){
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                recursiveAdd(player, x+i, y+j, i, j);
            }
        }
        grid[x][y] = player;
    }
    private boolean recursiveAdd(int colour, int x, int y, int xChange, int yChange){
        if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0))
            return false;
        if (grid[x][y] == 0)
            return false;
        if ((grid[x][y] == colour)){
            if (grid[x-xChange][y-yChange] != 0)
                return true;
            else return false;
        }
        if (recursiveAdd(colour, x+xChange, y+yChange, xChange, yChange)){
            grid[x][y] = colour;
            return true;
        }
        else return false;
    }
    public boolean checkForLegalMoves(int player){
//        if(player != 1 && player != 2)            add later
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(isLegalMove(i, j, player))
                    return true;
            }
        }
        return false;
    }
    public boolean checkForLegalMoves(){
        return(checkForLegalMoves(1) || checkForLegalMoves(2));
    }
    
    public Move[] getLegalMoves(int player){
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(isLegalMove(i, j, player)){
                    legalMoves.add(new Move(i,j,player));
                }
            }
        }
        return (Move[])legalMoves.toArray();
    }

    public int getScore(int player){
        int playerScore = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(grid[i][j] == player)
                    playerScore++;
            }
        }
        return playerScore;
    }
    
    public int getWinner(){
        int player1 = getScore(1);
        int player2 = getScore(2);
        if(player1 > player2)
            return 1;
        else if(player1 < player2)
            return 2;
        else return 0;
    }
}