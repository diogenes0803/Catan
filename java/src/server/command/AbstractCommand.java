package server.command;

import shared.model.IGame;
import shared.model.IPlayer;
import shared.model.ModelException;
import shared.model.Player;

import java.util.logging.Logger;


public abstract class AbstractCommand implements ICommand {
    private static Logger logger = Logger.getLogger("catanserver");

    private IPlayer local_player;
    private transient IGame local_game;
    private String local_action;

    public AbstractCommand(IGame game, IPlayer player, String actionDescription) throws IllegalCommandException {
        if (player == null) {
            throw new IllegalCommandException("No player was specified.");
        }

        assert game != null;
        local_game = game;
        local_player = player;
        local_action = actionDescription;
    }


    public AbstractCommand(IGame game, IPlayer player) throws IllegalCommandException {
        this(game, player, null);
    }


    @Override
    public final void execute() {
        logger.entering(this.getClass().getCanonicalName(), "execute");

        getGame().incrementVersion();
        logAction();
        performAction();

        logger.exiting(this.getClass().getCanonicalName(), "execute");
    }


    protected abstract void performAction();


    private void logAction() {
        if (local_action != null) {
            String message = local_player.getName() + " " + local_action;
            getGame().getGameplayLog().addMessage(local_player, message);
            logger.info(message);
        }
    }

    protected final IPlayer getPlayer() {
        return local_player;
    }

    @Override
    public void setGameAndPlayers(IGame game) throws ModelException {
        assert local_game == null;
        local_game = game;

        // check if this is a command with no player (i.e. a join game command)
        if (local_player.getIndex() != Player.NO_PLAYER) {
            local_player = local_game.getPlayerByIndex(local_player.getIndex());
        }
    }

    @Override
    public final IGame getGame() {
        assert local_game != null : "Game has not been set!";
        return local_game;
    }
}
