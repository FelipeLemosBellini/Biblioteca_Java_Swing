package presentation.view.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBarComponent {
    public static JMenuBar createMenuBar(JFrame parentFrame, ActionListener manageUsersAction, ActionListener exitAction) {
        JMenuBar menuBar = new JMenuBar();

        JMenu configMenu = new JMenu("Configurações");

        JMenuItem manageUsersItem = new JMenuItem("Gerenciar Usuários");
        manageUsersItem.addActionListener(manageUsersAction);

        JMenuItem aboutItem = new JMenuItem("Sobre");
        aboutItem.addActionListener(e -> showAboutDialog(parentFrame));

        JMenuItem exitItem = new JMenuItem("Sair");
        exitItem.addActionListener(exitAction);

        configMenu.add(manageUsersItem);
        configMenu.add(aboutItem);
        configMenu.add(exitItem);

        JMenu fileMenu = new JMenu("Home");

        fileMenu.addActionListener(e -> newFileAction());

        menuBar.add(fileMenu);
        menuBar.add(configMenu);

        return menuBar;
    }

    private static void showAboutDialog(JFrame parentFrame) {
        JOptionPane.showMessageDialog(parentFrame, "Library Management System\nVersion 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void newFileAction() {
        System.out.println("Novo arquivo acionado");
    }

    private static void openFileAction() {
        System.out.println("Abrir arquivo acionado");
    }
}
