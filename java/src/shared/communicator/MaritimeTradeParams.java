/**
 * 
 */
package shared.communicator;

/**
 * @author campbeln
 *
 */
public class MaritimeTradeParams {

	private int playerIndex;
	private int ratio;
	private String inputResource;
	private String outputResource;
	private String type;
	
	public MaritimeTradeParams(int playerIndex, int ratio, String inputResource, String outputResource) {
		this.playerIndex = playerIndex;
		this.ratio = ratio;
		this.inputResource = inputResource;
		this.outputResource = outputResource;
		setType("maritimeTrade");
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
	 * @return the ratio
	 */
	public int getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set (i.e. 3 for 3:1 trade)
	 */
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	/**
	 * @return the inputResource
	 */
	public String getInputResource() {
		return inputResource;
	}

	/**
	 * @param inputResource the inputResource to set
	 */
	public void setInputResource(String inputResource) {
		this.inputResource = inputResource;
	}

	/**
	 * @return the outputResource
	 */
	public String getOutputResource() {
		return outputResource;
	}

	/**
	 * @param outputResource the outputResource to set
	 */
	public void setOutputResource(String outputResource) {
		this.outputResource = outputResource;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
