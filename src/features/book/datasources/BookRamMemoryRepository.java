package features.book.datasources;

import features.book.entities.BookEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookRamMemoryRepository implements IBookRepository {
    private int sequence = 0;
    private List<BookEntity> listBookEntities = new ArrayList<>();

    @Override
    public void addBook(BookEntity bookEntity) {
        this.sequence++;
        listBookEntities.add(new BookEntity(getSequenceBooks(), bookEntity.getName(), bookEntity.getAuthor(), bookEntity.getCategory(), bookEntity.getISBN()));
    }

    @Override
    public void removeBook(BookEntity bookEntity) {
        this.listBookEntities.remove(bookEntity);
    }

    @Override
    public BookEntity getBook(int id) {
        for (BookEntity bookEntity : listBookEntities) {
            if (bookEntity.getId() == id) {
                return bookEntity;
            }
        }
        return null;
    }

    @Override
    public List<BookEntity> searchBook(String searchString) {
        List<BookEntity> search = new ArrayList<>();
        if (searchString == null || searchString.isEmpty()) {
            return listBookEntities; // Retorna todos os livros se a string de busca estiver vazia
        }

        String lowerCaseSearchString = searchString.toLowerCase();

        for (BookEntity bookEntity : listBookEntities) {
            boolean matches =
                    bookEntity.getName().toLowerCase().contains(lowerCaseSearchString) ||
                            bookEntity.getAuthor().toLowerCase().contains(lowerCaseSearchString) ||
                            bookEntity.getCategory().name().toLowerCase().contains(lowerCaseSearchString) ||
                            bookEntity.getISBN().toLowerCase().contains(lowerCaseSearchString);

            if (matches) {
                search.add(bookEntity);
            }
        }
        return search;
    }

    @Override
    public boolean borrow(BookEntity bookEntity, Date dateOfReturning) {
        if (bookEntity.getBorrowing())
            return false;

        bookEntity.setBorrowing(true);
        bookEntity.setDateOfReturning(dateOfReturning);

        Calendar calendar = Calendar.getInstance();
        
        bookEntity.setDateOfReturning(calendar.getTime());

        return true;
    }

    @Override
    public void returnTheBook(BookEntity bookEntity) {
        bookEntity.setBorrowing(false);
        bookEntity.setDateOfBorrowing(null);
        bookEntity.setDateOfReturning(null);
    }

    private int getSequenceBooks() {
        return sequence;
    }
}
