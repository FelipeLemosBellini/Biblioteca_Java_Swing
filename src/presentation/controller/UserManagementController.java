package presentation.controller;

import core.entities.Book;
import core.entities.User;
import infrastructure.interfaces.IUserRepository;
import presentation.PresentationManager;
import presentation.contracts.IBookRepositoryListener;
import presentation.contracts.IUserRepositoryListener;
import presentation.model.UserRepositoryListener;

import java.util.List;

public class UserManagementController {
    private final PresentationManager _presentationManager;
    private final IUserRepository _userRepository;
    private final UserRepositoryListener _userRepositoryListener;

    public UserManagementController(
            PresentationManager presentationManager,
            IUserRepository userRepository,
            UserRepositoryListener userRepositoryListener
    ) {
        _presentationManager = presentationManager;
        _userRepository = userRepository;
        _userRepositoryListener = userRepositoryListener;
    }

    public void addListener(IUserRepositoryListener listener){
        _userRepositoryListener.subscribe(listener);
    }

    public void closeWindow(){
        _presentationManager.closeWindow("UserManagement");
    }

    public void openEditWindow(User user){
        _presentationManager.startUserEdit(user);
    }

    public void openEditPassWindow(User user){
        _presentationManager.startUserPasswordEdit(user);
    }

    public void deleteUser(User user){
        _userRepository.removeUser(user);
    }

    public User getUser(int id){
        return _userRepository.getUser(id);
    }

    public List<User> getUsers(String searchString){
        return _userRepository.searchUser(searchString);
    }
}
