package server.command;

import shared.locations.HexLocation;
import shared.model.IGame;
import shared.model.IPlayer;
import shared.model.ModelException;
import shared.model.Player;


public class RobPlayerCommand extends AbstractCommand {
    private IPlayer local_victim;
    private HexLocation local_hexLocation;

    public RobPlayerCommand(IGame game, IPlayer player, IPlayer victim, HexLocation location) throws IllegalCommandException {
        super(game, player, "moved the robber" + (victim != null ? " and robbed " + victim.getName() : ""));

        if (!getGame().getMap().canPlaceRobber(location)) {
            throw new IllegalCommandException(("Player " + player.getName() + "tried to place the robber " +
                    "on hex location " + location.toString() + " but the robber is already there"));
        }

        if (victim != null && victim.getResources().getCount() == 0) {
            throw new IllegalCommandException("Player " + player.getName() + " tried to rob from " + victim.getName() + " but they had no cards");
        }

        local_victim = victim;
        local_hexLocation = location;
    }


    public void performAction() {
        getGame().robPlayer(getPlayer(), local_victim, local_hexLocation);

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
