package client.turntracker;

import client.base.Controller;
import shared.definitions.CatanColor;

import java.util.Observable;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {

    public TurnTrackerController(ITurnTrackerView view) {

        super(view);

        initFromModel();
    }

    @Override
    public ITurnTrackerView getView() {

        return (ITurnTrackerView) super.getView();
    }

    @Override
    public void endTurn() {

    }

    private void initFromModel() {
        //<temp>
        getView().setLocalPlayerColor(CatanColor.RED);
        //</temp>
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub

    }

}

