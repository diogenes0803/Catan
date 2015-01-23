package shared.models;

import shared.locations.VertexLocation;

/**
 * Model for HexTile's Vertices
 * @author HojuneYoo
 *
 */

public class Vertex {
	
	private VertexLocation Location;
	private Piece Settlement;
	private boolean hasSettlement;
	
	
	public VertexLocation getLocation() {
		return Location;
	}
	public void setLocation(VertexLocation location) {
		Location = location;
	}
	public Piece getSettlement() {
		return Settlement;
	}
	public void setSettlement(Piece settlement) {
		Settlement = settlement;
	}
	public boolean isHasSettlement() {
		return hasSettlement;
	}
	public void setHasSettlement(boolean hasSettlement) {
		this.hasSettlement = hasSettlement;
	}

}
