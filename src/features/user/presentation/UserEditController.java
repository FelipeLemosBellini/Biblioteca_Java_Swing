package features.user.presentation;

import features.user.datasources.*;
import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;
import infraestructure.PresentationManager;

public class UserEditController {
    private final IUserRepository _userRepository;
    private final IUserNotifier _userNotifier;
    private final IUserSubscriber _userSubscriber;
    private final PresentationManager _presentationManager;

    public UserEditController(PresentationManager presentationManager, IUserRepository userRepository, IUserNotifier userNotifier, IUserSubscriber userSubscriber) {
        _userRepository = userRepository;
        _userNotifier = userNotifier;
        _userSubscriber = userSubscriber;
        _presentationManager = presentationManager;
    }

    public void addListener(IUserListener listener) {
        _userSubscriber.subscribe(listener);
    }

    public void closeWindow() {
        _userNotifier.notifyUserChanged();
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
