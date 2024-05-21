package presentation.model;

import presentation.contracts.IUserRepositoryListener;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryListener {
    private final List<IUserRepositoryListener> listeners = new ArrayList<>();

    public void subscribe(IUserRepositoryListener userListListener) {
        listeners.add(userListListener);
    }

    public void notifyDataChanged() {
        for(IUserRepositoryListener listener : listeners) {
            listener.updateUserList();
        }
    }
}
