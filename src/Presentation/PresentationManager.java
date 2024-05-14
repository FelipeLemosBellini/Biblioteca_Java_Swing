package Presentation;

import Presentation.Controller.BookEditController;
import Presentation.Controller.BookLendingController;
import Presentation.Controller.LibraryController;
import Presentation.View.BookEditView;
import Presentation.View.BookLendingView;
import Presentation.View.LibraryView;
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
