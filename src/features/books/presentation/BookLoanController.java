package features.books.presentation;

import features.books.dataSources.IBookNotifier;
import features.books.dataSources.IBookRepository;
import features.books.entities.BookEntity;
import infraestructure.PresentationManager;

import java.util.Calendar;
import java.util.Date;

public class BookLoanController {
    private final IBookNotifier _bookNotifier;
    private final IBookRepository _bookRepository;
    private final PresentationManager _presentationManager;

    public BookLoanController(IBookNotifier bookNotifier, IBookRepository bookRepository, PresentationManager presentationManager) {
        _bookNotifier = bookNotifier;
        _bookRepository = bookRepository;
        _presentationManager = presentationManager;
    }
    
    public void closeWindow(){
        _presentationManager.closeWindow("BookLending");
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
