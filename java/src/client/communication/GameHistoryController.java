package client.communication;

import java.util.*;
import java.util.logging.Logger;

import client.base.*;
import shared.model.GameModelFacade;



public class GameHistoryController extends Controller implements IGameHistoryController {

    private final static Logger logger = Logger.getLogger("catan");

    public GameHistoryController(IGameHistoryView view) {
		
		super(view);

        GameModelFacade.instance().getGame().addObserver(this);
	}
	
	@Override
	public IGameHistoryView getView() {
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
        getView().setEntries(GameModelFacade.instance().getMoveHistory().getLogEntries());
	}

    @Override
    public void update(Observable o, Object arg) {
        initFromModel();
    }
}

