package features.books.presentation;

import features.books.dataSources.IBookNotifier;
import features.books.dataSources.IBookRepository;
import features.books.entities.BookEntity;

import java.util.Date;

public class BookLoanController {
    private final IBookNotifier _bookNotifier;
    private final IBookRepository _bookRepository;

    public BookLoanController(IBookNotifier bookNotifier, IBookRepository bookRepository) {
        _bookNotifier = bookNotifier;
        _bookRepository = bookRepository;
    }
    
    public void closeWindow(){
        _bookNotifier.notifyBookChanged();
    }
    
    public boolean borrowBook(BookEntity bookEntity, Date dateOfReturning){
        return _bookRepository.updateToBorrow(bookEntity, dateOfReturning);
    }
    
    public void returnBook(BookEntity bookEntity){
        _bookRepository.updateToUnborrowed(bookEntity);
    }
}
