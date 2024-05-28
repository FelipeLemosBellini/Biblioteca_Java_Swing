package features.bookLoan.dataSources;

import features.book.entities.BookEntity;

import java.util.Date;

public interface IBookLoanDao {
    void updateToBorrow(BookEntity bookEntity, Date dateOfReturning);
    void updateToUnborrowed(BookEntity bookEntity);
}
