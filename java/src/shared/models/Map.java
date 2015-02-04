package shared.models;

import java.util.List;

import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * 
 * @author HojuneYoo
 * Map Contains List of Hextile
 *
 */

public class Map {
	
	private List< List<HexTile> > hexTiles;
	private HexLocation robberLocation;
	
	/**
	 * Check if player can build a road on certain edge
	 * @param edgeLocation
	 * @return true if possible false if not
	 */
	public boolean canBuildRoadAt(EdgeLocation edgeLocation){
		HexTile thisTile = hexTiles.get(edgeLocation.getHexLoc().getX()).get(edgeLocation.getHexLoc().getY());
		Edge thisEdge = thisTile.getEdges().get(edgeLocation.getDir());
		if(thisEdge.getHasRoad()){
			return false;
		}
		else if(TurnTracker.turnCount < 5){
			return true;
		}
		else{
			List<EdgeLocation>neighborLocations = thisEdge.getNeighborEdgeLocations();
			for(EdgeLocation thisLocation : neighborLocations) {
				Edge neighborEdge = hexTiles.get(thisLocation.getHexLoc().getX()).get(thisLocation.getHexLoc().getY()).getEdges().get(thisLocation.getDir());
				if(neighborEdge.getHasRoad()) {
					if(neighborEdge.getRoad().getOwnerPlayerId() == TurnTracker.thisTurnPlayerId) {
						return true;
					}
				}
				
			}
			return false;
		}
	}
	
	/**
	 * Check if player can build a Settlement on certain vertex
	 * @param vertexLocation
	 * @return true if possible false if not
	 */
	public boolean canBuildSettlementAt(VertexLocation vertexLocation) {
		Vertex thisVertex = hexTiles.get(vertexLocation.getHexLoc().getX()).get(vertexLocation.getHexLoc().getY()).getVertices().get(vertexLocation);
		if(thisVertex.getHasSettlement()) {
			return false;
		}
		List<EdgeLocation>neighborLocations = thisVertex.getNeighborEdgeLocations();
		for(EdgeLocation thisLocation : neighborLocations) {
			Edge neighborEdge = hexTiles.get(thisLocation.getHexLoc().getX()).get(thisLocation.getHexLoc().getY()).getEdges().get(thisLocation.getDir());
			if(neighborEdge.getHasRoad()) {
				if(neighborEdge.getRoad().getOwnerPlayerId() == TurnTracker.thisTurnPlayerId) {
					return true;
				}
			}
			
		}
		return false;
	}
	
	/**
	 * Check if player can upgrade a settlement on certain vertex
	 * @param vertexLocation
	 * @return true if possible false if not
	 */
	public boolean canUpgradeSettlementAt(VertexLocation vertexLocation) {
		if(hexTiles.get(vertexLocation.getHexLoc().getX()).get(vertexLocation.getHexLoc().getY()).getVertices().get(vertexLocation).getSettlement().getType()!=PieceType.CITY) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Check if user can move a robber to certain Hex tile
	 * @param hexLocation
	 * @return true if possible false if not
	 */
	public boolean canMoveRobber(HexLocation hexLocation) {
		if(hexTiles.get(hexLocation.getX()).get(hexLocation.getY()).getHasRobber()) {
			return false;
		}
		else {
		return false;
		}
	}

	public List<List<HexTile>> getHexTiles() {
		return hexTiles;
	}

	public void setHexTiles(List<List<HexTile>> hexTiles) {
		this.hexTiles = hexTiles;
	}

	public HexLocation getRobberLocation() {
		return robberLocation;
	}

	public void setRobberLocation(HexLocation robberLocation) {
		this.robberLocation = robberLocation;
	}
	

}
