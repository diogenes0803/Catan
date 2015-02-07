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
        
    	UserLoginResults results = new UserLoginResults();
    	
    	Object response = clientComm.post("/user/login", params);
    	
        return results;
    }

    @Override
    public RegisterUserResults registerUser(RegisterUserParams params) {

    	RegisterUserResults results = new RegisterUserResults();
    	
    	Object response = clientComm.post("/user/register", params);
    	
        return results;
    }

    @Override
    public ListGamesResults listGames() {

    	ListGamesResults results = new ListGamesResults();
    	
    	Object response = clientComm.get("/games/list");
    	
        return results;
    }

    @Override
    public CreateGameResults createGame(CreateGameParams params) {

    	CreateGameResults results = new CreateGameResults();
    	
    	Object response = clientComm.post("/user/create", params);
    	
        return results;
    }

    @Override
    public JoinGameResults joinGame(JoinGameParams params) {

    	JoinGameResults results = new JoinGameResults();
    	
    	Object response = clientComm.post("/user/join", params);
    	
        return results;
    }

    @Override
    public SaveGameResults saveGame(SaveGameParams params) {

    	SaveGameResults results = new SaveGameResults();
    	
    	Object response = clientComm.post("/user/save", params);
    	
        return results;
    }

    @Override
    public LoadGameResults loadGame(LoadGameParams params) {

    	LoadGameResults results = new LoadGameResults();
    	
    	Object response = clientComm.post("/user/load", params);
    	
        return results;
    }

    @Override
    public CatanModel getModel() {

    	CatanModel results = (CatanModel)clientComm.get("/games/model");
    	
        return results;
    }

    @Override
    public CatanModel resetGame() {

    	CatanModel results = (CatanModel)clientComm.get("/games/reset");
    	
        return results;
    }

    @Override
    public GetCommandsResults getCommands(GetCommandsParams params) {

    	GetCommandsResults results = new GetCommandsResults();
    	
    	Object response = clientComm.post("/user/commands", params);
    	
        return results;
    }

    @Override
    public CatanModel executeCommands(ExecuteCommandsParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/commands", params);
    	
        return results;
    }

    @Override
    public ListAIResults listAI(ListAIParams params) {

    	ListAIResults results = new ListAIResults();
    	
    	Object response = clientComm.post("/user/listAI", params);
    	
        return results;
    }

    @Override
    public ChangeLogLevelResults changeLogLevel(ChangeLogLevelParams params) {

    	ChangeLogLevelResults results = new ChangeLogLevelResults();
    	
    	Object response = clientComm.post("/user/changeloglevel", params);
    	
        return results;
    }

    @Override
    public CatanModel sendChat(SendChatParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/sendChat", params);
    	
        return results;
    }

    @Override
    public CatanModel acceptTrade(AcceptTradeParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/acceptTrade", params);
    	
        return results;
    }

    @Override
    public CatanModel discardCards(DiscardCardsParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/discardCards", params);
    	
        return results;
    }

    @Override
    public CatanModel rollNumber(RollNumberParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/rollNumber", params);
    	
        return results;
    }

    @Override
    public CatanModel buildRoad(BuildRoadParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/buildRoad", params);
    	
        return results;
    }

    @Override
    public CatanModel buildSettlement(BuildSettlementParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/buildSettlement", params);
    	
        return results;
    }

    @Override
    public CatanModel buildCity(BuildCityParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/buildCity", params);
    	
        return results;
    }

    @Override
    public CatanModel offerTrade(OfferTradeParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/offerTrade", params);
    	
        return results;
    }

    @Override
    public CatanModel maritimeTrade(MaritimeTradeParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/maritimeTrade", params);
    	
        return results;
    }

    @Override
    public CatanModel robPlayer(RobPlayerParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/robPlayer", params);
    	
        return results;
    }

    @Override
    public CatanModel finishTurn(FinishTurnParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/finishTurn", params);
    	
        return results;
    }

    @Override
    public CatanModel buyDevCard(BuyDevCardParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/buyDevCard", params);
    	
        return results;
    }

    @Override
    public CatanModel playSoldier(PlaySoldierParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/Soldier", params);
    	
        return results;
    }

    @Override
    public CatanModel yearOfPlenty(YearOfPlentyParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/Year_of_Plenty", params);
    	
        return results;
    }

    @Override
    public CatanModel roadBuilding(RoadBuildingParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/Road_Building", params);
    	
        return results;
    }

    @Override
    public CatanModel monopoly(MonopolyParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/Monopoly", params);
    	
        return results;
    }

    @Override
    public CatanModel monument(MonumentParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/Monument", params);
    	
        return results;
    }

    @Override
    public CatanModel rollDice(MoveParams params) {

    	CatanModel results = (CatanModel)clientComm.post("/games/rollNumber", params);
    	
        return results;
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
