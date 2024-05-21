package core.entities;

import core.enums.ECategory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue
    protected int id;
    protected String name;
    protected String author;
    protected ECategory category;
    protected String ISBN;
    private Boolean borrowing = false;
    private Date dateOfBorrowing;
    private Date dateOfReturning;

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
        if(date != null)
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        else
            return "00/00/0000";
    }
}
