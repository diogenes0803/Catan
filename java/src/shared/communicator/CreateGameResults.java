package shared.communicator;

import shared.models.Player;

/**
 * Dumb data holder
 *
 * @author Jonathan
 */
public class CreateGameResults {
    String title;
    int id;
    Player[] players = new Player[4];

    public CreateGameResults(String title, int id, Player[] players2) {
        this.title = title;
        this.id = id;
        this.players = players2;
    }

    public CreateGameResults() {
        // TODO Auto-generated constructor stub
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }


}