package client.turntracker;

import client.base.IController;

/**
 * Interface for the turn tracker controller
 */
public interface ITurnTrackerController extends IController {

    /**
     * This is called when the local player ends their turn
     */
    void endTurn();
}

