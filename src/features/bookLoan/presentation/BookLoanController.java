package features.bookLoan.presentation;

import features.book.dataSources.BookListener;
import features.book.dataSources.IBookListener;
import features.book.dataSources.IBookNotifier;
import features.book.dataSources.IBookRepository;
import features.book.entities.BookEntity;
import features.bookLoan.dataSources.IBookLoanRepository;

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
