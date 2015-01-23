package shared.models;

import java.util.List;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

/**
 * 
 * @author HojuneYoo
 * Model for HexTile, HexTile contains Edges and Vertexes. It can also has robber piece
 * 
 */
public class HexTile {
	
	private HexLocation location;
	private List<Vertex> vertices;
	private List<Edge> edges;
	private ResourceType hexType;
	private int token;
	private Piece robber;
	private boolean hasRobber;
	
	public HexLocation getLocation() {
		return location;
	}
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	public List<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public ResourceType getHexType() {
		return hexType;
	}
	public void setHexType(ResourceType hexType) {
		this.hexType = hexType;
	}
	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token = token;
	}
	public Piece getRobber() {
		return robber;
	}
	public void setRobber(Piece robber) {
		this.robber = robber;
	}
	public boolean isHasRobber() {
		return hasRobber;
	}
	public void setHasRobber(boolean hasRobber) {
		this.hasRobber = hasRobber;
	}

}
