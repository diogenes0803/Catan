/**
 * 
 */
package client.communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import shared.communicator.*;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.models.Bank;
import shared.models.CatanModel;
import shared.models.DevCard;
import shared.models.Edge;
import shared.models.Game;
import shared.models.GameManager;
import shared.models.HexTile;
import shared.models.Piece;
import shared.models.Player;
import shared.models.ResCard;
import shared.models.TurnTracker;
import shared.models.User;
import shared.models.UserManager;
import shared.models.Map;
import shared.models.Vertex;

/**
 * @author campbeln
 *
 */
public class MockServer implements ServerStandinInterface, ServerInterface {
    
	
    private CatanModel model_ptr;
    
    //In case we create a more robust mock server
    private HashMap<String, String> users;

    public MockServer(CatanModel model){
        model_ptr = model;
        users = new HashMap<String, String>();
    }
    

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#userLogin(shared.communicator.UserLoginParams)
	 */
	@Override
	public UserLoginResults userLogin(UserLoginParams params) {
		
		UserLoginResults results = new UserLoginResults();
		
		if (!params.getUserName().equals("John"))
		{
			results.setResponseBody("Failed to login - bad username or password.");
			return results;
		}
		else if (!params.getPassword().equals("Doe"))
		{
			results.setResponseBody("Failed to login - bad username or password.");
			return results;
		}
	
		results.setResponseBody("Success");
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#registerUser(shared.communicator.RegisterUserParams)
	 */
	@Override
	public RegisterUserResults registerUser(RegisterUserParams params) {
		
		RegisterUserResults results = new RegisterUserResults();
		
		if (params.getUserName().equals("John"))
		{
			results.setResponseBody("Failed to register - someone already has that username.");
			return results;
		}

		results.setResponseBody("Success");
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#listGames(shared.communicator.ListGamesParams)
	 */
	@Override
	public ListGamesResults listGames() {
		
		ListGamesResults results = new ListGamesResults();
		
		ArrayList<Game> games = new ArrayList<Game>();
		
		//Game 1
		Player player = new Player();
		player.setUserId(0);
		//player.setColor("orange");
		//player.setName("Sam");
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		player = new Player();
		player.setUserId(1);
		//player.setColor("blue");
		//player.setName("Brooke");
		players.add(player);
		player = new Player();
		player.setUserId(10);
		//player.setColor("red");
		//player.setName("Pete");
		players.add(player);
		player = new Player();
		player.setUserId(11);
		//player.setColor("Green");
		//player.setName("Mark");
		players.add(player);
		
		Game game = new Game();
		game.setPlayers(players);
		game.setGameId(0);
		game.setGameTitle("Default Game");
		games.add(game);
		
		//Game 2
		player = new Player();
		player.setUserId(10);
		//player.setColor("orange");
		//player.setName("Pete");
		players = new ArrayList<Player>();
		players.add(player);
		player = new Player();
		player.setUserId(-2);
		//player.setColor("red");
		//player.setName("Quinn");
		players.add(player);
		player = new Player();
		player.setUserId(-3);
		//player.setColor("blue");
		//player.setName("Steve");
		players.add(player);
		player = new Player();
		player.setUserId(-4);
		//player.setColor("green");
		//player.setName("Ken");
		players.add(player);
		
		game = new Game();
		game.setPlayers(players);
		game.setGameId(1);
		game.setGameTitle("AI Game");
		games.add(game);
		
		//Game 3
		player = new Player();
		player.setUserId(0);
		//player.setColor("orange");
		//player.setName("Sam");
		players = new ArrayList<Player>();
		players.add(player);
		player = new Player();
		player.setUserId(1);
		//player.setColor("blue");
		//player.setName("Brooke");
		players.add(player);
		player = new Player();
		player.setUserId(10);
		//player.setColor("red");
		//player.setName("Pete");
		players.add(player);
		player = new Player();
		player.setUserId(11);
		//player.setColor("Green");
		//player.setName("Mark");
		players.add(player);
		
		game = new Game();
		game.setPlayers(players);
		game.setGameId(2);
		game.setGameTitle("Empty Game");
		games.add(game);
		
		
		results.setGames(games);
		
		return results;
		
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#createGame(shared.communicator.CreateGameParams)
	 */
	@Override
	public CreateGameResults createGame(CreateGameParams params) {
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		CreateGameResults results = new CreateGameResults("Fun Game", 3, players);
		
		
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#joinGame(shared.communicator.JoinGameParams)
	 */
	@Override
	public JoinGameResults joinGame(JoinGameParams params) {
		
		JoinGameResults results = new JoinGameResults("Success");
		
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#saveGame(shared.communicator.SaveGameParams)
	 */
	@Override
	public SaveGameResults saveGame(SaveGameParams params) {
		
		SaveGameResults results = new SaveGameResults("Success");
		
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#loadGame(shared.communicator.LoadGameParams)
	 */
	@Override
	public LoadGameResults loadGame(LoadGameParams params) {
		
		LoadGameResults results = new LoadGameResults("Success");
		
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#getModel(shared.communicator.GetModelParams)
	 */
	@Override
	public GetModelResults getModel() {
		
		GameManager gameManager = new GameManager();
		Game game = new Game();
		Map map = new Map();
		
		//1
		HexTile hex = new HexTile();
		HexLocation location = new HexLocation(0, -2);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		//ResourceType hexType = ResourceType.BRICK;
		//int token;
		//Piece robber = new Piece();
		boolean hasRobber = true;
		hex.setHasRobber(hasRobber);
		
		List< List<HexTile> > hexTiles = new ArrayList< List<HexTile>>();
		List<HexTile> hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//2
		hex = new HexTile();
		location = new HexLocation(1, -2);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		ResourceType hexType = ResourceType.BRICK;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		
		//3
		hex = new HexTile();
		location = new HexLocation(2, -2);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.WOOD;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//4
		hex = new HexTile();
		location = new HexLocation(-1, -1);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.BRICK;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//5
		hex = new HexTile();
		location = new HexLocation(0, 1);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.WOOD;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//6
		hex = new HexTile();
		location = new HexLocation(1, -1);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.ORE;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//7
		hex = new HexTile();
		location = new HexLocation(2, -1);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.SHEEP;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//8
		hex = new HexTile();
		location = new HexLocation(-2, 0);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.ORE;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//9
		hex = new HexTile();
		location = new HexLocation(-1, 0);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.SHEEP;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//10
		hex = new HexTile();
		location = new HexLocation(0, 0);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.WHEAT;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//11
		hex = new HexTile();
		location = new HexLocation(1, 0);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.BRICK;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//12
		hex = new HexTile();
		location = new HexLocation(2, 0);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.WHEAT;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//13
		hex = new HexTile();
		location = new HexLocation(-2, 1);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.WHEAT;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//14
		hex = new HexTile();
		location = new HexLocation(-1, 1);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.SHEEP;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//15
		hex = new HexTile();
		location = new HexLocation(0, 1);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.WOOD;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//16
		hex = new HexTile();
		location = new HexLocation(1, 1);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.SHEEP;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//17
		hex = new HexTile();
		location = new HexLocation(-2, 2);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.WOOD;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//18
		hex = new HexTile();
		location = new HexLocation(-1, 2);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.ORE;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);
		
		//19
		hex = new HexTile();
		location = new HexLocation(0, 2);
		hex.setLocation(location);
		//HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
		//HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
		hexType = ResourceType.WHEAT;
		hex.setHexType(hexType);
		//int token;
		//Piece robber = new Piece();
		hasRobber = false;
		hex.setHasRobber(hasRobber);
		
		hexTiles = new ArrayList< List<HexTile>>();
		hexes = new ArrayList<HexTile>();
		hexes.add(hex);
		hexTiles.add(hexes);

		//Sets variables for the map
		map.setHexTiles(hexTiles);
		
		Bank bank = new Bank();
		
		List<DevCard> devCards = new ArrayList<DevCard>();
		
		for (int i = 0; i < 2; i++)
		{
			DevCard card = new DevCard();
			card.setType(DevCardType.YEAR_OF_PLENTY);
			card.setUsable(true);
			devCards.add(card);
		}
		for (int i = 0; i < 2; i++)
		{
			DevCard card = new DevCard();
			card.setType(DevCardType.MONOPOLY);
			card.setUsable(true);
			devCards.add(card);
		}
		for (int i = 0; i < 14; i++)
		{
			DevCard card = new DevCard();
			card.setType(DevCardType.SOLDIER);
			card.setUsable(true);
			devCards.add(card);
		}
		for (int i = 0; i < 2; i++)
		{
			DevCard card = new DevCard();
			card.setType(DevCardType.ROAD_BUILD);
			card.setUsable(true);
			devCards.add(card);
		}
		for (int i = 0; i < 5; i++)
		{
			DevCard card = new DevCard();
			card.setType(DevCardType.MONUMENT);
			card.setUsable(true);
			devCards.add(card);
		}
		
		List<ResCard> resCards = new ArrayList<ResCard>();
		for (int i = 0; i < 24; i++)
		{
			ResCard card = new ResCard();
			card.setType(ResourceType.BRICK);
			resCards.add(card);
			card = new ResCard();
			card.setType(ResourceType.ORE);
			resCards.add(card);
			card = new ResCard();
			card.setType(ResourceType.SHEEP);
			resCards.add(card);
			card = new ResCard();
			card.setType(ResourceType.WHEAT);
			resCards.add(card);
			card = new ResCard();
			card.setType(ResourceType.WOOD);
			resCards.add(card);
		}
		
		
		bank.setDevCards(devCards);
		bank.setResCards(resCards);
		
		
		List<Player> players = new ArrayList<Player>();
		
		Player player = new Player();
		player.setUserId(0);
		player.setColor(CatanColor.RED);
		player.setPlayerId(12);
		player.setVictoryPoint(0);
		//player.setName("Sam");
		
		List<Piece> availablePieces = new ArrayList<Piece>();
		for (int i = 0; i < 15; i++)
		{
			Piece piece = new Piece();
			piece.setOwnerPlayerId(12);
			piece.setType(PieceType.ROAD);
			availablePieces.add(piece);
		}
		for (int i = 0; i < 4; i++)
		{
			Piece piece = new Piece();
			piece.setOwnerPlayerId(12);
			piece.setType(PieceType.CITY);
			availablePieces.add(piece);
		}
		for (int i = 0; i < 5; i++)
		{
			Piece piece = new Piece();
			piece.setOwnerPlayerId(12);
			piece.setType(PieceType.SETTLEMENT);
			availablePieces.add(piece);
		}
		
		List<ResCard> playerResCards = new ArrayList<ResCard>();
		List<DevCard> playaerDevCards = new ArrayList<DevCard>();
		
		player.setDevCards(playaerDevCards);
		player.setResCards(playerResCards);
		player.setAvailablePieces(availablePieces);
		players.add(player);
		
		TurnTracker turnTracker = new TurnTracker();
		

		
		//Sets Variables for the game
		game.setMap(map);
		game.setBank(bank);
		
		UserManager userManager = new UserManager();
		
		//gameManager.setAvailableGames(availableGames);
		gameManager.setGame(game);
		gameManager.setJoinedGame(true);
		
		
		User user = new User();
		user.setUserId(0);
		user.setUserName("johndoe");
		userManager.setLoggedIn(true);
		userManager.setLoggedInUser(user);
		
		model_ptr.setGameManager(gameManager);
		model_ptr.setUserManager(userManager);
		
		GetModelResults results = new GetModelResults(model_ptr);
		
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#resetGame(shared.communicator.ResetGameParams)
	 */
	@Override
	public ResetGameResults resetGame() {
		ResetGameResults results = new ResetGameResults(model_ptr);
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#getCommands(shared.communicator.GetCommandsParams)
	 */
	@Override
	public GetCommandsResults getCommands(GetCommandsParams params) {
		//May need to do some modification to this
		
		List<String> commands = new ArrayList<String>();
		GetCommandsResults results = new GetCommandsResults(commands);
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#executeCommands(shared.communicator.ExecuteCommandsParams)
	 */
	@Override
	public ExecuteCommandsResults executeCommands(ExecuteCommandsParams params) {
		//May need to do some modification to this
				ExecuteCommandsResults results = new ExecuteCommandsResults(model_ptr);
				return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#listAI(shared.communicator.ListAIParams)
	 */
	@Override
	public ListAIResults listAI(ListAIParams params) {
		
		List<String> aI = new ArrayList<String>();
		ListAIResults results = new ListAIResults(aI);
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#changeLogLevel(shared.communicator.ChangeLogLevelParams)
	 */
	@Override
	public ChangeLogLevelResults changeLogLevel(ChangeLogLevelParams params) {
		ChangeLogLevelResults results = new ChangeLogLevelResults("Success");
		return results;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#sendChat(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel sendChat(SendChatParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#acceptTrade(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel acceptTrade(AcceptTradeParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#discardCards(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel discardCards(DiscardCardsParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#rollNumber(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel rollNumber(RollNumberParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#buildRoad(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel buildRoad(BuildRoadParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#buildSettlement(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel buildSettlement(BuildSettlementParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#buildCity(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel buildCity(BuildCityParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#offerTrade(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel offerTrade(OfferTradeParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#maritimeTrade(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel maritimeTrade(MaritimeTradeParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#robPlayer(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel robPlayer(RobPlayerParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#finishTurn(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel finishTurn(FinishTurnParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#buyDevCard(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel buyDevCard(BuyDevCardParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#playSoldier(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel playSoldier(PlaySoldierParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#yearOfPlenty(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel yearOfPlenty(YearOfPlentyParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#roadBuilding(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel roadBuilding(RoadBuildingParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#monopoly(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel monopoly(MonopolyParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#monument(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel monument(MonumentParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#rollDice(shared.communicator.MoveParams)
	 */
	@Override
	public CatanModel rollDice(MoveParams params) {
		// TODO Auto-generated method stub
		return model_ptr;
	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#distributeCards(int)
	 */
	@Override
	public void distributeCards(int diceSum) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#startGame()
	 */
	@Override
	public void startGame() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see client.communication.ServerInterface#leaveGame()
	 */
	@Override
	public void leaveGame() {
		// TODO Auto-generated method stub

	}

    @Override
    public void updateModel() {
        // TODO Auto-generated method stub
        
    }
    
    
    //The following two methods are only to be used if we create a more robust mock server
    private void addUser(String userName, String password)
    {
    	users.put(userName, password);
    }
    
    private boolean checkForUser(String userName, String password)
    {
    	String value = users.get(userName);
    	if (value != null)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

}
