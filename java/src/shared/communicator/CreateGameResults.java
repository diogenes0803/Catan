package shared.communicator;

import shared.models.Player;

import java.util.List;

/**
 * Dumb data holder
 *
 * @author Jonathan
 */
public class CreateGameResults {
    String title;
    int id;
    List<Player> players;
    boolean success;

    public CreateGameResults(String title, int id, List<Player> players2) {
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}