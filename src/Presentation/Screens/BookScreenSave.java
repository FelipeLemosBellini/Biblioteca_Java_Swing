package Presentation.Screens;

import core.entities.Book;
import core.enums.ECategory;
import core.use_cases.BookUseCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BookScreenSave  extends JFrame {
    private final BookUseCase bookUseCase;

    private JTextField nameField;
    private JTextField authorField;
    private JTextField isbnField;
    private JComboBox<String> comboBoxCategory;

    private Book currentBook = null;

    public BookScreenSave(BookUseCase bookUseCase, Book book) {
        this.bookUseCase = bookUseCase;
        this.currentBook = book;

        defineWindowConfiguration();
        defineMenuConfiguration();
    }

    private void defineWindowConfiguration(){
        setTitle("Gestão de livros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    private void defineMenuConfiguration(){
        JPanel PanelButtons = new JPanel(new GridLayout(5, 2, 5, 5));

        comboBoxCategory = new JComboBox<String>(new String[]{});
        for (ECategory ebook : ECategory.values()) {
            comboBoxCategory.addItem(ebook.toString());
        }

        if (currentBook != null){
            nameField = new JTextField(currentBook.getName(),15);
            authorField = new JTextField(currentBook.getAuthor(),15);
            isbnField = new JTextField(currentBook.getISBN(),15);

            comboBoxCategory.setSelectedItem(currentBook.getCategory().toString());
        }
        else{
            nameField = new JTextField(15);
            authorField = new JTextField(15);
            isbnField = new JTextField(15);
        }

        JButton addButton = new JButton("Salvar");
        JButton removeButton = new JButton("Cancelar");

        addButton.addActionListener(this::saveRow);
        removeButton.addActionListener(this::closeWindow);

        PanelButtons.add(new JLabel("Nome:"));
        PanelButtons.add(nameField);

        PanelButtons.add(new JLabel("Autor"));
        PanelButtons.add(authorField);

        PanelButtons.add(new JLabel("Categoria"));
        PanelButtons.add(comboBoxCategory);

    PanelButtons.add(new JLabel("ISBN"));
        PanelButtons.add(isbnField);

        PanelButtons.add(addButton);
        PanelButtons.add(removeButton);

        getContentPane().add(PanelButtons);
    }

    private void closeWindow(ActionEvent event) {
        dispose();
    }

    private void saveRow(ActionEvent event) {
        try {
            String name = nameField.getText();
            ECategory category = ECategory.action.getECategory((String) comboBoxCategory.getSelectedItem());
            String author = authorField.getText();
            String isbn = isbnField.getText();

            if (!name.isEmpty() && category != null && !author.isEmpty() && !isbn.isEmpty()) {

                if(currentBook == null)
                    bookUseCase.create(name, author, category, isbn);
                else
                    currentBook.edit(name, author, category, isbn);

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o livro");
        }
    }
}
