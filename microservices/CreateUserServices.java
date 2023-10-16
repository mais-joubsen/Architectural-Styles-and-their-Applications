import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.*;

public class CreateUserServices extends UnicastRemoteObject implements CreateUserServicesAI {

    // Set up the JDBC driver name and database URL
    static final String JDBC_CONNECTOR = "com.mysql.jdbc.Driver";
    static final String DB_URL = Configuration.getJDBCConnection();

    // Set up the orderinfo database credentials
    static final String USER = "root";
    static final String PASS = Configuration.MYSQL_PASSWORD;

    public CreateUserServices() throws RemoteException {
    }

    public static void main(String args[]) {
        try {
            CreateUserServices obj = new CreateUserServices();

            Registry registry = Configuration.createRegistry();
            registry.bind("CreateUserServices", obj);

            System.out.println("CreateUserServices bound in registry");
        } catch (Exception e) {
            System.out.println("CreateUserServices binding err: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public Boolean makeUser(String username, String password) throws RemoteException {
        Connection conn = null;
        PreparedStatement pStatement = null; // A Statement object is an interface that represents a SQL statement.

        try {
            Class.forName(JDBC_CONNECTOR);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO users(username, password) VALUES  (?, ?)";

            pStatement = conn.prepareStatement(sql);
            pStatement.setString(1, username);
            pStatement.setString(2, password);
            
            int affectedRows = pStatement.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RemoteException("Internal error during user creation. Try again.");
        } finally {
            try {
                if (conn != null) {conn.close();}
                if (pStatement != null) {pStatement.close();}

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
