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
    public CatanModel acceptTrade(AcceptTradeParams params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatanModel discardCards(DiscardCardsParams params) {
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
