package features.user.presentation.controllers;

import features.user.datasources.IUserListener;
import features.user.datasources.IUserSubscriber;
import features.user.datasources.IUserRepository;
import features.user.entities.UserEntity;
import features.user.presentation.views.IUserEditPasswordView;
import features.user.presentation.views.IUserEditView;
import features.user.presentation.views.IUsersView;
import infraestructure.IPresentationManager;

import java.util.List;

public class UsersController implements IUsersController {
    private final IPresentationManager _presentationManager;
    private final IUserRepository _userRepository;
    private final IUserSubscriber _userSubscriber;

    public UsersController(
            IPresentationManager presentationManager,
            IUserRepository userRepository,
            IUserSubscriber userSubscriber
    ) {
        _presentationManager = presentationManager;
        _userRepository = userRepository;
        _userSubscriber = userSubscriber;
    }

    public void addListener(IUserListener listener){
        _userSubscriber.subscribe(listener);
    }

    public void closeWindow(){
        _presentationManager.closeWindow(IUsersView.class);
    }

    public void openEditWindow(UserEntity userEntity){
        _presentationManager.openWindow(IUserEditView.class, userEntity);
    }

    public void openEditPassWindow(UserEntity userEntity){
        _presentationManager.openWindow(IUserEditPasswordView.class, userEntity);
    }

    public void deleteUser(UserEntity userEntity){
        _userRepository.removeUser(userEntity);
    }

    public UserEntity getUser(int id){
        return _userRepository.getUser(id);
    }

    public List<UserEntity> getUsers(String searchString){
        return _userRepository.searchUsers(searchString);
    }
}
