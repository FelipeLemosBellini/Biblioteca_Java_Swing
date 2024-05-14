package Presentation.Model;

import Presentation.Contracts.IBookListListener;
import core.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksListModel {
    private List<Book> bookList = new ArrayList<>();
    private final List<IBookListListener> listeners = new ArrayList<>();

    public void subscribe(IBookListListener bookListListener) {
        listeners.add(bookListListener);
    }

    private void notifyDataChanged() {
        for(IBookListListener listener : listeners) {
            listener.updateBookList();
        }
    }

    public List<Book> getBooksList() {
        return bookList;
    }
}
