package features.books.presentation.views;

import features.books.presentation.controllers.IBooksController;
import infraestructure.IPresentationManager;
import features.books.dataSources.IBookListener;
import features.commonViewComponents.NotSelectedRowException;
import features.books.entities.BookEntity;
import features.commonViewComponents.MenuBarComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class BooksView extends JFrame implements IBookListener, IBooksView {
    private final IBooksController _booksController;

    private JTable table;
    private DefaultTableModel model;
    private JTextField searchStringField;

    public BooksView(IBooksController booksController) {
        _booksController = booksController;
        _booksController.addListener(this);

        initComponents();
        updateTable();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Gestão de livros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        defineMenuBar();
        defineLayout();
        
        setLocationRelativeTo(null);
    }

    private void defineMenuBar() {
        JMenuBar menuBar = MenuBarComponent.createMenuBar(
                this,
                _booksController.getPresentationManager(),
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
        _booksController.closeWindow();
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
            BookEntity bookEntity = _booksController.getBook(id);
            _booksController.openBookEdit(bookEntity);
        } catch (NotSelectedRowException e) {
            BookEntity bookEntity = null;
            _booksController.openBookEdit(bookEntity);
        }
    }

    private void removeRow(ActionEvent event) {
        try {
            int id = getBookIdFromTable();
            BookEntity bookEntity = _booksController.getBook(id);
            _booksController.deleteBook(bookEntity);
            updateTable();
        } catch (NotSelectedRowException e) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para excluir");
        }
    }

    private void borrow(ActionEvent event) {
        try {
            int id = getBookIdFromTable();
            BookEntity bookEntity = _booksController.getBook(id);
            _booksController.openBookLoan(bookEntity);
        } catch (NotSelectedRowException e) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para emprestar");
        }
    }

    @Override
    public void updateBooksChanged() {
        updateTable();
    }

    private void searchBook(ActionEvent event) {
        updateTable();
    }

    private void updateTable() {
        model.setRowCount(0);

        String searchString = searchStringField.getText();
        List<BookEntity> booksList = _booksController.getBook(searchString);

        for (BookEntity bookEntity : booksList) {
            String borrowText = bookEntity.getBorrowing() ? "Emprestado" : "Disponível";
            model.addRow(new Object[]{bookEntity.getId(), bookEntity.getName(), bookEntity.getAuthor(), bookEntity.getCategory(), bookEntity.getISBN(), borrowText});
        }
    }
}
