import java.rmi.*;


public interface CreateUserServicesAI extends java.rmi.Remote {
    Boolean makeUser(String username, String Password) throws RemoteException;
}
