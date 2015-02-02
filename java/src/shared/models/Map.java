package shared.models;

import java.util.List;

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
		else{
			List<EdgeLocation> edgeLocations = thisEdge.getLocations();
			for(EdgeLocation thisLocation : edgeLocations)
			{
				if(!edgeLocation.equals(thisLocation))
				{
					
				}
			}
			return true;
		}
	}
	
	/**
	 * Check if player can build a Settlement on certain vertex
	 * @param vertexLocation
	 * @return true if possible false if not
	 */
	public boolean canBuildSettlementAt(VertexLocation vertexLocation){
		return false;
	}
	
	/**
	 * Check if player can upgrade a settlement on certain vertex
	 * @param vertexLocation
	 * @return true if possible false if not
	 */
	public boolean canUpgradeSettlementAt(VertexLocation vertexLocation){
		return false;
	}
	
	/**
	 * Check if user can move a robber to certain Hex tile
	 * @param hexLocation
	 * @return true if possible false if not
	 */
	public boolean canMoveRobber(HexLocation hexLocation){
		return false;
	}

	public List<List<HexTile>> getHexTiles() {
		return hexTiles;
	}

	public void setHexTiles(List<List<HexTile>> hexTiles) {
		this.hexTiles = hexTiles;
	}
	
	

}
