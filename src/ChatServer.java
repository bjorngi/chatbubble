import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import static javax.swing.JOptionPane.*;

/**
 * Created by Bjorn Gilstad.
 */
public class ChatServer {
    public static void main(String[] args) throws Exception {
        final Registry registry = LocateRegistry.createRegistry(12000);
        ServerImpl server = new ServerImpl("ChatBubble");

        try {
            registry.bind(server.getServerName(), server);
        } catch (Exception e) {
            System.err.println("Server could not be started");
            System.exit(0);
        }
        System.out.println("Server up.");
        String serverMessage;
        while(!(serverMessage = serverMessage()).equals(null)) {
            server.broadcastMessage("Server", serverMessage);
        }

        registry.unbind(server.getServerName());
        System.exit(0);
    }

    public static String serverMessage() {
        String serverMessage = showInputDialog(null, "Server message!");
        if(serverMessage.equals("")) {
            return null;
        }
        return serverMessage;
    }
}
