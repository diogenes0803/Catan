package server.handler;

import server.facade.IMovesFacade;
import shared.communication.IGameParams;


public abstract class AbstractMovesHandler<ReqType extends IGameParams> extends AbstractInGameHandler<ReqType, IMovesFacade> {

    public AbstractMovesHandler(Class<ReqType> reqTypeClass, IMovesFacade facade) {
        super(reqTypeClass, facade);
    }
}
