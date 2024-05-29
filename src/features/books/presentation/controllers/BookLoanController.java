package features.books.presentation.controllers;

import features.books.dataSources.IBookNotifier;
import features.books.dataSources.IBookRepository;
import features.books.entities.BookEntity;
import features.books.presentation.views.IBookLoanView;
import infraestructure.IPresentationManager;
import infraestructure.PresentationManager;

import java.util.Calendar;
import java.util.Date;

public class BookLoanController implements IBookLoanController {
    private final IBookNotifier _bookNotifier;
    private final IBookRepository _bookRepository;
    private final IPresentationManager _presentationManager;

    public BookLoanController(IBookNotifier bookNotifier, IBookRepository bookRepository, IPresentationManager presentationManager) {
        _bookNotifier = bookNotifier;
        _bookRepository = bookRepository;
        _presentationManager = presentationManager;
    }
    
    public void closeWindow(){
        _presentationManager.closeWindow(IBookLoanView.class);
        _bookNotifier.notifyBookChanged();
    }
    
    public boolean borrowBook(BookEntity bookEntity, Date dateOfReturning){
        Calendar calendar = Calendar.getInstance();
        bookEntity.editLoan(true, calendar.getTime(), dateOfReturning);
        _bookRepository.editBook(bookEntity);
        
        return true;
    }
    
    public void returnBook(BookEntity bookEntity){
        bookEntity.editLoan(false, null, null);
        _bookRepository.editBook(bookEntity);
    }
}
