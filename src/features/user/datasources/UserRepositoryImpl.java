package features.user.datasources;

import features.books.dataSources.IBookDao;
import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;

import java.util.List;

public class UserRepositoryImpl implements IUserRepository {
    private IUserDao _userDao;

    public UserRepositoryImpl(IUserDao userDao) {
        _userDao = userDao;
    }
    
    @Override
    public void addUser(UserEntity userEntity) {
        _userDao.createUser(userEntity);
    }

    @Override
    public void editUser(UserEntity userEntity) {
        _userDao.updateUser(userEntity);
    }

    @Override
    public void removeUser(UserEntity userEntity) {
        _userDao.deleteUser(userEntity);
    }

    @Override
    public UserEntity getUser(int id) {
        return _userDao.readUser(id);
    }

    @Override
    public List<UserEntity> searchUsers(String searchString) {
        return _userDao.searchUsers(searchString);
    }
}
