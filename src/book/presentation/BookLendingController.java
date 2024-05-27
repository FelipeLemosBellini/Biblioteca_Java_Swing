package book.presentation;

import book.datasources.BookRepositoryListener;
import book.datasources.IBookRepository;
import book.entities.BookEntity;

import java.util.Date;

public class BookLendingController {
    private final BookRepositoryListener _bookRepositoryListener;
    private final IBookRepository _bookRepository;

    public BookLendingController(BookRepositoryListener bookRepositoryListener, IBookRepository bookRepository) {
        _bookRepositoryListener = bookRepositoryListener;
        _bookRepository = bookRepository;
    }
    
    public void closeWindow(){
        _bookRepositoryListener.notifyDataChanged();
    }
    
    public boolean borrowBook(BookEntity bookEntity, Date dateOfReturning){
        return _bookRepository.borrow(bookEntity, dateOfReturning);
    }
    
    public void returnBook(BookEntity bookEntity){
        _bookRepository.returnTheBook(bookEntity);
    }
}
