/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import othello.Move;

/**
 *
 * @author Carlos
 */
public class ClientService implements Runnable {
    
    Socket clientSocket;// = new Socket();
    //TextArea textArea = new TextArea();
    Random random = new Random();
    
    public ClientService(Socket inputSocket/*, TextArea inputTextArea*/){
        
        clientSocket = inputSocket;
        //textArea = inputTextArea;
    }    
    
    @Override public void run(){
        
        try {
            
            DataInputStream inputFromClient = new DataInputStream(clientSocket.getInputStream()); 
            DataOutputStream outputToClient = new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("We're not even in the loop");
            while(clientSocket.isConnected()){                
                
                System.out.println("Nevermind, loop achieved");
                int input = inputFromClient.readInt();
                System.out.println("The input to clientservice:  " + input);
                
                int result = random.nextInt(input);
                System.out.println("The output from clientservice:  " + result);
                
                outputToClient.write(result);
                System.out.println("I think we wrote it");
                
            /*    Platform.runLater( () -> {
                
                    textArea.appendText("Input from client: " + input + '\n');
                    textArea.appendText("Testing streams...: " + result + '\n');
                });*/
            }
            
        } catch(IOException ex){
            
            ex.printStackTrace();
        }
    }
}
