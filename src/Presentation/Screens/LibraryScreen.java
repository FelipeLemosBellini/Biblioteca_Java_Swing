package Presentation.Screens;

import core.entities.Book;
import core.enums.EBook;
import core.use_cases.BookUseCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

public class LibraryScreen extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField categoryField;
    private JTextField nameField;
    private JTextField authorField;
    private JTextField isbnField;
    private JComboBox<String> comboBoxCategory;

    private BookUseCase bookUseCase = new BookUseCase();

    public LibraryScreen() {
        defineWindowConfiguration();

        setVisible(true);
    }

    private void defineWindowConfiguration() {
        setTitle("Gestão de livros");
        setSize(1200, 780);
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
        model.addColumn("Está emprestado");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void defineMenuConfiguration() {
        JPanel PanelButtons = new JPanel(new GridLayout(6, 2, 5, 5));

        categoryField = new JTextField(15);
        nameField = new JTextField(15);
        authorField = new JTextField(15);
        isbnField = new JTextField(15);
        isbnField = new JTextField(15);
        comboBoxCategory = new JComboBox<String>(new String[]{});
        comboBoxCategory.addItem("");
        for (EBook ebook : EBook.values()) {
            comboBoxCategory.addItem(ebook.toString());
        }

        JButton addButton = new JButton("Adicionar");
        JButton searchButton = new JButton("Pesquisar");
        JButton removeButton = new JButton("Excluir");
        JButton seeButton = new JButton("Ver Livro");
        JButton borrowButton = new JButton("Emprestar livro");

        addButton.addActionListener(this::openSaveScreen);
        searchButton.addActionListener(this::searchBook);
        removeButton.addActionListener(this::removeRow);
        seeButton.addActionListener(this::openSaveScreen);
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
        PanelButtons.add(seeButton);

        getContentPane().add(PanelButtons, BorderLayout.SOUTH);
    }

    private void openSaveScreen(ActionEvent event) {

        Book book = null;

        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            book = bookUseCase.getBook(id);
        }

        BookScreenSave bookScreenSave = new BookScreenSave(bookUseCase, book);

        bookScreenSave.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                updateTable();
            }
        });
    }

    private void removeRow(ActionEvent event) {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada != -1) {
            int id = (int) model.getValueAt(linhaSelecionada, 0);
            var book = bookUseCase.getBook(id);
            bookUseCase.delete(book);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
        }
    }

    private void searchBook(ActionEvent event) {
        String name = nameField.getText();
        EBook category = EBook.action.getEBook((String) comboBoxCategory.getSelectedItem());
        String author = authorField.getText();
        String isbn = isbnField.getText();
        if (!name.isEmpty() || category != null || !author.isEmpty() || !isbn.isEmpty()) {
            List<Book> booksList = bookUseCase.searchBook(name.isEmpty() ? "" : name, author.isEmpty() ? "" : author, category, isbn.isEmpty() ? "" : isbn);
            if (!booksList.isEmpty()) {
                model.setNumRows(0);
                for (Book book : booksList) {
                    model.addRow(new Object[]{book.getId(), book.getName(), book.getAuthor(), book.getCategory(), book.getISBN()});
                }
            }
        } else {
            updateTable();
        }
    }

    private void borrow(ActionEvent event) {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada != -1) {
            int id = (int) model.getValueAt(linhaSelecionada, 0);
            Book book = bookUseCase.getBook(id);

//             bookBorrowScreenSave = new BookBorrowScreenSave(book);
//            bookBorrowScreenSave.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    updateTable();
//                }
//            });

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um livro para emprestar.");
        }
    }

    private void updateTable() {
        model.setNumRows(0);
        List<Book> booksList = bookUseCase.getBooks();
        for (Book book : booksList) {
            model.addRow(new Object[]{book.getId(), book.getName(), book.getAuthor(), book.getCategory(), book.getISBN(), book.getBorrowing()});
        }
    }
}
