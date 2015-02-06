package client.communication;

import shared.communicator.*;
import shared.models.CatanModel;

/**
 * Description: Holds the Client Communicator and the Server Facade
 * @author oxbor, campbeln, dbilleter
 *
 */
@SuppressWarnings("unused")
public class ServerProxy implements ServerStandinInterface, ServerInterface{
	private ClientCommunicator clientComm;
	
	public ServerProxy(){	
		clientComm = new ClientCommunicator();
	}//end constructor

    @Override
    public UserLoginResults userLogin(UserLoginParams params) {
       
    	//HttpURLResponse blah =  clientComm.post("/user/login", params);
    	// if blah == 200
    	UserLoginResults results = new UserLoginResults();
    	
        return results;
    }

    @Override
    public RegisterUserResults registerUser(RegisterUserParams params) {
       
    	RegisterUserResults results = (RegisterUserResults) clientComm.post("/user/register", params);
        return results;
    }

    @Override
    public ListGamesResults listGames() {
    	
    	ListGamesResults results = (ListGamesResults) clientComm.get("/games/list");
        return results;
    }

    @Override
    public CreateGameResults createGame(CreateGameParams params) {
    	
    	CreateGameResults results = (CreateGameResults) clientComm.post("/game/create", params);
        return results;
    }

    @Override
    public JoinGameResults joinGame(JoinGameParams params) {
    	
    	JoinGameResults results = (JoinGameResults) clientComm.post("/game/join", params);
        return results;
    }

    @Override
    public SaveGameResults saveGame(SaveGameParams params) {
    	
    	SaveGameResults results = (SaveGameResults) clientComm.post("/game/save", params);
        return results;
    }

    @Override
    public LoadGameResults loadGame(LoadGameParams params) {
    	
    	LoadGameResults results = (LoadGameResults) clientComm.post("/game/load", params);
        return results;
    }

    @Override
    public GetModelResults getModel() {
    	
    	GetModelResults results = (GetModelResults) clientComm.get("/games/model");
        return results;
    }

    @Override
    public ResetGameResults resetGame() {
    	ResetGameResults results = (ResetGameResults) clientComm.get("/games/reset");
        return results;
    }

    @Override
    public GetCommandsResults getCommands() {
    	
    	GetCommandsResults results = (GetCommandsResults) clientComm.get("/game/commands");
        return results;
    }

    @Override
    public ExecuteCommandsResults executeCommands(ExecuteCommandsParams params) {
    	
    	ExecuteCommandsResults results = (ExecuteCommandsResults) clientComm.post("/game/commands", params);
        return results;
    }

    @Override
    public ListAIResults listAI() {
    	ListAIResults results = (ListAIResults) clientComm.get("/game/listAI");
        return results;
    }
    
    @Override
	public String addAI(String AIType) {
    	
    	String results = (String) clientComm.get("/game/addAI");
        return results;
	}

    @Override
    public ChangeLogLevelResults changeLogLevel(ChangeLogLevelParams params) {

    	ChangeLogLevelResults results = (ChangeLogLevelResults) clientComm.post("/game/changeloglevel", params);
        return results;
    }

    @Override
    public CatanModel sendChat(SendChatParams params) {
        
    	CatanModel results = (CatanModel) clientComm.post("/game/sendChat", params);
        return results;
    }

    @Override
    public CatanModel acceptTrade(AcceptTradeParams params) {
    	
    	CatanModel results = (CatanModel) clientComm.post("/game/acceptTrade", params);
        return results;
    }

    @Override
    public CatanModel discardCards(DiscardCardsParams params) {

    	CatanModel results = (CatanModel) clientComm.post("/game/discardCardsParams", params);
        return results;
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
    public CatanModel offerTrade(OfferTradeParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel maritimeTrade(MaritimeTradeParams params) {
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
