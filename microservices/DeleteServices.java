import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.*;

public class DeleteServices extends UnicastRemoteObject implements DeleteServicesAI {

    // Set up the JDBC driver name and database URL
    static final String JDBC_CONNECTOR = "com.mysql.jdbc.Driver";
    static final String DB_URL = Configuration.getJDBCConnection();

    // Set up the orderinfo database credentials
    static final String USER = "root";
    static final String PASS = Configuration.MYSQL_PASSWORD;

    // constructor
    public DeleteServices() throws RemoteException {
    }

    // main
    public static void main(String args[]) {
        try {
            DeleteServices obj = new DeleteServices();

            Registry registry = Configuration.createRegistry();
            registry.bind("DeleteServices", obj);
            /*
            String[] boundNames = registry.list();
            System.out.println("Registered services:");
            for (String name : boundNames) {
                System.out.println("\t" + name);
            }*/
            // Bind this object instance to the name CreateServices' port in the
            // rmiregistry, as per registry.properties

        } catch (Exception e) {
            System.out.println("DeleteServices binding err: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public String deleteOrder(String id) throws RemoteException {
        Connection conn = null;
        String returnString = "Order Deleted";
        Statement statement = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            // This opens a connection to the database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare the SQL query to delete the order by order_id
            statement = conn.createStatement();

            // Execute the delete statement
            String sql = "DELETE FROM orders WHERE order_id=" + id;

            int deletedRows = statement.executeUpdate(sql);

            if (deletedRows > 0) {
                returnString += " successfully";
            } else {
                returnString = " no order found";
            }

            // clean up the environment
            statement.close();
            conn.close();

        } catch (Exception e) {
            returnString += " unsuccessfully";
        }

        return (returnString);

    } // new order
}
