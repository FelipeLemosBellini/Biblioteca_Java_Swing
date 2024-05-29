package features.books.presentation.views;

import features.books.entities.BookEntity;
import features.books.entities.ECategoryEntity;
import features.books.presentation.controllers.BookEditController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BookEditView extends JFrame {
    private final BookEditController _bookEditController;

    private JTextField nameField;
    private JTextField authorField;
    private JTextField isbnField;
    private JComboBox<String> comboBoxCategory;

    private final BookEntity currentBookEntity;

    public BookEditView(BookEditController bookEditController, BookEntity bookEntity) {
        _bookEditController = bookEditController;
        this.currentBookEntity = bookEntity;

        defineWindowConfiguration();
        defineMenuConfiguration();
    }

    private void defineWindowConfiguration() {
        setTitle("Gestão de livros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void defineMenuConfiguration() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(5, 5, 5, 5)));

        comboBoxCategory = new JComboBox<>();
        for (ECategoryEntity ebook : ECategoryEntity.values()) {
            comboBoxCategory.addItem(ebook.toString());
        }

        if (currentBookEntity != null) {
            nameField = new JTextField(currentBookEntity.getName(), 15);
            authorField = new JTextField(currentBookEntity.getAuthor(), 15);
            isbnField = new JTextField(currentBookEntity.getISBN(), 15);

            comboBoxCategory.setSelectedItem(currentBookEntity.getCategory().toString());
        } else {
            nameField = new JTextField(15);
            authorField = new JTextField(15);
            isbnField = new JTextField(15);
        }

        JButton saveButton = new JButton("Salvar");
        JButton cancelButton = new JButton("Cancelar");

        saveButton.addActionListener(this::saveRow);
        cancelButton.addActionListener(this::closeWindow);

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Autor:"));
        formPanel.add(authorField);

        formPanel.add(new JLabel("Categoria:"));
        formPanel.add(comboBoxCategory);

        formPanel.add(new JLabel("ISBN:"));
        formPanel.add(isbnField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }


    private void closeWindow(ActionEvent event) {
        closeWindow();
    }
    private void closeWindow() {
        _bookEditController.closeWindow();
    }

    private void saveRow(ActionEvent event) {
        try {
            String name = nameField.getText();
            ECategoryEntity category = ECategoryEntity.action.getECategory((String) comboBoxCategory.getSelectedItem());
            String author = authorField.getText();
            String isbn = isbnField.getText();

            if (!name.isEmpty() && category != null && !author.isEmpty() && !isbn.isEmpty()) {

                if(currentBookEntity == null) {
                    _bookEditController.createBook(name, author, category, isbn);
                }
                else
                    _bookEditController.editBook(currentBookEntity, name, author, category, isbn);

                closeWindow();
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o livro");
        }
    }
}
