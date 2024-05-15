package presentation.controller;

import presentation.PresentationManager;
import core.entities.Book;
import core.enums.ECategory;
import core.use_cases.BookUseCase;

import java.util.List;

public class LibraryController {
    private final PresentationManager _presentationManager;
    private final BookUseCase _bookUseCase;
    
    public LibraryController(
            PresentationManager presentationManager,
            BookUseCase bookUseCase
    ) {
        _presentationManager = presentationManager;
        _bookUseCase = bookUseCase;
    }
    
    public void openBookEdit(Book book){
        _presentationManager.StartBookEdit(book);
    }

    public void openBookLending(Book book){
        _presentationManager.StartBookLending(book);
    }
    
    
    public boolean deleteBook(Book book){
        _bookUseCase.delete(book);
        return true;
    }
    
    public Book getBook(int id){
        return _bookUseCase.getBook(id);   
    }
    
    public List<Book> getBook(String name, String author, ECategory category, String isbn){
        return _bookUseCase.searchBook(name.isEmpty() ? "" : name, author.isEmpty() ? "" : author, category, isbn.isEmpty() ? "" : isbn);
    }
}
