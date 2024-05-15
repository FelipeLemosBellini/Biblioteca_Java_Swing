package infrastructure.repositories;

import core.entities.Book;
import core.enums.ECategory;
import infrastructure.interfaces.IBookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookRamMemoryRepository implements IBookRepository {
    private int sequence = 0;
    private List<Book> listBooks = new ArrayList<>();

    
    @Override
    public void addBook(Book book) {
        this.sequence++;
        listBooks.add(new Book(getSequenceBooks(), book.getName(), book.getAuthor(), book.getCategory(), book.getISBN()));
    }

    @Override
    public void removeBook(Book book) {
        this.listBooks.remove(book);
    }

    @Override
    public Book getBook(int id) {
        for (Book book : listBooks) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
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
