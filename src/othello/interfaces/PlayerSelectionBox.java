/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.interfaces;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import participants.HumanPlayer;
import participants.LocalComputerPlayer;
import participants.Player;
import participants.RemoteComputerPlayer;

/**
 *
 * @author S152337
 */
public class PlayerSelectionBox extends VBox{
    
    private ToggleGroup group = new ToggleGroup();
    private final int playerId;
    
    public PlayerSelectionBox(int id){
        super(20);
        
        this.playerId = id;
        
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setStyle("-fx-border-color: black");
        this.getChildren().addAll(new Label("Player " + id + ": "));
        
        RadioButton[] radioButtons = generatePlayerSelectionButtons();
        this.getChildren().addAll(radioButtons);
    }
    
    private RadioButton[] generatePlayerSelectionButtons(){
        RadioButton hp = new RadioButton("Human Player");
        RadioButton cp = new RadioButton("Local Computer Player");
        RadioButton rp = new RadioButton("Remote Computer Player");
        
        hp.setToggleGroup(group);
        cp.setToggleGroup(group);
        rp.setToggleGroup(group);
        
        return new RadioButton[]{
            hp, cp, rp
        };
    }
    
    public Player getPlayerFromSelection(String newPlayerName){
        String selectedToggleTextValue = ((RadioButton)group.getSelectedToggle()).getText();
        
        switch(selectedToggleTextValue){
            case "Human Player":
                return new HumanPlayer(newPlayerName, this.playerId);
            case "Local Computer Player":
                return new LocalComputerPlayer(newPlayerName, this.playerId);
            case "Remote Computer Player":
                return new RemoteComputerPlayer(newPlayerName, this.playerId);
            default:
                throw new RuntimeException("Not a valid radio button selected.");
        }
    }
}
