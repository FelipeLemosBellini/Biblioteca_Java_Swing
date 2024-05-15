package presentation.controller;

import presentation.PresentationManager;
import presentation.model.BookRepositoryListener;

public class BookLendingController {
    private final BookRepositoryListener _bookRepositoryListener;

    public BookLendingController(BookRepositoryListener bookRepositoryListener) {
        _bookRepositoryListener = bookRepositoryListener;
    }
    
    public void closeWindow(){
        _bookRepositoryListener.notifyDataChanged();
    }
}
