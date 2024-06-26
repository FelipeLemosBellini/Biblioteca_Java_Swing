package features.user.presentation.views;

import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;
import features.user.presentation.controllers.IUserEditController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserEditView extends JFrame implements IUserEditView {
    private final IUserEditController _userEditController;

    private JTextField loginField;
    private JTextField passwordField;
    private JComboBox<String> comboBoxProfile;

    private final UserEntity _editingUser;

    public UserEditView(IUserEditController userEditController, UserEntity editingUser) {
        _userEditController = userEditController;
        _editingUser = editingUser;

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

        comboBoxProfile = new JComboBox<>();
        for (EProfileEntity eProfileEntity : EProfileEntity.values()) {
            comboBoxProfile.addItem(eProfileEntity.toString());
        }

        if (_editingUser != null) {
            loginField = new JTextField(_editingUser.getLogin(), 15);
            comboBoxProfile.setSelectedItem(_editingUser.getProfile().toString());
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

        if (_editingUser == null) {
            formPanel.add(new JLabel("Senha:"));
            formPanel.add(passwordField);
        }

        formPanel.add(new JLabel("Perfil:"));
        formPanel.add(comboBoxProfile);

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
        EProfileEntity category = EProfileEntity.employee.getEProfile((String) comboBoxProfile.getSelectedItem());
        String passwordFieldText = passwordField.getText();

        if (name.isEmpty() || category == null) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            return;
        }

        try {
            if (_editingUser == null) {
                if (passwordFieldText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos");
                    return;
                }
                _userEditController.createUser(name, passwordFieldText, category);
            } else {
                _userEditController.editUser(_editingUser, name, category);
            }
            closeWindow();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o livro");
        }
    }
}
