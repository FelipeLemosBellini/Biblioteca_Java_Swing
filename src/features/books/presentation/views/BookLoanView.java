package features.books.presentation.views;

import features.books.entities.BookEntity;
import features.books.presentation.controllers.IBookLoanController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookLoanView extends JFrame implements IBookLoanView {
    private final IBookLoanController _bookLoanController;

    private JFormattedTextField dateOfReturn;
    private JFormattedTextField dateOfBorrow;
    private JTextField borrow;
    private BookEntity currentBookEntity = null;

    public BookLoanView(IBookLoanController bookLoanController, BookEntity bookEntity) {
        _bookLoanController = bookLoanController;
        this.currentBookEntity = bookEntity;

        defineWindowConfiguration();
        defineMenuConfiguration();
    }

    private void defineWindowConfiguration() {
        setTitle("Gestão de livros");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        setVisible(true);
    }
    private void defineMenuConfiguration() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(5, 5, 5, 5)));

        try {
            MaskFormatter formatter = new MaskFormatter("##/##/####");
            dateOfReturn = new JFormattedTextField(formatter);
            dateOfBorrow = new JFormattedTextField(formatter);

            dateOfBorrow.setEditable(false);

            if (currentBookEntity.getDateOfReturningToString() != null) {
                dateOfReturn.setValue(currentBookEntity.getDateOfReturningToString());
            }

            if (currentBookEntity.getDateOfBorrowingToString() != null) {
                dateOfBorrow.setValue(currentBookEntity.getDateOfBorrowingToString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            dateOfBorrow = new JFormattedTextField();
            dateOfReturn = new JFormattedTextField();
        }

        borrow = new JTextField(currentBookEntity.getBorrowing() ? "Emprestado" : "Disponível", 15);
        borrow.setEditable(false);

        JButton returnBookButton = new JButton("Devolver");
        JButton borrowButton = new JButton("Emprestar");
        JButton cancelButton = new JButton("Cancelar");

        returnBookButton.addActionListener(this::returnBook);
        borrowButton.addActionListener(this::borrowBook);
        cancelButton.addActionListener(this::closeWindow);

        formPanel.add(new JLabel("Status do livro:"));
        formPanel.add(borrow);

        formPanel.add(new JLabel("Data de Empréstimo:"));
        formPanel.add(dateOfBorrow);

        formPanel.add(new JLabel("Data de Retorno do Livro:"));
        formPanel.add(dateOfReturn);

        formPanel.add(new JLabel("Retornar livro para a prateleira:"));
        formPanel.add(returnBookButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(borrowButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private void closeWindow(ActionEvent event) {
        closeWindow();
    }
    private void closeWindow() {
        _bookLoanController.closeWindow();
        dispose();
    }

    private void returnBook(ActionEvent event) {
        if (currentBookEntity.getBorrowing()) {
            _bookLoanController.returnBook(currentBookEntity);
            closeWindow();
        } else {
            JOptionPane.showMessageDialog(this, "O Livro não está emprestado!");
        }
    }
    private void borrowBook(ActionEvent event) {
        try {
            String dateOfReturnText = dateOfReturn.getText();

            if (!dateOfReturnText.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date receivedDate = sdf.parse(dateOfReturnText);
                
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                Date currentDate = cal.getTime();
                
                Date finalDate;

                if (receivedDate.before(currentDate)) {
                    cal.setTime(currentDate);
                    cal.add(Calendar.DAY_OF_MONTH, 7);
                    
                    finalDate = cal.getTime();
                } else {
                    finalDate = receivedDate;
                }

                boolean wasBorrow = _bookLoanController.borrowBook(currentBookEntity, finalDate);
                
                if (wasBorrow)
                    closeWindow();
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
