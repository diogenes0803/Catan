package shared.models;

import java.util.HashMap;

import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * 
 * @author HojuneYoo Map Contains List of Hextile
 *
 */

public class CatanMap {

	private HexTile[][] hexTiles;
	private HashMap<EdgeLocation, Port> ports;
	private HexLocation robberLocation;
	private int radius;

	public CatanMap() {
	}

	/**
	 * Check if player can build a road on certain edge
	 * 
	 * @param edgeLocation
	 * @return true if possible false if not
	 */
	public boolean canBuildRoadAt(int playerId, EdgeLocation edgeLocation) {
		if(isSea(edgeLocation)) {
			return false;
		}
		if(getRoadAt(edgeLocation) != null) {
			return false;
		}
		else {
			if(TurnTracker.getInstance().getStatus().equals("FirstRound") || TurnTracker.getInstance().getStatus().equals("SecondRound")) {
				if(playerHasNeighboringRoad(playerId, edgeLocation))
					return true;
				else if(edgeHasNeighboringSettlementOfOthers(playerId, edgeLocation))
					return false;
				else
					return true;
			}
			else {
				if(playerHasNeighboringRoad(playerId, edgeLocation))
					return true;
				else if(playerHasNeighboringSettlementAt(playerId, edgeLocation))
					return true;
			}
		}
		return false;

	}

	/**
	 * Check if player can build a Settlement on certain vertex
	 * 
	 * @param vertexLocation
	 * @return true if possible false if not
	 */
	public boolean canBuildSettlementAt(int playerId, VertexLocation vertexLocation) {
		if(getHexTileAt(vertexLocation.getHexLoc()) == null)
			return false;
		if(getSettlementAt(vertexLocation) != null)
			return false;
		else {
			if(vertexHasNeighboringSettlement(vertexLocation))
				return false;
			else if(playerHasNeighboringRoad(playerId, vertexLocation))
				return true;
		}
		return false;
	}

	/**
	 * Check if player can upgrade a settlement on certain vertex
	 * 
	 * @param vertexLocation
	 * @return true if possible false if not
	 */
	public boolean canUpgradeSettlementAt(int playerId,
			VertexLocation vertexLocation) {
		Vertex thisVertex = getHexTileAt(vertexLocation.getHexLoc())
				.getVertexAt(vertexLocation.getDir());
		if (thisVertex.getHasSettlement()) {
			if (thisVertex.getSettlement().getType() != PieceType.CITY
					&& thisVertex.getSettlement().getOwnerPlayerIndex() == playerId) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if user can move a robber to certain Hex tile
	 * 
	 * @param hexLocation
	 * @return true if possible false if not
	 */
	public boolean canMoveRobber(HexLocation hexLocation) {
		if (getHexTileAt(hexLocation).getHasRobber()) {
			return false;
		} else {
			if(getHexTileAt(hexLocation).getHexType() == HexType.WATER)
				return false;
			else
				return true;
		}
	}
	
	
	public Piece getSettlementAt(VertexLocation vLoc) {
		HexTile hexTile1 = getHexTileAt(vLoc.getHexLoc());
		HexTile hexTile2 = null;
		HexTile hexTile3 = null;
		Vertex vertex1 = hexTile1.getVertexAt(vLoc.getDir());
		Vertex vertex2 = null;
		Vertex vertex3 = null;
		int x = hexTile1.getLocation().getX();
		int y = hexTile1.getLocation().getY();
		switch(vLoc.getDir()) {
			case NorthEast :
				hexTile2 = getHexTileAt(new HexLocation(x+1, y-1));
				if(hexTile2 != null)
					vertex2 = hexTile2.getVertexAt(VertexDirection.West);
				hexTile3 = getHexTileAt(new HexLocation(x, y-1));
				if(hexTile3 != null)
					vertex3 = hexTile3.getVertexAt(VertexDirection.SouthEast);
				break;
			case East :
				hexTile2 = getHexTileAt(new HexLocation(x+1, y-1));
				if(hexTile2 != null)
					vertex2 = hexTile2.getVertexAt(VertexDirection.SouthWest);
				hexTile3 = getHexTileAt(new HexLocation(x, y-1));
				if(hexTile3 != null)
					vertex3 = hexTile3.getVertexAt(VertexDirection.NorthWest);
				break;
			case SouthEast :
				hexTile2 = getHexTileAt(new HexLocation(x+1, y));
				if(hexTile2 != null)
					vertex2 = hexTile2.getVertexAt(VertexDirection.West);
				hexTile3 = getHexTileAt(new HexLocation(x, y+1));
				if(hexTile3 != null)
					vertex3 = hexTile3.getVertexAt(VertexDirection.NorthEast);
				break;
			case SouthWest :
				hexTile2 = getHexTileAt(new HexLocation(x, y+1));
				if(hexTile2 != null)
					vertex2 = hexTile2.getVertexAt(VertexDirection.NorthWest);
				hexTile3 = getHexTileAt(new HexLocation(x-1, y+1));
				if(hexTile3 != null)
					vertex3 = hexTile3.getVertexAt(VertexDirection.East);
				break;
			case West :
				hexTile2 = getHexTileAt(new HexLocation(x-1, y+1));
				if(hexTile2 != null)
					vertex2 = hexTile2.getVertexAt(VertexDirection.NorthEast);
				hexTile3 = getHexTileAt(new HexLocation(x-1, y));
				if(hexTile3 != null)
					vertex3 = hexTile3.getVertexAt(VertexDirection.SouthEast);
				break;
			case NorthWest :
				hexTile2 = getHexTileAt(new HexLocation(x-1, y));
				if(hexTile2 != null)
					vertex2 = hexTile2.getVertexAt(VertexDirection.East);
				hexTile3 = getHexTileAt(new HexLocation(x, y-1));
				if(hexTile3 != null)
					vertex3 = hexTile3.getVertexAt(VertexDirection.SouthWest);
				break;
			default:
				return null;
		}
		if(vertex1.getHasSettlement()) {
			return vertex1.getSettlement();
		}
		if(vertex2 != null) {
			if(vertex2.getHasSettlement())
				return vertex2.getSettlement();
		}
		if(vertex3 != null) {
			if(vertex3.getHasSettlement())
				return vertex3.getSettlement();
		}
		
		return null;
		
	}
	
	private Piece getRoadAt(EdgeLocation eLoc) {
		HexTile hexTile1 = getHexTileAt(eLoc.getHexLoc());
		Edge edge1 = hexTile1.getEdgeAt(eLoc.getDir());
		if(edge1.getHasRoad())
			return edge1.getRoad();
		else {
			HexTile hexTile2 = getHexTileAt(hexTile1.getLocation().getNeighborLoc(eLoc.getDir()));
			if(hexTile2 != null) {
				Edge edge2 = hexTile2.getEdgeAt(eLoc.getDir().getOppositeDirection());
				if(edge2.getHasRoad())
					return edge2.getRoad();
			}
		}
		return null;
	}
	
	private boolean vertexHasNeighboringSettlement(VertexLocation vLoc) {
		Vertex[] vertices = getNeighboringVertices(vLoc);
		for(Vertex thisVertex : vertices) {
			if(thisVertex != null) {
				Piece settlement = getSettlementAt(thisVertex.getLocation());
				if(settlement != null)
					return true;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	private boolean isSea(EdgeLocation eLoc) {
		HexTile thisTile = getHexTileAt(eLoc.getHexLoc());
		if(thisTile == null) {
			return true;
		}
		HexTile neighborTile = getHexTileAt(thisTile.getLocation().getNeighborLoc(eLoc.getDir()));
		if(neighborTile == null) {
			return true;
		}
		if(thisTile.getHexType() == HexType.WATER && neighborTile.getHexType() == HexType.WATER) {
			return true;
		}
		else
			return false;
	}
	
	private boolean playerHasNeighboringRoad(int playerId, VertexLocation vLoc) {
		HexTile thisTile = getHexTileAt(vLoc.getHexLoc());
		HexTile neighborTile = null;
		Edge edge1 = null;
		Edge edge2 = null;
		Edge edge3 = null;
		int x = thisTile.getLocation().getX();
		int y = thisTile.getLocation().getY();
		
		switch(vLoc.getDir()) {
			case NorthEast:
				edge1 = thisTile.getEdgeAt(EdgeDirection.North);
				edge2 = thisTile.getEdgeAt(EdgeDirection.NorthEast);
				neighborTile = getHexTileAt(new HexLocation(x, y-1));
				if(neighborTile != null)
					edge3 = neighborTile.getEdgeAt(EdgeDirection.SouthEast);
				break;
			case East:
				edge1 = thisTile.getEdgeAt(EdgeDirection.NorthEast);
				edge2 = thisTile.getEdgeAt(EdgeDirection.SouthEast);
				neighborTile = getHexTileAt(new HexLocation(x+1, y));
				if(neighborTile != null)
					edge3 = neighborTile.getEdgeAt(EdgeDirection.North);
				break;
			case SouthEast:
				edge1 = thisTile.getEdgeAt(EdgeDirection.SouthEast);
				edge2 = thisTile.getEdgeAt(EdgeDirection.South);
				neighborTile = getHexTileAt(new HexLocation(x, y+1));
				if(neighborTile != null)
					edge3 = neighborTile.getEdgeAt(EdgeDirection.NorthEast);
				break;
			case SouthWest:
				edge1 = thisTile.getEdgeAt(EdgeDirection.South);
				edge2 = thisTile.getEdgeAt(EdgeDirection.SouthWest);
				neighborTile = getHexTileAt(new HexLocation(x, y+1));
				if(neighborTile != null)
					edge3 = neighborTile.getEdgeAt(EdgeDirection.NorthWest);
				break;
			case West:
				edge1 = thisTile.getEdgeAt(EdgeDirection.SouthWest);
				edge2 = thisTile.getEdgeAt(EdgeDirection.NorthWest);
				neighborTile = getHexTileAt(new HexLocation(x-1, y));
				if(neighborTile != null)
					edge3 = neighborTile.getEdgeAt(EdgeDirection.South);
				break;
			case NorthWest:
				edge1 = thisTile.getEdgeAt(EdgeDirection.NorthWest);
				edge2 = thisTile.getEdgeAt(EdgeDirection.North);
				neighborTile = getHexTileAt(new HexLocation(x, y-1));
				if(neighborTile != null)
					edge3 = neighborTile.getEdgeAt(EdgeDirection.SouthWest);
				break;
		}
		Edge[] edges = new Edge[3];
		edges[0] = edge1;
		edges[1] = edge2;
		edges[2] = edge3;
		
		for(Edge thisEdge : edges) {
			if(thisEdge != null) {
				Piece road = getRoadAt(thisEdge.getLocation());
				if(road != null) {
					if(road.getOwnerPlayerIndex() == playerId)
						return true;
				}
			}
		}
		return false;
	}
	
	private Vertex[] getNeighboringVertices(VertexLocation vLoc) {
		Vertex[] vertices = new Vertex[3];
		HexTile thisTile = getHexTileAt(vLoc.getHexLoc());
		HexTile neighborTile = null;
		Vertex vertex1 = null;
		Vertex vertex2 = null;
		Vertex vertex3 = null;
		int x = thisTile.getLocation().getX();
		int y = thisTile.getLocation().getY();
		switch(vLoc.getDir()) {
			case NorthEast:
				vertex1 = thisTile.getVertexAt(VertexDirection.NorthWest);
				vertex2 = thisTile.getVertexAt(VertexDirection.East);
				neighborTile = getHexTileAt(new HexLocation(x, y-1));
				if(neighborTile != null)
					vertex3 = neighborTile.getVertexAt(VertexDirection.East);
				break;
			case East:
				vertex1 = thisTile.getVertexAt(VertexDirection.NorthEast);
				vertex2 = thisTile.getVertexAt(VertexDirection.SouthEast);
				neighborTile = getHexTileAt(new HexLocation(x+1, y));
				if(neighborTile != null)
					vertex3 = neighborTile.getVertexAt(VertexDirection.NorthEast);
				break;
			case SouthEast:
				vertex1 = thisTile.getVertexAt(VertexDirection.East);
				vertex2 = thisTile.getVertexAt(VertexDirection.SouthWest);
				neighborTile = getHexTileAt(new HexLocation(x, y+1));
				if(neighborTile != null)
					vertex3 = neighborTile.getVertexAt(VertexDirection.East);
				break;
			case SouthWest:
				vertex1 = thisTile.getVertexAt(VertexDirection.SouthEast);
				vertex2 = thisTile.getVertexAt(VertexDirection.West);
				neighborTile = getHexTileAt(new HexLocation(x, y+1));
				if(neighborTile != null)
					vertex3 = neighborTile.getVertexAt(VertexDirection.West);
				break;
			case West:
				vertex1 = thisTile.getVertexAt(VertexDirection.SouthWest);
				vertex2 = thisTile.getVertexAt(VertexDirection.NorthWest);
				neighborTile = getHexTileAt(new HexLocation(x-1, y));
				if(neighborTile != null)
					vertex3 = neighborTile.getVertexAt(VertexDirection.SouthWest);
				break;
			case NorthWest:
				vertex1 = thisTile.getVertexAt(VertexDirection.West);
				vertex2 = thisTile.getVertexAt(VertexDirection.NorthEast);
				neighborTile = getHexTileAt(new HexLocation(x, y-1));
				if(neighborTile != null)
					vertex3 = neighborTile.getVertexAt(VertexDirection.West);
				break;
		}
		vertices[0] = vertex1;
		vertices[1] = vertex2;
		vertices[2] = vertex3;
		return vertices;
	}
	
	private boolean playerHasNeighboringRoad(int playerId, EdgeLocation eLoc) {
		HexTile thisTile = getHexTileAt(eLoc.getHexLoc());
		HexTile neighborTile = getHexTileAt(eLoc.getHexLoc().getNeighborLoc(eLoc.getDir()));
		Edge edge1 = null;
		Edge edge2 = null;
		Edge edge3 = null;
		Edge edge4 = null;
		switch(eLoc.getDir()) {
			case North:
				edge1 = thisTile.getEdgeAt(EdgeDirection.NorthEast);
				edge2 = thisTile.getEdgeAt(EdgeDirection.NorthWest);
				break;
			case NorthEast:
				edge1 = thisTile.getEdgeAt(EdgeDirection.North);
				edge2 = thisTile.getEdgeAt(EdgeDirection.SouthEast);
				break;
			case SouthEast:
				edge1 = thisTile.getEdgeAt(EdgeDirection.NorthEast);
				edge2 = thisTile.getEdgeAt(EdgeDirection.South);
				break;
			case South:
				edge1 = thisTile.getEdgeAt(EdgeDirection.SouthEast);
				edge2 = thisTile.getEdgeAt(EdgeDirection.SouthWest);
				break;
			case SouthWest:
				edge1 = thisTile.getEdgeAt(EdgeDirection.South);
				edge2 = thisTile.getEdgeAt(EdgeDirection.NorthWest);
				break;
			case NorthWest:
				edge1 = thisTile.getEdgeAt(EdgeDirection.North);
				edge2 = thisTile.getEdgeAt(EdgeDirection.SouthWest);
				break;
			default:
				break;
		}
		if(neighborTile != null) {
			edge3 = neighborTile.getEdgeAt(edge1.getLocation().getDir().getOppositeDirection());
			edge4 = neighborTile.getEdgeAt(edge2.getLocation().getDir().getOppositeDirection());
		}
		Edge[] edges = new Edge[4];
		edges[0] = edge1;
		edges[1] = edge2;
		edges[2] = edge3;
		edges[3] = edge4;
		
		for(Edge thisEdge :edges) {
			if(thisEdge != null) {
				Piece thisRoad = getRoadAt(thisEdge.getLocation());
				if(thisRoad != null)
					if(thisRoad.getOwnerPlayerIndex() == playerId)
						return true;
			}
		}
		return false;
	}
	
	private Vertex[] getNeighboringVertices(EdgeLocation eLoc) {
		HexTile thisTile = getHexTileAt(eLoc.getHexLoc());
		Vertex vertex1 = null;
		Vertex vertex2 = null;
		
		switch(eLoc.getDir()) {
			case North:
				vertex1 = thisTile.getVertexAt(VertexDirection.NorthWest);
				vertex2 = thisTile.getVertexAt(VertexDirection.NorthEast);
				break;
			case NorthEast:
				vertex1 = thisTile.getVertexAt(VertexDirection.NorthEast);
				vertex2 = thisTile.getVertexAt(VertexDirection.East);
				break;
			case SouthEast:
				vertex1 = thisTile.getVertexAt(VertexDirection.East);
				vertex2 = thisTile.getVertexAt(VertexDirection.SouthEast);
				break;
			case South:
				vertex1 = thisTile.getVertexAt(VertexDirection.SouthEast);
				vertex2 = thisTile.getVertexAt(VertexDirection.SouthWest);
				break;
			case SouthWest:
				vertex1 = thisTile.getVertexAt(VertexDirection.SouthWest);
				vertex2 = thisTile.getVertexAt(VertexDirection.West);
				break;
			case NorthWest:
				vertex1 = thisTile.getVertexAt(VertexDirection.West);
				vertex2 = thisTile.getVertexAt(VertexDirection.NorthWest);
				break;
		}
		Vertex[] vertices = new Vertex[2];
		vertices[0] = vertex1;
		vertices[1] = vertex2;
		return vertices;
	}
	
	public boolean playerHasNeighboringSettlementAt(int playerId, EdgeLocation eLoc) {
		Vertex[] vertices = getNeighboringVertices(eLoc);
		for(Vertex vertex : vertices) {
			Piece settlement = getSettlementAt(vertex.getLocation());
			if(settlement != null) {
				if(settlement.getOwnerPlayerIndex() == playerId)
					return true;
			}
		}
		return false;
	}
	
	public boolean edgeHasNeighboringSettlementOfOthers(int playerId, EdgeLocation eLoc) {
		Vertex[] vertices = getNeighboringVertices(eLoc);
		for(Vertex vertex : vertices) {
			Piece settlement = getSettlementAt(vertex.getLocation());
			if(settlement != null) {
				if(settlement.getOwnerPlayerIndex() != playerId)
					return true;
			}
		}
		return false;
	}

	public HexTile[][] getHexTiles() {
		return hexTiles;
	}

	public void setHexTiles(HexTile[][] hexTiles) {
		this.hexTiles = hexTiles;
	}

	public HexLocation getRobberLocation() {
		return robberLocation;
	}

	public void setRobberLocation(HexLocation robberLocation) {
		this.robberLocation = robberLocation;
	}

	public HashMap<EdgeLocation, Port> getPorts() {
		return ports;
	}

	public void setPorts(HashMap<EdgeLocation, Port> ports) {
		this.ports = ports;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public HexTile getHexTileAt(HexLocation location) {
		int x = location.getX() + radius;
		int y = location.getY() + radius;
		if (!(x > hexTiles.length-1 || y > hexTiles.length-1 || x < 0 || y < 0)) {
			return hexTiles[x][y];
		} else {
			return null;
		}
	}

}
