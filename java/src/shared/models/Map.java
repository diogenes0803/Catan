package shared.models;

import java.util.List;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class Map {
	
	private List< List<HexTile> > hexTiles;
	
	public boolean canBuildRoadAt(EdgeLocation edgeLocation){
		return false;
	}
	
	public boolean canBuildSettlementAt(VertexLocation vertexLocation){
		return false;
	}
	
	public boolean canUpgradeSettlementAt(VertexLocation vertexLocation){
		return false;
	}
	
	public boolean canMoveRobber(HexLocation hexLocation){
		return false;
	}
	
	public void buildRoadAt(EdgeLocation edgeLocation){
		return;
	}
	
	public void buildSettlementAt(VertexLocation vertexLocation){
		return;
	}
	
	public void upgradeSettlementAy(VertexLocation vertexLocation){
		return;
	}
	
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
