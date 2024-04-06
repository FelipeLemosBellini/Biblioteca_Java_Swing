package Presentation.Screens;

import core.use_cases.BookUseCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LibraryScreen extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton pesquisarButton;
    private BookUseCase bookUseCase;

    public LibraryScreen(){
        bookUseCase = new BookUseCase();
        setWindow();
    }

    private void setWindow(){
        setTitle("Gerenciador da Biblioteca");
        setSize(1200, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
