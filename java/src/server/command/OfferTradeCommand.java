package server.command;

import shared.model.*;


public class OfferTradeCommand extends AbstractCommand {

    private IResourceBank offer;
    private IPlayer receiver;

    public OfferTradeCommand(IGame theGame, IPlayer player, IPlayer receiver, IResourceBank offer) throws IllegalCommandException {

        super(theGame, player, "offered a trade to " + (receiver != null ? receiver.getName() : ""));
        this.offer = offer;
        this.receiver = receiver;

        // is the trade offer null?
        if (getGame().getTradeOffer() != null) {
            throw new IllegalCommandException("There is already a trade being offered!");
        }
        else if (!player.canAfford(offer) || !offer.isValidTradeOffer() || receiver == null || player.equals(receiver)) {
            throw new IllegalCommandException("Invalid trade offer.");
        }
    }


    public void performAction() {
        // create a new trade offer with the parameters
        ITradeOffer trade = new TradeOffer(getPlayer(), receiver, offer);

        // set the game object to point at the trade offer
        getGame().setTradeOffer(trade);
    }

    @Override
    public void setGameAndPlayers(IGame game) throws ModelException {
        super.setGameAndPlayers(game);
        receiver = getGame().getPlayerByIndex(receiver.getIndex());
    }
}
