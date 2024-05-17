package presentation.view;

import presentation.PresentationManager;
import presentation.controller.HomeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {
    private final HomeController homeController;
    private final PresentationManager presentationManager;

    public HomeView(PresentationManager presentationManager, HomeController homeController) {
        this.homeController = homeController;
        this.presentationManager = presentationManager;

        configureWindow();
        setVisible(true);
    }

    private void configureWindow() {
        setTitle("Administração de Sistema");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Sistema de Biblioteca", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton userRegistrationButton = createButton("Cadastro de Usuário", e -> presentationManager.startUserManagement());
        JButton libraryManagementButton = createButton("Gestão da Biblioteca", e -> presentationManager.startLibrary());
        buttonPanel.add(userRegistrationButton);
        buttonPanel.add(libraryManagementButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(230, 230, 250));
        button.addActionListener(actionListener);
        return button;
    }
}
