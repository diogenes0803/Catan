package client.communication;

import shared.communicator.*;
import shared.models.CatanModel;

/**
 * Description: The Server Facade takes in parameter objects for the commands that the server is able to perform,
 * passes them to the client communicator and then receives a results objects back containing the server's response 
 * @author oxbor
 *
 */
/**
 * @author Nate Campbell
 * @author Jonathan Oxborrow
 */
public class ServerFacade implements ServerInterface {
	
	/**
	 * @pre username is not null, password is not null
	 * 
	 * @post If the passed�in (username, password) pair is valid,
	 * 	1. The server returns an HTTP 200 success response with "Success" in the body.
	 * 	2. The HTTP response headers set the catan.user cookie to contain the identity of the
	 * 	logged�in player. The cookie uses "Path=/", and its value contains a url�encoded JSON object of
	 * 	the following form: { "name": STRING, "password": STRING, "playerID": INTEGER }. For
	 * 	example, { "name": "Rick", "password": "secret", "playerID": 14 }.
	 * 	If the passed�in (username, password) pair is not valid, or the operation fails for any other
	 * 	reason,
	 * 	1. The server returns an HTTP 400 error response, and the body contains an error
	 * 	message.
	 */
	public UserLoginResults userLogin(UserLoginParams params){
		return null;
	}
	
	/**
	 * @pre username is not null, password is not null, The specified username is not already in use.
	 * 
	 * @post If there is no existing user with the specified username,
	 * 1. A new user account has been created with the specified username and password.
	 * 2. The server returns an HTTP 200 success response with "Success" in the body.
	 * 3. The HTTP response headers set the catan.user cookie to contain the identity of the
	 * logged�in player. The cookie uses "Path=/", and its value contains a url�encoded JSON object of
	 * the following form: { "name": STRING, "password": STRING, "playerID": INTEGER }. For
	 * example, { "name": "Rick", "password": "secret", "playerID": 14 }.
	 * If there is already an existing user with the specified name, or the operation fails for any other
	 * reason,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public RegisterUserResults registerUser(RegisterUserParams params){
		return null;
	}
	
	/**
	 * @pre None.
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response.
	 * 2. The body contains a JSON array containing a list of objects that contain information
	 * about the server's games
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public ListGamesResults listGames(){
		return null;
	}
	
	/**
	 * @pre name != null 
	 * randomTiles, randomNumbers, and randomPorts contain valid boolean values
	 * 
	 * @post If the operation succeeds,
	 * 1. A new game with the specified properties has been created
	 * 2. The server returns an HTTP 200 success response.
	 * 3. The body contains a JSON object describing the newly created game
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public CreateGameResults createGame(CreateGameParams params){
		return null;
	}
	
	/**
	 * @pre 1. The user has previously logged in to the server (i.e., they have a valid catan.user HTTP cookie).
	 * 2. The player may join the game because
	 * 2.a They are already in the game, OR
	 * 2.b There is space in the game to add a new player
	 * 3. The specified game ID is valid
	 * 4. The specified color is valid (red, green, blue, yellow, puce, brown, white, purple,
	 * orange)
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response with "Success" in the body.
	 * 2. The player is in the game with the specified color (i.e. calls to /games/list method will
	 * show the player in the game with the chosen color).
	 * 3. The server response includes the "Set�cookie" response header setting the catan.game
	 * HTTP cookie
	 * If the operation fails,1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public JoinGameResults joinGame(JoinGameParams params){
		return null;
	}
	
	/**
	 * @pre 1. The specified game ID is valid
	 * 2. The specified file name is valid (i.e., not null or empty)
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response with "Success" in the body.
	 * 2. The current state of the specified game (including its ID) has been saved to the
	 * specified file name in the server's saves/ directory
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public SaveGameResults saveGame(SaveGameParams params){
		return null;
	}
	
	/**
	 * @pre 1. A previously saved game file with the specified name exists in the server's saves/directory.
	 * 
	 * @post If the operation succeeds,1. The server returns an HTTP 200 success response with "Success" in the body.
	 * 2. The game in the specified file has been loaded into the server and its state restored
	 * (including its ID).
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error message.
	 */
	public LoadGameResults loadGame(LoadGameParams params){
		return null;
	}
	
	/**
	 * @pre 1. The caller has previously logged in to the server and joined a game (i.e., they have
	 * valid catan.user and catan.game HTTP cookies).
	 * 2. If specified, the version number is included as the "version" query parameter in the
	 * request URL, and its value is a valid integer.
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response.
	 * 2. The response body contains JSON data
	 * a. The full client model JSON is returned if the caller does not provide a version
	 * number, or the provide version number does not match the version on the server
	 * b. "true" (true in double quotes) is returned if the caller provided a version number,
	 * and the version number matched the version number on the server
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public GetModelResults getModel(){
		return null;
	}
	
	/**
	 * @pre 1. The caller has previously logged in to the server and joined a game (i.e., they have
	 * valid catan.user and catan.game HTTP cookies).
	 * 
	 * @post If the operation succeeds,
	 * 1. The game's command history has been cleared out
	 * 2. The game's players have NOT been cleared out
	 * 3. The server returns an HTTP 200 success response.
	 * 4. The body contains the game's updated client model JSON
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public ResetGameResults resetGame(){
		return null;
	}
	
	/**
	 * @pre 1. The caller has previously logged in to the server and joined a game (i.e., they have
	 * valid catan.user and catan.game HTTP cookies).
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response.
	 * 2. The body contains a JSON array of commands that have been executed in the game.
	 * This command array is suitable for passing back to the /game/command [POST] method to
	 * restore the state of the game later (after calling /game/reset to revert the game to its initial state).
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public GetCommandsResults getCommands(GetCommandsParams params){
		return null;
	}
	
	/**
	 * @pre 1. The caller has previously logged in to the server and joined a game (i.e., they have
	 * valid catan.user and catan.game HTTP cookies).
	 * 
	 * @post If the operation succeeds,
	 * 1. The passed�in command list has been applied to the game.
	 * 2. The server returns an HTTP 200 success response.
	 * 3. The body contains the game's updated client model JSON
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public ExecuteCommandsResults executeCommands(ExecuteCommandsParams params){
		return null;
	}
	
	/**
	 * @pre None.
	 * 
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response.
	 * 2. The body contains a JSON string array enumerating the different types of AI players.
	 * These are the values that may be passed to the /game/addAI method.
	 */
	public ListAIResults listAI(ListAIParams params){
		return null;
	}
	
	/**
	 * @pre 1.The caller specifies a valid logging level. Valid values include: SEVERE, WARNING,
	 * INFO, CONFIG, FINE, FINER, FINEST
	 * @post If the operation succeeds,
	 * 1. The server returns an HTTP 200 success response with "Success" in the body.
	 * 2. The Server is using the specified logging level
	 * If the operation fails,
	 * 1. The server returns an HTTP 400 error response, and the body contains an error
	 * message.
	 */
	public ChangeLogLevelResults changeLogLevel(ChangeLogLevelParams params){
		return null;
	}
	
	/* Move Commands */
	
	/*
	 * Universal Pre-Conditions:
	 * All /move/* methods also have a common pre�condition in that they assume that the caller has
	 * already logged in to the server and joined a game. This pre�condition is not repeated on each
	 * move type, but should be assumed.
	 */
	
	
	
	/* Anytime Commands */
	
	/**
	 * @pre None.
	 * @post The chat contains your message at the end.
	 */
	public CatanModel sendChat(SendChatParams params) {
		return null;
	}
	
	/* Miscellaneous Commands */

	/**
	 * @pre You have been offered a domestic trade
	 * To accept the offered trade, you have the required resources
	 * @post If you accepted, you and the player who offered swap the specified resources
	 * If you declined no resources are exchanged
	 * The trade offer is removed
	 */
	public CatanModel acceptTrade(AcceptTradeParams params) {
		return null;
	}
	
	/**
	 * @pre The status of the client model is 'Discarding'
	 * You have over 7 cards
	 * You have the cards you're choosing to discard.
	 * @post You gave up the specified resources
	 * If you're the last one to discard, the client model status changes to 'Robbing
	 */
	public CatanModel discardCards(DiscardCardsParams params) {
		return null;
	}
	
	/* Rolling Commands */
	
	
	/**
	 * @pre It is your turn
	 * The client model's status is �'Rolling'
	 * @post The client model's status is now in �'Discarding' or �'Robbing' or �'Playing'
	 */
	public CatanModel rollNumber(RollNumberParams params) {
		return null;
	}
	
	/* Playing Commands */
	
	/*
	 * Playing Command Pre-Conditions:
	 * It is your turn
	 * The client model's status is 'Playing'
	 */
	
	/**
	 * @pre The road location is open
	 * The road location is connected to another road owned by the player
	 * The road location is not on water
	 * You have the required resources (1 wood, 1 brick,1 road)
	 * Setup round: Must be placed by settlement owned by the player with no adjacent
	 * road
	 * @post You lost the resources required to build a road (1 wood, 1 brick,1 road)
	 * The road is on the map at the specified location
	 * If applicable, "longest road" has been awarded to the player with the longest road
	 */
	public CatanModel buildRoad(BuildRoadParams params) {
		return null;
	}
	
	
	/**
	 * @pre The settlement location is open 
	 * The settlement location is not on water
	 * The settlement location is connected to one of your roads except during setup
	 * You have the required resources (1 wood, 1 brick, 1 wheat, 1 sheep,1 settlement)
	 * The settlement cannot be placed adjacent to another settlement
	 * @post You lost the resources required to build a settlement (1 wood, 1 brick, 1 wheat, 1 sheep,1 settlement)
	 * The settlement is on the map at the specified location
	 */
	public CatanModel buildSettlement(BuildSettlementParams params) {
		return null;
	}
	
	
	/**
	 * @pre The city location is where you currently have a settlement
	 * You have the required resources (2 wheat, 3 ore,1 city)
	 * @post You lost the resources required to build a city (2 wheat, 3 ore,1 city)
	 * The city is on the map at the specified location
	 * You got a settlement back
	 */
	public CatanModel buildCity(BuildCityParams params) {
		return null;
	}
	
	/**
	 * @pre You have the resources you are offering
	 * @post The trade is offered to the other player (stored in the server model)
	 */
	public CatanModel offerTrade(OfferTradeParams params) {
		return null;
	}
	
	/**
	 * @pre You have the resources you are giving
	 * For ratios less than 4, you have the correct port for the trade
	 * @post The trade has been executed (the offered resources are in the bank, and the
	 * requested resource has been received)
	 */
	public CatanModel maritimeTrade(MaritimeTradeParams params) {
		return null;
	}
	
	/**
	 * @pre The robber is not being kept in the same location
	 * If a player is being robbed (i.e., victimIndex != �1), the player being robbed has resource cards
	 * @post The robber is in the new location
	 * The player being robbed (if any) gave you one of his resource cards (randomly selected)
	 */
	public CatanModel robPlayer(RobPlayerParams params) {
		return null;
	}
	
	/**
	 * @pre None (except the preconditions for all Playing Commands)
	 * @post The cards in your new dev card hand have been transferred to your old dev card hand
	 * It is the next player's turn
	 */
	public CatanModel finishTurn(FinishTurnParams params) {
		return null;
	}
	
	/**
	 * @pre You have the required resources (1 ore, 1 wheat, 1 sheep)
	 * There are dev cards left in the deck
	 * @post You have a new card
	 * If it is a monument card, it has been added to your old devcard hand
	 * If it is a non�monument card, it has been added to your new devcard hand (unplayable this turn)
	 */
	public CatanModel buyDevCard(BuyDevCardParams params) {
		return null;
	}
	
	/* Dev Card Commands */
	
	/*
	 * Dev Card Command Pre-Conditions:
	 * It is your turn
	 * The client model status is 'Playing'
	 * You have the specific card you want to play in your old dev card hand
	 * You have not yet played a non�monument dev card this turn
	 */
	
	/**
	 * @pre The robber is not being kept in the same location
	 * If a player is being robbed (i.e., victimIndex != �1), the player being robbed has resource cards
	 * @post The robber is in the new location.
	 * The player being robbed (if any) gave you one of his resource cards (randomly selected).
	 * If applicable, "largest army" has been awarded to the player who has played the most soldier cards.
	 * You are not allowed to play other development cards during this turn (except for
	 * monument cards, which may still be played).
	 */
	public CatanModel playSoldier(PlaySoldierParams params) {
		return null;
	}
	
	/**
	 * @pre The two specified resources are in the bank.
	 * @post You gained the two specified resources.
	 */
	public CatanModel yearOfPlenty(YearOfPlentyParams params) {
		return null;
	}
	
	/**
	 * @pre The first road location (spot1) is connected to one of your roads.
	 * The second road location (spot2) is connected to one of your roads or to the first
	 * road location (spot1).
	 * Neither road location is on water.
	 * You have at least two unused roads.
	 * @post You have two fewer unused roads.
	 * Two new roads appear on the map at the specified locations.
	 * If applicable, "longest road" has been awarded to the player with the longest road.
	 */
	public CatanModel roadBuilding(RoadBuildingParams params) {
		return null;
	}
	
	/**
	 * @pre None (except the general preconditions for this section).
	 * @post All of the other players have given you all of their resource cards of the specified type.
	 */
	public CatanModel monopoly(MonopolyParams params) {
		return null;
	}
	
	/**
	 * @pre You have enough monument cards to win the game (i.e., reach 10 victory points).
	 * @post You gained a victory point.
	 */
	public CatanModel monument(MonumentParams params) {
		return null;
	}
	
	/**
	 * @pre You must be in your turn
	 * @post Roll two dices and sum result
	 */
	//Nate: Do we really need this function?
	public CatanModel rollDice(MoveParams params) {
		return null;
	}
	
	/**
	 * Bank Distribute Resource Cards to users who are qualified 
	 * @pre Dices needs to be thrown on that turn before bank can distribute a card
	 * @post Remove resource cards that has been distributed
	 * @param diceSum
	 */
	public void distributeCards(int diceSum){
		return;
	}
	
	/**
	 * Start a game
	 * @pre Game must have 4 players and game must not been started yet.
	 * @post Game must have started
	 */
	public void startGame(){
		return;
	}
	
	/**
	 * Allow user to leave a game
	 * @pre User must be in a game
	 * @post Game has to be stopped and user must be out of game
	 */
	public void leaveGame(){
		return;
	}
	
}
