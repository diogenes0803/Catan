package server.facade;

import shared.communication.CredentialsParams;
import shared.model.IUser;


public interface IUserFacade {


    public IUser login(CredentialsParams creds);


    public IUser register(CredentialsParams creds);
}
