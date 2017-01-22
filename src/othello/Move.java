/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

/**
 * This class is used to store coordinates and the identity of the player who
 * made the move
 * @author S153298
 */
public class Move {
    private int x;
    private int y;
    private int markerId;
    
    public Move(int x, int y, int markerId){
        this.x = x;
        this.y = y;
        this.markerId = markerId;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }    
    public int getId(){
        return markerId;
    }
    
    /**
     * This is a template for users that would rather use the Move class as an 
     * ADT, this extracts the data and can be used as a wrapper class.
     * Just replace doAction() with the correct method.
     */
    /*
        private void moveAdapter(Move move){
            int x = move.getX();
            int y = move.getY();
            int player = move.getId();
            doAction(x,y,player);
        }
    
            private void moveAdapter2(Move move, Method method){
            int x = move.getX();
            int y = move.getY();
            int player = move.getId();
            method.invoke(x,y,player);
        }
    */
}
