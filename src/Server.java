import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Bjorn Gilstad
 */
public interface Server extends Remote {
    String getServerName() throws RemoteException;
    void registerNewClient(Client client) throws RemoteException;
    ArrayList<Client> getClients() throws RemoteException;
    void broadcastMessage(String nickname, String message) throws RemoteException;
    ArrayList<String> getNicklist() throws RemoteException;
}
