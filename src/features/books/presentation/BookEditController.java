package features.books.presentation;

import features.books.dataSources.*;
import features.books.entities.BookEntity;
import features.books.entities.ECategoryEntity;
import infraestructure.PresentationManager;

public class BookEditController {
    private final IBookRepository _bookRepository;
    private final IBookNotifier _bookNotifier;
    private final IBookSubscriber _bookSubscriber;

    private final PresentationManager _presentationManager;
    
    
    public BookEditController(PresentationManager presentationManager, IBookRepository bookRepository, IBookNotifier bookNotifier, IBookSubscriber bookSubscriber) {
        _bookRepository = bookRepository;
        _bookNotifier = bookNotifier;
        _bookSubscriber = bookSubscriber;
        _presentationManager = presentationManager;
    }
    
    public void addListener(IBookListener listener){
        _bookSubscriber.subscribe(listener);
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
