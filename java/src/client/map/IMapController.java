package client.map;

import client.base.IController;
import client.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;


public interface IMapController extends IController
{

	boolean canPlaceRoad(EdgeLocation edgeLoc);
	

	boolean canPlaceSettlement(VertexLocation vertLoc);
	

	boolean canPlaceCity(VertexLocation vertLoc);
	

	boolean canPlaceRobber(HexLocation hexLoc);
	

	void placeRoad(EdgeLocation edgeLoc);
	

	void placeSettlement(VertexLocation vertLoc);
	

	void placeCity(VertexLocation vertLoc);
	

	void placeRobber(HexLocation hexLoc);
	

	void startMove(PieceType pieceType);
	

	void cancelMove();
	

	void playSoldierCard();
	

	void playRoadBuildingCard();
	

	void robPlayer(RobPlayerInfo victim);
}

