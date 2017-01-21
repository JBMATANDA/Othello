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
/**
 *
 * @author S153298
 */
import java.util.ArrayList;
import participants.Player;

public class GameGrid {
    //private int[][] grid;
    private GameGridProperty gridProperty = new GameGridProperty();
    
    public GameGrid(){
        //grid = new int[8][8];
        initializeBoard();
    }
    
    /**
     * Initializes the game grid by setting all the squares to empty
     * except the 4 middle ones, just like Othello.
     */
    private void initializeBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
//                grid[i][j] = 0;
                gridProperty.set(i, j, 0);
            }
        }
/*       
        grid[3][4] = 2;
        grid[4][3] = 2;
        grid[3][3] = 1;
        grid[4][4] = 1;
        */
        
        gridProperty.set(3, 4, 2);
        gridProperty.set(4, 3, 2);
        gridProperty.set(3, 3, 1);
        gridProperty.set(4, 4, 1);
    }
    
    /**
     * returns the grid.
     * @return 
     */
    public GameGridProperty gridProperty(){
        return gridProperty;
    }
    /**
     * Prints out the board in a nice, clean and sleek manner. Wow! It's better 
     * than graphics!
     */
    public void printBoard(){
        System.out.print("  X ");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nY");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "   ");
            for (int j = 0; j < 8; j++) {
//                System.out.print(grid[j][i] + " ");
                System.out.print(gridProperty.get(i, j) + " ");
                
            }
            System.out.println();
        }
    }
    /**
     * Prints out all the non-empty squares.
     */
    public void printboardSkipZero(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //if (grid[i][j] != 0)
                if (gridProperty.get(i,j) != 0)
                //System.out.println("x: " + i + "  y: " + j + "  " + grid[i][j]);
                System.out.println("x: " + i + "  y: " + j + "  " + gridProperty.get(i,j));
            }
        }
    }
    /**
     * adds a chip(an integer) to the grid.  
     * @param move 
     */
    void addToGrid(Move move){
        addToGrid(move.getId(), move.getX(), move.getY());
    }
    /**
     * Checks if a move is legal
     * @param move
     * @return boolean
     */
    boolean isLegalMove(Move move){
        return(isLegalMove(move.getId(),move.getX(),move.getY()));
    }
    
    private boolean isLegalMove(int player, int x, int y){
        if((x >= 8) || (y >= 8) || (x < 0) || (y < 0))
            return false;
        //if(grid[x][y] != 0)
        if(gridProperty.get(x, y) != 0)
            return false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (legalMoveAndAdd(player, x+i, y+j, i, j, false))
                    return true;
            }
        }
        return false;
    }
    
    
    /**
     * Adds a chip to the grid and flips all the right chips.
     * It is recommended to only use it if isLegalMove has returned true.
     * @param player
     * @param x
     * @param y 
     */
    private void addToGrid(int player, int x, int y){
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                legalMoveAndAdd(player, x+i, y+j, i, j, true);
            }
        }
        //grid[x][y] = player;
        gridProperty.set(x, y, player);
    }
    /**
     * part of the legal-move checking. The last argument determines whether or
     * not it should also change the colour of the chips(in which case set it to true)
     * @param colour
     * @param x
     * @param y
     * @param directionX
     * @param directionY
     * @param addToGrid
     * @return 
     */
    private boolean legalMoveAndAdd(int colour, int x, int y, int directionX, int directionY, boolean addToGrid){
        if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0))
            return false;
        //if (grid[x][y] == 0)
        if (gridProperty.get(x, y) == 0)
            return false;
        //if ((grid[x][y] == colour)){
        if ((gridProperty.get(x, y) == colour)){    
            //if (grid[x-directionX][y-directionY] != 0)
            if (gridProperty.get(x-directionX,y-directionY) != 0)
                return true;
            else return false;
        }
        if (legalMoveAndAdd(colour, x+directionX, y+directionY, directionX, directionY, addToGrid)){
            if(addToGrid)
                //grid[x][y] = colour;
                gridProperty.set(x, y, colour);
            return true;
        }
        else return false;
    }
    
    
    
    /**
     * checks if a player has any possible moves to make
     * @param player
     * @return 
     */
    private boolean checkForLegalMoves(int player){
//        if(player != 1 && player != 2)            add later
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(isLegalMove(player, i, j))
                    return true;
            }
        }
        return false;
    }
    /**
     * checks if either player can make a move
     * @return 
     */
    public boolean checkForLegalMoves(){
        return(checkForLegalMoves(1) || checkForLegalMoves(2));
    }
    /**
     * Checks if a specific player can make a move
     * @param player
     * @return 
     */
    public boolean checkForLegalMoves(Player player){
        return(checkForLegalMoves(player.getMarkerId()));
    }
    /**
     * returns an array with every legal move available to the player
     * @param player
     * @return 
     */
    public Move[] getLegalMoves(Player player){
        return(getLegalMoves(player.getMarkerId()));
    }
    
    private Move[] getLegalMoves(int player){
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(isLegalMove(player, i, j)){
                    legalMoves.add(new Move(i,j,player));
                }
            }
        }
        return (legalMoves.toArray(new Move[legalMoves.size()]));
    }
    /**
     * returns the number of points a player has
     * @param player
     * @return
     */
    public int getScore(int player){
        int playerScore = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //if(grid[i][j] == player)
                if(gridProperty.get(i,j) == player)
                    playerScore++;
            }
        }
        return playerScore;
    }
    /**
     * returns the winning player, or a 0 if a draw
     * @return 
     */
    public int getWinner(){
        int player1 = getScore(1);
        int player2 = getScore(2);
        return(getWinner(player1, player2));
    }
    public int getWinner(int player1, int player2){
        if(player1 > player2)
        return 1;
        else if(player1 < player2)
            return 2;
        else return 0;
    }
    
    
    /**
     * prints out all legal moves available to the player.
     * @param player 
     */
    public void printAllMoves(Player player){
        printAllMoves(getLegalMoves(player));
    }
    
    private void printAllMoves(Move[] list){
        for(int i = 0; i < list.length; i++){
            System.out.println("X: " + list[i].getX() + " Y: " + list[i].getY());
        }
    }
}