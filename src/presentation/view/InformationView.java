package presentation.view;

import presentation.controller.InformationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InformationView extends JFrame {
    private JLabel messageLabel;
    private JButton closeButton;

    public InformationView(InformationController informationController, String message) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(400, 200);
        setLayout(new BorderLayout());

        messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        closeButton = new JButton("Fechar");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.addActionListener(e -> informationController.close(message));

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        add(messagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
