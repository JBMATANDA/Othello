/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




package participants;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is a producer-consumer, used to avoid direct communication between
 * view and model. The view(GameBoard produces moves through mouseclicks, which
 * are consumed by the players.
 * @author S153298
 */
public class GetMove {
    
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    
    private Integer x = null;
    private Integer y = null;
    
    
    private static final GetMove getMove = new GetMove();
    public static GetMove getMove(){
        return getMove;
    }
    
    
    /**
     * sets the coordinates which the player clicked on.
     * @param newX
     * @param newY
     * @throws InterruptedException 
     */
    public void put(int newX, int newY) throws InterruptedException{
        lock.lock();
        try{
            while(this.x != null && this.y != null)
                notFull.await();
            
            this.x = new Integer(newX);
            this.y = new Integer(newY);
            notEmpty.signal();
        }
        finally{
            lock.unlock();
        }
    }   
    
    
    /**
     * Takes the coordinates to use as a move, and removes them from the variables
     * @return
     * @throws InterruptedException 
     */
    public int[] take() throws InterruptedException{
        lock.lock();
        
        try{
            while(this.x == null && this.y == null)
                notEmpty.await();
            int[] result = {
                this.x,
                this.y
            };
            
            this.x = null;
            this.y = null;
            notFull.signal();
            
            return result;
        }
        finally{
            lock.unlock();
        }
    }
    
    
    
    
    
}
