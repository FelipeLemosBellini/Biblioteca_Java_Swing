package features.user.presentation;

import features.user.datasources.IUserListener;
import features.user.datasources.IUserSubscriber;
import features.user.datasources.IUserRepository;
import features.user.entities.UserEntity;
import infraestructure.PresentationManager;

import java.util.List;

public class UserManagementController {
    private final PresentationManager _presentationManager;
    private final IUserRepository _userRepository;
    private final IUserSubscriber _userSubscriber;

    public UserManagementController(
            PresentationManager presentationManager,
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
        return _userRepository.searchUsers(searchString);
    }
}
