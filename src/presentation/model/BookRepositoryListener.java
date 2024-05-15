package presentation.model;

import presentation.contracts.IBookRepositoryListener;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryListener {
    private final List<IBookRepositoryListener> listeners = new ArrayList<>();

    public void subscribe(IBookRepositoryListener bookListListener) {
        listeners.add(bookListListener);
    }

    public void notifyDataChanged() {
        for(IBookRepositoryListener listener : listeners) {
            listener.updateBookList();
        }
    }
}
