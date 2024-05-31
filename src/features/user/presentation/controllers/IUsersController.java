package features.user.presentation.controllers;

import features.user.datasources.IUserListener;
import features.user.entities.UserEntity;
import infraestructure.IPresentationManager;
import infraestructure.PresentationManager;

import java.util.List;

public interface IUsersController {
    void deleteUser(UserEntity userEntity);
    void openEditPassWindow(UserEntity userEntity);
    void openEditWindow(UserEntity userEntity);
    void addListener(IUserListener listener);
    void closeWindow();
    UserEntity getUser(int id);
    List<UserEntity> getUsers(String search);
    IPresentationManager getPresentationManager();
}
