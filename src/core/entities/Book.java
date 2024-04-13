package core.entities;

import core.enums.ECategory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book {
    protected int id;
    protected String name;
    protected String author;
    protected ECategory category;
    protected String ISBN;
    private Boolean borrowing = false;
    private Date dateOfBorrowing = new Date();
    private Date dateOfReturning = new Date();

    public Book(int id, String name, String author, ECategory category, String ISBN) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.ISBN = ISBN;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public ECategory getCategory() {
        return category;
    }

    public Boolean getBorrowing() {
        return borrowing;
    }

    public String getDateOfBorrowingToString() {
        return returnDate(dateOfBorrowing);
    }

    public String getDateOfReturningToString() {
        return returnDate(dateOfReturning);
    }

    public void edit(String name, String author, ECategory category, String ISBN) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.ISBN = ISBN;
    }

    public boolean borrow(Date dateOfReturning) {
        if (borrowing)
            return false;

        borrowing = true;
        this.dateOfReturning = dateOfReturning;

        Calendar calendar = Calendar.getInstance();
        this.dateOfBorrowing = calendar.getTime();

        return true;
    }

    public void returnTheBook() {
        borrowing = false;
        dateOfBorrowing = null;
        dateOfReturning = null;
    }

    private String returnDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
