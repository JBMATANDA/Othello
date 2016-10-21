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

/**
 *
 * @author optimusprime
 */
public class RemoteComputerPlayer/* extends Player*/{
    
}
