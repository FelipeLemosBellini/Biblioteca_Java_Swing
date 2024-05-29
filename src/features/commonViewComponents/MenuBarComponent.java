package features.commonViewComponents;

import features.books.presentation.views.IBooksView;
import features.menu.IMenuView;
import features.user.presentation.views.IUsersView;
import infraestructure.IPresentationManager;
import infraestructure.PresentationManager;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBarComponent {
    public static JMenuBar createMenuBar(JFrame parentFrame, IPresentationManager presentationManager, ActionListener exitAction) {
        JMenuBar menuBar = new JMenuBar();

        JMenu configMenu = new JMenu("Configurações");

        JMenuItem manageUsersItem = new JMenuItem("Gerenciar Usuários");
        manageUsersItem.addActionListener(e -> presentationManager.openWindow(IUsersView.class));

        JMenuItem manageLibrary = new JMenuItem("Gerenciar Biblioteca");
        manageLibrary.addActionListener(e -> presentationManager.openWindow(IBooksView.class));

        JMenuItem aboutItem = new JMenuItem("Sobre");
        aboutItem.addActionListener(e -> showAboutDialog(parentFrame));

        JMenuItem exitItem = new JMenuItem("Sair");
        exitItem.addActionListener(exitAction);

        JMenuItem homeItem = new JMenuItem("Voltar para a Home");
        homeItem.addActionListener(e -> presentationManager.openWindow(IMenuView.class));

        configMenu.add(homeItem);
        configMenu.add(manageUsersItem);
        configMenu.add(manageLibrary);
        configMenu.add(aboutItem);
        configMenu.add(exitItem);

        menuBar.add(configMenu);

        return menuBar;
    }

    private static void showAboutDialog(JFrame parentFrame) {
        JOptionPane.showMessageDialog(parentFrame, "Library Management System\nVersion 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
    }
}
