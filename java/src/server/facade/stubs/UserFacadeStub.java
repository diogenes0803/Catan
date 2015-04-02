package server.facade.stubs;

import server.facade.IUserFacade;
import shared.communication.CredentialsParams;
import shared.model.IUser;


public class UserFacadeStub implements IUserFacade {

    @Override
    public IUser login(CredentialsParams creds) {
        return null;
    }


    @Override
    public IUser register(CredentialsParams creds) {
        return null;
    }
}
