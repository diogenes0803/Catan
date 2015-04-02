package server.handler;

import shared.communication.IGameParams;
import shared.model.IGame;

import java.io.IOException;


public abstract class AbstractInGameHandler<ReqType extends IGameParams, FacadeType> extends AbstractHandler<ReqType, IGame, FacadeType> {

    public AbstractInGameHandler(Class<ReqType> reqTypeClass, FacadeType facade) {
        super(reqTypeClass, facade);
    }


    @Override
    protected void processRequestCookies(CookieJar cookies, ReqType requestData) throws IOException, MissingCookieException {
        boolean foundUser = false;
        boolean foundGame = false;

        for (Cookie cookie : cookies) {
            if (cookie.nameIs("catan.user")) {
                requestData.setUserId(readUserID(cookie.getValue()));
                foundUser = true;
            }
            else if (cookie.nameIs("catan.game")) {
                requestData.setGameId(Integer.parseInt(cookie.getValue()));
                foundGame = true;
            }
        }

        if (!foundUser) {
            throw new MissingCookieException("The user cookie is not set.");
        }
        else if (!foundGame) {
            throw new MissingCookieException("The game cookie is not set.");
        }
    }
}

