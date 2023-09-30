/******************************************************************************************************************
* File: RetrieveServicesAI.java
* Course: DIT345
* Project: Assignment A4
* Copyright: Copyright (c) 2018 Carnegie Mellon University
*
* Description: This class provides the interface for the orders retrieval microservice, RetrieveServices.
* The implementation of this interface can be found in the RetrieveServices.java class. Each service 
* is its own process. Note that services can be duplicated and differentiated by IP
* and/or port# they are hosted on. For this assignment, Create and Retrieve services have already 
* been provided as examples, and services are to be hosted on the localhost, on RMI ports 1099 and 1098 respectively. 
*
* Parameters: None
*
* Internal Methods:
*  String retrieveOrders() - gets and returns all the orders in the orderinfo database
*  String retrieveOrders(String id) - gets and returns the order associated with the order id
*
* External Dependencies: None
******************************************************************************************************************/

import java.rmi.*;
		
public interface RetrieveServicesAI extends java.rmi.Remote
{
	/*******************************************************
	* Retrieves all orders from the orderinfo database and 
	* returns them in the form of a string list.
	*******************************************************/

	String retrieveOrders() throws RemoteException;

	/*******************************************************
	* Retrieves the order corresponding to the order id in 
	* method argument form the orderinfo database and 
	* returns the order in the form of a string.
	*******************************************************/	

	String retrieveOrders(String id) throws RemoteException;
}