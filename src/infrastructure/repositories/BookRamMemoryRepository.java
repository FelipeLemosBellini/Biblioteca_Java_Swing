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
    public List<Book> searchBook(String searchString) {
        List<Book> search = new ArrayList<>();
        if (searchString == null || searchString.isEmpty()) {
            return listBooks; // Retorna todos os livros se a string de busca estiver vazia
        }

        String lowerCaseSearchString = searchString.toLowerCase();

        for (Book book : listBooks) {
            boolean matches =
                    book.getName().toLowerCase().contains(lowerCaseSearchString) ||
                            book.getAuthor().toLowerCase().contains(lowerCaseSearchString) ||
                            book.getCategory().name().toLowerCase().contains(lowerCaseSearchString) ||
                            book.getISBN().toLowerCase().contains(lowerCaseSearchString);

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
