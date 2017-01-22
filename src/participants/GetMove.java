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
 *
 * @author S153298
 */
public class GetMove {
    
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    
    private Integer x = null;
    private Integer y = null;
    

    
    
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
