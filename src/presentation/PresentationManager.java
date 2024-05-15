package presentation;

import infrastructure.interfaces.IBookRepository;
import infrastructure.repositories.BookRamMemoryRepository;
import presentation.controller.BookEditController;
import presentation.controller.BookLendingController;
import presentation.controller.LibraryController;
import presentation.model.BookRepositoryListener;
import presentation.view.BookEditView;
import presentation.view.BookLendingView;
import presentation.view.LibraryView;
import core.entities.Book;

public class PresentationManager {
    private final BookRepositoryListener _bookRepositoryListener;
    private final IBookRepository _bookRepository;
    
    public PresentationManager() {
        _bookRepository = new BookRamMemoryRepository();
        _bookRepositoryListener = new BookRepositoryListener();
        
        StartLibrary();
    }
    
    private void StartLibrary(){
        var controller = new LibraryController(this, _bookRepository, _bookRepositoryListener);
        new LibraryView(controller);
    }

    public void StartBookEdit(Book book){
        var controller = new BookEditController(_bookRepository, _bookRepositoryListener);
        new BookEditView(controller, book);
    }

    public void StartBookLending(Book book){
        var controller = new BookLendingController(this);
        new BookLendingView(controller, book);
    }
}
