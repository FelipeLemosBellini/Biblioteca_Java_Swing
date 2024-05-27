package core.services;

import core.entities.User;
import core.interfaces.ICurrentUser;

public class CurrentUserImpl implements ICurrentUser {
    private User user;

    @Override
    public void setCurrentUser(User user) throws Exception {
        if (this.user != null)
            throw new Exception("Já existe usuário atual");
        
        if(user == null)
            throw new Exception("Não é possivel atribuir um usuário nulo");
        
        this.user = user;
    }

    @Override
    public User getCurrentUser() {
        return this.user;
    }
}
