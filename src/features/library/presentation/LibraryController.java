package features.library.presentation;

import features.library.datasources.ILibraryRepository;
import infraestructure.PresentationManager;
import features.book.entities.BookEntity;
import features.book.datasources.IBookRepositoryListener;
import features.book.datasources.BookRepositoryListener;

import java.util.List;

public class LibraryController {
    private final PresentationManager _presentationManager;
    private final ILibraryRepository _libraryRepository;
    private final BookRepositoryListener _bookRepositoryListener;
    
    public LibraryController(
            PresentationManager presentationManager,
            ILibraryRepository libraryRepository,
            BookRepositoryListener bookRepositoryListener
    ) {
        _presentationManager = presentationManager;
        _libraryRepository = libraryRepository;
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
