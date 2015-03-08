package client.points;

import client.base.Controller;
import client.communication.ServerProxy;
import shared.models.CatanModel;
import shared.models.Game;

import java.util.Observable;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController {

    private IGameFinishedView finishedView;

    /**
     * PointsController constructor
     *
     * @param view         Points view
     * @param finishedView Game finished view, which is displayed when the game is over
     */
    public PointsController(IPointsView view, IGameFinishedView finishedView) {

        super(view);

        setFinishedView(finishedView);

        initFromModel();
    }

    public IPointsView getPointsView() {

        return (IPointsView) super.getView();
    }

    public IGameFinishedView getFinishedView() {
        return finishedView;
    }

    public void setFinishedView(IGameFinishedView finishedView) {
        this.finishedView = finishedView;
    }

    private void initFromModel() {
        //<temp>
        getPointsView().setPoints(0);
        //</temp>
    }

    @Override
    public void update(Observable o, Object arg) {
    	if(arg instanceof Game) {
	    	int points = CatanModel.getInstance().getGameManager().getGame().getPlayers()[CatanModel.getInstance().getGameManager().getGame().getPlayerIndexByPlayerId(ServerProxy.getInstance().getlocalPlayer().getId())].getVictoryPoint();
	    	if (points >= 10)
	    	{
	    		finishedView.showModal();
	    	}
	    	else
	    	{
	    		getPointsView().setPoints(points);
	    	}
    	}

    }

}

