/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chip;

import participants.Player;

/**
 * CAN THIS BE A INNER CLASS??? I DONT KNOW BUT LET US CHECK LET US DISCOVER THE BEAUTY AND MAJEXSTY OF OBJECT ORIENTED PROGRAMMING TKAE MY HAND YOU AMAZING AND DELICIOUS FRIEND FOR TOGETHER WE SHALL SEEK KNOWLEDGTE
 * @author S153298
 */
public class Chip {
    private Player player;
    
    //PLACEHOLDER FIX IT EVENTUALLY!!!
    public Chip(){
        player = null; 
    }
    
    public Chip(Player player){
        this.player = player;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    public Player getPlayer(){
        return player;
    } 
}
