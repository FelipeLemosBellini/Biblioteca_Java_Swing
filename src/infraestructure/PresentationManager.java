package infraestructure;

import construtionSet.dependencyInjection.implementations.ServiceLocatorImpl;
import features.books.presentation.views.*;
import features.login.presentation.ILoginView;
import features.menu.IMenuView;
import features.messageInformer.IInformationView;
import features.user.entities.UserEntity;
import features.session.ICurrentUser;
import features.permissions.AdminPermissions;

import features.books.entities.BookEntity;
import features.user.presentation.views.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class PresentationManager implements IPresentationManager {
    private final Map<String, JFrame> openWindows;

    public PresentationManager() {
        openWindows = new HashMap<>();
    }

    public void openWindow(Class<?> viewClass, Object... parameters) {
        var key = viewClass.getSimpleName();
        
        if (!openWindows.containsKey(key)) {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            var frame = (JFrame) serviceLocator.getService(viewClass, parameters);
            
            openWindows.put(key, frame);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    closeWindow(key);
                }
            });
        } else {
            var frame = openWindows.get(key);
            frame.toFront();
            frame.requestFocus();
        }
    }

    public void closeWindow(Class<?> viewClass) {
        var key = viewClass.getSimpleName();
        closeWindow(key);
    }

    private void closeWindow(String key) {
        JFrame window = openWindows.get(key);
        if (window != null) {
            window.dispose();
            openWindows.remove(key);
        }
    }
}