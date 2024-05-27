package features.user.datasources;

import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRamMemoryDAO implements IUserRepository {
    private int sequence = 0;
    private List<UserEntity> listOfUserEntities = new ArrayList<>();

    public UserRamMemoryDAO() {
        SeedList();
    }

    private void SeedList() {
        createUser(new UserEntity("admin", "admin", EProfileEntity.admin));
        createUser(new UserEntity("employee", "employee", EProfileEntity.employee));
    }

    @Override
    public void createUser(UserEntity userEntity) {
        userEntity.setId(getSequence());
        listOfUserEntities.add(userEntity);
    }

    public boolean changePassword(UserEntity userEntity, String oldPassword, String newPassword, String confirmNewPassword) {
        if (!userEntity.verifyPassword(oldPassword) || !newPassword.equals(confirmNewPassword))
            return false;

        userEntity.setPassword(newPassword);
            
        return true;
    }

    public boolean editUser(UserEntity userEntity, String login, EProfileEntity profile) {
        userEntity.setLogin(login);
        userEntity.setProfile(profile);
        
        return true;
    }

    @Override
    public void removeUser(UserEntity userEntity) {
        listOfUserEntities.removeIf(u -> Objects.equals(u.getId(), userEntity.getId()));
    }

    @Override
    public UserEntity getUser(int id) {
        for (UserEntity userEntity : listOfUserEntities) {
            if (Objects.equals(userEntity.getId(), id)) {
                return userEntity;
            }
        }
        return null;
    }

//    @Override
//    public UserEntity getUser(String login) {
//        for (UserEntity userEntity : listOfUserEntities) {
//            if (Objects.equals(userEntity.getLogin(), login)) {
//                return userEntity;
//            }
//        }
//        return null;
//    }

    @Override
    public List<UserEntity> searchUser(String searchString) {
        if (searchString == null || searchString.isEmpty())
            return listOfUserEntities;

        List<UserEntity> search = new ArrayList<>();
        String lowerCaseSearchString = searchString.toLowerCase();

        for (UserEntity userEntity : listOfUserEntities) {
            boolean matches =
                    userEntity.getLogin().toLowerCase().contains(lowerCaseSearchString) ||
                            userEntity.getProfile().name().toLowerCase().contains(lowerCaseSearchString);

            if (matches) {
                search.add(userEntity);
            }
        }

        return search;
    }

    private int getSequence() {
        this.sequence++;
        return this.sequence;
    }
}
