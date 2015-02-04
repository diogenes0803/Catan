package shared.models;

import java.util.ArrayList;
import java.util.List;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * Model for HexTile's Vertices
 * @author HojuneYoo
 *
 */

public class Vertex {
	
	private List<VertexLocation> locations;
	private Piece settlement;
	private boolean hasSettlement;
	
	public List<EdgeLocation> getNeighborEdgeLocations() {
		List<EdgeLocation> neighborLocations = new ArrayList<EdgeLocation>();
		for(VertexLocation thisLocation : locations) {
			VertexDirection thisDirection = thisLocation.getDir();
			EdgeDirection neighbor1 = null;
			EdgeDirection neighbor2 = null;
			switch(thisDirection) {
				case West:
					neighbor1 = EdgeDirection.NorthWest;
					neighbor2 = EdgeDirection.SouthWest;
					break;
				case NorthWest:
					neighbor1 = EdgeDirection.NorthWest;
					neighbor2 = EdgeDirection.North;
					break;
				case NorthEast:
					neighbor1 = EdgeDirection.North;
					neighbor2 = EdgeDirection.NorthEast;
					break;
				case East:
					neighbor1 = EdgeDirection.NorthEast;
					neighbor2 = EdgeDirection.SouthEast;
					break;
				case SouthEast:
					neighbor1 = EdgeDirection.SouthEast;
					neighbor2 = EdgeDirection.South;
					break;
				case SouthWest:
					neighbor1 = EdgeDirection.SouthWest;
					neighbor2 = EdgeDirection.South;
					break;
				default:
					break;
			}
			if(!neighborLocations.contains(neighbor1)) {
				neighborLocations.add(new EdgeLocation(thisLocation.getHexLoc(), neighbor1));
			}
			if(!neighborLocations.contains(neighbor2)) {
				neighborLocations.add(new EdgeLocation(thisLocation.getHexLoc(), neighbor2));
			}
		}
		return neighborLocations;
	}
	
	
	public List<VertexLocation> getLocations() {
		return locations;
	}


	public void setLocations(List<VertexLocation> locations) {
		this.locations = locations;
	}


	public Piece getSettlement() {
		return settlement;
	}
	public void setSettlement(Piece settlement) {
		this.settlement = settlement;
	}
	public boolean getHasSettlement() {
		return hasSettlement;
	}
	public void setHasSettlement(boolean hasSettlement) {
		this.hasSettlement = hasSettlement;
	}

}
