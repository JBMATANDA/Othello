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
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 *
 * @author Carlos
 */
public class ClientService implements Runnable {
    
    Socket clientSocket = new Socket();
    TextArea textArea = new TextArea();
    
    public ClientService(Socket inputSocket, TextArea inputTextArea){
        
        clientSocket = inputSocket;
        textArea = inputTextArea;
    }    
    
    @Override public void run(){
        
        try {
            
            DataInputStream inputFromClient = new DataInputStream(clientSocket.getInputStream()); 
            DataOutputStream outputToClient = new DataOutputStream(clientSocket.getOutputStream());
            
            while(true){
                
                int input = inputFromClient.readInt();
                int result = input * input;
                outputToClient.writeInt(result);
                
                Platform.runLater( () -> {
                
                    textArea.appendText("Input from client: " + input + '\n');
                    textArea.appendText("Testing streams...: " + result + '\n');
                });
            }
            
        } catch(IOException ex){
            
            ex.printStackTrace();
        }
    }
}
