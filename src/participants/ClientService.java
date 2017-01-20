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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TextArea;


/**
 *
 * @author Carlos
 */
public class ClientService implements Runnable {
    
    Socket clientSocket;
    //TextArea textArea = new TextArea();
    Random random = new Random();
    DataInputStream inputFromClient;
    DataOutputStream outputToClient;
    
    public ClientService(Socket inputSocket/*, TextArea inputTextArea*/){
        
        try {
            clientSocket = inputSocket;
            inputFromClient = new DataInputStream(clientSocket.getInputStream());
            outputToClient = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @Override public void run(){
        
        try {
            
            System.out.println("We're not even in the loop");
            while(clientSocket.isConnected()){                
                
                System.out.println("Nevermind, loop achieved");
                int input = inputFromClient.read();
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
