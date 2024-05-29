package features.books.presentation.controllers;

import features.books.dataSources.*;
import features.books.entities.BookEntity;
import features.books.entities.ECategoryEntity;
import infraestructure.IPresentationManager;
import infraestructure.PresentationManager;

public class BookEditController implements IBookEditController {
    private final IBookRepository _bookRepository;
    private final IBookNotifier _bookNotifier;
    private final IBookSubscriber _bookSubscriber;

    private final IPresentationManager _presentationManager;
    
    
    public BookEditController(IPresentationManager presentationManager, IBookRepository bookRepository, IBookNotifier bookNotifier, IBookSubscriber bookSubscriber) {
        _bookRepository = bookRepository;
        _bookNotifier = bookNotifier;
        _bookSubscriber = bookSubscriber;
        _presentationManager = presentationManager;
    }
    
    public void closeWindow(){
        _presentationManager.closeWindow("BookEdit");
        _bookNotifier.notifyBookChanged();
        
    }

    public void createBook(String name, String author, ECategoryEntity category, String isbn) {
        BookEntity newBookEntity = new BookEntity(0, name, author, category, isbn);
        _bookRepository.addBook(newBookEntity);
    }

    public void editBook(BookEntity bookEntity, String name, String author, ECategoryEntity category, String isbn) {
        bookEntity.edit(name, author, category, isbn);
        _bookRepository.editBook(bookEntity);
    }
}
