package server.command;

import shared.definitions.DevCardType;
import shared.locations.HexLocation;
import shared.model.IGame;
import shared.model.IPlayer;
import shared.model.ModelException;
import shared.model.Player;

import java.util.logging.Logger;


public class SoldierCommand extends AbstractCommand {

    private final static Logger logger = Logger.getLogger("catan");

    private IPlayer local_victim;
    private HexLocation local_hexLocation;

    public SoldierCommand(IGame game, IPlayer player, IPlayer victim, HexLocation hexLocation) throws IllegalCommandException {
        super(game, player, "played a soldier card and " + (victim != null ? "robbed " + victim.getName() : "moved the robber"));

        if (!getGame().canPlaySoldier(hexLocation, player)) {
            throw new IllegalCommandException("Player " + player.getName() + " tried to play a soldier card and move the " +
                                                "the robber to location " + hexLocation.toString());
        }

        local_victim = victim;
        local_hexLocation = hexLocation;
    }


    public void performAction() {
        getPlayer().playDevCard(DevCardType.SOLDIER);

        //Increments the player's soldier count
        getPlayer().incrementSoldiers();

        // Performs the robbing of the player
        getGame().robPlayer(getPlayer(), local_victim, local_hexLocation);

        getGame().calculateVictoryPoints();
        assert (getGame().verifyResourceAmount());
    }

    @Override
    public void setGameAndPlayers(IGame game) throws ModelException {
        super.setGameAndPlayers(game);

        if (local_victim != null && local_victim.getId() != Player.NO_PLAYER) {
            local_victim = getGame().getPlayerByIndex(local_victim.getIndex());
        }
    }
}
