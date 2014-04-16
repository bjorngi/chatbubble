import java.rmi.*;

/**
 * Created by bjorn on 4/15/14.
 */

public interface Client extends Remote {
    String getClientId() throws RemoteException;
    String getNickname() throws RemoteException;
    void setNickname(String nickname) throws RemoteException;
    void sendMessage(String message) throws RemoteException;
}
