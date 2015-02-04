package client.communication;

import shared.communicator.BuildCityParams;
import shared.communicator.BuildRoadParams;
import shared.communicator.BuildSettlementParams;
import shared.communicator.BuyDevCardParams;
import shared.communicator.ChangeLogLevelParams;
import shared.communicator.ChangeLogLevelResults;
import shared.communicator.CreateGameParams;
import shared.communicator.CreateGameResults;
import shared.communicator.ExecuteCommandsParams;
import shared.communicator.ExecuteCommandsResults;
import shared.communicator.FinishTurnParams;
import shared.communicator.GetCommandsParams;
import shared.communicator.GetCommandsResults;
import shared.communicator.GetModelResults;
import shared.communicator.JoinGameParams;
import shared.communicator.JoinGameResults;
import shared.communicator.ListAIParams;
import shared.communicator.ListAIResults;
import shared.communicator.ListGamesResults;
import shared.communicator.LoadGameParams;
import shared.communicator.LoadGameResults;
import shared.communicator.MonopolyParams;
import shared.communicator.MonumentParams;
import shared.communicator.MoveParams;
import shared.communicator.PlaySoldierParams;
import shared.communicator.RegisterUserParams;
import shared.communicator.RegisterUserResults;
import shared.communicator.ResetGameResults;
import shared.communicator.RoadBuildingParams;
import shared.communicator.RobPlayerParams;
import shared.communicator.RollNumberParams;
import shared.communicator.SaveGameParams;
import shared.communicator.SaveGameResults;
import shared.communicator.SendChatParams;
import shared.communicator.UserLoginParams;
import shared.communicator.UserLoginResults;
import shared.communicator.YearOfPlentyParams;
import shared.models.CatanModel;

/**
 * Description: Holds the Client Communicator and the Server Facade
 * @author oxbor
 *
 */
@SuppressWarnings("unused")
public class ServerProxy implements ServerStandinInterface, ServerInterface{
	private ClientCommunicator clientComm;
    private ServerFacade serverFacade;
    private CatanModel model_ptr;
	
	ServerProxy(CatanModel model){	
	    this.model_ptr = model;
	    serverFacade = new ServerFacade();
	    
	}//end constructor

    @Override
    public UserLoginResults userLogin(UserLoginParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RegisterUserResults registerUser(RegisterUserParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListGamesResults listGames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CreateGameResults createGame(CreateGameParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JoinGameResults joinGame(JoinGameParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SaveGameResults saveGame(SaveGameParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LoadGameResults loadGame(LoadGameParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GetModelResults getModel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResetGameResults resetGame() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GetCommandsResults getCommands(GetCommandsParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExecuteCommandsResults executeCommands(ExecuteCommandsParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListAIResults listAI(ListAIParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChangeLogLevelResults changeLogLevel(ChangeLogLevelParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel sendChat(SendChatParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel acceptTrade(MoveParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel discardCards(MoveParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel rollNumber(RollNumberParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel buildRoad(BuildRoadParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel buildSettlement(BuildSettlementParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel buildCity(BuildCityParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel offerTrade(MoveParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel maritimeTrade(MoveParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel robPlayer(RobPlayerParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel finishTurn(FinishTurnParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel buyDevCard(BuyDevCardParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel playSoldier(PlaySoldierParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel yearOfPlenty(YearOfPlentyParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel roadBuilding(RoadBuildingParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel monopoly(MonopolyParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel monument(MonumentParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel rollDice(MoveParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void distributeCards(int diceSum) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void leaveGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateModel() {
        // TODO Auto-generated method stub
        
    }

}
