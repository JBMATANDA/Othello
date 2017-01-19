/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Label;
import othello.DatabaseManager;
import othello.Move;

/**
 *
 * @author Carlos
 */
public class RemoteComputerPlayer extends  Player{
    
    DataOutputStream toServer =  null;
    DataInputStream fromServer = null;
    
    
    //Random rng = new Random();
    
    public RemoteComputerPlayer(String name, int markerId){
        super(name, markerId);
        try {
            
            String[] details = DatabaseManager.getConnectionDetails(1);
            String ipAdress = details[0];
            //new ServerOthello();
            //int portNumber = Integer.parseInt(portNo);
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
                Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
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
    
/*        

       textFieldX.setOnAction(e -> {
       
           try {
               
               int inputToServer = Integer.parseInt(textFieldX.getText());
               
               toServer.writeInt(inputToServer);
               toServer.flush();
               
               int resultFromServer = fromServer.readInt();
               
               textArea.appendText("Your number: " + inputToServer + '\n');
               textArea.appendText("Result from server\nYour number times itself is: " + resultFromServer);
               
               
           } catch(IOException ex){
               
               ex.printStackTrace();
           }
       });
       
       textFieldY.setOnAction(e -> {
       
           try {
               
               int inputToServer = Integer.parseInt(textFieldY.getText());
               
               toServer.writeInt(inputToServer);
               toServer.flush();               
             
                   
                Move[] resultFromServer = (Move[])fromServer.readObject();
                   
              
               textArea.appendText("Your number: " + inputToServer + '\n');
               textArea.appendText("Result from server\nYour number times itself is: " + resultFromServer[0].getX());
               
               
           } catch(IOException ex){
               
               ex.printStackTrace();
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(RemoteComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       
       try {
           
           
           String portNo = DatabaseManager.GetConnectionDetails(1)[1];
           int portNumber = Integer.parseInt(portNo);
           Socket socket = new Socket("localhost", portNumber);
           
           fromServer = new ObjectInputStream(socket.getInputStream());
           toServer = new DataOutputStream(socket.getOutputStream());
           
//           Move[] availableMoves = null; //insert valid code here :)
//           ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
//           output.writeObject(availableMoves);
//           
//           ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
//           Move choice = (Move)input.readObject();
                      
//       } catch(IOException ex){           
//        
//           ex.printStackTrace();
//       } catch (ClassNotFoundException ex) {       
//            Logger.getLogger(RemoteComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
//        }       
    } catch(IOException ex){
        
        ex.printStackTrace();
        }
    }   
    public static void main(String[] args){
        
        launch(args);
    }

    @Override
    public void getMove(Move[] moveList, ObjectProperty<Move> playerMadeMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

