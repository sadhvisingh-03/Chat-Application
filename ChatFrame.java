package chatapp;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ChatFrame extends JFrame {
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    JTextArea area;
    JTextField field;

    public ChatFrame(String username) {
        setTitle("Chat - " + username);
        setSize(400, 500);
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);

        field = new JTextField();
        add(field, BorderLayout.SOUTH);

        try {
            socket = new Socket("localhost", 5050);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(username);

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null)
                        area.append(msg + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        field.addActionListener(e -> {
            out.println(username + ": " + field.getText());
            field.setText("");
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new ChatFrame("User1"));
    }

}

