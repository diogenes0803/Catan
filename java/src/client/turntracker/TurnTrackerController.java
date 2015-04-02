package client.turntracker;

import client.base.Controller;
import shared.model.CatanConstants;
import shared.model.GameModelFacade;
import shared.model.IPlayer;
import shared.model.ServerModelFacade;

import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;



public class TurnTrackerController extends Controller implements ITurnTrackerController {

    private final static Logger logger = Logger.getLogger("catan");
    private boolean m_playersInitialized;

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);

        GameModelFacade.instance().getGame().addObserver(this);

        m_playersInitialized = false;
	}
	
	@Override
	public ITurnTrackerView getView() {
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
        try {
            ServerModelFacade.getInstance().finishTurn();
        } catch (Exception e) {
            logger.log(Level.WARNING, "ERROR: See endTurn() in the TurnTrackerController.", e);
        }
    }
	
    @Override
    public void update(Observable o, Object arg) {
        initFromModel();
    }

	private void initFromModel() {
        // get the list of players
        List<IPlayer> players = GameModelFacade.instance().getGame().getPlayers();
        if(players.size() < CatanConstants.NUM_PLAYERS) {
            return;
        }

        // iterate through them and set up their box on the tracker
        for(IPlayer pl : players) {
            // first round initializes the turn tracker
            if (!m_playersInitialized) {
                getView().initializePlayer(pl.getIndex(), pl.getName(), pl.getColor());
            }
            getView().updatePlayer(
                            pl.getIndex(),
                            pl.getVictoryPoints(),
                            GameModelFacade.instance().getGame().isPlayersTurn(pl),
                            GameModelFacade.instance().playerHasLargestArmy(pl),
                            GameModelFacade.instance().playerHasLongestRoad(pl),
                            pl.getColor()
                        );
        }
        m_playersInitialized = true;

        String gameState;
        if (GameModelFacade.instance().localPlayerIsPlaying()) {
            gameState = "Finish Turn";
        }
        else if (GameModelFacade.instance().localPlayerIsBeingOfferedTrade()) {
            gameState = "Accept or Reject Trade";
        }
        else if (GameModelFacade.instance().localPlayerIsDiscarding()) {
            gameState = "Discard Cards";
        }
        else if (GameModelFacade.instance().localPlayerIsRolling()) {
            gameState = "Roll the Dice";
        }
        else if (GameModelFacade.instance().localPlayerPlayerIsRobbing()) {
            gameState = "Place the Robber";
        }
        else if (GameModelFacade.instance().localPlayerIsPlacingInitialPieces()) {
            gameState = "Place Initial Pieces";
        }
        else {
            gameState = "Waiting for Other Players";
        }

        getView().setLocalPlayerColor((GameModelFacade.instance().getLocalColor()));
        getView().updateGameState(gameState, GameModelFacade.instance().localPlayerIsPlaying());
	}
}

