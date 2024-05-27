package features.home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {
    private final HomeController _homeController;

    public HomeView(HomeController homeController) {
        _homeController = homeController;
        
        configureWindow();
        setVisible(true);
    }

    private void configureWindow() {
        setTitle("Administração de Sistema");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Sistema de Biblioteca", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton userRegistrationButton = createButton("Cadastro de Usuário", e -> _homeController.openUserManager());
        JButton libraryManagementButton = createButton("Gestão da Biblioteca", e -> _homeController.openLibraryManager());
        buttonPanel.add(userRegistrationButton);
        buttonPanel.add(libraryManagementButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);

        setLocationRelativeTo(null);
    }
    
    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(230, 230, 250));
        button.addActionListener(actionListener);
        return button;
    }
}
