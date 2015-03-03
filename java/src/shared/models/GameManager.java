package shared.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GameManager extends Observable {

    private boolean isJoinedGame;
    private Game game;
    private List<GameInfo> availableGames;

    public GameManager() {
        isJoinedGame = false;
        game = null;
        availableGames = new ArrayList<GameInfo>();
    }


    public boolean isJoinedGame() {
        return isJoinedGame;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        setChanged();
        notifyObservers(game);
    }

    public List<GameInfo> getAvailableGames() {
        return availableGames;
    }

    public void setAvailableGames(List<GameInfo> availableGames) {
        this.availableGames = availableGames;
    }

    public void setJoinedGame(boolean isJoinedGame) {
        this.isJoinedGame = isJoinedGame;
    }

    public void changed() {
        setChanged();
    }


}
