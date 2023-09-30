## <strong>Module 3:</strong> Microservices Architecture

<hr>

This is an order management application, that stores customer data as a collection of orders. In this module, you will add modifications to the system, as well as extend its existing functionalities, keeping in mind that the system follows the principles of microservice-oriented architecture. On the server side, the microservices system contains a set of classes, which can be thought of as independent software services. Communication between the UI and the microservices uses RMI (Remote-Method Invocation). RetrieveServices has been provided, as an example, that returns the entries in the `orders` table, query and return selected orders by `order_id`. As another example, CreateServices service is responsible for creating a new entry in the `orders` table. A quick view of the system is shown below: 

![System-View](./assets/image-2.png)

For this module, you need Java 11 or higher, as well as MySQL. <br> [Linux](https://www.youtube.com/watch?v=oZx0NY8heWI) / [Windows](https://dev.mysql.com/downloads/installer/) / [MacOS (Homebrew)](https://formulae.brew.sh/formula/mysql)

**Files for the module:**
- dbtemplate.sql – file: for initializing the MySQL Database Management System with an `orders` table following a schema. 
- CreateServices.java - a service that handles the creation of new orders.
- RetrieveServices.java - service for handling the retrieval of orders from the database, based on provided queries.
- MSClientAPI.java – Abstracts the underlying communication with the services, from the user interface. 
- OrdersUI.java - Application user interface.
- registry.properties – A properties file containing the location of the microservices.


### **How to initialise the project:**
1. Once MySQL is installed, you will need to set up a password for accessing it. The MySQL server will have a default "root" user, and might not come with a password, but it can be good to set one. This project's default password for initialization is: "foo", and it is recommended to change your login credentials for your "root" user to match it. 

> In Windows, if one chooses to utilize MySQL through its UI client rather than the terminal, then start the MySQL Terminal Client of version "Unicode", instead of MySQL Terminal Client.

It is possible to start the MySQL client by typing in the terminal:
```
mysql -uroot -p
```
and entering the password you may have set while following the installation procedure of MySQL. In case no password was set, then run the same command as above, without `-p`. 

Enter the following line **upon starting** the MySQL terminal:
   
```
> alter user 'root'@'localhost' identified with mysql_native_password by 'foo';
```

**OPTIONAL:** Change “foo” to be the password you want to use. To do this, you will need to edit the file: `Configuration.java` and change the following line, to your custom-set password. Be sure to reflect the same password changes on your machine installation of MySQL.

```
public static final String MYSQL_PASSWORD = 'foo';
```

2. You will need to create a place in your DBMS to store the order data, so that database information persists between runs. To do so, open MySQL on your command line:

```
$ mysql -uroot -pfoo
```

3. Once logged in, you should see the command-line interface of MySQL Server. To see the list of your databases, enter the following command:
```
> show databases;
```
`Note: Make sure to end all entered commands with a ';' in MySQL.`

4. Create a new database called **'ms_orderinfo'**, and initialize it with the tables + schema that the orders follow, which are to be stored and managed via the application. The initialization operations are all listed in a SQL file called [dbtemplate.sql](dbtemplate.sql) in this module's folder.

```
> create database ms_orderinfo;
> use ms_orderinfo;
> source ./dbtemplate.sql;
```
> If MySQL is not able to locate the script SQL file (dbtemplate.sql), then enter the absolute path to the file, from the root directory of the filesystem. 

The desired output should be:
```
Query OK, 0 rows affected (0,02 sec)
Query OK, 0 rows affected (0,00 sec)
Query OK, 0 rows affected (0,00 sec)
...
```

Here the database and tables get initialised, where the data is to be persisted. To test if it was successful, run:

```
> show tables;
```
Output:
```
+------------------------+
| Tables_in_ms_orderinfo |
+------------------------+
| orders                 |
+------------------------+
```
In order to see its schema, run:
```
> describe orders;
```
Output:
```
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| order_id   | int unsigned | NO   | PRI | NULL    | auto_increment |
| order_date | date         | YES  |     | NULL    |                |
| first_name | varchar(20)  | YES  |     | NULL    |                |
| last_name  | varchar(20)  | YES  |     | NULL    |                |
| address    | varchar(80)  | YES  |     | NULL    |                |
| phone      | varchar(15)  | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
```
### **Building and running the microservices**  
During the installation of MySQL on your local machine, if default installation settings were not changed, ensure that the MySQL server is running on port `3306`. It is possible to double-check if the port is correct, by this command in the MySQL client:

```
> show global variables like 'port';
```

The desired output should be:
```
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| port          | 3306  |
+---------------+-------+
```

**MySQL-JDBC (Java Database Connectivity):** <br>
The repository comes with a `.jar` file called `mysql-connector-j-8.0.33.jar`, that is needed for, as the name describes, acting as an API for the services to communicate, and execute queries to the database.

While the database has been fully set up and running on this port, every microservice can be run in parallel, on different command prompt windows. As previously mentioned, the microservices communicate with the client, database, and each other, using Remote Method Invocation (RMI) in Java. Every service is to be run on a different RMI port (also called an RMI Registry), to allow isolated operations of each of the services.

The first step is to take a look at the `registry.properties`, and notice the ports that are provided next to the names of the services. As an example, the CreateServices has properties `localhost:1099`, which means that it is to be binded with the port 1099 of the RMI registry, to host the microservice.

When Java is installed on your machine, make sure that the **path to the JDK is present in the system path**. With the installation of the JDK, an executable called `rmiregistry` should be included with it, which is a must-have for this module.

<hr>

Below is a general template on how to run microservices in separate terminals, also when your group is to implement new microservice(s), during the course of this assignment. For each service that is run, one will require **two** terminal tabs/windows, 1) for hosting the service that is to be run, and 2) that starts & runs the service.

> Note: Make sure you are in the right directory before the first step (**a4-group-XX/microservices**).

### Mac / Linux

1. In one terminal, open a port based on the `registry.properties` file:
```
rmiregistry 1098
```
2. Open a new terminal, and paste the following commands in your terminal. The first line, is to make an environment variable called CLASSPATH that makes the MySQL Java Database Connectivity .jar file (included with this module), as well as the module's folder visible for the JRE (use **version 8.0.33**).

In the second line, the port used in the RMI is to be declared as an environment variable, as multiple services will communicate via RMI, and they run isolated in their own ports. Hence it is declared at the start, in it's intended terminal window. In the example of RetrieveServices, declare the port as 1098, according to the way it is defined in the registry.properties file, and then run *that* particular service (comes later in Step 3). 

```
export CLASSPATH=<full-path to the JDBC>:<full-path to the microservices/ dir>
export RMI_REMOTE_PORT=1098
```
3. Then, build the .java files, and run the .class file of the microservice that is to be run on that particular port:
```
javac *.java
java RetrieveServices
```

Desired Output:
```
Registered Services:
    RetrieveServices
```

> Tip: The variable of CLASSPATH, as well as RMI_REMOTE_PORT get deleted upon closing the shell. If you wish to fix those variables, and not have to re-enter CLASSPATH every time before running a service (can be very tedious), then add the following line to your ~/.zshrc file (ZSH) or ~/.bashrc file (if using a BASH terminal):

```
export CLASSPATH=<full-path to the JDBC>:<full-path to the microservices/ dir>
```
Save the changes by running `source ~/.bashrc` or `source ~/.zshrc`, or restart the terminal.

### Windows

For **Windows**, see this [link](https://www.geeksforgeeks.org/how-to-set-classpath-in-java/) for the instructions. Below is the process shown, for setting the CLASSPATH system variable. For this, add a new System Variable (under Environment Variables), and create a new one, like so:

![System Variable](./assets/image-1.png)

> By doing so, it is not required to declare the CLASSPATH everytime a service is to be run. Additionally, observe above that the two paths are separated with a ";" for Windows instead of a ":", as shown for Mac/Linux machines. 

In one terminal, open a Java RMI registry based on the port you wish to run it on. For this example, we try to run RetrieveServices at port 1098.
```
> rmiregistry 1098
```

Next, in a new terminal, set a new variable called **"RMI_REMOTE_PORT"**, with the value of the port of the service that is to be run. 

Compile all of the .java files by running:
```
> javac *.java
```

The variable **RMI_REMOTE_PORT** may not remain after the terminal is closed, which is fine. As different terminals need to be set for running different services, it is good to declare **RMI_REMOTE_PORT** as a first step, to *designate* a particular service to be run at a port.

As an example, here we run RetrieveServices. The service is identifiable for the RMI registry, and gets hosted there. For running eg. CreateServices, replace the value of 1098 with 1099, and repeat the process. 

> When running the services, make sure you are in the same directory in your machine i.e the microservices/ directory in both of your terminals.

![Running the service](./assets/image.png) 

As your group is to implement more functionalities in this assignment, it is possible to change the **registry.properties** file, with incrementing the port numbers, initializing them in a new RMI registry in the same manner, and compiling + running in a new terminal window, when followed the procedure in the aforementioned instructions. 

### **Running the microservices client** 

Run the CLI client, to make requests to interact with the services. In a new terminal, this can be done through the command: 

```
$ java OrdersUI 
```

This will open a CLI in the terminal:
```
Orders Database User Interface: 

Select an Option: 

1: Retrieve all orders in the order database.
2: Retrieve an order by ID.
3: Add a new order to the order database.
X: Exit

>>>> 
``` 

This will give you a menu and you should be able to choose options. From the previous example, try to run RetrieveServices. To test, select the first option by entering 1 in the prompt (Retrieve all orders in the order database), then press Enter. You should see a result, without any thrown exceptions, that looks like: 

```
Retrieving All Orders:: 
[] 
```

> Note: Like in this example, to perform the right operation in the system, the microservice that handles the corresponding operation must be running, to execute it successfully. The UI module will run normally, however eg. the RetrieveServices has not been started, the retrieval operation will not work.

<hr>

## Assignment Instructions

### **Part 1: Modifications to the Systems** 

In part 1 of the assignment, your team will modify the provided system and you will be required to compare-and-contrast (vis-à-vis a writeup) your experiences in making these changes. The modifications will be completed as a team. 

1. Modify both systems to enable users to delete an order by **order_id**. 

    Keeping in mind the principles of microservices, **modify/refactor the source code**, during the process of adding the "deletion" functionality, and whichever new features are added herein during the course of the module, ensuring it is consistent with the architectural style. 
    
    This may include making appropriate decisions of generalizing/specifying a service that it resembles a business context/use case. Additionally, microservices are to be designed in a decoupled manner, that they all have their own database.
    
    This is to be reflected upon in depth, later during **Part 2**.

2. Modify both systems to implement user identification and authorization in order to use the provided services. Assume that first-time users are required to provide a username and password before they use any services. Credentials must be entered before a user is able to use any service. Once they have provided credentials and have been authorized, they may use any service as often as they like until they exit the application. 

    **NOTE:** Do not confuse this with the authentication that happens between the services and the database. In this context, the identification and authorization is required for users to access any service (other than the first-time user operation to create a new identification and password described above). i.e., it should not be possible for a user to use any service unless they have authenticated.     

3. Modify both systems to provide a logging function that logs the services provided to every user on the system. Assume that you will store the logs as a collection on the server side of the system. The purpose of logging is to be able to debug and understand what happened in the system should there be some question about who made what changes, or where errors might have occurred in the system. 

### **Part 2: Data collection and Reporting (to be done in the provided document on Canvas)** 
For each of the modifications in Part 1, each team should collect some data, in order to analyse and reflect about the modifiability of the system. For each implemented feature from Part 1, you should report data about: 

● The number of modules affected <br>
● The number of new modules that were required <br>
● Time to make the new changes <br>
● Extent to which the changes affect other elements of the system 

### **Part 3: Architectural Discussion (to be done in the provided document on Canvas)**

The team report should also contain a description of **architecture choices** made in the implementation, along with the **justifications** to the changes and the **impacts** of those decisions. 

<hr>

The report should also contain **architecture diagrams** that represent the topological view of the end architecture of the system the team implemented. Attach them in this folder, with an appropriate name.
