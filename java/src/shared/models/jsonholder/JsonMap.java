package shared.models.jsonholder;

import java.util.List;

public class JsonMap {
	private List<Hex> hexes;
	private List<Road> roads;
	private List<City> cities;
	private List<Settlement> settlements; 
	private int radius;
	private List<Port> ports;
	private Robber robber;
	
	
	public List<Hex> getHexes() {
		return hexes;
	}
	public void setHexes(List<Hex> hexes) {
		this.hexes = hexes;
	}
	public List<Road> getRoads() {
		return roads;
	}
	public void setRoads(List<Road> roads) {
		this.roads = roads;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	public List<Settlement> getSettlements() {
		return settlements;
	}
	public void setSettlements(List<Settlement> settlements) {
		this.settlements = settlements;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}

	public List<Port> getPorts() {
		return ports;
	}
	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}
	public Robber getRobber() {
		return robber;
	}
	public void setRobber(Robber robber) {
		this.robber = robber;
	}
	
	

}
