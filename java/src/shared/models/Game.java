package shared.models;

import java.util.List;

public class Game {
	
	private int gameId;
	private String gameTitle;
	private List<Player> players;
	private boolean isStarted;
	private Map map;
	private Bank bank;
	private Dice[] dices = new Dice[2];
	
	public void startGame(){
		return;
	}
	
	public void leaveGame(){
		return;
	}
	
	public void SaveGame(){
		
	}
	
	public void LoadGame(){
		
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Dice[] getDices() {
		return dices;
	}

	public void setDices(Dice[] dices) {
		this.dices = dices;
	}
	
	

}
