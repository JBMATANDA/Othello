/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author S153298
 */
public class GameGridProperty implements ObservableValue<int[][]>{
    
    private List<ChangeListener<? super int[][]>> listeners = new ArrayList<>();
    
    int[][] gameGrid;
    
    public GameGridProperty(){
        gameGrid = new int[8][8];
    }
    
    public int get(int x, int y){
        return gameGrid[x][y];
    }
    
    public void set(int x, int y, int newValue){
        System.out.println("ITS CHANGING!");
        gameGrid[x][y] = newValue;
        activateListeners();
    }
    
    private void activateListeners() {
        System.out.println("\n\tACTIVATED LISTENER\n");

        for(ChangeListener<? super int[][]> listener : listeners){
            listener.changed(this, gameGrid, gameGrid);
        }
    }

    @Override
    public void addListener(ChangeListener<? super int[][]> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super int[][]> listener) {
        listeners.remove(listener);
    }

    @Override
    public int[][] getValue() {
        return this.gameGrid;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
