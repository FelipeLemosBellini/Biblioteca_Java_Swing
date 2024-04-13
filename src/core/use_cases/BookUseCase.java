package core.use_cases;

import core.entities.Book;
import core.enums.ECategory;

import java.util.ArrayList;
import java.util.List;

public class BookUseCase {
    private int sequence = 0;
    private List<Book> listBooks = new ArrayList<>();

    public void create(String name, String author, ECategory category, String ISBN) {
        this.sequence++;
        listBooks.add(new Book(getSequenceBooks(), name, author, category, ISBN));
    }

    public void delete(Book book) {
        this.listBooks.remove(book);
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

    public List<Book> searchBook(String name, String author, ECategory category, String ISBN) {
        List<Book> search = new ArrayList<>();
        for (Book book : listBooks) {
            boolean matches = true;

            if (name != null && !name.isEmpty()) {
                matches &= book.getName().toLowerCase().contains(name.toLowerCase());
            }

            if (author != null && !author.isEmpty()) {
                matches &= book.getAuthor().toLowerCase().contains(author.toLowerCase());
            }

            if (category != null) {
                matches &= book.getCategory() == category;
            }

            if (ISBN != null && !ISBN.isEmpty()) {
                matches &= book.getISBN().toLowerCase().contains(ISBN.toLowerCase());
            }

            if (matches) {
                search.add(book);
            }
        }
        return search;
    }

    private int getSequenceBooks() {
        return sequence;
    }
}
