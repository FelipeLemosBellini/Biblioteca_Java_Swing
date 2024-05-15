package presentation.controller;

import core.entities.Book;
import core.enums.ECategory;
import infrastructure.interfaces.IBookRepository;
import presentation.contracts.IBookRepositoryListener;
import presentation.model.BookRepositoryListener;

public class BookEditController {
    private final IBookRepository _bookRepository;
    private final BookRepositoryListener _bookRepositoryListener;

    public BookEditController(IBookRepository bookRepository, BookRepositoryListener bookRepositoryListener) {
        _bookRepository = bookRepository;
        _bookRepositoryListener = bookRepositoryListener;
        
    }
    
    public void addListener(IBookRepositoryListener listener){
        _bookRepositoryListener.subscribe(listener);
    }

    public void closeWindow(){
        _bookRepositoryListener.notifyDataChanged();
    }

    public void createBook(String name, String author, ECategory category, String isbn) {
        Book newBook = new Book(0, name, author, category, isbn);
        _bookRepository.addBook(newBook);
    }
}
