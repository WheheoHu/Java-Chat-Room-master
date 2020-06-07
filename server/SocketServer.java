package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class SocketServer implements Runnable {
    ServerGUI serverGUI = new ServerGUI();
    volatile boolean isServerStart = false;

    @SuppressWarnings("resource")
    @Override
    public void run() {
        try {
            serverGUI.tf_server_ip.setText( InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ConnectionInfo info = new ConnectionInfo();
        final ServerSocket[] server = new ServerSocket[1];
        //a hashmap store online users' name and address
        serverGUI.serverButton.addActionListener(actionEvent -> {
            try {
                server[0] =new ServerSocket(Integer.parseInt(serverGUI.tf_server_port.getText()) );
                isServerStart =true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        while (!isServerStart) {
            Thread.onSpinWait();
        }
        serverGUI.msg_text.append("Server Start!\n");

        while (isServerStart) {


            // accept a connection
            SuperSock socket = new SuperSock();
            try {
                socket.setSocket(server[0].accept());
                // like scanf in C, waiting for the result
                // if have connected, then record that socket

                System.out.println("new connection");
            } catch (Exception e) {
                System.out.println("Accept Error:" + e);
            }

            //start a new thread for a new user
            //back to listening afterwards
            oneServer newServer = new oneServer(socket, info,serverGUI);
            Thread t = new Thread(newServer);
            t.start();
        }

    }}




