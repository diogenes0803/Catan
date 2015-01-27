package shared.models;

public class TurnTracker {
	
	private int turnCount;
	private int thisTurnPlayerId;
	private int firstDice;
	private int secondDice;
	
	public int getTurnCount() {
		return turnCount;
	}
	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}
	public int getThisTurnPlayerId() {
		return thisTurnPlayerId;
	}
	public void setThisTurnPlayerId(int thisTurnPlayerId) {
		this.thisTurnPlayerId = thisTurnPlayerId;
	}
	public int getFirstDice() {
		return firstDice;
	}
	public void setFirstDice(int firstDice) {
		this.firstDice = firstDice;
	}
	public int getSecondDice() {
		return secondDice;
	}
	public void setSecondDice(int secondDice) {
		this.secondDice = secondDice;
	}

}
