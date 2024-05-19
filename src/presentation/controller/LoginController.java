package presentation.controller;

import core.entities.Administrator;
import core.entities.Employee;
import core.entities.User;
import core.enums.EProfile;
import infrastructure.interfaces.IUserRepository;
import presentation.PresentationManager;

public class LoginController {
    private final PresentationManager _presentationManager;
    private final IUserRepository _userRepository;
    
    public LoginController(
            PresentationManager presentationManager,
            IUserRepository userRepository
    ) 
    {
        _presentationManager = presentationManager;
        _userRepository = userRepository;
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
        
        if(user.getProfile() == EProfile.admin)
            currentUser = new Administrator(user);
        else if(user.getProfile() == EProfile.employee)
            currentUser = new Employee(user);
        else
            currentUser = user;
        
        _presentationManager.setCurrentUser(currentUser);
    }
}
