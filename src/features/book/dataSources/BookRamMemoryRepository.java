package features.book.dataSources;

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


    private int getSequenceBooks() {
        return sequence;
    }
}
