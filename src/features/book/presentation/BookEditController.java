package features.book.presentation;

import features.book.datasources.BookRepositoryListener;
import features.book.datasources.IBookRepository;
import features.book.datasources.IBookRepositoryListener;
import features.book.entities.BookEntity;
import features.book.entities.ECategoryEntity;

public class BookEditController {
    private final IBookRepository _bookRepository;
    private final BookRepositoryListener _bookRepositoryListener;

    public BookEditController(IBookRepository bookRepository, BookRepositoryListener bookRepositoryListener) {
        _bookRepository = bookRepository;
        _bookRepositoryListener = bookRepositoryListener;
        
    }
    
    public void addListener(IBookRepositoryListener listener){
        _bookRepositoryListener.subscribe(listener);
    }

    public void closeWindow(){
        _bookRepositoryListener.notifyDataChanged();
    }

    public void createBook(String name, String author, ECategoryEntity category, String isbn) {
        BookEntity newBookEntity = new BookEntity(0, name, author, category, isbn);
        _bookRepository.addBook(newBookEntity);
    }
}
