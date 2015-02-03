package shared.communicator;
/**
 * Dumb data holder
 * @author Jonathan
 *
 */
public class CreateGameParams {
	boolean randomTiles;
	boolean randomNumbers;
	boolean randomPorts;
	
	CreateGameParams(boolean randomTiles, boolean randomNumbers, boolean randomPorts)
	{
		this.randomTiles = randomTiles;
		this.randomNumbers = randomNumbers;
		this.randomPorts = randomPorts;
	}

	public boolean isRandomTiles() {
		return randomTiles;
	}

	public void setRandomTiles(boolean randomTiles) {
		this.randomTiles = randomTiles;
	}

	public boolean isRandomNumbers() {
		return randomNumbers;
	}

	public void setRandomNumbers(boolean randomNumbers) {
		this.randomNumbers = randomNumbers;
	}

	public boolean isRandomPorts() {
		return randomPorts;
	}

	public void setRandomPorts(boolean randomPorts) {
		this.randomPorts = randomPorts;
	}
	
	
}
