package Presentation.Screens;

import core.entities.Book;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookBorrowScreenSave extends JFrame {
    private JFormattedTextField dateOfReturn;
    private JFormattedTextField dateOfBorrow;
    private JTextField borrow;
    private Book currentBook = null;

    public BookBorrowScreenSave(Book book) {
        this.currentBook = book;

        defineWindowConfiguration();
        defineMenuConfiguration();
    }

    private void defineWindowConfiguration() {
        setTitle("Gestão de livros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    private void defineMenuConfiguration() {
        JPanel PanelButtons = new JPanel(new GridLayout(5, 2, 5, 5));

        var borrowText = currentBook.getBorrowing() ? "Emprestado" : "Disponível";
        borrow = new JTextField(borrowText,15);
        borrow.setEditable(false);
        var stringDateOfReturn = currentBook.getDateOfReturningToString();
        var stringdateOfBorrow = currentBook.getDateOfBorrowingToString();


        try {
            MaskFormatter formatter = new MaskFormatter("##/##/####");
            dateOfReturn = new JFormattedTextField(formatter);
            dateOfBorrow = new JFormattedTextField(formatter);

            dateOfBorrow.setEditable(false);

            if (stringDateOfReturn != null && !stringDateOfReturn.isEmpty()) {
                dateOfReturn.setValue(stringDateOfReturn);
            }

            if (stringdateOfBorrow != null && !stringdateOfBorrow.isEmpty()) {
                dateOfBorrow.setValue(stringdateOfBorrow);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            dateOfBorrow = new JFormattedTextField();
            dateOfReturn = new JFormattedTextField();
        }
        JButton returnBookButton = new JButton("Devolver");

        JButton addButton = new JButton("Emprestar");
        JButton removeButton = new JButton("Cancelar");

        addButton.addActionListener(this::borrowBook);
        returnBookButton.addActionListener(this::returnBook);
        removeButton.addActionListener(this::closeWindow);

        PanelButtons.add(new JLabel("Status do livro:"));
        PanelButtons.add(borrow);

        PanelButtons.add(new JLabel("Data de Emprestimo:"));
        PanelButtons.add(dateOfBorrow);

        PanelButtons.add(new JLabel("Data de Retorno do Livro:"));
        PanelButtons.add(dateOfReturn);

        PanelButtons.add(new JLabel("Retorna o livro para a prateleira:"));
        PanelButtons.add(returnBookButton);

        PanelButtons.add(addButton);
        PanelButtons.add(removeButton);

        getContentPane().add(PanelButtons);
    }

    private void closeWindow(ActionEvent event) {
        dispose();
    }

    private void returnBook(ActionEvent event){
        if(currentBook.getBorrowing()){
            currentBook.returnTheBook();
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "O Livro não está emprestado!");
        }
    }

    private void borrowBook(ActionEvent event) {
        try {
            String dateOfReturnText = dateOfReturn.getText();

            if (!dateOfReturnText.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date data = dateFormat.parse(dateOfReturnText);

                var wasBorrow = currentBook.borrow(data);

                if(wasBorrow)
                    dispose();
                else
                    JOptionPane.showMessageDialog(this, "O Livro nao esta disponivel");
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o livro");
        }
    }
}
