package features.bookLoan.dataSources;

import features.book.entities.BookEntity;

import java.util.Date;

public interface IBookLoanRepository {
    boolean borrow(BookEntity bookEntity, Date dateOfReturning);
    void returnTheBook(BookEntity bookEntity);
}
