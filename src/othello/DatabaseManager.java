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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S153298
 */
public class DatabaseManager {
    
    /*
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
    }*/
    /*
    private static ResultSet receiveResult(String SQLMessage){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery(SQLMessage);
            connection.close();
            return resultSet;
        }
        catch(SQLException ex){
            System.out.print(ex.getMessage());
            return null;
        }
    }
    */
    
    private static DatabaseManager databaseManager = new DatabaseManager();
    private DatabaseManager(){}
    public static DatabaseManager getDatabase(){
        return databaseManager;
    }
    
    
    
    
    
    public String getGroupId(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();
            
            
            String SQLMessage = "select* from OthelloServer";
            
            ResultSet resultSet = statement.executeQuery(SQLMessage);
            resultSet.next();
            String stuff = resultSet.getString(1);
            connection.close();            
            return stuff;
        }
        catch(SQLException ex){
            System.out.print(ex.getMessage());
            return "Error";
        }
    }
    /*
    public static String getIpAddress(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();
            
            
            String SQLMessage = "select* from OthelloServer";
            
            ResultSet resultSet = statement.executeQuery(SQLMessage);
            resultSet.next();
            String stuff = resultSet.getString(2);
            connection.close();            
            return stuff;
        }
        catch(SQLException ex){
            System.out.print(ex.getMessage());
            return "Error";
        }
    }
    */
    
    private String getIpAddress(int groupId){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();
            
            String SQLMessage = "select* from OthelloServer where groupId = " + groupId;
            
            ResultSet resultSet = statement.executeQuery(SQLMessage);
            resultSet.next();
            String stuff = resultSet.getString(2);
            connection.close();            
            return stuff;
        }
        catch(SQLException ex){
            System.out.print(ex.getMessage());
            return "Error";
        }
    }
    
    /*
    public static String getPort(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();
            
            String SQLMessage = "select* from OthelloServer";
            
            ResultSet resultSet = statement.executeQuery(SQLMessage);
            resultSet.next();
            String stuff = resultSet.getString(3);
            connection.close();            
            return stuff;
        }
        catch(SQLException ex){
            System.out.print(ex.getMessage());
            return "Error";
        }
    }
    */
    private String getPort(int groupId){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();
            
            String SQLMessage = "select* from OthelloServer where groupId = " + groupId;
            
            ResultSet resultSet = statement.executeQuery(SQLMessage);
            resultSet.next();
            String stuff = resultSet.getString(3);
            connection.close();            
            return stuff;
        }
        catch(SQLException ex){
            System.out.print(ex.getMessage());
            return "Error";
        }
    }
    
    
    
    /**
     * [0] = ipAddress
     * [1] = port number
     * @param groupId
     * @return 
     */
    
    public String[] getConnectionDetails(int groupId){
        
        String[] details = new String[2];
        details[0] = getIpAddress(groupId);
        details[1] = getPort(groupId);
        return details;
    }
    
    /*
    public static void addClient(int groupId, String ipAdress, int port){
        String SQLMessage = "insert into OthelloServer (groupId, ipAddress, port) VALUES ('"+groupId+"', '"+ipAdress+"', '"+port+"');";
        sendSQLMessage(SQLMessage);
    }*/
    
    
    public void updateDatabaseTable(int groupId, String ipAdress, int port){
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();
            
            
            //String SQLMessage = "insert into OthelloServer (groupId, ipAddress, port) VALUES ('"+groupId+"', '"+ipAdress+"', '"+port+"');";
            String SQLMessage = "select* from OthelloServer where groupId = " + groupId + ";";
       
            ResultSet resultSet = statement.executeQuery(SQLMessage);
            if(resultSet.next()){
                
                
                SQLMessage = "UPDATE OthelloServer SET ipAddress = '" + ipAdress + "', port = " + port +" WHERE groupId = " + groupId ;
                statement.executeUpdate(SQLMessage);
            }
            else{
                
                //System.out.println("Goodbye world");
                SQLMessage = "CREATE TABLE OthelloServer ( groupId int NOT NULL, ipAddress VARCHAR(30) NOT NULL, port int NOT NULL, PRIMARY KEY (groupId));";
                statement.executeUpdate(SQLMessage);
                SQLMessage = "insert into OthelloServer (groupId, ipAddress, port) VALUES ('" + groupId + "','" + ipAdress + "','" + port + "')";
                statement.executeUpdate(SQLMessage);
            }
        }
        catch(SQLException ex){
            System.out.print(ex.getMessage());
        }
    }
    
    
    
    
    
    /*
    private static void checkIfItExists(int groupId){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();
            
            String SQLMessage = "select* from OthelloServer where groupId = " + groupId;
            
            ResultSet resultSet = statement.executeQuery(SQLMessage);
            resultSet.next();
            String stuff = resultSet.getString(3);
            connection.close();            
        }
        catch(SQLException ex){
            System.out.print(ex.getMessage());
        }
    }*/
}
    
