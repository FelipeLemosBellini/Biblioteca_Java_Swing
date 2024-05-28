package features.bookLoan.dataSources;

import features.book.entities.BookEntity;

import java.util.Date;

public class BookLoanDaoHibernateImpl implements IBookLoanDao{
    @Override
    public void updateToBorrow(BookEntity bookEntity, Date dateOfReturning) {
        
    }

    @Override
    public void updateToUnborrowed(BookEntity bookEntity) {

    }
}
