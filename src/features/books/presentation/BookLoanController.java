package features.books.presentation;

import features.books.dataSources.IBookNotifier;
import features.books.entities.BookEntity;
import features.loans.dataSources.IBookLoanRepository;

import java.util.Date;

public class BookLoanController {
    private final IBookNotifier _bookNotifier;
    private final IBookLoanRepository _bookLoanRepository;

    public BookLoanController(IBookNotifier bookNotifier, IBookLoanRepository bookloanRepository) {
        _bookNotifier = bookNotifier;
        _bookLoanRepository = bookloanRepository;
    }
    
    public void closeWindow(){
        _bookNotifier.notifyBookChanged();
    }
    
    public boolean borrowBook(BookEntity bookEntity, Date dateOfReturning){
        return _bookLoanRepository.borrow(bookEntity, dateOfReturning);
    }
    
    public void returnBook(BookEntity bookEntity){
        _bookLoanRepository.returnTheBook(bookEntity);
    }
}
