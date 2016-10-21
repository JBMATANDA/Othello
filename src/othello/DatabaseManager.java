/*
DatabaseManager sköter all kommunikation med den relationella databashanteraren.
Då Othello Servern startar skall metadata hämtas från DBMSen angående tabellen
OthelloServer(groupId, ipAddress, port). Om inte tabellen finns (enligt metadatan)
skall tabellen skapas med hjälp av en Statement eller PreparedStatement.
Därefter skall Othello Servern alltid skriva sin IP-adress och portnummer till 
tabellen (om inte tabellen fanns i metadatan, skall en INSERT användas, annars skall en UPDATE användas).
Då Othello Klienten skapar en RemoteComputerPlayer, skall IP-adressen samt 
portnumret hämtas från databasen via den lagrade proceduren GetConnectionDetails
med 1 IN-parameter GroupID av typ INT som representerar groupId i tabellen (erat 
gruppnummer). Den lagrade proceduren skall alltså returnera IP-adressen och portnumret
som RemoteComputerPlayer skall använda sig av för att kommunicera med Othello Servern.
Den lagrade proceduren GetConnectionDetails skall anropas via en CallableStatement
 */
package othello;

/**
 *
 * @author S153298
 */
public class DatabaseManager {
    
}
