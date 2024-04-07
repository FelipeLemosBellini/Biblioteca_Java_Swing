package Presentation.Screens;

import core.enums.EBook;
import core.use_cases.BookUseCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import java.util.function.Function;

public class BookScreenSave  extends JFrame {
    private BookUseCase bookUseCase;

    private JTextField nameField;
    private JTextField authorField;
    private JTextField isbnField;
    private JComboBox<String> comboBoxCategory;


    public BookScreenSave(BookUseCase bookUseCase) {
        this.bookUseCase = bookUseCase;

        defineWindowConfiguration();
        defineMenuConfiguration();
    }

    private void defineWindowConfiguration(){
        setTitle("Gestão de livros");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    private void defineMenuConfiguration(){
        JPanel PanelButtons = new JPanel(new GridLayout(6, 2, 5, 5));

        nameField = new JTextField(15);
        authorField = new JTextField(15);
        isbnField = new JTextField(15);

        comboBoxCategory = new JComboBox<String>(new String[]{});
        for (EBook ebook : EBook.values()) {
            comboBoxCategory.addItem(ebook.toString());
        }

        JButton addButton = new JButton("Salvar");
        JButton removeButton = new JButton("Cancelar");

        addButton.addActionListener(this::addRow);

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

    private void addRow(ActionEvent e) {
        try {
            String name = nameField.getText();
            EBook category = EBook.action.getEBook((String) comboBoxCategory.getSelectedItem());
            String author = authorField.getText();
            String isbn = isbnField.getText();

            if (!name.isEmpty() && category != null && !author.isEmpty() && !isbn.isEmpty()) {
                bookUseCase.create(name, author, category, isbn);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o livro");
        }
    }
}