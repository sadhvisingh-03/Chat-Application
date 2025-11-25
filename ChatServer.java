package chatapp;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    static Set<PrintWriter> clients = new HashSet<>();

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5050);
        System.out.println("Server started...");
        while (true) {
            Socket socket = server.accept();
            new ClientHandler(socket).start();
        }
    }

    static class ClientHandler extends Thread {
        Socket socket;
        PrintWriter out;
        BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                clients.add(out);

                String msg;
                while ((msg = in.readLine()) != null) {
                    for (PrintWriter writer : clients) {
                        writer.println(msg);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

