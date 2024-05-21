package presentation.view;

import core.entities.User;
import core.enums.ECategory;
import core.enums.EProfile;
import presentation.controller.UserEditController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserEditView extends JFrame {
    private final UserEditController _userEditController;

    private JTextField loginField;
    private JTextField passwordField;
    private JComboBox<String> comboBoxCategory;

    private User currentUser = null;

    public UserEditView(UserEditController userEditController, User user) {
        _userEditController = userEditController;
        this.currentUser = user;

        defineWindowConfiguration();
        defineMenuConfiguration();
    }

    private void defineWindowConfiguration() {
        setTitle("Gestão de livros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void defineMenuConfiguration() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(5, 5, 5, 5)));

        comboBoxCategory = new JComboBox<>();
        for (EProfile eProfile : EProfile.values()) {
            comboBoxCategory.addItem(eProfile.toString());
        }

        if (currentUser != null) {
            loginField = new JTextField(currentUser.getLogin(), 15);
            comboBoxCategory.setSelectedItem(currentUser.getProfile().toString());
        } else {
            loginField = new JTextField(15);
        }

        passwordField = new JTextField(15);

        JButton saveButton = new JButton("Salvar");
        JButton cancelButton = new JButton("Cancelar");

        saveButton.addActionListener(this::saveRow);
        cancelButton.addActionListener(this::closeWindow);

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(loginField);

        if (currentUser == null) {
            formPanel.add(new JLabel("Senha:"));
            formPanel.add(passwordField);
        }

        formPanel.add(new JLabel("Perfil:"));
        formPanel.add(comboBoxCategory);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }


    private void closeWindow(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        _userEditController.closeWindow();
    }

    private void saveRow(ActionEvent event) {
        String name = loginField.getText();
        EProfile category = EProfile.employee.getEProfile((String) comboBoxCategory.getSelectedItem());
        String passwordFieldText = passwordField.getText();

        if (name.isEmpty() || category == null) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            return;
        }

        try {
            if (currentUser == null) {
                if (passwordFieldText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos");
                    return;
                }
                _userEditController.createUser(name, passwordFieldText, category);
            } else {
                currentUser.edit(name, category);
            }
            closeWindow();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o livro");
        }
    }
}
