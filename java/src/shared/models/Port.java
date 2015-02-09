package shared.models;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;

public class Port {
	
	private int ratio;
	private ResourceType resource;
	private EdgeLocation location;
	
	public Port(int ratio, ResourceType type, EdgeLocation location) {
		this.ratio = ratio;
		this.resource = type;
		this.location = location;
	}
	public int getRatio() {
		return ratio;
	}
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	public ResourceType getResource() {
		return resource;
	}
	public void setResource(ResourceType resource) {
		this.resource = resource;
	}
	public EdgeLocation getLocation() {
		return location;
	}
	public void setLocation(EdgeLocation location) {
		this.location = location;
	}
}
