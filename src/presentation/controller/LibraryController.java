package presentation.controller;

import infrastructure.interfaces.IBookRepository;
import presentation.PresentationManager;
import core.entities.Book;
import core.enums.ECategory;
import presentation.contracts.IBookRepositoryListener;
import presentation.model.BookRepositoryListener;

import java.util.List;

public class LibraryController {
    private final PresentationManager _presentationManager;
    private final IBookRepository _bookRepository;
    private final BookRepositoryListener _bookRepositoryListener;
    
    public LibraryController(
            PresentationManager presentationManager,
            IBookRepository bookRepository,
            BookRepositoryListener bookRepositoryListener
    ) {
        _presentationManager = presentationManager;
        _bookRepository = bookRepository;
        _bookRepositoryListener = bookRepositoryListener;
    }

    public void closeWindow(){
    }

    public void addListener(IBookRepositoryListener listener){
        _bookRepositoryListener.subscribe(listener);
    }

    public void openBookEdit(Book book){
            _presentationManager.startBookEdit(book);
    }

    public void openBookLending(Book book){
        _presentationManager.startBookLending(book);
    }
    
    
    public void deleteBook(Book book){
        _bookRepository.removeBook(book);
    }
    
    public Book getBook(int id){
        return _bookRepository.getBook(id);   
    }
    
    public List<Book> getBook(String name, String author, ECategory category, String isbn){
        return _bookRepository.searchBook(name.isEmpty() ? "" : name, author.isEmpty() ? "" : author, category, isbn.isEmpty() ? "" : isbn);
    }
}
