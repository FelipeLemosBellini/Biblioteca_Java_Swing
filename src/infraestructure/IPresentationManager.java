package infraestructure;

import features.books.entities.BookEntity;
import features.user.entities.UserEntity;

public interface IPresentationManager {
    void startLogin();
    void startHome();
    void startLibrary();
    void startUserManagement();
    void startUserEdit(UserEntity userEntity);
    void startUserPasswordEdit(UserEntity userEntity);
    void startBookEdit(BookEntity bookEntity);
    void startBookLending(BookEntity bookEntity);
    void startInformationWindow(String message);
    void closeWindow(String windowName);
}
