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
	
	public boolean playerHasRoadOnNeighborAt(int playerId, EdgeDirection direction) {
		Edge edge1 = null;
		Edge edge2 = null;
		boolean result = false;
		switch(direction) {
			case North:
				edge1 = edges.get(EdgeDirection.NorthWest);
				edge2 = edges.get(EdgeDirection.NorthEast);
				break;
			case NorthEast:
				edge1 = edges.get(EdgeDirection.North);
				edge2 = edges.get(EdgeDirection.SouthEast);
				break;
			case SouthEast:
				edge1 = edges.get(EdgeDirection.NorthEast);
				edge2 = edges.get(EdgeDirection.South);
				break;
			case South:
				edge1 = edges.get(EdgeDirection.SouthEast);
				edge2 = edges.get(EdgeDirection.SouthWest);
				break;
			case SouthWest:
				edge1 = edges.get(EdgeDirection.NorthWest);
				edge2 = edges.get(EdgeDirection.South);
				break;
			case NorthWest:
				edge1 = edges.get(EdgeDirection.North);
				edge2 = edges.get(EdgeDirection.SouthWest);
				break;
			default:
				break;
		}
		
		if(edge1.getHasRoad()) {
			if(edge1.getRoad().getOwnerPlayerId() == playerId)
				result = true;
		}
		if(edge2.getHasRoad()) {
			if(edge2.getRoad().getOwnerPlayerId() == playerId)
				result = true;
		}
		
		return result;
	}
	
	public boolean playerHasRoadOnNeighborAt(int playerId, VertexDirection direction) {
		Edge edge1 = null;
		Edge edge2 = null;
		boolean result = false;
		switch(direction) {
			case NorthEast:
				edge1 = edges.get(EdgeDirection.North);
				edge2 = edges.get(EdgeDirection.NorthEast);
				break;
			case East:
				edge1 = edges.get(EdgeDirection.NorthEast);
				edge2 = edges.get(EdgeDirection.SouthEast);
				break;
			case SouthEast:
				edge1 = edges.get(EdgeDirection.SouthEast);
				edge2 = edges.get(EdgeDirection.South);
				break;
			case SouthWest:
				edge1 = edges.get(EdgeDirection.South);
				edge2 = edges.get(EdgeDirection.SouthWest);
				break;
			case West:
				edge1 = edges.get(EdgeDirection.NorthWest);
				edge2 = edges.get(EdgeDirection.SouthWest);
				break;
			case NorthWest:
				edge1 = edges.get(EdgeDirection.North);
				edge2 = edges.get(EdgeDirection.NorthWest);
				break;
			default:
				break;
		}
		
		if(edge1.getHasRoad()) {
			if(edge1.getRoad().getOwnerPlayerId() == playerId)
				result = true;
		}
		if(edge2.getHasRoad()) {
			if(edge2.getRoad().getOwnerPlayerId() == playerId)
				result = true;
		}
		
		return result;
	}
	
	public boolean findNeighboringSettlementAt(EdgeDirection direction) {
		boolean result = false;
		Vertex vertex1 = null;
		Vertex vertex2 = null;
		switch(direction) {
		case North:
			vertex1 = vertices.get(VertexDirection.NorthWest);
			vertex2 = vertices.get(VertexDirection.NorthEast);
			break;
		case NorthEast:
			vertex1 = vertices.get(VertexDirection.NorthEast);
			vertex2 = vertices.get(VertexDirection.East);
			break;
		case SouthEast:
			vertex1 = vertices.get(VertexDirection.East);
			vertex2 = vertices.get(VertexDirection.SouthEast);
			break;
		case South:
			vertex1 = vertices.get(VertexDirection.SouthEast);
			vertex2 = vertices.get(VertexDirection.SouthWest);
			break;
		case SouthWest:
			vertex1 = vertices.get(VertexDirection.West);
			vertex2 = vertices.get(VertexDirection.SouthWest);
			break;
		case NorthWest:
			vertex1 = vertices.get(VertexDirection.NorthWest);
			vertex2 = vertices.get(VertexDirection.West);
			break;
		default:
			break;
		}
		
		if(vertex1.getHasSettlement()) {
			result = true;
		}
		if(vertex2.getHasSettlement()) {
			result = true;
		}
		
		return result;
	}
	
	public boolean playerHasSettlementOnNeighborAt(int playerId, EdgeDirection direction) {
		boolean result = false;
		
		Vertex vertex1 = null;
		Vertex vertex2 = null;
		switch(direction) {
		case North:
			vertex1 = vertices.get(VertexDirection.NorthWest);
			vertex2 = vertices.get(VertexDirection.NorthEast);
			break;
		case NorthEast:
			vertex1 = vertices.get(VertexDirection.NorthEast);
			vertex2 = vertices.get(VertexDirection.East);
			break;
		case SouthEast:
			vertex1 = vertices.get(VertexDirection.East);
			vertex2 = vertices.get(VertexDirection.SouthEast);
			break;
		case South:
			vertex1 = vertices.get(VertexDirection.SouthEast);
			vertex2 = vertices.get(VertexDirection.SouthWest);
			break;
		case SouthWest:
			vertex1 = vertices.get(VertexDirection.West);
			vertex2 = vertices.get(VertexDirection.SouthWest);
			break;
		case NorthWest:
			vertex1 = vertices.get(VertexDirection.NorthWest);
			vertex2 = vertices.get(VertexDirection.West);
			break;
		default:
			break;
		}
		
		if(vertex1.getHasSettlement()) {
			if(vertex1.getSettlement().getOwnerPlayerId() == playerId)
				result = true;
		}
		if(vertex2.getHasSettlement()) {
			if(vertex2.getSettlement().getOwnerPlayerId() == playerId)
				result = true;
		}
		
		return result;
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
