package server;

import javax.swing.*;

public class ServerGUI extends JFrame {
    private JPanel Server;
    public JTextField tf_server_ip;
    public JTextField tf_server_port;
    public JTextArea msg_text;
    public JButton serverButton;
    private JLabel IP;

    public ServerGUI() {
        super("Server");
        setVisible(true);
        setContentPane(Server);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
