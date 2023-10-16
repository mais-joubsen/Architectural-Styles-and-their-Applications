
/******************************************************************************************************************
* File:OrdersUI.java
* Course: DIT345
* Project: Assignment A4
* Copyright: Copyright (c) 2018 Carnegie Mellon University
*
* Description: This class is the console/user-interface handler for the CLI application. 
*
* Parameters: None
* Internal Methods: None ( excluding main() )
*
******************************************************************************************************************/
import java.lang.Exception;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Console;

public class OrdersUI {
	public static void main(String args[]) throws Exception {
		boolean done = false; // main loop flag
		boolean error = false; // error flag
		char option; // Menu choice from user
		Console c = System.console(); // Press any key
		String date = null; // order date
		String first = null; // customer first name
		String last = null; // customer last name
		String address = null; // customer address
		String phone = null; // customer phone number
		String orderid = null; // order ID
		String username = null;
		String password = null;
		String response = null;
		Scanner keyboard = new Scanner(System.in); // keyboard scanner object for user input
		DateTimeFormatter dtf = null; // Date object formatter
		LocalDate localDate = null; // Date object
		MSClientAPI api = new MSClientAPI();
		boolean isAuthenticated = false;
		boolean created = false;

		/////////////////////////////////////////////////////////////////////////////////
		// TODO:
		// Auth feature, before users can access any of the services in the application.
		// Takes a username, and a password, alt. a possibility to make an account if
		// they don't exist.
		/////////////////////////////////////////////////////////////////////////////////

		/*
		 * TODO: Authentication
		 * - Login of an existing user with a username and password, before accessing
		 * the application's functionalities. Make a new option on the UI side.
		 * 
		 * TODO: User Registration
		 * - Making a new option on the UI side, to handle creation of new user accounts
		 * before accessing services.
		 * 
		 * Add the prompts to be shown in the user console for this authentication step,
		 * as part of the implementation of authenticating and registering users before
		 * accessing the application.
		 */


		/////////////////////////////////////////////////////////////////////////////////
		// Main Authentication loop
		/////////////////////////////////////////////////////////////////////////////////

		while(!isAuthenticated){
			System.out.println("Welcome to the Orders Database User Interface");
            System.out.println("Select an Option:");
            System.out.println("1: Log in");
            System.out.println("2: Create a new account");
            System.out.println("X: Exit");

			System.out.println("\n>>>> ");
			option = keyboard.next().charAt(0);
			keyboard.nextLine();

			if(option == '1'){
				// Log in
                System.out.print("Enter your username: ");
                username = keyboard.nextLine();
                System.out.print("Enter your password: ");
                password = new String(c.readPassword());

				isAuthenticated = api.authenticate(username, password);
				
				if(isAuthenticated){
					System.out.println("Authentication Successfull!");
				}
				else{
					System.out.println("Authentication failed. Please try again.");
				}
			}
			if(option == '2'){
				// Here the user can input credentials to create a new account
                System.out.print("Enter a new username: ");
                username = keyboard.nextLine();
                System.out.print("Enter a password: ");
                password = new String(c.readPassword());

				//The new accounts gets registered
                created = api.makeUser(username, password);
				if (created) {
                    System.out.println("Account created successfully!");
                    isAuthenticated = true;
                } else {
                    System.out.println("Account creation failed. Please try again.");
                }
			
			//Exit option
	
			if(option=='X'|| option=='x'){
				done = true;
                System.out.println("Exiting...");
			}
			}
			
			if ((option == 'X') || (option == 'x')) {
				done = true;
				System.out.println("\nDone...\n\n");

			}
		}


		/////////////////////////////////////////////////////////////////////////////////
		// Main UI loop
		/////////////////////////////////////////////////////////////////////////////////

		while (!done) {
			// starting main menu: set of choices ->
			System.out.println("\n\n\n\n");
			System.out.println("Orders Database User Interface: \n");
			System.out.println("Select an Option: \n");
			System.out.println("1: Retrieve all orders in the order database.");
			System.out.println("2: Retrieve an order by ID.");
			System.out.println("3: Add a new order to the order database.");
			System.out.println("4: Delete an order by ID.");
			System.out.println("X: Exit\n");
			System.out.print("\n>>>> ");
			option = keyboard.next().charAt(0);
			keyboard.nextLine(); // Removes other data from keyboard buffer.

			//////////// option 1 ////////////
			if (option == '1') {
				// Here we retrieve all the orders in the ms_orderinfo database
				System.out.println("\nRetrieving All Orders::");
				try {
					response = api.retrieveOrders();
					System.out.println(response);

				} catch (Exception e) {
					System.out.println("Request failed:: " + e);
				}

				System.out.println("\nPress enter to continue...");
				c.readLine();

			} // if

			//////////// option 2 ////////////

			if (option == '2') {
				// get the order ID from the user
				error = true;

				while (error) {
					System.out.print("\nEnter the order ID: ");
					orderid = keyboard.nextLine();

					try {
						Integer.parseInt(orderid);
						error = false;
					} catch (NumberFormatException e) {

						System.out.println("Not a number, please try again...");
						System.out.println("\nPress enter to continue...");

					} // if

				} // while

				try {
					response = api.retrieveOrders(orderid);
					System.out.println(response);

				} catch (Exception e) {

					System.out.println("Request failed:: " + e);

				}

				System.out.println("\nPress enter to continue...");
				c.readLine();

			} // if

			//////////// option 3 ////////////

			if (option == '3') {
				// Here we create a new order entry in the database
				dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				localDate = LocalDate.now();
				date = localDate.format(dtf);

				System.out.println("Enter first name:");
				first = keyboard.nextLine();

				System.out.println("Enter last name:");
				last = keyboard.nextLine();

				System.out.println("Enter address:");
				address = keyboard.nextLine();

				System.out.println("Enter phone:");
				phone = keyboard.nextLine();

				System.out.println("Creating the following order:");
				System.out.println("==============================");
				System.out.println(" Date:" + date);
				System.out.println(" First name:" + first);
				System.out.println(" Last name:" + last);
				System.out.println(" Address:" + address);
				System.out.println(" Phone:" + phone);
				System.out.println("==============================");
				System.out.println("\nPress 'y' to create this order:");

				option = keyboard.next().charAt(0);

				if ((option == 'y') || (option == 'Y')) {
					try {
						System.out.println("\nCreating order...");
						response = api.newOrder(date, first, last, address, phone);
						System.out.println(response);

					} catch (Exception e) {

						System.out.println("Request failed:: " + e);

					}

				} else {

					System.out.println("\nOrder not created...");
				}

				System.out.println("\nPress enter to continue...");
				c.readLine();

				option = ' '; // Clearing option.

			} // if

			//////////// option 4 ////////////

			/*
			 * TODO: Delete Functionality
			 * - Here the user should be able to see a new option, such as
			 * "4. Delete an order from the database.".
			 * Add the UI implementation for this new option, selectable by users, that is
			 * to be part of
			 * the functionality to delete an order of a particular ID from the database.
			 */

			if (option == '4') {
				System.out.print("Enter the order ID to delete: ");
				orderid = keyboard.nextLine();

				try {
					Integer.parseInt(orderid);
					System.out.println(response);
				} catch (Exception e) {
					System.out.println("Request failed: " + e);
				}

				response = api.deleteOrder(orderid);

				System.out.println("\nPress enter to continue...");
				c.readLine();
			}


			//////////// option X ////////////

			if ((option == 'X') || (option == 'x')) {
				isAuthenticated = false;
				done = true;
				System.out.println("\nDone...\n\n");

			}

		}
		keyboard.close();
	}
}
