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
import othello.DatabaseManager;

/**
 * This class is needed to start the server. Our main motivation for putting it
 * in participants was because it was player-related. 
 *
 */
public class ServerOthello {
         

    private int groupNo = 4;
    
    public ServerOthello(){

        int port =  8000;
        try {
            String ipAdress = InetAddress.getLocalHost().getHostAddress();
            DatabaseManager.getDatabase().updateDatabaseTable(groupNo, ipAdress, port);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerOthello.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread( () -> {           
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while(true){
                    Socket clientSocket = serverSocket.accept();           
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
