package client.map.state;

import client.data.RobPlayerInfo;
import client.map.MapController;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.GameModelFacade;
import shared.model.IPlayer;
import shared.model.ModelException;
import shared.model.ServerModelFacade;

import java.util.Collection;


public class PlayingState extends MapState {
    boolean m_isPlayingRoadBuildingCard = false;
    EdgeLocation m_road1 = null;

    boolean m_isPlayingSoldier = false;
    HexLocation m_newRobberLocation = null;


    @Override
    public boolean canPlaceCity(VertexLocation vertLoc) {
        return GameModelFacade.instance().canPlaceCity(vertLoc);
    }


    @Override
    public void placeCity(VertexLocation vertLoc) throws ModelException {
        ServerModelFacade.getInstance().placeCity(vertLoc);
    }


    @Override
    public void startMove(MapController controller, PieceType pieceType) {
        // allowed to cancel
        controller.getView().startDrop(pieceType, GameModelFacade.instance().getLocalPlayer().getColor(), true);
    }


    @Override
    public boolean canPlaceRobber(HexLocation hexLoc) {
        assert m_newRobberLocation == null;
        return GameModelFacade.instance().getGame().getMap().canPlaceRobber(hexLoc);
    }


    @Override
    public void placeRobber(MapController controller, HexLocation hexLoc) {
        assert hexLoc != null && GameModelFacade.instance().getGame().getMap().canPlaceRobber(hexLoc);
        assert m_newRobberLocation == null;

        m_newRobberLocation = hexLoc;
        Collection<IPlayer> players = GameModelFacade.instance().getRobbablePlayers(m_newRobberLocation);
        controller.getRobView().setPlayers(RobPlayerInfo.fromPlayers(players));
        controller.getRobView().showModal();
    }


    @Override
    public void robPlayer(RobPlayerInfo victim) throws ModelException {
        assert m_newRobberLocation != null;

        try {
            ServerModelFacade.getInstance().playSoldier(m_newRobberLocation, victim.getPlayerIndex());
        }
        finally {
            m_newRobberLocation = null;
        }
    }


    @Override
    public void playSoldierCard(MapController controller) {
        assert m_isPlayingSoldier == false;
        assert GameModelFacade.instance().getLocalPlayer().canPlayDevCard(DevCardType.SOLDIER);

        m_isPlayingSoldier = true;

        controller.startMove(PieceType.ROBBER);
    }


    @Override
    public void playRoadBuildingCard(MapController controller) {
        assert m_isPlayingRoadBuildingCard == false : "Wait -- already was playing road building card!";
        assert GameModelFacade.instance().getLocalPlayer().canPlayDevCard(DevCardType.ROAD_BUILD);

        m_isPlayingRoadBuildingCard = true;
        controller.startMove(PieceType.ROAD);
    }


    @Override
    public boolean canPlaceRoad(EdgeLocation edgeLoc) {
        //if they are playing road build card && are on second road
        if (m_isPlayingRoadBuildingCard && m_road1 != null) {
            return GameModelFacade.instance().canPlayRoadBuilding(m_road1, edgeLoc);
        }
        else {
            return GameModelFacade.instance().canPlaceRoad(edgeLoc);
        }
    }


    @Override
    public void placeRoad(MapController controller, EdgeLocation edgeLoc) throws ModelException {
        if (m_isPlayingRoadBuildingCard) {
            if (m_road1 == null) {
                assert GameModelFacade.instance().canPlaceRoad(edgeLoc);

                // if the only the first road, set the local variable
                m_road1 = edgeLoc;
                // and temporarily add to the view
                controller.getView().placeRoad(m_road1, GameModelFacade.instance().getLocalColor());
                // immediately choose the next road
                controller.startMove(PieceType.ROAD);
            }
            else { // this is the second road
                // reset the first road location before the request in case of exceptions
                EdgeLocation firstRoad = m_road1;
                m_road1 = null;
                ServerModelFacade.getInstance().playRoadBuilding(firstRoad, edgeLoc);
            }
        }
        else {
            ServerModelFacade.getInstance().placeRoad(edgeLoc);
        }
    }


    @Override
    public void cancelMove(MapController controller) {
        if (m_isPlayingRoadBuildingCard) {
            m_isPlayingRoadBuildingCard = false;

            // if the road had been temporarily placed, unplace it
            if (m_road1 != null) {
                controller.getView().removeRoad(m_road1);
            }
            m_road1 = null;
        }

        m_isPlayingSoldier = false;
        m_newRobberLocation = null;
    }


    @Override
    public boolean canPlaceSettlement(VertexLocation vertLoc) {
        return GameModelFacade.instance().canPlaceSettlement(vertLoc);
    }


    @Override
    public void placeSettlement(VertexLocation vertLoc) throws ModelException {
        ServerModelFacade.getInstance().placeSettlement(vertLoc);
    }
}
