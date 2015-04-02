package client.map;

import client.base.*;
import client.data.*;
import shared.definitions.*;
import shared.locations.*;


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

