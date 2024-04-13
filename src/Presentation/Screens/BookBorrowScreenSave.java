package Presentation.Screens;

import core.entities.Book;
import core.enums.ECategory;
import core.use_cases.BookUseCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BookBorrowScreenSave extends JFrame {
    private final BookUseCase bookUseCase;

    private JTextField dateOfReturn;
    private JComboBox<String> comboBoxCategory;

    private Book currentBook = null;

    public BookBorrowScreenSave(BookUseCase bookUseCase, Book book) {
        this.bookUseCase = bookUseCase;
        this.currentBook = book;

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

        comboBoxCategory = new JComboBox<String>(new String[]{});
        for (ECategory ebook : ECategory.values()) {
            comboBoxCategory.addItem(ebook.toString());
        }

        if (currentBook != null){
            dateOfReturn = new JTextField(currentBook.getName(),15);
        }
        else{
            dateOfReturn = new JTextField(15);
        }

        JButton addButton = new JButton("Salvar");
        JButton removeButton = new JButton("Cancelar");

        addButton.addActionListener(this::borrowBook);

        PanelButtons.add(new JLabel("Data de Retorno do Livro:"));
        PanelButtons.add(dateOfReturn);

        PanelButtons.add(addButton);
        PanelButtons.add(removeButton);

        getContentPane().add(PanelButtons);
    }

    private void borrowBook(ActionEvent event) {
        try {
            String dateOfReturnText = dateOfReturn.getText();

            if (!dateOfReturnText.isEmpty()) {

//                currentBook.borrow()

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o livro");
        }
    }
}
