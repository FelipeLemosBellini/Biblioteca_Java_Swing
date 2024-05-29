package features.login.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ILoginView {
    private final ILoginController _loginController;
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    
    public LoginView(ILoginController loginController) {
        _loginController = loginController;
        
        defineWindowConfig();
    }
    
    private void defineWindowConfig(){
        setTitle("Sistema de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Bem-vindo ao Sistema");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel usernameLabel = new JLabel("Usuário:");
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(300, 35));

        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(300, 35));


        JButton loginButton = new JButton("Entrar");
        loginButton.setPreferredSize(new Dimension(200, 35));

        loginButton.addActionListener(this::login);

        JButton infoButton = new JButton("ℹ️ Informações");
        infoButton.addActionListener(this::showInformation);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        gbc.gridy++;
        panel.add(infoButton, gbc);

        add(panel);

        setVisible(true);
    }
    
    private void showInformation(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Sistema Biblioteca versão 1.0\n\n" +
                "Logins disponiveis:\n" +
                "Login: admin | Senha: admin\n" +
                "Login: employee | Senha: employee");
    }

    private void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        boolean wasLogged = _loginController.tryMakeUserLogin(username, password);
        
        if(!wasLogged)
            JOptionPane.showMessageDialog(this, "Login ou senha incorretos");
            
    }
}
