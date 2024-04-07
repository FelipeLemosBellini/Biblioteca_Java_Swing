package core.use_cases;

import core.entities.Book;
import core.enums.EBook;

import java.util.ArrayList;
import java.util.List;

public class BookUseCase {
    private int sequence = 0;
    private List<Book> listBooks = new ArrayList<>();

    public void create(String name, String author, EBook category, String ISBN) {
        this.sequence++;
        listBooks.add(new Book(getSequenceBooks(), name, author, category, ISBN));
    }

    public void delete(Book book) {
        this.listBooks.remove(book);
    }

    public void edit(Book oldBook, String name, String author, EBook category, String ISBN) {
        for (Book book : listBooks) {
            if (book.getId() == oldBook.getId()) {
                book.setName(name);
                book.setAuthor(author);
                book.setCategory(category);
                book.setISBN(ISBN);
                return;
            }
        }
    }

    public List<Book> getBooks() {
        return this.listBooks;
    }

    public Book getBook(int id) {
        for (Book book : listBooks) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public List<Book> searchBook(String name, String author, EBook category, String ISBN) {
        List<Book> search = new ArrayList<>();
        return search;
    }

    private int getSequenceBooks() {
        return sequence;
    }
}
