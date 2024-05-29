package infraestructure;

import features.books.entities.BookEntity;
import features.user.entities.UserEntity;

public interface IPresentationManager {
    void closeWindow(Class<?> viewClass);
    void openWindow(Class<?> viewClass, Object... parameters);
}
