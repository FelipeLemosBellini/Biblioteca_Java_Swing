package login.presentation;

import user.entities.AdministratorEntity;
import user.entities.EmployeeEntity;
import user.entities.UserEntity;
import user.entities.EProfileEntity;
import user.services.ICurrentUser;
import user.datasources.IUserRepository;
import utils.PresentationManager;

import java.util.Objects;

public class LoginController {
    private final PresentationManager _presentationManager;
    private final IUserRepository _userRepository;
    private final ICurrentUser _currentUser;
    
    public LoginController(
            PresentationManager presentationManager,
            IUserRepository userRepository,
            ICurrentUser currentUser
    ) 
    {
        _presentationManager = presentationManager;
        _userRepository = userRepository;
        _currentUser = currentUser;
    }
    
    public boolean tryMakeUserLogin(String login, String password){
        try {
            UserEntity userEntity = _userRepository.getUser(login);
            boolean userCanLogin = userEntity.verifyPassword(password);
            
            if(userCanLogin) {
                defineUserPermissions(userEntity);    
                
                _presentationManager.startHome();
                _presentationManager.closeWindow("Login");
                
                return true;
            }

            return false;
        }
        catch(Exception e){
            return false;
        }
    }
    
    private void defineUserPermissions(UserEntity userEntity){
        UserEntity currentUserEntity;
        
        if(Objects.equals(userEntity.getProfile(), EProfileEntity.admin))
            currentUserEntity = new AdministratorEntity(userEntity);
        else if(Objects.equals(userEntity.getProfile(), EProfileEntity.employee))
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
