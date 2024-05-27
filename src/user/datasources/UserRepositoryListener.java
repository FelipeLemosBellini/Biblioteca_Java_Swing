package user.datasources;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryListener {
    private final List<IUserListener> listeners = new ArrayList<>();

    public void subscribe(IUserListener userListListener) {
        listeners.add(userListListener);
    }

    public void notifyDataChanged() {
        for(IUserListener listener : listeners) {
            listener.updateUserList();
        }
    }
}
