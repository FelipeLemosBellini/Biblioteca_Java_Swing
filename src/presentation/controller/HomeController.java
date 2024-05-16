package presentation.controller;

import infrastructure.interfaces.IBookRepository;
import presentation.PresentationManager;
import presentation.model.BookRepositoryListener;

public class HomeController {
    private final PresentationManager _presentationManager;

    public HomeController(
            PresentationManager presentationManager
    ) {
        _presentationManager = presentationManager;
    }
}
