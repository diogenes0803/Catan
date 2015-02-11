package shared.models;

import java.util.HashMap;

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
		HexTile thisTile = getHexTileAt(edgeLocation.getHexLoc());
		if (thisTile.getEdges().get(edgeLocation.getDir()).getHasRoad()) {
			return false;
		} 
		else {
			HexTile neighborTile = getHexTileAt(thisTile.getLocation()
					.getNeighborLoc(edgeLocation.getDir()));
			if (thisTile.playerHasRoadOnNeighborAt(playerId,
					edgeLocation.getDir()))
				return true;
			else if (thisTile.playerHasSettlementOnNeighborAt(playerId,
					edgeLocation.getDir()))
				return true;
			else if (neighborTile != null) {
				if (neighborTile.playerHasRoadOnNeighborAt(playerId,
						edgeLocation.getDir()))
					return true;
				else if (neighborTile.playerHasSettlementOnNeighborAt(playerId,
						edgeLocation.getDir()))
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
		HexTile thisTile = getHexTileAt(vertexLocation.getHexLoc());
		EdgeDirection dir1 = null;
		EdgeDirection dir2 = null;
		VertexDirection vdir1 = null;
		VertexDirection vdir2 = null;
		if (thisTile.getVertices().get(vertexLocation.getDir())
				.getHasSettlement()) {
			return false;
		}
		switch (vertexLocation.getDir()) {
		case NorthEast:
			dir1 = EdgeDirection.North;
			dir2 = EdgeDirection.NorthEast;
			vdir1 = VertexDirection.SouthEast;
			vdir2 = VertexDirection.West;
			break;
		case East:
			dir1 = EdgeDirection.NorthEast;
			dir2 = EdgeDirection.SouthEast;
			vdir1 = VertexDirection.SouthWest;
			vdir2 = VertexDirection.NorthWest;
			break;
		case SouthEast:
			dir1 = EdgeDirection.SouthEast;
			dir2 = EdgeDirection.South;
			vdir1 = VertexDirection.West;
			vdir2 = VertexDirection.NorthEast;
			break;
		case SouthWest:
			dir1 = EdgeDirection.South;
			dir2 = EdgeDirection.SouthWest;
			vdir1 = VertexDirection.NorthWest;
			vdir2 = VertexDirection.East;
			break;
		case West:
			dir1 = EdgeDirection.SouthWest;
			dir1 = EdgeDirection.NorthWest;
			vdir1 = VertexDirection.NorthEast;
			vdir2 = VertexDirection.SouthEast;
			break;
		case NorthWest:
			dir1 = EdgeDirection.NorthWest;
			dir2 = EdgeDirection.North;
			vdir1 = VertexDirection.East;
			vdir2 = VertexDirection.SouthWest;
			break;
		default:
			break;
		}
		HexTile neighborTile1 = getHexTileAt(thisTile.getLocation()
				.getNeighborLoc(dir1));
		HexTile neighborTile2 = getHexTileAt(thisTile.getLocation()
				.getNeighborLoc(dir2));
		
		if (playerHasRoadOnNeighborAt(playerId, vertexLocation.getDir(), neighborTile1)) {
			return true;
		} else if (neighborTile1.playerHasRoadOnNeighborAt(playerId, vdir1)) {
			return true;
		} else if (neighborTile2.playerHasRoadOnNeighborAt(playerId, vdir2)) {
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
					&& thisVertex.getSettlement().getOwnerPlayerId() == playerId) {
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
			return false;
		}
	}
	
	public boolean playerHasRoadOnNeighborAt(int playerId, EdgeDirection direction, HexTile tile) {
		Edge edge1 = null;
		Edge edge2 = null;
		boolean result = false;
		switch(direction) {
			case North:
				edge1 = tile.getEdges().get(EdgeDirection.NorthWest);
				edge2 = tile.getEdges().get(EdgeDirection.NorthEast);
				break;
			case NorthEast:
				edge1 = tile.getEdges().get(EdgeDirection.North);
				edge2 = tile.getEdges().get(EdgeDirection.SouthEast);
				break;
			case SouthEast:
				edge1 = tile.getEdges().get(EdgeDirection.NorthEast);
				edge2 = tile.getEdges().get(EdgeDirection.South);
				break;
			case South:
				edge1 = tile.getEdges().get(EdgeDirection.SouthEast);
				edge2 = tile.getEdges().get(EdgeDirection.SouthWest);
				break;
			case SouthWest:
				edge1 = tile.getEdges().get(EdgeDirection.NorthWest);
				edge2 = tile.getEdges().get(EdgeDirection.South);
				break;
			case NorthWest:
				edge1 = tile.getEdges().get(EdgeDirection.North);
				edge2 = tile.getEdges().get(EdgeDirection.SouthWest);
				break;
			default:
				break;
		}
		EdgeLocation loc1 = edge1.getLocation().getNormalizedLocation();
		EdgeLocation loc2 = edge2.getLocation().getNormalizedLocation();
		edge1 = getHexTileAt(loc1.getHexLoc()).getEdgeAt(loc1.getDir());
		edge2 = getHexTileAt(loc2.getHexLoc()).getEdgeAt(loc2.getDir());
		if(edge1.getHasRoad()) {
			if(edge1.getRoad().getOwnerPlayerId() == playerId)
				result = true;
		}
		if(edge2.getHasRoad()) {
			if(edge2.getRoad().getOwnerPlayerId() == playerId)
				result = true;
		}
		
		return result;
	}
	
	public boolean locationHasNeighboringSettlement(VertexDirection direction, HexTile tile) {
		Vertex vertex1 = null;
		Vertex vertex2 = null;
		Vertex vertex3 = null;
		switch(direction) {
			
		}
	}
	
	public boolean playerHasRoadOnNeighborAt(int playerId, VertexDirection direction, HexTile tile) {
		Edge edge1 = null;
		Edge edge2 = null;
		boolean result = false;
		switch(direction) {
			case NorthEast:
				edge1 = tile.getEdgeAt(EdgeDirection.North);
				edge2 = tile.getEdgeAt(EdgeDirection.NorthEast);
				break;
			case East:
				edge1 = tile.getEdgeAt(EdgeDirection.NorthEast);
				edge2 = tile.getEdgeAt(EdgeDirection.SouthEast);
				break;
			case SouthEast:
				edge1 = tile.getEdgeAt(EdgeDirection.SouthEast);
				edge2 = tile.getEdgeAt(EdgeDirection.South);
				break;
			case SouthWest:
				edge1 = tile.getEdgeAt(EdgeDirection.South);
				edge2 = tile.getEdgeAt(EdgeDirection.SouthWest);
				break;
			case West:
				edge1 = tile.getEdgeAt(EdgeDirection.NorthWest);
				edge2 = tile.getEdgeAt(EdgeDirection.SouthWest);
				break;
			case NorthWest:
				edge1 = tile.getEdgeAt(EdgeDirection.North);
				edge2 = tile.getEdgeAt(EdgeDirection.NorthWest);
				break;
			default:
				break;
		}
		
		EdgeLocation loc1 = edge1.getLocation().getNormalizedLocation();
		EdgeLocation loc2 = edge2.getLocation().getNormalizedLocation();
		edge1 = getHexTileAt(loc1.getHexLoc()).getEdgeAt(loc1.getDir());
		edge2 = getHexTileAt(loc2.getHexLoc()).getEdgeAt(loc2.getDir());
		
		if(edge1.getHasRoad()) {
			if(edge1.getRoad().getOwnerPlayerId() == playerId)
				result = true;
		}
		if(edge2.getHasRoad()) {
			if(edge2.getRoad().getOwnerPlayerId() == playerId)
				result = true;
		}
		
		return result;
	}
	
	public boolean playerHasSettlementOnNeighborAt(int playerId, EdgeDirection direction, HexTile tile) {
		boolean result = false;
		
		Vertex vertex1 = null;
		Vertex vertex2 = null;
		switch(direction) {
		case North:
			vertex1 = tile.getVertexAt(VertexDirection.NorthWest);
			vertex2 = tile.getVertexAt(VertexDirection.NorthEast);
			break;
		case NorthEast:
			vertex1 = tile.getVertexAt(VertexDirection.NorthEast);
			vertex2 = tile.getVertexAt(VertexDirection.East);
			break;
		case SouthEast:
			vertex1 = tile.getVertexAt(VertexDirection.East);
			vertex2 = tile.getVertexAt(VertexDirection.SouthEast);
			break;
		case South:
			vertex1 = tile.getVertexAt(VertexDirection.SouthEast);
			vertex2 = tile.getVertexAt(VertexDirection.SouthWest);
			break;
		case SouthWest:
			vertex1 = tile.getVertexAt(VertexDirection.West);
			vertex2 = tile.getVertexAt(VertexDirection.SouthWest);
			break;
		case NorthWest:
			vertex1 = tile.getVertexAt(VertexDirection.NorthWest);
			vertex2 = tile.getVertexAt(VertexDirection.West);
			break;
		default:
			break;
		}
		VertexLocation loc1 = vertex1.getLocation().getNormalizedLocation();
		VertexLocation loc2 = vertex2.getLocation().getNormalizedLocation();
		vertex1 = getHexTileAt(loc1.getHexLoc()).getVertexAt(loc1.getDir());
		vertex2 = getHexTileAt(loc2.getHexLoc()).getVertexAt(loc2.getDir());
		if(vertex1.getHasSettlement()) {
			if(vertex1.getSettlement().getOwnerPlayerId() == playerId)
				result = true;
		}
		if(vertex2.getHasSettlement()) {
			if(vertex2.getSettlement().getOwnerPlayerId() == playerId)
				result = true;
		}
		
		return result;
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

	private HexTile getHexTileAt(HexLocation location) {
		int x = location.getX() + radius-1;
		int y = location.getY() + radius-1;
		if (!(x > hexTiles.length-1 || y > hexTiles.length-1 || x < 0 || y < 0)) {
			return hexTiles[x][y];
		} else {
			return null;
		}
	}

}
