package client.map.state;

import client.data.RobPlayerInfo;
import client.map.MapController;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.GameModelFacade;
import shared.model.ModelException;

import java.util.logging.Logger;


public abstract class MapState implements IMapState {
    private final static Logger logger = Logger.getLogger("catan");

    public static MapState determineState() {
        if (GameModelFacade.instance().getGame().isNotInitialized() || !GameModelFacade.instance().getGame().gameHasStarted() || !GameModelFacade.instance().isLocalPlayersTurn()) {
            logger.finer("MapState is NotPlaying.");
            return new NotPlayingState();
        }
        else { // isLocalPlayersTurn()
            switch (GameModelFacade.instance().getGame().getGameState()) {
                case Playing:
                    logger.finer("MapState is Playing.");
                    return new PlayingState();
                case FirstRound:
                    logger.finer("MapState is FirstRound");
                    return new FirstRoundState();
                case SecondRound:
                    logger.finer("MapState is SecondRound");
                    return new SecondRoundState();
                case Robbing:
                    logger.finer("MapState is Robbing.");
                    return new RobbingState();
                case Rolling:
                case Discarding:
                    logger.finer("MapState is NotPlaying.");
                    return new NotPlayingState();
                default:
                    assert false : "Unknown game state.";
                    return null;
            }
        }
    }


    @Override
    public boolean canPlaceRoad(EdgeLocation edgeLoc) {
        return false;
    }


    @Override
    public boolean canPlaceSettlement(VertexLocation vertLoc) {
        return false;
    }


    @Override
    public boolean canPlaceCity(VertexLocation vertLoc) {
        return false;
    }


    @Override
    public boolean canPlaceRobber(HexLocation hexLoc) {
        return false;
    }


    @Override
    public void placeRoad(MapController controller, EdgeLocation edgeLoc) throws ModelException {
        assert false : "Called MapController placeRoad from illegal state: " + this.getClass().getSimpleName();
    }


    @Override
    public void placeSettlement(VertexLocation vertLoc) throws ModelException {
        assert false : "Called MapController placeSettlement from illegal state: " + this.getClass().getSimpleName();
    }


    @Override
    public void placeCity(VertexLocation vertLoc) throws ModelException {
        assert false : "Called MapController placeCity from illegal state: " + this.getClass().getSimpleName();
    }


    @Override
    public void placeRobber(MapController controller, HexLocation hexLoc) {
        assert false : "Called MapController placeRobber from illegal state: " + this.getClass().getSimpleName();
    }


    @Override
    public void startMove(MapController controller, PieceType pieceType) {
        assert false : "Called MapController startMove from illegal state: " + this.getClass().getSimpleName();
    }


    @Override
    public void playSoldierCard(MapController controller) {
        assert false : "Called MapController playSoldierCard from illegal state: " + this.getClass().getSimpleName();
    }


    @Override
    public void playRoadBuildingCard(MapController controller) {
        assert false : "Called MapController playRoadBuildingCard from illegal state: " + this.getClass().getSimpleName();
    }


    @Override
    public void robPlayer(RobPlayerInfo victim) throws ModelException {
        assert false : "Called MapController robPlayer from illegal state: " + this.getClass().getSimpleName();
    }


    @Override
    public void initializeDialogs(MapController controller) {

    }


    @Override
    public void cancelMove(MapController controller) {

    }
}
