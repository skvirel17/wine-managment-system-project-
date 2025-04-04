package boundary;

import control.auth.AuthService;
import control.auth.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // отступы

        JLabel lblUser = new JLabel("Username:");
        JLabel lblPass = new JLabel("Password:");
        JTextField txtUser = new JTextField(15);
        JPasswordField txtPass = new JPasswordField(15);
        JButton btnLogin = new JButton("Login");
        JLabel lblMessage = new JLabel("");

        // Username label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblUser, gbc);

        // Username field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtUser, gbc);

        // Password label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblPass, gbc);

        // Password field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtPass, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnLogin, gbc);

        // Message label
        gbc.gridy = 3;
        panel.add(lblMessage, gbc);

        add(panel);

        btnLogin.addActionListener((ActionEvent e) -> {
            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());

            User user = AuthService.login(username, password);

            if (user != null) {
                lblMessage.setForeground(Color.GREEN);
                lblMessage.setText("Welcome " + user.getRole().name().toLowerCase() + "!");
                new MainMenu(user).setVisible(true);
                dispose();
            } else {
                lblMessage.setForeground(Color.RED);
                lblMessage.setText("Wrong username or password.");
            }
        });
    }
}

