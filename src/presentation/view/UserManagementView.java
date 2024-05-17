package presentation.view;

import core.enums.ECategory;
import presentation.PresentationManager;
import presentation.controller.LibraryController;
import presentation.controller.UserManagementController;
import presentation.view.components.MenuBarComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserManagementView extends JFrame {
    private final UserManagementController _userManagementController;
    private final PresentationManager _presentationManager;

    public UserManagementView(UserManagementController userManagementController, PresentationManager presentationManager) {
        _userManagementController = userManagementController;
        _userManagementController.addListener();

        _presentationManager = presentationManager;

        defineWindowConfiguration();
        setVisible(true);
    }

    private void defineWindowConfiguration() {
        setTitle("Gestão de Usuários");
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        defineMenuBar();
    }

    private void defineMenuBar() {
        JMenuBar menuBar = MenuBarComponent.createMenuBar(
                this,
                _presentationManager,
                e -> closeWindow()
        );

        setJMenuBar(menuBar);
    }

    private void closeWindow(ActionEvent event) {
        closeWindow();
    }
    private void closeWindow() {
        _userManagementController.closeWindow();
        dispose();
    }
}
