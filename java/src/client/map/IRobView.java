package client.map;

import client.base.IOverlayView;
import client.data.RobPlayerInfo;

/**
 * Interface for the rob view, which lets the user select a player to rob
 */
public interface IRobView extends IOverlayView {

    void setPlayers(RobPlayerInfo[] candidateVictims);
}

