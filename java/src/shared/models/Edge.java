package shared.models;

import java.util.ArrayList;
import java.util.List;

import shared.locations.EdgeDirection;
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
	
	public List<EdgeLocation> getNeighborEdgeLocations() {
		List<EdgeLocation> neighborLocations = new ArrayList<EdgeLocation>();
		for(EdgeLocation thisLocation : locations) {
			EdgeDirection thisDirection = thisLocation.getDir();
			EdgeDirection neighbor1 = null;
			EdgeDirection neighbor2 = null;
			switch(thisDirection) {
				case NorthWest:
					neighbor1 = EdgeDirection.North;
					neighbor2 = EdgeDirection.SouthWest;
					break;
				case North:
					neighbor1 = EdgeDirection.NorthWest;
					neighbor2 = EdgeDirection.NorthEast;
					break;
				case NorthEast:
					neighbor1 = EdgeDirection.North;
					neighbor2 = EdgeDirection.SouthEast;
					break;
				case SouthEast:
					neighbor1 = EdgeDirection.NorthEast;
					neighbor2 = EdgeDirection.South;
					break;
				case South:
					neighbor1 = EdgeDirection.SouthEast;
					neighbor2 = EdgeDirection.SouthWest;
					break;
				case SouthWest:
					neighbor1 = EdgeDirection.NorthWest;
					neighbor2 = EdgeDirection.South;
					break;
				default:
					break;
			}
			neighborLocations.add(new EdgeLocation(thisLocation.getHexLoc(), neighbor1));
			neighborLocations.add(new EdgeLocation(thisLocation.getHexLoc(), neighbor2));
		}
		return neighborLocations;
	}
	
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
