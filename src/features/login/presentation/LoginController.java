package features.login.presentation;

import features.login.datasources.ILoginRepository;
import features.user.entities.AdministratorEntity;
import features.user.entities.EmployeeEntity;
import features.user.entities.UserEntity;
import features.user.entities.EProfileEntity;
import features.currentUser.ICurrentUser;
import infraestructure.PresentationManager;

import java.util.Objects;

public class LoginController {
    private final PresentationManager _presentationManager;
    private final ILoginRepository _loginRepository;
    private final ICurrentUser _currentUser;

    public LoginController(
            PresentationManager presentationManager,
            ILoginRepository loginRepository,
            ICurrentUser currentUser
    ) {
        _presentationManager = presentationManager;
        _loginRepository = loginRepository;
        _currentUser = currentUser;
    }

    public boolean tryMakeUserLogin(String login, String password) {
        try {
            boolean userCanLogin = _loginRepository.verifyUserLogin(login, password);

            if (userCanLogin) {
                UserEntity user = _loginRepository.getUser(login);
                defineUserPermissions(user);

                _presentationManager.startHome();
                _presentationManager.closeWindow("Login");

                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private void defineUserPermissions(UserEntity userEntity) {
        UserEntity currentUserEntity;

        if (Objects.equals(userEntity.getProfile(), EProfileEntity.admin))
            currentUserEntity = new AdministratorEntity(userEntity);
        else if (Objects.equals(userEntity.getProfile(), EProfileEntity.employee))
            currentUserEntity = new EmployeeEntity(userEntity);
        else
            currentUserEntity = userEntity;

        try {
            _currentUser.setCurrentUser(currentUserEntity);
        } catch (Exception e) {
            _presentationManager.startInformationWindow("Usuário já autenticado!");
        }
    }
}
