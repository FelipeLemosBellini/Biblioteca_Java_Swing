package presentation.view;

import presentation.PresentationManager;
import presentation.contracts.IBookRepositoryListener;
import presentation.controller.LibraryController;
import presentation.exceptions.NotSelectedRowException;
import core.entities.Book;
import presentation.view.components.MenuBarComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class LibraryView extends JFrame implements IBookRepositoryListener {
    private final LibraryController _libraryController;
    private final PresentationManager _presentationManager;

    private JTable table;
    private DefaultTableModel model;
    private JTextField searchStringField;

    public LibraryView(LibraryController libraryController, PresentationManager presentationManager) {
        _libraryController = libraryController;
        _libraryController.addListener(this);

        _presentationManager = presentationManager;

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Gestão de livros");
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
        model.addColumn("Nome");
        model.addColumn("Autor");
        model.addColumn("Categoria");
        model.addColumn("ISBN");
        model.addColumn("Disponibilidade");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("Adicionar/Editar");
        JButton removeButton = new JButton("Excluir");
        JButton borrowButton = new JButton("Empréstimo de livro");

        addButton.addActionListener(this::openSaveScreen);
        removeButton.addActionListener(this::removeRow);
        borrowButton.addActionListener(this::borrow);

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
                                .addComponent(borrowButton))
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
                                .addComponent(borrowButton))
        );

        getContentPane().add(mainPanel);
    }

    private void closeWindow() {
        _libraryController.closeWindow();
        dispose();
    }

    private int getBookIdFromTable() throws NotSelectedRowException {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) model.getValueAt(selectedRow, 0);
        } else {
            throw new NotSelectedRowException();
        }
    }

    private void openSaveScreen(ActionEvent event) {
        try {
            int id = getBookIdFromTable();
            Book book = _libraryController.getBook(id);
            _libraryController.openBookEdit(book);
        } catch (NotSelectedRowException e) {
            Book book = null;
            _libraryController.openBookEdit(book);
        }
    }

    private void removeRow(ActionEvent event) {
        try {
            int id = getBookIdFromTable();
            Book book = _libraryController.getBook(id);
            _libraryController.deleteBook(book);
            updateTable();
        } catch (NotSelectedRowException e) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para excluir");
        }
    }

    private void borrow(ActionEvent event) {
        try {
            int id = getBookIdFromTable();
            Book book = _libraryController.getBook(id);
            _libraryController.openBookLending(book);
        } catch (NotSelectedRowException e) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para emprestar");
        }
    }

    @Override
    public void updateBookList() {
        updateTable();
    }

    private void searchBook(ActionEvent event) {
        updateTable();
    }

    private void updateTable() {
        model.setRowCount(0);

        String searchString = searchStringField.getText();
        List<Book> booksList = _libraryController.getBook(searchString);

        for (Book book : booksList) {
            String borrowText = book.getBorrowing() ? "Emprestado" : "Disponível";
            model.addRow(new Object[]{book.getId(), book.getName(), book.getAuthor(), book.getCategory(), book.getISBN(), borrowText});
        }
    }
}
