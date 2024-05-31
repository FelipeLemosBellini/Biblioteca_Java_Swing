package features.user.presentation.views;

import features.user.entities.UserEntity;
import features.user.presentation.controllers.IUserEditPasswordController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserEditPasswordView extends JFrame implements IUserEditPasswordView {
    private final IUserEditPasswordController _userEditPasswordController;

    private JTextField newPasswordField;
    private JTextField confirmedPasswordField;

    private final UserEntity _userEditing;

    public UserEditPasswordView(IUserEditPasswordController userEditPasswordController, UserEntity userEditing) {
        _userEditPasswordController = userEditPasswordController;
        _userEditing = userEditing;

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

        newPasswordField = new JTextField(15);
        confirmedPasswordField = new JTextField(15);

        JButton saveButton = new JButton("Salvar");
        JButton cancelButton = new JButton("Cancelar");

        saveButton.addActionListener(this::save);
        cancelButton.addActionListener(this::closeWindow);

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

    private void save(ActionEvent event) {
        String newPasswordFieldText = newPasswordField.getText();
        String confirmedPasswordFieldText = confirmedPasswordField.getText();

        if (newPasswordFieldText.isEmpty() || confirmedPasswordFieldText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            return;
        }

        try {
            if (_userEditing == null) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado");
                return;
            } else {
                var passWasEdited = _userEditPasswordController.changePassword(_userEditing, newPasswordFieldText, confirmedPasswordFieldText);
                
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
