import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

/**
 * Created by bjorn on 4/15/14.
 */
public class ClientGui implements ActionListener {
    private Client client;
    private JTextField inputField;
    private JTextArea chatArea;
    private JList nicklist;
    private Server server;

    public ClientGui(Client newClient, Server server) throws Exception{
        this.client = newClient;
        this.server = server;

        chatArea = new JTextArea(8, 40);
        chatArea.setEditable(false);
        chatArea.setFocusable(false);
        JScrollPane chatScroll = new JScrollPane(chatArea);
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.add(new JLabel("Chat:", SwingConstants.LEFT), BorderLayout.PAGE_START);
        chatPanel.add(chatScroll);

        inputField = new JTextField(40);
        JButton sendBtn = new JButton("Send");
        sendBtn.addActionListener(this);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));
        inputPanel.add(inputField);
        inputPanel.add(sendBtn);

        JPanel youLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        youLabelPanel.add(new JLabel(newClient.getNickname()));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(chatPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(youLabelPanel);
        mainPanel.add(inputPanel);

        nicklist = new JList(server.getNicklist().toArray());
        mainPanel.add(nicklist);

        JFrame frame = new JFrame("ChatBubble");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            writeMessage(client.getNickname(), inputField.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        inputField.setText("");
    }

    public void writeMessage(String message) throws RemoteException {
        chatArea.append(message + "\n");
    }

    public void writeMessage(String nickname, String message) throws RemoteException {
        server.broadcastMessage(nickname, message);
    }

    public void privateMessage(Client reciever, String message) throws RemoteException {

    }
}
