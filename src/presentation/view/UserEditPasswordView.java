package presentation.view;

import core.entities.User;
import core.enums.EProfile;
import presentation.contracts.IUserRepositoryListener;
import presentation.controller.UserEditController;
import presentation.controller.UserEditPasswordController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEditPasswordView extends JFrame {
    private final UserEditPasswordController _userEditPasswordController;

    private JTextField oldPasswordField;
    private JTextField newPasswordField;
    private JTextField confirmedPasswordField;

    private final User currentUser;

    public UserEditPasswordView(UserEditPasswordController userEditPasswordController, User user) {
        _userEditPasswordController = userEditPasswordController;
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

        oldPasswordField = new JTextField(15);
        newPasswordField = new JTextField(15);
        confirmedPasswordField = new JTextField(15);

        JButton saveButton = new JButton("Salvar");
        JButton cancelButton = new JButton("Cancelar");

        saveButton.addActionListener(this::saveRow);
        cancelButton.addActionListener(this::closeWindow);

        formPanel.add(new JLabel("Senha Antiga:"));
        formPanel.add(oldPasswordField);

        formPanel.add(new JLabel("Nova Senha:"));
        formPanel.add(newPasswordField);

        formPanel.add(new JLabel("Confirmação de Senha:"));
        formPanel.add(confirmedPasswordField);

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
        _userEditPasswordController.closeWindow();
    }

    private void saveRow(ActionEvent event) {
        String oldPasswordFieldText = oldPasswordField.getText();
        String newPasswordFieldText = newPasswordField.getText();
        String confirmedPasswordFieldText = confirmedPasswordField.getText();

        if (oldPasswordFieldText.isEmpty() || newPasswordFieldText.isEmpty() || confirmedPasswordFieldText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            return;
        }

        try {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado");
                return;
            } else {
                var passWasEdited = currentUser.changePassword(oldPasswordFieldText, newPasswordFieldText, confirmedPasswordFieldText);
                
                if (!passWasEdited) {
                    JOptionPane.showMessageDialog(this, "A senha antiga ou as novas senhas não coencidem");
                    return;
                }
            }
            
            closeWindow();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao mudar a senha");
        }
    }
}
