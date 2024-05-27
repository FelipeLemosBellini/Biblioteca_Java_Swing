package presentation.controller;

import core.entities.Administrator;
import core.entities.Employee;
import core.entities.User;
import core.enums.EProfile;
import core.interfaces.ICurrentUser;
import infrastructure.interfaces.IUserRepository;
import presentation.PresentationManager;

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
            User user = _userRepository.getUser(login);
            boolean userCanLogin = user.verifyPassword(password);
            
            if(userCanLogin) {
                defineUserPermissions(user);    
                
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
    
    private void defineUserPermissions(User user){
        User currentUser;
        
        if(Objects.equals(user.getProfile(), EProfile.admin))
            currentUser = new Administrator(user);
        else if(Objects.equals(user.getProfile(), EProfile.employee))
            currentUser = new Employee(user);
        else
            currentUser = user;

        try {
            _currentUser.setCurrentUser(currentUser);
        } catch (Exception e) {
            _presentationManager.startInformationWindow("Usuário já autenticado!");
        }
    }
}
