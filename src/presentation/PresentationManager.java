package presentation;

import presentation.controller.BookEditController;
import presentation.controller.BookLendingController;
import presentation.controller.LibraryController;
import presentation.view.BookEditView;
import presentation.view.BookLendingView;
import presentation.view.LibraryView;
import core.entities.Book;
import core.use_cases.BookUseCase;

public class PresentationManager {
    private BookUseCase _bookUseCase;
    
    public PresentationManager() {
        _bookUseCase = new BookUseCase();
        
        StartLibrary();
    }
    
    private void StartLibrary(){
        var controller = new LibraryController(this, _bookUseCase);
        new LibraryView(controller);
    }

    public void StartBookEdit(Book book){
        var controller = new BookEditController(this);
        new BookEditView(controller, book);
    }

    public void StartBookLending(Book book){
        var controller = new BookLendingController(this);
        new BookLendingView(controller, book);
    }
}
