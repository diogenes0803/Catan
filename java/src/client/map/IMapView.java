package client.map;

import shared.definitions.*;
import shared.locations.*;
import client.base.*;


public interface IMapView extends IView
{
	

	void addHex(HexLocation hexLoc, HexType hexType);
	

	void addNumber(HexLocation hexLoc, int num);
	

	void addPort(EdgeLocation edgeLoc, PortType portType);
	

	void placeRoad(EdgeLocation edgeLoc, CatanColor color);

    void removeRoad(EdgeLocation edgeLoc);


	void placeSettlement(VertexLocation vertLoc, CatanColor color);
	

	void placeCity(VertexLocation vertLoc, CatanColor color);
	

	void placeRobber(HexLocation hexLoc);
	

	void startDrop(PieceType pieceType, CatanColor pieceColor,
				   boolean isCancelAllowed);
}

