
package othello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author S153298
 */
public class DatabaseManager {
    
    
    private static DatabaseManager databaseManager = new DatabaseManager();
    private DatabaseManager(){}
    
    /**
     * This class uses the singleton design pattern, and thus this function is
     * needed in order to access the database.
     * @return 
     */
    public static DatabaseManager getDatabase(){
        return databaseManager;
    }
    
    /**
     * Returns the contents of a single row and column, with the row being 
     * identified by its primary key (groupId)
     * @param groupId - The primary key
     * @param column - 2 means ipAddress, 3 is port
     * @return 
     */
    private String getInformation(int groupId, int column){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();
            
            String SQLMessage = "select* from OthelloServer where groupId = " + groupId;
            
            ResultSet resultSet = statement.executeQuery(SQLMessage);
            resultSet.next();
            String stuff = resultSet.getString(column);
            connection.close();            
            return stuff;
        }
        catch(SQLException ex){
            System.out.print(ex.getMessage());
            return "Error";
        }
    }
    
    /**
     * returns a String array with the ip address and port, based of the 
     * primary key.
     * [0] = ipAddress
     * [1] = port number
     * @param groupId - the primary key
     * @return 
     */
    public String[] getConnectionDetails(int groupId){
        
        String[] details = new String[2];
        details[0] = getInformation(groupId, 2); //ip address
        details[1] = getInformation(groupId, 3); //port
        return details;
    }
    
    
    /**
     * checks if the table exists, if it doesn't the table gets created. The
     * table is updated with the entered arguments.
     * @param groupId
     * @param ipAdress
     * @param port 
     */
    public void updateDatabaseTable(int groupId, String ipAdress, int port){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://hitsql-db.hb.se:56077;database=oomuht1604;user=oomuht1604;password=stab66");
            Statement statement = connection.createStatement();
            
            String SQLMessage = "select* from OthelloServer where groupId = " + groupId + ";";
       
            ResultSet resultSet = statement.executeQuery(SQLMessage);
            if(resultSet.next()){
                
                SQLMessage = "UPDATE OthelloServer SET ipAddress = '" + ipAdress + "', port = " + port +" WHERE groupId = " + groupId ;
                statement.executeUpdate(SQLMessage);
            }
            else{
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
}
    
