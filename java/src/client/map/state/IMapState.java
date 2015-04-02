package client.map.state;

import client.data.RobPlayerInfo;
import client.map.MapController;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.ModelException;


public interface IMapState {

    public boolean canPlaceRoad(EdgeLocation edgeLoc);


    public boolean canPlaceSettlement(VertexLocation vertLoc);


    public boolean canPlaceCity(VertexLocation vertLoc);


    public boolean canPlaceRobber(HexLocation hexLoc);


    public void placeRoad(MapController controller, EdgeLocation edgeLoc) throws ModelException;


    public void placeSettlement(VertexLocation vertLoc) throws ModelException;


    public void placeCity(VertexLocation vertLoc) throws ModelException;


    public void placeRobber(MapController controller, HexLocation hexLoc);


    public void startMove(MapController controller, PieceType pieceType);


    public void playSoldierCard(MapController controller);


    public void playRoadBuildingCard(MapController controller);


    public void robPlayer(RobPlayerInfo victim) throws ModelException;


    public void initializeDialogs(MapController controller);


    public void cancelMove(MapController controller);
}
