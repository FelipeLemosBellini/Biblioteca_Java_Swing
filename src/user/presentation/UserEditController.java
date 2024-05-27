package user.presentation;

import user.datasources.IUserListener;
import user.datasources.UserRepositoryListener;
import user.datasources.IUserRepository;
import user.entities.EProfileEntity;
import user.entities.UserEntity;
import utils.PresentationManager;

public class UserEditController {
    private final IUserRepository _userRepository;
    private final UserRepositoryListener _userRepositoryListener;
    private final PresentationManager _presentationManager;

    public UserEditController(PresentationManager presentationManager, IUserRepository userRepository, UserRepositoryListener userRepositoryListener) {
        _userRepository = userRepository;
        _userRepositoryListener = userRepositoryListener;
        _presentationManager = presentationManager;
    }

    public void addListener(IUserListener listener) {
        _userRepositoryListener.subscribe(listener);
    }

    public void closeWindow() {
        _userRepositoryListener.notifyDataChanged();
        _presentationManager.closeWindow("UserEdit");
    }

    public void createUser(String login, String password, EProfileEntity profile) {
        UserEntity newUserEntity = new UserEntity(login, password, profile);
        _userRepository.createUser(newUserEntity);
    }

    public void editUser(UserEntity userEntity, String login, EProfileEntity profile) {
        _userRepository.editUser(userEntity, login, profile);
    }
}
