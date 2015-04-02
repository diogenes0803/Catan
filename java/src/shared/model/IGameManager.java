package shared.model;

import java.util.Collection;


public interface IGameManager {


    public IGame createGame(String gameName, boolean randomPorts, boolean randomTiles, boolean randomNumbers) throws ModelException;


    public IGame getGame(int gameId) throws ModelException;


    public Collection<IGame> listGames();


    public void loadGame(IGame game);
}
