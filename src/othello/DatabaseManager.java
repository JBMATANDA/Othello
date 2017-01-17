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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author S153298
 */
public class DatabaseManager {
    
    
    private static void sendSQLMessage(String SQLMessage){
        try{
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();

            statement.executeUpdate(SQLMessage);
            connection.close();
        }
        catch (SQLException ex)
        {
            System.out.print(ex.getMessage());
        } 
    }
    
    public static void addStuff(int groupId, String ipAdress, int port){
        String SQLMessage = "insert into OthelloServer (groupId, ipAddress, port) VALUES ('"+groupId+"', '"+ipAdress+"', '"+port+"');";
        sendSQLMessage(SQLMessage);
    }
}
