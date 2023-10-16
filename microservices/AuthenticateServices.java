import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.*;

public class AuthenticateServices extends UnicastRemoteObject implements AuthenticateServicesAI{
    // Set up the JDBC driver name and database URL
    static final String JDBC_CONNECTOR = "com.mysql.jdbc.Driver";
    static final String DB_URL = Configuration.getJDBCConnection();

    // Set up the orderinfo database credentials
    static final String USER = "root";
    static final String PASS = Configuration.MYSQL_PASSWORD;

    public AuthenticateServices() throws RemoteException {
    }

    public static void main(String args[]) {
        try {
            AuthenticateServices obj = new AuthenticateServices();

            Registry registry = Configuration.createRegistry();
            registry.bind("AuthenticateServices", obj);

            System.out.println("AuthenticateServices bound in registry");
        } catch (Exception e) {
            System.out.println("AuthenticateServices binding err: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /*
     * public boolean makeUser(String id) throws RemoteException {
     * Connection conn = null;
     * String returnMessage = "User Registered";
     * try {
     * Class.forName(JDBC_CONNECTOR);
     * conn = DriverManager.getConnection(DB_URL, USER, PASS);
     * 
     * String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
     * 
     * try (PreparedStatement stmt = conn.prepareStatement(sql)) {
     * stmt.setString(1, username);
     * stmt.setString(2, password);
     * stmt.executeUpdate();
     * }
     * } catch (SQLException e){
     * if(e.getErrorCode() == 1062) { // Duplicate entry error code
     * returnMessage = "Username already exists!";
     * } else {
     * returnMessage = "Error: " + e.getMessage();
     * }
     * } catch (ClassNotFoundException e) {
     * returnMessage = "JDBC Driver not found: " + e.getMessage();
     * } finally {
     * try {
     * if (conn != null) {
     * conn.close();
     * }
     * } catch (SQLException e) {
     * e.printStackTrace();
     * }
     * }
     * return returnMessage;
     * }
     */

     public Boolean authenticate(String username, String password) throws RemoteException {
        Connection conn = null;
        boolean isAuthenticated = false;
        PreparedStatement pStatement = null;
        ResultSet res = null;
    
        try {
            Class.forName(JDBC_CONNECTOR);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
    
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pStatement = conn.prepareStatement(sql);  
            pStatement.setString(1, username);        
            pStatement.setString(2, password);         
            res = pStatement.executeQuery();       
            isAuthenticated = res.next();
    
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pStatement != null){               
                    pStatement.close();
                }
                if (res != null){                           
                    res.close();                         
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isAuthenticated;
    }
    


}
