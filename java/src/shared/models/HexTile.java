package shared.models;

import java.util.HashMap;
import java.util.List;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * 
 * @author HojuneYoo
 * Model for HexTile, HexTile contains Edges and Vertexes. It can also has robber piece
 * 
 */
public class HexTile {
	
	private HexLocation location;
	private HashMap<EdgeLocation, Edge> edges;
	private HashMap<VertexLocation, Vertex> vertices;
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
	public HashMap<EdgeLocation, Edge> getEdges() {
		return edges;
	}
	public void setEdges(HashMap<EdgeLocation, Edge> edges) {
		this.edges = edges;
	}
	public HashMap<VertexLocation, Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(HashMap<VertexLocation, Vertex> vertices) {
		this.vertices = vertices;
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
