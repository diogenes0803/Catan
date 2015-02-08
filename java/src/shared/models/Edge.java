package shared.models;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
/**
 * 
 * @author HojuneYoo
 * HexTile's Edge Model
 */
public class Edge {
	
	private EdgeLocation location;
	private Piece road;
	private boolean hasRoad;
	
	public Edge(EdgeDirection direction, HexLocation hexLoc) {
		this.location = new EdgeLocation(hexLoc, direction);
	}
	

	public EdgeLocation getLocation() {
		return location;
	}
	public void setLocation(EdgeLocation location) {
		this.location = location;
	}
	public Piece getRoad() {
		return road;
	}
	public void setRoad(Piece road) {
		this.road = road;
	}
	public boolean getHasRoad() {
		return hasRoad;
	}
	public void setHasRoad(boolean hasRoad) {
		this.hasRoad = hasRoad;
	}

}
