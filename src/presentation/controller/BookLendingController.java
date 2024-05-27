package presentation.controller;

import core.entities.Book;
import infrastructure.interfaces.IBookRepository;
import presentation.PresentationManager;
import presentation.model.BookRepositoryListener;

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
    
    public boolean borrowBook(Book book, Date dateOfReturning){
        return _bookRepository.borrow(book, dateOfReturning);
    }
    
    public void returnBook(Book book){
        _bookRepository.returnTheBook(book);
    }
}
