package client;

import java.util.List;

import shared.model.TradeOffer;
import shared.model.GameMap;
import shared.model.MessageList;
import shared.model.Player;
import shared.model.ResourceList;
import shared.model.TurnTracker;

public class ClientModel {

    private ResourceList bank;
    private MessageList chat;
    private MessageList log;
    private GameMap map;
    private List<Player> players;
    private TradeOffer tradeOffer;
    private TurnTracker turnTracker;
    private int version_id;
    private int winner; // is -1 when no winner yet. 0-4 when correspondig
                        // player wins.

    public ClientModel() {
        version_id = 0;
        winner = -1;
    }// end consructor

    // ===========================================================================
    // Getters and Setters
    // ===========================================================================
    public ResourceList getBank() {
        return bank;
    }

    public void setBank(ResourceList bank) {
        this.bank = bank;
    }

    public MessageList getChat() {
        return chat;
    }

    public void setChat(MessageList chat) {
        this.chat = chat;
    }

    public MessageList getLog() {
        return log;
    }

    public void setLog(MessageList log) {
        this.log = log;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public TradeOffer getTradeOffer() {
        return tradeOffer;
    }

    public void setTradeOffer(TradeOffer tradeOffer) {
        this.tradeOffer = tradeOffer;
    }

    public TurnTracker getTurnTracker() {
        return turnTracker;
    }

    public void setTurnTracker(TurnTracker turnTracker) {
        this.turnTracker = turnTracker;
    }

    public int getVersion_id() {
        return version_id;
    }

    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

}// end class
