package features.library.presentation;

import features.book.dataSources.IBookSubscriber;
import features.library.datasources.ILibraryRepository;
import infraestructure.PresentationManager;
import features.book.entities.BookEntity;
import features.book.dataSources.IBookListener;
import features.book.dataSources.BookListener;

import java.util.List;

public class LibraryController {
    private final PresentationManager _presentationManager;
    private final ILibraryRepository _libraryRepository;
    private final IBookSubscriber _bookSubscriber;
    
    public LibraryController(
            PresentationManager presentationManager,
            ILibraryRepository libraryRepository,
            IBookSubscriber bookSubscriber
    ) {
        _presentationManager = presentationManager;
        _libraryRepository = libraryRepository;
        _bookSubscriber = bookSubscriber;
    }

    public void addListener(IBookListener listener){
        _bookSubscriber.subscribe(listener);
    }

    public void openBookEdit(BookEntity bookEntity){
            _presentationManager.startBookEdit(bookEntity);
    }

    public void openBookLending(BookEntity bookEntity){
        _presentationManager.startBookLending(bookEntity);
    }
    
    
//    public void deleteBook(BookEntity bookEntity){
//        _libraryRepository.removeBook(bookEntity);
//    }
    
    public BookEntity getBook(int id){
        return _libraryRepository.getBook(id);   
    }
    
    public List<BookEntity> getBook(String search){
        return _libraryRepository.searchBooks(search);
    }
}
