package presentation.view;

import presentation.contracts.IBookRepositoryListener;
import presentation.controller.LibraryController;
import presentation.exceptions.NotSelectedRowException;
import core.entities.Book;
import core.enums.ECategory;
import presentation.model.BookRepositoryListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LibraryView extends JFrame implements IBookRepositoryListener {
    private final LibraryController _libraryController;

    private JTable table;
    private DefaultTableModel model;

    private JTextField nameField;
    private JTextField authorField;
    private JTextField isbnField;
    private JComboBox<String> comboBoxCategory;

    public LibraryView(LibraryController libraryController) {
        _libraryController = libraryController;
        _libraryController.addListener(this);

        defineWindowConfiguration();
        setVisible(true);
    }

    private void defineWindowConfiguration() {
        setTitle("Gestão de livros");
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        defineTableConfiguration();
        defineMenuConfiguration();
    }
    private void defineTableConfiguration() {
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
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
    private void defineMenuConfiguration() {
        JPanel PanelButtons = new JPanel(new GridLayout(6, 2, 5, 5));

        nameField = new JTextField(15);
        authorField = new JTextField(15);
        isbnField = new JTextField(15);
        isbnField = new JTextField(15);
        comboBoxCategory = new JComboBox<String>(new String[]{});
        comboBoxCategory.addItem("");
        for (ECategory ebook : ECategory.values()) {
            comboBoxCategory.addItem(ebook.toString());
        }

        JButton addButton = new JButton("Adicionar/Editar");
        JButton searchButton = new JButton("Pesquisar");
        JButton removeButton = new JButton("Excluir");
        JButton borrowButton = new JButton("Empréstimo de livro");

        //TODO: mover metodos para a model
        addButton.addActionListener(this::openSaveScreen);
        searchButton.addActionListener(this::searchBook);
        removeButton.addActionListener(this::removeRow);
        borrowButton.addActionListener(this::borrow);

        PanelButtons.add(new JLabel("Nome:"));
        PanelButtons.add(nameField);

        PanelButtons.add(new JLabel("Autor"));
        PanelButtons.add(authorField);

        PanelButtons.add(new JLabel("Categoria"));
        PanelButtons.add(comboBoxCategory);

        PanelButtons.add(new JLabel("ISBN"));
        PanelButtons.add(isbnField);

        PanelButtons.add(addButton);
        PanelButtons.add(searchButton);
        PanelButtons.add(removeButton);
        PanelButtons.add(borrowButton);

        getContentPane().add(PanelButtons, BorderLayout.SOUTH);
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
            _libraryController.openBookEdit(null);
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
        model.setNumRows(0);

        String name = nameField.getText();
        ECategory category = ECategory.action.getECategory((String) comboBoxCategory.getSelectedItem());
        String author = authorField.getText();
        String isbn = isbnField.getText();

        List<Book> booksList = _libraryController.getBook(name, author, category, isbn);

        for (Book book : booksList) {
            var borrowText = book.getBorrowing() ? "Emprestado" : "Disponível";
            model.addRow(new Object[]{book.getId(), book.getName(), book.getAuthor(), book.getCategory(), book.getISBN(), borrowText});
        }
    }
}
