package shared.models;

import java.util.List;

/**
 * Model for on going game
 * @author HojuneYoo
 *
 */

public class Game {
	
	private String gameTitle;
	private List<Player> players;
	private boolean isStarted;
	private Map map;
	private Bank bank;
	private TurnTracker turnTracker;
	private List<MessageLine> logs;
	private List<MessageLine> chat;
	private int winner;
	private int version;
	private int gameId;
	
	

	public List<MessageLine> getLogs() {
		return logs;
	}

	public void setLogs(List<MessageLine> logs) {
		this.logs = logs;
	}

	public List<MessageLine> getChat() {
		return chat;
	}

	public void setChat(List<MessageLine> chat) {
		this.chat = chat;
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

	public TurnTracker getTurnTracker() {
		return turnTracker;
	}

	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	

}
