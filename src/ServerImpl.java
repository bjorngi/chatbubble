import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by bjorn on 4/15/14.
 */
public class ServerImpl extends UnicastRemoteObject implements Server {
    private String serverName;
    private ArrayList<Client> clients = new ArrayList<Client>();

    public ServerImpl(String serverName) throws RemoteException {
        this.serverName = serverName;
    }


    @Override
    public synchronized String getServerName() throws RemoteException {
        return serverName;
    }

    @Override
    public synchronized void registerNewClient(Client client) throws RemoteException {
        clients.add(client);
        System.out.println("New client registered, clientID = " + client.getClientId() + " number of clients connected: " + clients.size());
        broadcastMessage("Server", client.getNickname() + " has connected.");
    }

    @Override
    public synchronized ArrayList<Client> getClients() throws RemoteException {
        return clients;
    }



    /*@Override
    public void broadcastMessage(String nickname, String message) throws RemoteException {
        for(Client c : clients) {
            try {
                c.sendMessage(nickname + ": " + message);
            } catch (Exception e) {
                System.out.println("Client " + c.getNickname() + " has disconnected.");
                clients.remove(c);
            }
        }
    }*/
    @Override
    public void broadcastMessage(String nickname, String message) throws RemoteException {
        int clientIndex = 0;
        System.out.println("Size: " + clients.size());
        while(clientIndex < clients.size()) {
            Client thisClient = clients.get(clientIndex);
            try {
                thisClient.sendMessage(nickname + ": " + message);
                System.out.println("Sent message :" + message + " from " + nickname + " to " + thisClient.getNickname());
                clientIndex++;
            } catch (Exception e) {
                e.printStackTrace();
                clients.remove(clientIndex);
                broadcastMessage("Server: ", nickname + " disconnected.");
            }
        }
    }

    @Override
    public ArrayList<String> getNicklist() throws RemoteException {
        ArrayList<String> nicklist = new ArrayList<String>();
        for(Client c : clients) {
            nicklist.add(c.getNickname());
        }
        return nicklist;
    }
}
