package shared.models;

import java.util.HashMap;

import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;

/**
 * 
 * @author HojuneYoo
 * Model for HexTile, HexTile contains Edges and Vertexes. It can also has robber piece
 * 
 */
public class HexTile {
	
	private HexLocation location;
	private HashMap<EdgeDirection, Edge> edges;
	private HashMap<VertexDirection, Vertex> vertices;
	private ResourceType resourceType;
	private int token = -1;
	private Piece robber;
	private boolean hasRobber;
	
	public HexTile(){}
	
	public HexTile(int x, int y, ResourceType resource, int token) {
		edges = new HashMap<EdgeDirection, Edge>();
		vertices = new HashMap<VertexDirection, Vertex>();
		location = new HexLocation(x, y);
		resourceType  = resource;
		this.token = token;
		edges.put(EdgeDirection.North, new Edge(EdgeDirection.North, location));
		edges.put(EdgeDirection.NorthEast, new Edge(EdgeDirection.NorthEast, location));
		edges.put(EdgeDirection.NorthWest, new Edge(EdgeDirection.NorthWest, location));
		edges.put(EdgeDirection.South, new Edge(EdgeDirection.South, location));
		edges.put(EdgeDirection.SouthEast, new Edge(EdgeDirection.SouthEast, location));
		edges.put(EdgeDirection.SouthWest, new Edge(EdgeDirection.SouthWest, location));
		vertices.put(VertexDirection.East, new Vertex(VertexDirection.East, location));
		vertices.put(VertexDirection.NorthEast, new Vertex(VertexDirection.NorthEast, location));
		vertices.put(VertexDirection.NorthWest, new Vertex(VertexDirection.NorthWest, location));
		vertices.put(VertexDirection.SouthEast, new Vertex(VertexDirection.SouthEast, location));
		vertices.put(VertexDirection.SouthWest, new Vertex(VertexDirection.SouthWest, location));
		vertices.put(VertexDirection.West, new Vertex(VertexDirection.West, location));
		hasRobber = false;
		
	}
	
	public Vertex getVertexAt(VertexDirection direction) {
		return vertices.get(direction);
	}
	
	public Edge getEdgeAt(EdgeDirection direction) {
		return edges.get(direction);
	}
	
	public HexLocation getLocation() {
		return location;
	}
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	public HashMap<EdgeDirection, Edge> getEdges() {
		return edges;
	}
	public void setEdges(HashMap<EdgeDirection, Edge> edges) {
		this.edges = edges;
	}
	public HashMap<VertexDirection, Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(HashMap<VertexDirection, Vertex> vertices) {
		this.vertices = vertices;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType hexType) {
		this.resourceType = hexType;
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
	public boolean getHasRobber() {
		return hasRobber;
	}
	public void setHasRobber(boolean hasRobber) {
		this.hasRobber = hasRobber;
	}
	

}
