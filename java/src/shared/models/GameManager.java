package shared.models;

import java.util.Observable;

import shared.data.GameInfo;

public class GameManager extends Observable {

    private boolean isJoinedGame;
    private Game game;
    private GameInfo[] availableGames;

    public GameManager() {
        isJoinedGame = false;
        game = null;
        availableGames = null;
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

    public GameInfo[] getAvailableGames() {
        return availableGames;
    }

    public void setAvailableGames(GameInfo[] availableGames) {
        this.availableGames = availableGames;
        setChanged();
        notifyObservers(availableGames);
    }

    public void setJoinedGame(boolean isJoinedGame) {
        this.isJoinedGame = isJoinedGame;
    }

    public void changed() {
        setChanged();
    }
    
    public GameInfo findGameInfoById(int gameId) {
    	for(GameInfo gameInfo : availableGames) {
    		if(gameInfo.getId() == gameId) {
    			return gameInfo;
    		}
    	}
    	return null;
    }


}
