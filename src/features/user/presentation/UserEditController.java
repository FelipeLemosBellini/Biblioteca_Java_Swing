package features.user.presentation;

import features.user.datasources.IUserListener;
import features.user.datasources.UserObserverImpl;
import features.user.datasources.IUserRepository;
import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;
import infraestructure.PresentationManager;

public class UserEditController {
    private final IUserRepository _userRepository;
    private final UserObserverImpl _userObserverImpl;
    private final PresentationManager _presentationManager;

    public UserEditController(PresentationManager presentationManager, IUserRepository userRepository, UserObserverImpl userObserverImpl) {
        _userRepository = userRepository;
        _userObserverImpl = userObserverImpl;
        _presentationManager = presentationManager;
    }

    public void addListener(IUserListener listener) {
        _userObserverImpl.subscribe(listener);
    }

    public void closeWindow() {
        _userObserverImpl.notifyDataChanged();
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
