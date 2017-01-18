/*
Denna klass fungerar som LocalComputerPlayer, fast exekverar i en annan process och kommunicerar med Othelloklienten via sockets.
När Othello Servern startar, skall den registrera vilken dator (IP-adress) den exekverar på och vilket portnummer dess ServerSocket
lyssnar på. Denna informationen skall lagras i en MS SQL databas, i en tabell med följande schema:
OthelloServer(groupId, ipAddress, port)
När Othello Klienten startar skall den fråga databashanteraren vilket portnummer 
som används på en given dator (IP-address). Detta portnumret skall RemoteComputerPlayer 
använda sig av för att koppla upp sig mot Othello Servern. Eftersom både spelare 1 och spelare
2 kan vara av typ RemoteComputerPlayer, måste Othello Servern hantera detta (varje instans av 
en datorspelare på Othello Servern skall kommunicera över sin Socket på en egen tråd). 
Dessutom, när ett spel tar slut eller om Othello Klienten stängs ner, så skall Othello
Servern upptäcka att klient-Socketen har stängts och avsluta tråden på ett kontrollerat sätt.
 */
package participants;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
public class RemoteComputerPlayer extends Application {
    
    private TextArea textArea = new TextArea();
    private int clientNo = 0;
    private int groupNo = 5;
    
    @Override public void start(Stage primaryStage){
     
       Scene scene = new Scene(new ScrollPane(textArea), 450, 195);
       primaryStage.setTitle("Othello Server");
       primaryStage.setScene(scene);
       primaryStage.show();
       
       new Thread( () -> {
           
           try {
               
               ServerSocket serverSocket = new ServerSocket(8000);
               textArea.appendText("Othello multithreaded Server started...\n");            
               
               while(true){
                   
                   Socket clientSocket = serverSocket.accept();
                   clientNo++;
                   
                   Platform.runLater( () -> {
                       
                       InetAddress clientConnectInfo = clientSocket.getInetAddress();
                   
                       textArea.appendText("Starting a thread for client: " + clientNo + '\n');
                       textArea.appendText("The client's IP address is: " + clientConnectInfo.getHostAddress() + '\n');                       
                       textArea.appendText("The client is using port nummer: 8000\n");
                       DatabaseManager.addClient(clientNo, clientConnectInfo.getHostAddress(), 8000);
                   });
                   
                   new Thread(new ClientService(clientSocket, textArea)).start();
               }
               
           } catch (IOException ex) {
               
               Logger.getLogger(RemoteComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }).start();        
    }
    
    public static void main(String[] args){
        
        launch(args);
    }
}
