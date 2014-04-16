import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Bjorn Gilstad
 */
public class ClientImpl extends UnicastRemoteObject implements Client {
    private String clientId;
    private String nickname;
    private ClientGui gui;

    public ClientImpl(String clientId, String nickname) throws RemoteException {
        this.clientId = clientId;
        this.nickname = nickname;
    }

    @Override
    public String getClientId() throws RemoteException {
        return clientId;
    }

    @Override
    public String getNickname() throws RemoteException {
        return nickname;
    }

    @Override
    public void setNickname(String nickname) throws RemoteException {
        this.nickname = nickname;
    }

    public void sendMessage(String message) throws RemoteException {
        gui.writeMessage(message);
    }

    public void addGUI(ClientGui gui) {
        this.gui = gui;
    }
}
