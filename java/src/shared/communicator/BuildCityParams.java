/**
 * 
 */
package shared.communicator;

import shared.locations.VertexLocation;

/**
 * @author campbeln
 *
 */
public class BuildCityParams {

	private String type;
	private int playerIndex;
	private JsonFriendlyVertexLocation vertexLocation;
	
	public BuildCityParams(int playerIndex, VertexLocation vertexLocation) {
		this.playerIndex = playerIndex;
		this.setVertexLocation(new JsonFriendlyVertexLocation(vertexLocation));
		setType("buildCity");
	}

	/**
	 * @return the playerIndex
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * @param playerIndex the playerIndex to set
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	/**
	 * @return the vertexLocation
	 */
	public JsonFriendlyVertexLocation getVertexLocation() {
		return vertexLocation;
	}

	/**
	 * @param vertexLocation the vertexLocation to set
	 */
	public void setVertexLocation(JsonFriendlyVertexLocation vertexLocation) {
		this.vertexLocation = vertexLocation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
