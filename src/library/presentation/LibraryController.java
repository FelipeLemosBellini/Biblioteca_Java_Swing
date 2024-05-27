package library.presentation;

import book.datasources.IBookRepository;
import utils.PresentationManager;
import book.entities.BookEntity;
import book.datasources.IBookRepositoryListener;
import book.datasources.BookRepositoryListener;

import java.util.List;

public class LibraryController {
    private final PresentationManager _presentationManager;
    private final IBookRepository _bookRepository;
    private final BookRepositoryListener _bookRepositoryListener;
    
    public LibraryController(
            PresentationManager presentationManager,
            IBookRepository bookRepository,
            BookRepositoryListener bookRepositoryListener
    ) {
        _presentationManager = presentationManager;
        _bookRepository = bookRepository;
        _bookRepositoryListener = bookRepositoryListener;
    }

    public void addListener(IBookRepositoryListener listener){
        _bookRepositoryListener.subscribe(listener);
    }

    public void openBookEdit(BookEntity bookEntity){
            _presentationManager.startBookEdit(bookEntity);
    }

    public void openBookLending(BookEntity bookEntity){
        _presentationManager.startBookLending(bookEntity);
    }
    
    
    public void deleteBook(BookEntity bookEntity){
        _bookRepository.removeBook(bookEntity);
    }
    
    public BookEntity getBook(int id){
        return _bookRepository.getBook(id);   
    }
    
    public List<BookEntity> getBook(String searchString){
        return _bookRepository.searchBook(searchString);
    }
}
