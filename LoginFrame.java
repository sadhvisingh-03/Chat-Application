package chatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin, btnRegister;

    public LoginFrame() {
        setTitle("Login - Chat App");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        txtUser = new JTextField();
        txtUser.setBounds(150, 50, 150, 30);
        add(txtUser);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(50, 100, 100, 30);
        add(l2);

        txtPass = new JPasswordField();
        txtPass.setBounds(150, 100, 150, 30);
        add(txtPass);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 150, 100, 30);
        add(btnLogin);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 150, 100, 30);
        add(btnRegister);

        btnLogin.addActionListener(e -> login());
        btnRegister.addActionListener(e -> register());

        setVisible(true);
    }

    private void login() {
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtUser.getText());
            ps.setString(2, String.valueOf(txtPass.getPassword()));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                new ChatFrame(txtUser.getText());
                //dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void register() {
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users(username, password) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtUser.getText());
            ps.setString(2, String.valueOf(txtPass.getPassword()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration successful!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new LoginFrame();
    }
}

