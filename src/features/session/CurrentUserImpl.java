package features.session;

import features.user.entities.UserEntity;

public class CurrentUserImpl implements ICurrentUser {
    private UserEntity userEntity;

    @Override
    public void setCurrentUser(UserEntity userEntity) throws Exception {
        if (this.userEntity != null)
            throw new Exception("Já existe usuário atual");
        
        if(userEntity == null)
            throw new Exception("Não é possivel atribuir um usuário nulo");
        
        this.userEntity = userEntity;
    }

    @Override
    public UserEntity getCurrentUser() {
        return this.userEntity;
    }

    @Override
    public void cleanUser() {
        userEntity = null;
    }
}
