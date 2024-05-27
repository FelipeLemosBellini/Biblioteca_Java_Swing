package features.user.presentation;

import features.currentUser.ICurrentUser;
import features.user.entities.EProfileEntity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserEditView extends JFrame {
    private final UserEditController _userEditController;

    private JTextField loginField;
    private JTextField passwordField;
    private JComboBox<String> comboBoxCategory;

    private ICurrentUser _currentUser;

    public UserEditView(UserEditController userEditController, ICurrentUser currentUser) {
        _userEditController = userEditController;
        _currentUser = currentUser;

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
        for (EProfileEntity eProfileEntity : EProfileEntity.values()) {
            comboBoxCategory.addItem(eProfileEntity.toString());
        }

        if (_currentUser.getCurrentUser() != null) {
            loginField = new JTextField(_currentUser.getCurrentUser().getLogin(), 15);
            comboBoxCategory.setSelectedItem(_currentUser.getCurrentUser().getProfile().toString());
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

        if (_currentUser.getCurrentUser() == null) {
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
        EProfileEntity category = EProfileEntity.employee.getEProfile((String) comboBoxCategory.getSelectedItem());
        String passwordFieldText = passwordField.getText();

        if (name.isEmpty() || category == null) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            return;
        }

        try {
            if (_currentUser.getCurrentUser() == null) {
                if (passwordFieldText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos");
                    return;
                }
                _userEditController.createUser(name, passwordFieldText, category);
            } else {
                _userEditController.editUser(_currentUser.getCurrentUser(), name, category);
            }
            closeWindow();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o livro");
        }
    }
}
