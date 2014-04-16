import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static javax.swing.JOptionPane.showInputDialog;
/**
 * Created by Bjorn Gilstad.
 */

public class ChatClient {
    public static void main(String[] args) throws Exception {
        String clientId = InetAddress.getLocalHost().toString();
        System.out.print(clientId);
        String nickname = showInputDialog("Enter nickname: ");
        Registry registry = LocateRegistry.getRegistry("cipralex.tihlde.org", 12000);
        Server server = (Server) registry.lookup("ChatBubble");
        ClientImpl newClient = new ClientImpl(clientId, nickname);
        ClientGui gui = new ClientGui(newClient, server);
        newClient.addGUI(gui);
        server.registerNewClient(newClient);
    }
}
