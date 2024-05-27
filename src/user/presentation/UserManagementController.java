package user.presentation;

import user.datasources.IUserListener;
import user.datasources.UserRepositoryListener;
import user.datasources.IUserRepository;
import user.entities.UserEntity;
import utils.PresentationManager;

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

    public void addListener(IUserListener listener){
        _userRepositoryListener.subscribe(listener);
    }

    public void closeWindow(){
        _presentationManager.closeWindow("UserManagement");
    }

    public void openEditWindow(UserEntity userEntity){
        _presentationManager.startUserEdit(userEntity);
    }

    public void openEditPassWindow(UserEntity userEntity){
        _presentationManager.startUserPasswordEdit(userEntity);
    }

    public void deleteUser(UserEntity userEntity){
        _userRepository.removeUser(userEntity);
    }

    public UserEntity getUser(int id){
        return _userRepository.getUser(id);
    }

    public List<UserEntity> getUsers(String searchString){
        return _userRepository.searchUser(searchString);
    }
}
