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
		} else if (TurnTracker.getInstance().getCurrentTurn() < 5) {
			return true;
		} else {
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
	public boolean canBuildSettlementAt(int playerId,
			VertexLocation vertexLocation) {
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
		if (thisTile.playerHasRoadOnNeighborAt(playerId,
				vertexLocation.getDir())) {
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
		int x = location.getX() + 2;
		int y = location.getY() + 2;
		if (!(x > 4 || y > 4 || x < 0 || y < 0)) {
			return hexTiles[x][y];
		} else {
			return null;
		}
	}

}
