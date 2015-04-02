package client.points;

import client.base.Controller;
import shared.model.CatanConstants;
import shared.model.GameModelFacade;

import java.util.Observable;
import java.util.logging.Logger;



public class PointsController extends Controller implements IPointsController {

    private final static Logger logger = Logger.getLogger("catan");

	private IGameFinishedView finishedView;
	

	public PointsController(IPointsView view, IGameFinishedView finishedView) {
		
		super(view);
		
		setFinishedView(finishedView);

        GameModelFacade.instance().getGame().addObserver(this);
	}
	
	public IPointsView getPointsView() {
		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView() {
		return finishedView;
	}
	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

    @Override
    public void gameFinishedModalClosed() {
        client.main.Catan.leaveGame();
    }

	private void initFromModel() {
        if (GameModelFacade.instance().getGame().getWinner() != null) {
            getFinishedView().setWinner(GameModelFacade.instance().getGame().getWinner().getName(), GameModelFacade.instance().getGame().getWinner().equals(GameModelFacade.instance().getLocalPlayer()));

            if (!getFinishedView().isModalShowing()) {
                getFinishedView().showModal();
            }
        }

        // only set up to 10 points so that the view doesn't get angry at us
        int points = GameModelFacade.instance().getLocalPlayer().getVictoryPoints();
        assert points >= 0;

        if (points <= CatanConstants.VICTORY_POINTS_TO_WIN) {
            getPointsView().setPoints(points);
        }
        else {
            getPointsView().setPoints(CatanConstants.VICTORY_POINTS_TO_WIN);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        initFromModel();
    }
}

