package shared.models;

import shared.locations.VertexLocation;

/**
 * Model for HexTile's Vertices
 * @author HojuneYoo
 *
 */

public class Vertex {
	
	private VertexLocation location;
	private Piece settlement;
	private boolean hasSettlement;
	

	public VertexLocation getLocation() {
		return location;
	}
	public void setLocation(VertexLocation location) {
		this.location = location;
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
