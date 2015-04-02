package client.map.state;

import client.data.RobPlayerInfo;
import client.map.MapController;
import shared.definitions.PieceType;
import shared.locations.HexLocation;
import shared.model.*;

import java.util.Collection;


public class RobbingState extends MapState {
    private HexLocation m_newRobberLocation = null;
    private boolean m_startedRobbing = false;


    @Override
    public void robPlayer(RobPlayerInfo victim) throws ModelException {
        assert m_newRobberLocation != null;

        try {
            ServerModelFacade.getInstance().robPlayer(m_newRobberLocation, victim.getPlayerIndex());
        }
        finally {
            m_newRobberLocation = null;
        }
    }


    @Override
    public void placeRobber(MapController controller, HexLocation hexLoc) {
        assert hexLoc != null && GameModelFacade.instance().getGame().getMap().canPlaceRobber(hexLoc);

        m_newRobberLocation = hexLoc;
        Collection<IPlayer> players = GameModelFacade.instance().getRobbablePlayers(m_newRobberLocation);
        controller.getRobView().setPlayers(RobPlayerInfo.fromPlayers(players));
        controller.getRobView().showModal();
    }


    @Override
    public boolean canPlaceRobber(HexLocation hexLoc) {
        return GameModelFacade.instance().getGame().getMap().canPlaceRobber(hexLoc);
    }


    @Override
    public void initializeDialogs(MapController controller) {
        if (!m_startedRobbing) {
            m_startedRobbing = true;
            controller.startMove(PieceType.ROBBER);
        }
    }


    @Override
    public void startMove(MapController controller, PieceType pieceType) {
        assert pieceType == PieceType.ROBBER;
        // cannot cancel
        controller.getView().startDrop(PieceType.ROBBER, null, false);
    }
}
