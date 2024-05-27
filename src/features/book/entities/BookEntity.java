package features.book.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Book")
public class BookEntity {
    @Id
    @GeneratedValue
    protected int id;
    
    @Column(name = "name")
    protected String name;
    
    @Column(name = "author")
    protected String author;
    
    @Column(name = "category")
    protected ECategoryEntity category;
    
    @Column(name = "ISBN")
    protected String ISBN;
    
    @Column(name = "borrowing")
    private Boolean borrowing = false;
    
    @Column(name = "dateOfBorrowing")
    private Date dateOfBorrowing;

    @Column(name = "dateOfReturning")
    private Date dateOfReturning;

    public BookEntity(int id, String name, String author, ECategoryEntity category, String ISBN) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.ISBN = ISBN;
    }

    public BookEntity() {
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

    public ECategoryEntity getCategory() {
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

    public void setBorrowing(Boolean borrowing) {
        this.borrowing = borrowing;
    }
    
    public void setDateOfBorrowing(Date dateOfBorrowing) {
        this.dateOfBorrowing = dateOfBorrowing;
    }
    
    public void setDateOfReturning(Date dateOfReturning) {
        this.dateOfReturning = dateOfReturning;
    }

    public void edit(String name, String author, ECategoryEntity category, String ISBN) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.ISBN = ISBN;
    }

//    public boolean borrow(Date dateOfReturning) {
//        if (borrowing)
//            return false;
//
//        borrowing = true;
//        this.dateOfReturning = dateOfReturning;
//
//        Calendar calendar = Calendar.getInstance();
//        this.dateOfBorrowing = calendar.getTime();
//
//        return true;
//    }
//
//    public void returnTheBook() {
//        borrowing = false;
//        dateOfBorrowing = null;
//        dateOfReturning = null;
//    }

    private String returnDate(Date date) {
        if (date != null)
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        else
            return "00/00/0000";
    }
}
