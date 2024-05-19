package presentation.view;

import presentation.PresentationManager;
import presentation.controller.UserManagementController;
import presentation.view.components.MenuBarComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserManagementView extends JFrame {
    private final UserManagementController _userManagementController;
    private final PresentationManager _presentationManager;

    private JTable table;
    private DefaultTableModel model;
    private JTextField searchStringField;

    public UserManagementView(UserManagementController userManagementController, PresentationManager presentationManager) {
        _userManagementController = userManagementController;
        _userManagementController.addListener();

        _presentationManager = presentationManager;

        initComponents();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void initComponents() {
        setTitle("Gestão de Usuários");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        defineMenuBar();
        defineLayout();
    }

    private void defineMenuBar() {
        JMenuBar menuBar = MenuBarComponent.createMenuBar(
                this,
                _presentationManager,
                e -> closeWindow()
        );
        setJMenuBar(menuBar);
    }

    private void defineLayout() {
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Pesquisar na Tabela:");
        searchStringField = new JTextField(15);
        JButton searchButton = new JButton("Pesquisar");
        searchButton.addActionListener(this::searchBook);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Id");
        model.addColumn("Login");
        model.addColumn("Profile");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("Adicionar/Editar");
        JButton removeButton = new JButton("Excluir");
        JButton changePasswordButton = new JButton("Alterar Senha");

        addButton.addActionListener(this::openSaveScreen);
        removeButton.addActionListener(this::removeRow);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addComponent(searchStringField)
                                .addComponent(searchButton))
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addComponent(removeButton)
                                .addComponent(changePasswordButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(searchStringField)
                                .addComponent(searchButton))
                        .addComponent(scrollPane)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addButton)
                                .addComponent(removeButton)
                                .addComponent(changePasswordButton))
        );

        getContentPane().add(mainPanel);
    }

    private void closeWindow(ActionEvent event) {
        closeWindow();
    }
    private void closeWindow() {
        _userManagementController.closeWindow();
    }

    private void openSaveScreen(ActionEvent event) {
//        try {
//            int id = getBookIdFromTable();
//            Book book = _libraryController.getBook(id);
//            _libraryController.openBookEdit(book);
//        } catch (NotSelectedRowException e) {
//            Book book = null;
//            _libraryController.openBookEdit(book);
//        }
    }

    private void removeRow(ActionEvent event) {
//        try {
//            int id = getBookIdFromTable();
//            Book book = _libraryController.getBook(id);
//            _libraryController.deleteBook(book);
//            updateTable();
//        } catch (NotSelectedRowException e) {
//            JOptionPane.showMessageDialog(this, "Selecione um livro para excluir");
//        }
    }

    private void searchBook(ActionEvent event) {
        updateTable();
    }

    private void updateTable() {
        model.setRowCount(0);

        String searchString = searchStringField.getText();
//        List<Book> booksList = _libraryController.getBook(searchString);
//
//        for (Book book : booksList) {
//            String borrowText = book.getBorrowing() ? "Emprestado" : "Disponível";
//            model.addRow(new Object[]{book.getId(), book.getName(), book.getAuthor(), book.getCategory(), book.getISBN(), borrowText});
//        }
    }
}
