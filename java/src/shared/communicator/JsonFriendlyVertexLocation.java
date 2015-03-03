/**
 * 
 */
package shared.communicator;

import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * @author campbeln
 *
 */
public class JsonFriendlyVertexLocation {

	private int x;
	private int y;
	private String direction;
	
	public JsonFriendlyVertexLocation(int x, int y, String direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public JsonFriendlyVertexLocation(VertexLocation vertexLocation) {
		x = vertexLocation.getHexLoc().getX();
		y = vertexLocation.getHexLoc().getY();
		direction = vertexToString(vertexLocation.getDir());
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
	
	private String vertexToString(VertexDirection dir) {
		String result = null;
		
		switch (dir) {
			
		case East: result = "E";
		break;
		case NorthWest: result = "NW";
		break;
		case NorthEast: result = "NE";
		break;
		case West: result = "W";
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
