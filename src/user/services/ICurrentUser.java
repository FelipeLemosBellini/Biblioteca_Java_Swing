package user.services;

import user.entities.UserEntity;

public interface ICurrentUser {
    void setCurrentUser(UserEntity userEntity) throws Exception;
    UserEntity getCurrentUser();
}
