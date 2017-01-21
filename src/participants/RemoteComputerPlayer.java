/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import othello.DatabaseManager;
import othello.Move;
    
/**
 *
 * @author Carlos
 */
public class RemoteComputerPlayer extends  Player{
    
    DataOutputStream toServer =  null;
    DataInputStream fromServer = null;
    final int groupId = 4;
    
    
   
    
    public RemoteComputerPlayer(String name, int markerId){
        super(name, markerId);
        try {
            
            String[] details = DatabaseManager.getDatabase().getConnectionDetails(groupId);
            String ipAdress = details[0];

            int portNumber = Integer.valueOf(details[1]);
            Socket socket = new Socket(ipAdress, portNumber);
//            Socket socket = new Socket("localhost", 8000);

            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(RemoteComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void getMove(Move[] moveList, ObjectProperty<Move> playerMadeMove) {
        
        new Thread(new MoveMaker(moveList, playerMadeMove)).start();
    }
    
    private class MoveMaker implements Runnable{

        private Move[] moveList;
        private ObjectProperty<Move> playerMadeMove;
        
        public MoveMaker(Move[] moveList, ObjectProperty<Move> playerMadeMove){
            this.moveList = moveList;
            this.playerMadeMove = playerMadeMove;
        }
        
        @Override
        public void run() {
            printMoveList(moveList);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(LocalComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int inputToServer = moveList.length;
               
            try {
                toServer.write(inputToServer);
                //toServer.flush();
                //Thread.sleep(1000);
                System.out.println("Waiting for the move");

                int selectedMove = fromServer.read();
                System.out.println("This was the selected move index: " + selectedMove);
                System.out.println(moveList[selectedMove].getX()+ " " + moveList[selectedMove].getY());
                playerMadeMove.set(moveList[selectedMove]);
            } catch (IOException ex) {
                Logger.getLogger(RemoteComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("IoException in run");
            }      
        }
    }
}
   