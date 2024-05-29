package features.books.dataSources;

import java.util.ArrayList;
import java.util.List;

public class BookObserverImpl implements IBookSubscriber, IBookNotifier {
    private final List<IBookListener> listeners = new ArrayList<>();

    public void subscribe(IBookListener bookListListener) {
        listeners.add(bookListListener);
    }

    public void notifyBookChanged() {
        for(IBookListener listener : listeners) {
            listener.updateBooksChanged();
        }
    }
}
