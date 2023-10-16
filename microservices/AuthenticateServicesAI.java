import java.rmi.*;
import java.rmi.RemoteException;


public interface AuthenticateServicesAI extends java.rmi.Remote {
    Boolean authenticate(String username, String Password) throws RemoteException;
}
