package server.command;

import shared.definitions.ResourceType;
import shared.model.IGame;
import shared.model.IPlayer;


public class MaritimeTradeCommand extends AbstractCommand {

    private int ratio;
    private ResourceType inputResource;
    private ResourceType outputResource;

    public MaritimeTradeCommand (IGame theGame, IPlayer player, ResourceType inputResource, ResourceType outputResource, int ratio) throws IllegalCommandException {

        super(theGame, player, "performed a maritime trade");
        this.ratio = ratio;
        this.inputResource = inputResource;
        this.outputResource = outputResource;

        // check to see if the command is valid
        if(!getGame().canMaritimeTrade(player, inputResource, outputResource, ratio)) {
            throw new IllegalCommandException(
                    String.format("%s cannot trade %d %s for 1 %s.", player.getName(), ratio, inputResource.toString().toLowerCase(), outputResource.toString().toLowerCase())
            );
        }
    }


    public void performAction() {
        // get the player from the index
        //IPlayer p = getGame().getPlayers().get(playerIndex);

        // remove the input resources from the player
        getPlayer().getResources().subtract(ratio, inputResource);

        // give the input resources to the game bank
        getGame().getResourceBank().add(ratio, inputResource);

        // add the output resources to the player
        getPlayer().getResources().add(1, outputResource);

        // remove output resources from the game bank
        getGame().getResourceBank().subtract(1, outputResource);

        assert (getGame().verifyResourceAmount());
    }
}
