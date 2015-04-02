package server.facade;

import server.command.ICommand;
import server.command.IllegalCommandException;
import server.command.JoinGameCommand;
import server.persistence.IPersistenceManager;
import server.persistence.PersistenceException;
import shared.communication.*;
import shared.model.IGame;
import shared.model.IGameManager;
import shared.model.IUserManager;
import shared.model.ModelException;

import java.io.*;


public class JoinGameFacade implements IJoinGameFacade {
    private IPersistenceManager local_persistenceManager;
    private IGameManager local_gameManager;
    private IUserManager local_userManager;

    public JoinGameFacade(IGameManager gameManager, IUserManager userManager, IPersistenceManager persistenceManager) {
        local_gameManager = gameManager;
        local_userManager = userManager;
        local_persistenceManager = persistenceManager;
    }


    @Override
    public GameInfo[] list() {
        return GameInfo.toGameInfoArray(local_gameManager.listGames());
    }


    @Override
    public GameInfo create(CreateGameRequestParams params) throws ModelException {
        IGame game = local_gameManager.createGame(params.name, params.randomPorts, params.randomTiles, params.randomNumbers);

        try {
            local_persistenceManager.startTransaction();
            local_persistenceManager.createGamesDAO().saveGame(game);
            local_persistenceManager.endTransaction(true);
        }
        catch (PersistenceException e) {
            local_persistenceManager.endTransaction(false);
        }

        return new GameInfo(game);
    }


    @Override
    public Integer join(JoinGameRequestParams params) throws ModelException, IllegalCommandException {
        ICommand command = new JoinGameCommand(local_gameManager.getGame(params.id), local_userManager.getUser(params.getUserId()), params.color);
        MovesFacade.serializeCommand(local_persistenceManager, command);
        command.execute();
        return params.id;
    }


    @Override
    public void save(SaveGameRequestParams saveGame) throws IOException, ModelException {
        IGame game = local_gameManager.getGame(saveGame.id);

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(saveGame.name))) {
            writer.writeObject(game);
        }
    }


    @Override
    public void load(LoadGameRequestParams loadGame) throws IOException {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(loadGame.name))) {
            local_gameManager.loadGame((IGame) reader.readObject());
        } catch (ClassNotFoundException e) {
            throw new IOException("Failed reading game from disk.", e);
        }
    }
}
