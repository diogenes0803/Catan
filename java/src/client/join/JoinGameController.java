package client.join;

import client.base.Controller;
import client.base.IAction;
import client.communication.ServerProxy;
import client.data.GameInfo;
import client.misc.IMessageView;
import shared.communicator.CreateGameParams;
import shared.communicator.CreateGameResults;
import shared.communicator.JoinGameParams;
import shared.communicator.JoinGameResults;
import shared.definitions.CatanColor;
import shared.models.CatanModel;

import java.util.Observable;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController {

    private INewGameView newGameView;
    private ISelectColorView selectColorView;
    private IMessageView messageView;
    private IAction joinAction;
    private GameInfo gameToJoin;

    /**
     * JoinGameController constructor
     *
     * @param view            Join game view
     * @param newGameView     New game view
     * @param selectColorView Select color view
     * @param messageView     Message view (used to display error messages that occur while the user is joining a game)
     */
    public JoinGameController(IJoinGameView view, INewGameView newGameView,
                              ISelectColorView selectColorView, IMessageView messageView) {

        super(view);

        setNewGameView(newGameView);
        setSelectColorView(selectColorView);
        setMessageView(messageView);
    }

    public IJoinGameView getJoinGameView() {

        return (IJoinGameView) super.getView();
    }

    /**
     * Returns the action to be executed when the user joins a game
     *
     * @return The action to be executed when the user joins a game
     */
    public IAction getJoinAction() {

        return joinAction;
    }

    /**
     * Sets the action to be executed when the user joins a game
     *
     * @param value The action to be executed when the user joins a game
     */
    public void setJoinAction(IAction value) {

        joinAction = value;
    }

    public INewGameView getNewGameView() {

        return newGameView;
    }

    public void setNewGameView(INewGameView newGameView) {

        this.newGameView = newGameView;
    }

    public ISelectColorView getSelectColorView() {

        return selectColorView;
    }

    public void setSelectColorView(ISelectColorView selectColorView) {

        this.selectColorView = selectColorView;
    }

    public IMessageView getMessageView() {

        return messageView;
    }

    public void setMessageView(IMessageView messageView) {

        this.messageView = messageView;
    }

    @Override
    public void start() {
        GameInfo[] games = ServerProxy.getInstance().listGames().getGames();
        getJoinGameView().showModal();
        getJoinGameView().setGames(games, ServerProxy.getInstance().getlocalPlayer());
    }

    @Override
    public void startCreateNewGame() {

        getNewGameView().showModal();
    }

    @Override
    public void cancelCreateNewGame() {

        getNewGameView().closeModal();
    }

    @Override
    public void createNewGame() {
        boolean randomTiles = getNewGameView().getRandomlyPlaceHexes();
        boolean randomNumbers = getNewGameView().getRandomlyPlaceNumbers();
        boolean randomPorts = getNewGameView().getUseRandomPorts();
        String name = getNewGameView().getTitle();
        CreateGameParams params = new CreateGameParams(randomTiles, randomNumbers, randomPorts, name);

        CreateGameResults result = ServerProxy.getInstance().createGame(params);
        GameInfo game = new GameInfo();
        game.setId(result.getId());
        game.setTitle(result.getTitle());

        getNewGameView().closeModal();
        startJoinGame(game);
    }

    @Override
    public void startJoinGame(GameInfo game) {

        gameToJoin = game;
        getSelectColorView().showModal();
    }

    @Override
    public void cancelJoinGame() {

        getJoinGameView().closeModal();
    }

    @Override
    public void joinGame(CatanColor color) {
        int id = gameToJoin.getId();
        String colorString = CatanColor.getStringColor(color);
        JoinGameParams params = new JoinGameParams(id, colorString);
        JoinGameResults result = ServerProxy.getInstance().joinGame(params);

        if (result.isSuccess()) {
            // If join succeeded
            getSelectColorView().closeModal();
            getJoinGameView().closeModal();

            CatanModel.getInstance().getGameManager().setGame(ServerProxy.getInstance().getModel().getGameManager().getGame());
            CatanModel.getInstance().getGameManager().setJoinedGame(true);

            joinAction.execute();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        GameInfo[] games = ServerProxy.getInstance().listGames().getGames();
        getJoinGameView().setGames(games, ServerProxy.getInstance().getlocalPlayer());
    }

}

