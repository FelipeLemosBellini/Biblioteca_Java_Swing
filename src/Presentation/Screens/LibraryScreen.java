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
        comboBoxCategory = new JComboBox<String>(new String[]{});

        JButton addButton = new JButton("Adicionar");
        JButton searchButton = new JButton("Pesquisar");
        JButton removeButton = new JButton("Excluir");
        JButton seeButton = new JButton("Ver Livro");

        addButton.addActionListener(this::OpenSaveScreen);
        removeButton.addActionListener(this::removeRow);
        seeButton.addActionListener(this::seeRow);

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

    private void OpenSaveScreen(ActionEvent e) {

        BookScreenSave bookScreenSave = new BookScreenSave(bookUseCase);

        bookScreenSave.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Window closed");
                UpdateTable();
            }
        });
    }

    private void removeRow(ActionEvent e) {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada != -1) {
            int id = (int) model.getValueAt(linhaSelecionada, 0);
            var book = bookUseCase.getBook(id);
            bookUseCase.delete(book);
            UpdateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
        }
    }

    private void seeRow(ActionEvent e) {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada != -1) {
            long id = (long) model.getValueAt(linhaSelecionada, 0);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para ver o livro.");
        }
    }

    private void searchBook(ActionEvent event) {
        String name = nameField.getText();
        EBook category = EBook.action.getEBook((String) comboBoxCategory.getSelectedItem());
        String author = authorField.getText();
        String isbn = isbnField.getText();

        if (!name.isEmpty() || category != null || !author.isEmpty() || !isbn.isEmpty()) {
            model.setNumRows(0);
            List<Book> booksList = bookUseCase.searchBook("", "", category, "");
            for (Book book : booksList) {
                model.addRow(new Object[]{book.getId(), book.getName(), book.getAuthor(), book.getCategory(), book.getISBN()});
            }
        } else {
            UpdateTable();
        }
    }

    private void UpdateTable() {
        model.setNumRows(0);
        List<Book> booksList = bookUseCase.getBooks();
        for (Book book : booksList) {
            model.addRow(new Object[]{book.getId(), book.getName(), book.getAuthor(), book.getCategory(), book.getISBN()});
        }
    }
}
