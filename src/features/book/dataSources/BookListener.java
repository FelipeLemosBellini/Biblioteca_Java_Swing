package features.book.dataSources;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BookListener implements IBookSubscriber, IBookNotifier {
    private final List<IBookListener> listeners = new ArrayList<>();

    public void subscribe(IBookListener bookListListener) {
        listeners.add(bookListListener);
    }

    public void notifyBookChanged() {
        for(IBookListener listener : listeners) {
            listener.notifyBookChanged();
        }
    }
}
