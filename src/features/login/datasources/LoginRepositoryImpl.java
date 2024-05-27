package features.login.datasources;

import features.user.entities.UserEntity;

public class LoginRepositoryImpl implements ILoginRepository {
    private ILoginDao _loginDao;
    
    public LoginRepositoryImpl(ILoginDao loginDAO) {
        _loginDao = loginDAO;
    }
    
    @Override
    public boolean verifyUserLogin(String username, String password) {
        return _loginDao.verifyUserLogin(username, password);
    }

    @Override
    public UserEntity getUser(String login) {
        return _loginDao.readUser(login);
    }
}
