package client.map.state;

import client.map.MapController;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.GameModelFacade;
import shared.model.ModelException;
import shared.model.ServerModelFacade;


public abstract class SetupState extends MapState {
    private boolean m_startedPlaceRoad = false;


    @Override
    public void startMove(MapController controller, PieceType pieceType) {
        // cannot cancel
        controller.getView().startDrop(pieceType, GameModelFacade.instance().getLocalPlayer().getColor(), false);
    }


    @Override
    public boolean canPlaceRoad(EdgeLocation edgeLoc) {
        return GameModelFacade.instance().canPlaceRoad(edgeLoc);
    }


    @Override
    public void placeRoad(MapController controller, EdgeLocation edgeLoc) throws ModelException {
        ServerModelFacade.getInstance().placeRoad(edgeLoc);

        // place initial settlement
        controller.startMove(PieceType.SETTLEMENT);
    }


    @Override
    public void initializeDialogs(MapController controller) {
        // place initial road if the dialog is not already showing
        if (!m_startedPlaceRoad) {
            m_startedPlaceRoad = true;
            controller.startMove(PieceType.ROAD);
        }
    }


    @Override
    public boolean canPlaceSettlement(VertexLocation vertLoc) {
        return GameModelFacade.instance().canPlaceSettlement(vertLoc);
    }


    @Override
    public void placeSettlement(VertexLocation vertLoc) throws ModelException {
        ServerModelFacade.getInstance().placeSettlement(vertLoc);

        ServerModelFacade.getInstance().finishTurn();
    }
}
