package features.books.presentation.controllers;

import features.books.dataSources.IBookRepository;
import features.books.dataSources.IBookSubscriber;
import features.books.presentation.views.IBookEditView;
import features.books.presentation.views.IBookLoanView;
import features.books.presentation.views.IBooksView;
import infraestructure.IPresentationManager;
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
            _presentationManager.openWindow(IBookEditView.class, bookEntity);
    }

    public void openBookLoan(BookEntity bookEntity){
        _presentationManager.openWindow(IBookLoanView.class, bookEntity);
    }
    
    public void deleteBook(BookEntity bookEntity){ _bookRepository.removeBook(bookEntity);}
    
    public BookEntity getBook(int id){
        return _bookRepository.getBook(id);   
    }
    
    public List<BookEntity> getBook(String search){
        return _bookRepository.searchBooks(search);
    }

    public void closeWindow(){
        _presentationManager.closeWindow(IBooksView.class);
    }

    @Override
    public IPresentationManager getPresentationManager() {
        return _presentationManager;
    }
}
