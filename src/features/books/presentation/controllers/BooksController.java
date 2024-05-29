package features.books.presentation.controllers;

import features.books.dataSources.IBookRepository;
import features.books.dataSources.IBookSubscriber;
import infraestructure.IPresentationManager;
import infraestructure.PresentationManager;
import features.books.entities.BookEntity;
import features.books.dataSources.IBookListener;

import java.util.List;

public class BooksController implements IBooksController {
    private final IPresentationManager _presentationManager;
    private final IBookRepository _bookRepository;
    private final IBookSubscriber _bookSubscriber;
    
    public BooksController(
            IPresentationManager presentationManager,
            IBookRepository bookRepository,
            IBookSubscriber bookSubscriber
    ) {
        _presentationManager = presentationManager;
        _bookRepository = bookRepository;
        _bookSubscriber = bookSubscriber;
    }

    public void addListener(IBookListener listener){
        _bookSubscriber.subscribe(listener);
    }

    public void openBookEdit(BookEntity bookEntity){
            _presentationManager.startBookEdit(bookEntity);
    }

    public void openBookLending(BookEntity bookEntity){
        _presentationManager.startBookLending(bookEntity);
    }
    
    public void deleteBook(BookEntity bookEntity){ _bookRepository.removeBook(bookEntity);}
    
    public BookEntity getBook(int id){
        return _bookRepository.getBook(id);   
    }
    
    public List<BookEntity> getBook(String search){
        return _bookRepository.searchBooks(search);
    }
}
