package shared.models;

import java.util.List;

import shared.locations.EdgeLocation;
/**
 * 
 * @author HojuneYoo
 * HexTile's Edge Model
 */
public class Edge {
	
	private List<EdgeLocation> locations;
	private Piece road;
	private boolean hasRoad;
	
	
	
	public List<EdgeLocation> getLocations() {
		return locations;
	}
	public void setLocations(List<EdgeLocation> locations) {
		this.locations = locations;
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
