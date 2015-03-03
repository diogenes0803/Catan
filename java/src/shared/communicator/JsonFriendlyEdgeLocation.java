/**
 * 
 */
package shared.communicator;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;

/**
 * @author campbeln
 *
 */
public class JsonFriendlyEdgeLocation {
	
	private int x;
	private int y;
	private String direction;
	
	public JsonFriendlyEdgeLocation(int x, int y, String direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public JsonFriendlyEdgeLocation(EdgeLocation edgeLocation) {
		x = edgeLocation.getHexLoc().getX();
		y = edgeLocation.getHexLoc().getY();
		direction = edgeToString(edgeLocation.getDir());
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	private String edgeToString(EdgeDirection dir) {
		String result = null;
		
		switch (dir) {
			
		case North: result = "N";
		break;
		case NorthWest: result = "NW";
		break;
		case NorthEast: result = "NE";
		break;
		case South: result = "S";
		break;
		case SouthEast: result = "SE";
		break;
		case SouthWest: result = "SW";
		break;
		default: result = null;
		break;
		}
		
		return result;
	}
}
