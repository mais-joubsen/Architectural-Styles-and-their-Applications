
/******************************************************************************************************************
* File: MSClientAPI.java
* Course: DIT345
* Project: Assignment A4
* Copyright: Copyright (c) 2018 Carnegie Mellon University
*
* Description: This class provides access to the webservices via RMI. Users of this class need not worry about the
* details of RMI (provided the services are running and registered via rmiregistry).  
*
* Parameters: None
*
* Internal Methods (already implemented):
*  String retrieveOrders() - gets and returns all the orders in the orderinfo database
*  String retrieveOrders(String id) - gets and returns the order associated with the order id
*  String newOrder(String date, String firstName, String lastName, String address, String phone) - creates a new 
*  order in the orderinfo database
*
* External Dependencies: None


******************************************************************************************************************/
import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.io.FileWriter;

public class MSClientAPI {
	String response = "";
	Properties registry = null;
	private static final String LOG_FILE = "system_log.txt";

	public MSClientAPI() throws IOException {
		// Loads the registry from 'registry.properties'
		// This files contains entries like:
		// <Service> = <host>:<port>
		// indicating that a service is registered in
		// an RMI registry at host on port.

		registry = new Properties();
		registry.load(new FileReader("registry.properties"));
	}

	/********************************************************************************
	 * Description: Retrieves all the orders in the orderinfo database..
	 * Parameters: None
	 * Returns: String of all the current orders in the orderinfo database
	 ********************************************************************************/
	public String retrieveOrders() throws Exception {
		try {
			// Get the registry entry for RetrieveServices service from registry.properties
			String entry = registry.getProperty("RetrieveServices");
			String host = entry.split(":")[0];
			String port = entry.split(":")[1];

			// Get the RMI registry
			Registry reg = LocateRegistry.getRegistry(host, Integer.parseInt(port));
			RetrieveServicesAI obj = (RetrieveServicesAI) reg.lookup("RetrieveServices");
			response = obj.retrieveOrders();
		} catch (Exception e) {

			reportLog("Error in retrieveOrders: " + e.getMessage()); // Logging the error
			return ("Internal error in retrieving all orders. Try again.");
		}
		return response;
	}

	/********************************************************************************
	 * Description: Retrieves the order based on the id argument provided from the
	 * orders' database.
	 * Parameters: String id
	 * Returns: String of the order corresponding to the <id> argument
	 * in the ms_orderinfo database.
	 ********************************************************************************/
	public String retrieveOrders(String id) throws Exception {
		try {
			// Get the registry entry for RetrieveServices service from registry.properties
			String entry = registry.getProperty("RetrieveServices");
			String host = entry.split(":")[0];
			String port = entry.split(":")[1];

			// Get the RMI registry
			Registry reg = LocateRegistry.getRegistry(host, Integer.parseInt(port));
			RetrieveServicesAI obj = (RetrieveServicesAI) reg.lookup("RetrieveServices");
			response = obj.retrieveOrders(id);
		} catch (Exception e) {
			reportLog("Error in retrieveOrders: " + e.getMessage()); // Logging the error
			return ("Internal error the order with id:" + id + "Try again.");
		}
		return (response);
	}

	/********************************************************************************
	 * Description: Creates the new order to the orderinfo database
	 * Parameters: String Date, String FirstName, String LastName, String Address,
	 * String Phone
	 * Returns: String that contains the status of the create operation (created /
	 * not created).
	 ********************************************************************************/
	public String newOrder(String Date, String FirstName, String LastName, String Address, String Phone)
			throws Exception {
		try {
			// Get the registry entry for CreateServices service from registry.properties
			String entry = registry.getProperty("CreateServices");
			String host = entry.split(":")[0];
			String port = entry.split(":")[1];

			// Get the RMI registry (should be running at start, in order to locate the
			// port)
			Registry reg = LocateRegistry.getRegistry(host, Integer.parseInt(port));
			CreateServicesAI obj = (CreateServicesAI) reg.lookup("CreateServices");
			response = obj.newOrder(Date, FirstName, LastName, Address, Phone);
		} catch (Exception e) {
			reportLog("Error in newOrder for user " + FirstName + " " + LastName + ": " + e.getMessage()); // Logging
			return ("Internal error in making the order. Try again.");
		}

		return (response);
	}

	public String deleteOrder(String id) throws Exception {
		/*
		 * TODO:
		 * - Delete an order from the database, matching the orderID that is passed
		 * as an argument in this function.
		 */
		try {
			// Get the registry entry for CreateServices service from registry.properties
			String entry = registry.getProperty("DeleteServices");
			String host = entry.split(":")[0];
			String port = entry.split(":")[1];

			// Get the RMI registry (should be running at start, in order to locate the
			// port)
			Registry reg = LocateRegistry.getRegistry(host, Integer.parseInt(port));
			DeleteServicesAI obj = (DeleteServicesAI) reg.lookup("DeleteServices");
			response = obj.deleteOrder(id);
		} catch (Exception e) {
			reportLog("Error in deleteOrder with ID " + id + ": " + e.getMessage());
			return "Internal error in deleting the order. Try again.";
		}
		return (response);
	}

	public boolean authenticate(String username, String password) throws Exception {
		/*
		 * TODO:
		 * - Authenticate users upon entering a correct username, and a correct
		 * password.
		 * Without provided valid login credentials at the start of the application, the
		 * user should not be able to access any functionalities of the system.
		 */
		try {
			String entry = registry.getProperty("AuthenticateServices");
			String host = entry.split(":")[0];
			String port = entry.split(":")[1];

			// Get the RMI registry (should be running at start, in order to locate the
			// port)
			Registry reg = LocateRegistry.getRegistry(host, Integer.parseInt(port));
			AuthenticateServicesAI obj = (AuthenticateServicesAI) reg.lookup("AuthenticateServices");

			return obj.authenticate(username, password);
		} catch (Exception e) {
			reportLog("Error in authenticate for user " + username + ": " + e.getMessage());
			throw new Exception("Internal error in authenticating. Try again.");
		}
	}

	public Boolean makeUser(String username, String password) throws Exception {
		try {
			/*
			 * TODO:
			 * - Register a user account for first-time users, where a username, and
			 * password are provided
			 * as arguments. No two users shall have the same username.
			 */
			String entry = registry.getProperty("CreateUserServices");
			String host = entry.split(":")[0];
			String port = entry.split(":")[1];

			// Get the RMI registry (should be running at start, in order to locate the
			// port)
			Registry reg = LocateRegistry.getRegistry(host, Integer.parseInt(port));
			CreateUserServicesAI obj = (CreateUserServicesAI) reg.lookup("CreateUserServices");
			boolean res = obj.makeUser(username, password);

			return res;
		} catch (Exception e) {
			reportLog("Error in makeUser for username " + username + ": " + e.getMessage());
			throw new Exception("Internal error in registering the user. Try again.");
		}
	}

	public void reportLog(String msg) throws Exception {
		/*
		 * TODO:
		 * - Logging activity such as to keep track of creation/modification operations
		 * conducted in the system, as
		 * well as which user conducted it, along with when there are errors that take
		 * place in the
		 * system (exceptions), to know when and which functionality was affected.
		 */
		

		try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
			writer.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) + ": " + msg + "\n");
		} catch (IOException e) {
			System.err.println("Error while writing to the log file: " + e.getMessage());
		}

	}
}