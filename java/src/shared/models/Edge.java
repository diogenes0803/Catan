package shared.models;

import shared.locations.EdgeLocation;

public class Edge {
	
	private EdgeLocation location;
	private Piece road;
	private boolean hasRoad;
	
	
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
	public boolean isHasRoad() {
		return hasRoad;
	}
	public void setHasRoad(boolean hasRoad) {
		this.hasRoad = hasRoad;
	}

}