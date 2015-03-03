package client.communication;

import shared.models.CatanModel;

/**
 * For server injection. Allows Launcher to easily switch between using SeverProxy or MockServer.
 *
 * @author dbileter
 */

public interface ServerStandinInterface {

    /**
     * Allows poller to tell server to update Model.
     */
    public CatanModel getModel();
}
