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
		return false;
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
	
	/**
	 * Build a road at certain edge
	 * @pre edge must not contains any piece and players must have enough resources or development card on their turn
	 * @post edge must have a road that belongs to player who built, player must lost resources or a development card
	 * @param edgeLocation
	 */
	public void buildRoadAt(EdgeLocation edgeLocation){
		return;
	}
	
	/**
	 * Build a Settlement at certain vertex
	 * @pre vertex must not contains any piece and players must have enough resources on their turn
	 * @post vertex must have a settlement that belongs to player who built, player must lost resources
	 * @param vertexLocation
	 */
	public void buildSettlementAt(VertexLocation vertexLocation){
		return;
	}
	
	/**
	 * Upgrade Settlement at certain vertex to City
	 * @pre vertex must contains a settlement that belongs to a player who triggered this function and players must have enough resources on their turn
	 * @post vertex must remove settlement and it has to have a city that belongs to player who built, player must lost resources
	 * @param vertexLocation
	 */
	public void upgradeSettlementAy(VertexLocation vertexLocation){
		return;
	}
	
	/**
	 * Move robber to certain Hex Tile
	 * @pre Player must have used Knight/Soldier card or roll a dice and have 7 as a sum of two dice
	 * @post Robber must have moved to the other hex tile and player who triggered this function can steal a resource from one of other player who has settlement or city on that hex tile
	 * @param from
	 * @param to
	 */
	public void moveRobber(HexLocation from, HexLocation to){
		return;
	}

	public List<List<HexTile>> getHexTiles() {
		return hexTiles;
	}

	public void setHexTiles(List<List<HexTile>> hexTiles) {
		this.hexTiles = hexTiles;
	}
	
	

}
