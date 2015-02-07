package shared.models.jsonholder;

import java.util.List;

import shared.models.Game;

public class JsonModelHolder {
	private Deck deck;
	private List<Player> players;
	private Log log;
	private Chat chat;
	private Bank bank;
	private TurnTracker turnTracker;
	private int winner;
	private int version;
	
	public Game buildCatanModel() {
		Game game = new Game();
		game.setWinner(winner);
		game.setVersion(version);
		
		return null;
	}
	
	
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public Chat getChat() {
		return chat;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
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
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
