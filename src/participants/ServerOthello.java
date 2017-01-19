/*
Denna klass fungerar som LocalComputerPlayer, fast exekverar i en annan process och kommunicerar med Othelloklienten via sockets.
När Othello Servern startar, skall den registrera vilken dator (IP-adress) den exekverar på och vilket portnummer dess ServerSocket
lyssnar på. Denna informationen skall lagras i en MS SQL databas, i en tabell med följande schema:
OthelloServer(groupId, ipAddress, port)
När Othello Klienten startar skall den fråga databashanteraren vilket portnummer 
som används på en given dator (IP-address). Detta portnumret skall ServerOthello 
använda sig av för att koppla upp sig mot Othello Servern. Eftersom både spelare 1 och spelare
2 kan vara av typ ServerOthello, måste Othello Servern hantera detta (varje instans av 
en datorspelare på Othello Servern skall kommunicera över sin Socket på en egen tråd). 
Dessutom, när ett spel tar slut eller om Othello Klienten stängs ner, så skall Othello
Servern upptäcka att klient-Socketen har stängts och avsluta tråden på ett kontrollerat sätt.
 */
package participants;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import othello.DatabaseManager;

/**
 *
 * @author optimusprime
 */
public class ServerOthello {
    
//    private TextArea textArea = new TextArea();
    private int clientNo = 0;
    private int groupNo = 1;
    
    public ServerOthello(){
        
        
        
        //String[] details = DatabaseManager.getConnectionDetails(groupNo);
        //String ipAddress = details[0];
        //int port = Integer.valueOf(details[1]);
        int port =  8000;
        try {
            String ipAdress = InetAddress.getLocalHost().getHostAddress();
            DatabaseManager.updateDatabaseTable(groupNo, ipAdress, port);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerOthello.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
       
        new Thread( () -> {           
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while(true){
                    Socket clientSocket = serverSocket.accept();
                    clientNo++;
                    //Platform.runLater( () -> {
                        //InetAddress clientConnectInfo = clientSocket.getInetAddress();
                        //DatabaseManager.updateDatabaseTable(clientNo, clientConnectInfo.getHostAddress(), port);
                    //});                   
                    new Thread(new ClientService(clientSocket)).start();
                }               
            } catch (IOException ex) {               
                Logger.getLogger(ServerOthello.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }).start();        
    }
        public static void main(String[] args) {
        // TODO code application logic here
        ServerOthello sO = new ServerOthello();
    }
}
