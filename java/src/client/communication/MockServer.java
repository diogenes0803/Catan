/**
 *
 */
package client.communication;

import shared.communicator.*;
import shared.definitions.*;
import shared.locations.HexLocation;
import shared.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author campbeln
 */
public class MockServer implements ServerStandinInterface, ServerInterface {
    

    private CatanModel model_ptr;
    private int timesUpdated;
    
    //In case we create a more robust mock server
    private HashMap<String, String> users;

    public MockServer() {
        model_ptr = null;
        users = new HashMap<String, String>();
        timesUpdated = 0;
    }

    public int getTimesUpdated() {
        return timesUpdated;
    }


    /* (non-Javadoc)
     * @see client.communication.ServerInterface#userLogin(shared.communicator.UserLoginParams)
     */
    @Override
    public UserLoginResults userLogin(UserLoginParams params) {

        UserLoginResults results = new UserLoginResults();

        if (!params.getusername().equals("John")) {
            results.setResponseBody("Failed to login - bad username or password.");
            return results;
        } else if (!params.getPassword().equals("Doe")) {
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

        if (params.getusername().equals("John")) {
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
        player.setPlayerId(0);
        //player.setColor("orange");
        //player.setName("Sam");
        Player[] players = new Player[4];
        players[0] = (player);
        player = new Player();
        player.setPlayerId(1);
        //player.setColor("blue");
        //player.setName("Brooke");
        players[1] = player;
        player = new Player();
        player.setPlayerId(10);
        //player.setColor("red");
        //player.setName("Pete");
        players[2] = player;
        player = new Player();
        player.setPlayerId(11);
        //player.setColor("Green");
        //player.setName("Mark");
        players[3] = player;

        Game game = new Game();
        game.setPlayers(players);
        game.setGameId(0);
        game.setGameTitle("Default Game");
        games.add(game);

        //Game 2
        player = new Player();
        player.setPlayerId(10);
        //player.setColor("orange");
        //player.setName("Pete");
        players = new Player[4];
        players[0] = player;
        player = new Player();
        player.setPlayerId(-2);
        //player.setColor("red");
        //player.setName("Quinn");
        players[1] = player;
        player = new Player();
        player.setPlayerId(-3);
        //player.setColor("blue");
        //player.setName("Steve");
        players[2] = player;
        player = new Player();
        player.setPlayerId(-4);
        //player.setColor("green");
        //player.setName("Ken");
        players[3] = player;

        game = new Game();
        game.setPlayers(players);
        game.setGameId(1);
        game.setGameTitle("AI Game");
        games.add(game);

        //Game 3
        player = new Player();
        player.setPlayerId(0);
        //player.setColor("orange");
        //player.setName("Sam");
        players = new Player[4];
        players[0] = player;
        player = new Player();
        player.setPlayerId(1);
        //player.setColor("blue");
        //player.setName("Brooke");
        players[1] = player;
        player = new Player();
        player.setPlayerId(10);
        //player.setColor("red");
        //player.setName("Pete");
        players[2] = player;
        player = new Player();
        player.setPlayerId(11);
        //player.setColor("Green");
        //player.setName("Mark");
        players[3] = player;

        game = new Game();
        game.setPlayers(players);
        game.setGameId(2);
        game.setGameTitle("Empty Game");
        games.add(game);


        return results;

    }

    /* (non-Javadoc)
     * @see client.communication.ServerInterface#createGame(shared.communicator.CreateGameParams)
     */
    @Override
    public CreateGameResults createGame(CreateGameParams params) {

        Player[] players = new Player[4];

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
    public CatanModel getModel() {

        GameManager gameManager = new GameManager();
        Game game = new Game();
        CatanMap map = new CatanMap();

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

        HexTile[][] hexTiles = new HexTile[5][5];
        hexTiles[0][0] = hex;

        //2
        hex = new HexTile();
        location = new HexLocation(1, -2);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        HexType hexType = HexType.BRICK;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[0][1] = hex;


        //3
        hex = new HexTile();
        location = new HexLocation(2, -2);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.WOOD;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[0][2] = hex;

        //4
        hex = new HexTile();
        location = new HexLocation(-1, -1);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.BRICK;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[0][3] = hex;

        //5
        hex = new HexTile();
        location = new HexLocation(0, 1);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.WOOD;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[0][4] = hex;
        //6
        hex = new HexTile();
        location = new HexLocation(1, -1);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.ORE;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[1][0] = hex;

        //7
        hex = new HexTile();
        location = new HexLocation(2, -1);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.SHEEP;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[1][1] = hex;

        //8
        hex = new HexTile();
        location = new HexLocation(-2, 0);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.ORE;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[1][2] = hex;

        //9
        hex = new HexTile();
        location = new HexLocation(-1, 0);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.SHEEP;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[1][3] = hex;

        //10
        hex = new HexTile();
        location = new HexLocation(0, 0);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.WHEAT;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[1][4] = hex;

        //11
        hex = new HexTile();
        location = new HexLocation(1, 0);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.BRICK;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[2][0] = hex;

        //12
        hex = new HexTile();
        location = new HexLocation(2, 0);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.WHEAT;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[2][1] = hex;

        //13
        hex = new HexTile();
        location = new HexLocation(-2, 1);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.WHEAT;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[2][2] = hex;

        //14
        hex = new HexTile();
        location = new HexLocation(-1, 1);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.SHEEP;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[2][3] = hex;

        //15
        hex = new HexTile();
        location = new HexLocation(0, 1);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.WOOD;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[2][4] = hex;

        //16
        hex = new HexTile();
        location = new HexLocation(1, 1);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.SHEEP;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[3][0] = hex;

        //17
        hex = new HexTile();
        location = new HexLocation(-2, 2);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.WOOD;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[3][1] = hex;

        //18
        hex = new HexTile();
        location = new HexLocation(-1, 2);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.ORE;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[3][2] = hex;

        //19
        hex = new HexTile();
        location = new HexLocation(0, 2);
        hex.setLocation(location);
        //HashMap<EdgeLocation, Edge> edges = new HashMap<EdgeLocation, Edge>();
        //HashMap<VertexLocation, Vertex> vertices =  new HashMap<VertexLocation, Vertex>();
        hexType = HexType.WHEAT;
        hex.setHexType(hexType);
        //int token;
        //Piece robber = new Piece();
        hasRobber = false;
        hex.setHasRobber(hasRobber);

        hexTiles[3][3] = hex;

        //Sets variables for the map
        map.setHexTiles(hexTiles);

        Bank bank = new Bank();

        List<DevCard> devCards = new ArrayList<DevCard>();

        for (int i = 0; i < 2; i++) {
            DevCard card = new DevCard();
            card.setType(DevCardType.YEAR_OF_PLENTY);
            card.setOld(true);
            devCards.add(card);
        }
        for (int i = 0; i < 2; i++) {
            DevCard card = new DevCard();
            card.setType(DevCardType.MONOPOLY);
            card.setOld(true);
            devCards.add(card);
        }
        for (int i = 0; i < 14; i++) {
            DevCard card = new DevCard();
            card.setType(DevCardType.SOLDIER);
            card.setOld(true);
            devCards.add(card);
        }
        for (int i = 0; i < 2; i++) {
            DevCard card = new DevCard();
            card.setType(DevCardType.ROAD_BUILD);
            card.setOld(true);
            devCards.add(card);
        }
        for (int i = 0; i < 5; i++) {
            DevCard card = new DevCard();
            card.setType(DevCardType.MONUMENT);
            card.setOld(true);
            devCards.add(card);
        }

        List<ResCard> resCards = new ArrayList<ResCard>();
        for (int i = 0; i < 24; i++) {
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


        Player[] players = new Player[4];

        Player player = new Player();
        player.setPlayerId(0);
        player.setColor(CatanColor.RED);
        player.setPlayerId(12);
        player.setVictoryPoint(0);
        //player.setName("Sam");

        List<Piece> availablePieces = new ArrayList<Piece>();
        for (int i = 0; i < 15; i++) {
            Piece piece = new Piece();
            piece.setOwnerPlayerIndex(12);
            piece.setType(PieceType.ROAD);
            availablePieces.add(piece);
        }
        for (int i = 0; i < 4; i++) {
            Piece piece = new Piece();
            piece.setOwnerPlayerIndex(12);
            piece.setType(PieceType.CITY);
            availablePieces.add(piece);
        }
        for (int i = 0; i < 5; i++) {
            Piece piece = new Piece();
            piece.setOwnerPlayerIndex(12);
            piece.setType(PieceType.SETTLEMENT);
            availablePieces.add(piece);
        }

        List<ResCard> playerResCards = new ArrayList<ResCard>();
        List<DevCard> playaerDevCards = new ArrayList<DevCard>();

        player.setDevCards(playaerDevCards);
        player.setResCards(playerResCards);
        player.setAvailablePieces(availablePieces);
        players[0] = player;


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

        return model_ptr;
    }

    /* (non-Javadoc)
     * @see client.communication.ServerInterface#resetGame(shared.communicator.ResetGameParams)
     */
    @Override
    public CatanModel resetGame() {
        return model_ptr;
    }

    /* (non-Javadoc)
     * @see client.communication.ServerInterface#getCommands(shared.communicator.GetCommandsParams)
     */
    @Override
    public GetCommandsResults getCommands() {
        //May need to do some modification to this

        List<String> commands = new ArrayList<String>();
        GetCommandsResults results = new GetCommandsResults(commands);
        return results;
    }

    /* (non-Javadoc)
     * @see client.communication.ServerInterface#executeCommands(shared.communicator.ExecuteCommandsParams)
     */
    @Override
    public CatanModel executeCommands(ExecuteCommandsParams params) {
        //May need to do some modification to this
        return model_ptr;
    }

    /* (non-Javadoc)
     * @see client.communication.ServerInterface#listAI(shared.communicator.ListAIParams)
     */
    @Override
    public ListAIResults listAI() {

        List<String> aI = new ArrayList<String>();
        ListAIResults results = new ListAIResults(aI);
        return results;
    }

    /* (non-Javadoc)
     * @see client.communication.ServerInterface#changeLogLevel(shared.communicator.AddAIParams)
     */
    @Override
    public AddAIResults AddAI(String params) {
        // TODO Auto-generated method stub
        return null;
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

    
    //The following two methods are only to be used if we create a more robust mock server
    private void addUser(String userName, String password) {
        users.put(userName, password);
    }
    
    private boolean checkForUser(String userName, String password) {
        String value = users.get(userName);
        return value != null;
    }

}
