package features.bookLoan.dataSources;

import features.book.entities.BookEntity;

import java.util.Date;

public class BookLoanRepositoryImpl implements IBookLoanRepository {
    @Override
    public boolean borrow(BookEntity bookEntity, Date dateOfReturning) {
        return false;
    }

    @Override
    public void returnTheBook(BookEntity bookEntity) {

    }
}
