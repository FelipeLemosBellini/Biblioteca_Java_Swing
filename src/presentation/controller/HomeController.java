package presentation.controller;

import presentation.PresentationManager;

public class HomeController {
    private final PresentationManager _presentationManager;

    public HomeController(
            PresentationManager presentationManager
    ) {
        _presentationManager = presentationManager;
    }
}
