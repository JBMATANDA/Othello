/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

/**
 *
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
    
}
