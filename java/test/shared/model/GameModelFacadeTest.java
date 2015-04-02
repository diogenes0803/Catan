package shared.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.locations.*;

import static org.junit.Assert.*;

public class GameModelFacadeTest {
    private IGameModelFacade facade;
    private IModelInitializer serializer;
    private IGame game;

    @Before
    public void setUp() throws Exception {
        facade = GameModelFacade.instance();
        serializer = new ModelInitializer();
        //initializes the model with test data from JSON file
        String testJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("sample/test_2.json")));
        serializer.initializeClientModel(testJSON, 0);
        game = GameModelFacade.instance().getGame();
    }

    @After
    public void tearDown() throws Exception {
        serializer = null;
        game = null;
    }

    @Test
    public void testCanPlaceRoad() throws Exception {
        HexLocation hex1 = new HexLocation(-1, 0);
        EdgeLocation edge1 = new EdgeLocation(hex1, EdgeDirection.South);
        assertTrue("Placing road in valid location", facade.canPlaceRoad(edge1));

        HexLocation hex2 = new HexLocation(0, 0);
        EdgeLocation edge2 = new EdgeLocation(hex2, EdgeDirection.SouthWest);
        assertFalse("The location is not open", facade.canPlaceRoad(edge2));

        HexLocation hex3 = new HexLocation(0, -1);
        EdgeLocation edge3 = new EdgeLocation(hex3, EdgeDirection.North);
        assertFalse("Is not connected to your road", facade.canPlaceRoad(edge3));

        HexLocation hex4 = new HexLocation(-3, 0);
        EdgeLocation edge4 = new EdgeLocation(hex4, EdgeDirection.NorthEast);
        assertFalse("The location is on water", facade.canPlaceRoad(edge4));

        HexLocation hex5 = new HexLocation(0, 1);
        EdgeLocation edge5 = new EdgeLocation(hex5, EdgeDirection.NorthWest);
        assertFalse("Trying to connect to another player's road", facade.canPlaceRoad(edge5));

        HexLocation hex6 = new HexLocation(0, 1);
        EdgeLocation edge6 = new EdgeLocation(hex6, EdgeDirection.NorthEast);
        assertFalse("Road blocked by another player's town", facade.canPlaceRoad(edge6));

        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canPlaceRoad(edge1));

        game.setCurrentPlayer(game.getPlayers().get(1));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertFalse("Not enough resources", facade.canPlaceRoad(edge1));

        game.setCurrentPlayer(game.getPlayers().get(2));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("No road pieces", facade.canPlaceRoad(edge1));

        game.setCurrentPlayer(game.getPlayers().get(3));
        assertFalse("Not your turn", facade.canPlaceRoad(edge1));
    }

    @Test
    public void testCanPlaceSettlement() throws Exception {
        HexLocation hex1 = new HexLocation(0, 0);
        VertexLocation ver1 = new VertexLocation(hex1, VertexDirection.West);
        assertTrue("Placing settlement in valid location", facade.canPlaceSettlement(ver1));

        HexLocation hex2 = new HexLocation(0, 0);
        VertexLocation ver2 = new VertexLocation(hex2, VertexDirection.SouthEast);
        assertFalse("The location is not open", facade.canPlaceSettlement(ver2));

        HexLocation hex3 = new HexLocation(0, -1);
        VertexLocation ver3 = new VertexLocation(hex3, VertexDirection.NorthEast);
        assertFalse("Is not connected to your road", facade.canPlaceSettlement(ver3));

        HexLocation hex4 = new HexLocation(-3, 0);
        VertexLocation ver4 = new VertexLocation(hex4, VertexDirection.NorthEast);
        assertFalse("The location is on water", facade.canPlaceSettlement(ver4));

        HexLocation hex5 = new HexLocation(0, 2);
        VertexLocation ver5 = new VertexLocation(hex5, VertexDirection.NorthEast);
        assertFalse("Trying to connect to another player's road", facade.canPlaceSettlement(ver5));

        HexLocation hex6 = new HexLocation(0, 0);
        VertexLocation ver6 = new VertexLocation(hex6, VertexDirection.NorthWest);
        assertFalse("Too close to another town", facade.canPlaceSettlement(ver6));

        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canPlaceSettlement(ver1));

        game.setGameState(GameState.FirstRound);
        assertTrue("Place free settlement", facade.canPlaceSettlement(ver1));
        game.setGameState(GameState.Playing);

        game.setCurrentPlayer(game.getPlayers().get(1));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertFalse("Not enough resources", facade.canPlaceSettlement(ver1));

        game.setCurrentPlayer(game.getPlayers().get(2));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("Not enough settlement pieces", facade.canPlaceSettlement(ver1));

        game.setCurrentPlayer(game.getPlayers().get(3));
        assertFalse("Not your turn", facade.canPlaceSettlement(ver1));
    }

    @Test
    public void testCanPlaceCity() throws Exception {
        HexLocation hex1 = new HexLocation(0, 0);
        VertexLocation ver1 = new VertexLocation(hex1, VertexDirection.NorthEast);
        assertTrue("Placing city in valid location", facade.canPlaceCity(ver1));

        HexLocation hex2 = new HexLocation(0, 0);
        VertexLocation ver2 = new VertexLocation(hex2, VertexDirection.West);
        assertFalse("No settlement", facade.canPlaceCity(ver2));

        HexLocation hex3 = new HexLocation(0, 0);
        VertexLocation ver3 = new VertexLocation(hex3, VertexDirection.SouthEast);
        assertFalse("Another player's settlement", facade.canPlaceCity(ver3));

        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canPlaceCity(ver1));
        game.setGameState(GameState.Playing);

        game.setCurrentPlayer(game.getPlayers().get(1));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertFalse("Not enough resources", facade.canPlaceCity(ver1));

        game.setCurrentPlayer(game.getPlayers().get(2));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("Not enough city pieces", facade.canPlaceCity(ver1));

        game.setCurrentPlayer(game.getPlayers().get(3));
        assertFalse("Not your turn", facade.canPlaceCity(ver1));
    }

    @Test
    public void testCanPlaceInitialRoad() throws Exception {
        IGame initGame = initAGame("sample/empty_board.json");
        IGameModelFacade gf = GameModelFacade.instance();

        // assert that it is the first round
        assertTrue("Not the First Round",
                initGame.getGameState() == GameState.FirstRound);
        // assert that there are no settlements
        assertTrue("There should be no settlements on a blank map",
                initGame.getMap().getSettlements().isEmpty());
        // assert that there are no roads on the map after game start
        assertTrue("There should be no roads on a blank map",
                initGame.getMap().getRoads().isEmpty());
        // assert that road can be placed on a terrain edge
        GameModelFacade.instance().setLocalPlayer(initGame.getPlayers().get(0));
        assertTrue("Somehow 0,0,SW is unacceptable",
                gf.canPlaceRoad(new EdgeLocation(0,0,EdgeDirection.SouthWest)));
        // assert that a road cannot be placed off the map
        assertFalse("Road should not be able to be placed off the map",
                gf.canPlaceRoad(new EdgeLocation(-4,4,EdgeDirection.SouthWest)));
        // assert that a road cannot be placed on the coast
        assertTrue("Road should be able to be placed on the coast",
                gf.canPlaceRoad(new EdgeLocation(-2,2,EdgeDirection.SouthWest)));
        // assert that a road can be placed ending on the coast
        assertTrue("Road should be able to be placed ending on the coast",
                gf.canPlaceRoad(new EdgeLocation(-1,2,EdgeDirection.NorthWest)));
    }

    @Test
    public void testCanBuyCity() throws Exception {
        assertTrue("Player can buy city", facade.canBuyCity());

        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertFalse("Not your turn", facade.canBuyCity());

        game.setCurrentPlayer(game.getPlayers().get(1));
        assertFalse("Not enough resources", facade.canBuyCity());

        game.setCurrentPlayer(game.getPlayers().get(2));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("Not enough city pieces", facade.canBuyCity());

        game.setCurrentPlayer(game.getPlayers().get(0));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(0));
        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canBuyCity());
    }

    @Test
    public void testCanBuyRoad() throws Exception {
        assertTrue("Player can buy road", facade.canBuyRoad());

        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertFalse("Not your turn", facade.canBuyRoad());

        game.setCurrentPlayer(game.getPlayers().get(1));
        assertFalse("Not enough resources", facade.canBuyRoad());

        game.setCurrentPlayer(game.getPlayers().get(2));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("Not enough road pieces", facade.canBuyRoad());

        game.setCurrentPlayer(game.getPlayers().get(0));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(0));
        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canBuyRoad());
    }

    @Test
    public void testCanBuySettlement() throws Exception {
        assertTrue("Player can buy settlement", facade.canBuySettlement());

        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertFalse("Not your turn", facade.canBuySettlement());

        game.setCurrentPlayer(game.getPlayers().get(1));
        assertFalse("Not enough resources", facade.canBuySettlement());

        game.setCurrentPlayer(game.getPlayers().get(2));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("Not enough settlement pieces", facade.canBuySettlement());

        game.setCurrentPlayer(game.getPlayers().get(0));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(0));
        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canBuySettlement());
    }

    @Test
    public void testCanBuyDevCard() throws Exception {
        assertTrue("Player can buy dev card", facade.canBuyDevCard());

        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertFalse("Not your turn", facade.canBuyDevCard());

        game.setCurrentPlayer(game.getPlayers().get(1));
        assertFalse("Not enough resources", facade.canBuyDevCard());

        game.setCurrentPlayer(game.getPlayers().get(0));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(0));
        game.setDevCards(new DevCardHand());
        assertFalse("No available dev cards", facade.canBuyDevCard());

        game.setCurrentPlayer(game.getPlayers().get(0));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(0));
        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canBuyDevCard());
    }

    @Test
    public void testCanAcceptTrade() throws Exception {
        // player 3 only has 3 resource cards
        // player 0 has 100 of each resource
        IGame tradeGame = initAGame("sample/pending_trade.json");
        IGameModelFacade gf = GameModelFacade.instance();

        // assert that there is a pending trade offer
        assertTrue("There is no pending trade in a game that has a trade offer pending",
                tradeGame.getTradeOffer() != null);
        // assert that the game state is set to playing
        assertTrue("Game state should be set to playing",
                tradeGame.getGameState() == GameState.Playing);
        // assert that player 3 can't afford the trade offer
        GameModelFacade.instance().setLocalPlayer(tradeGame.getPlayers().get(3));
        assertTrue(GameModelFacade.instance().getLocalPlayer().getIndex() == 3);
        assertTrue(GameModelFacade.instance().getLocalPlayer().getResources().getWood() == 1);
        assertTrue(GameModelFacade.instance().getLocalPlayer().getResources().getSheep() == 1);
        assertTrue(GameModelFacade.instance().getLocalPlayer().getResources().getOre() == 1);
        assertFalse("Player 0 to player 3 trade should not be affordable by player 3",
                gf.canAcceptTrade());
        // assert that player 0 can't accept the trade because he is not the receiver
        GameModelFacade.instance().setLocalPlayer(tradeGame.getPlayers().get(0));
        assertFalse("Player 0 should not be able to accept the trade if he is not the receiver ",
                gf.canAcceptTrade());
    }

    @Test
    public void testCanPlayDevCard() throws Exception {
        assertTrue("Player can play dev card", facade.canPlayDevCard());

        game.setCurrentPlayer(game.getPlayers().get(1));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertTrue("Should be able to play a monument", facade.canPlayDevCard());

        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("Not your turn", facade.canPlayDevCard());

        game.setCurrentPlayer(game.getPlayers().get(2));
        assertFalse("No dev cards", facade.canPlayDevCard());

        game.setCurrentPlayer(game.getPlayers().get(0));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(0));
        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canPlayDevCard());
    }

    @Test
    public void testCanPlayMonopoly() throws Exception {
        assertTrue("Player can play monopoly", facade.canPlayMonopoly());

        game.setCurrentPlayer(game.getPlayers().get(1));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertFalse("Already played dev card", facade.canPlayMonopoly());

        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("Not your turn", facade.canPlayMonopoly());

        game.setCurrentPlayer(game.getPlayers().get(2));
        assertFalse("No monopoly card", facade.canPlayMonopoly());

        game.setCurrentPlayer(game.getPlayers().get(0));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(0));
        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canPlayMonopoly());
    }

    @Test
    public void testCanPlaySoldier() throws Exception {
        // make sure it fails if the local player is not playing
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("A player tried to play a solider when it was not their turn", facade.canPlaySoldier(new HexLocation(0, 2)));

        // make sure it fails if the player has a soldier card, but it's not playable
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(0));
        GameModelFacade.instance().getLocalPlayer().getPlayableDevCards().remove(DevCardType.SOLDIER);
        GameModelFacade.instance().getLocalPlayer().getPlayableDevCards().remove(DevCardType.SOLDIER);
        GameModelFacade.instance().getLocalPlayer().getPlayableDevCards().remove(DevCardType.SOLDIER);
        assertFalse("A player tried to play a soldier when it was not in their playable hand", facade.canPlaySoldier(new HexLocation(0, 2)));

        // make sure it fails if the player has no soldier card
        GameModelFacade.instance().getLocalPlayer().getNewDevCards().remove(DevCardType.SOLDIER);
        GameModelFacade.instance().getLocalPlayer().getNewDevCards().remove(DevCardType.SOLDIER);
        GameModelFacade.instance().getLocalPlayer().getNewDevCards().remove(DevCardType.SOLDIER);
        assertFalse("A player tried to play a soldier when they didn't have one", facade.canPlaySoldier(new HexLocation(0, 2)));

        // make sure it fails if the hex location has the robber on it.
        GameModelFacade.instance().getLocalPlayer().getPlayableDevCards().add(DevCardType.SOLDIER);
        assertFalse("A player tried to play a soldier on the hex that the robber was already on", facade.canPlaySoldier(new HexLocation(0, -2)));

        GameModelFacade.instance().getLocalPlayer().getPlayableDevCards().add(DevCardType.SOLDIER);
        assertTrue("A player can play a soldier because they have one in their playable hand, and the hex is valid", facade.canPlaySoldier(new HexLocation(0, 2)));
    }

    @Test
    public void testCanPlayYearOfPlenty() throws Exception {
        assertTrue("Player can play year of plenty", facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WHEAT));

        game.setCurrentPlayer(game.getPlayers().get(1));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertFalse("Already played dev card", facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WHEAT));

        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("Not your turn", facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WHEAT));

        game.setCurrentPlayer(game.getPlayers().get(2));
        assertFalse("No year of plenty card", facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WHEAT));

        game.setCurrentPlayer(game.getPlayers().get(0));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(0));
        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WHEAT));

        game.setGameState(GameState.Playing);
        game.setResourceBank(new ResourceBank());
        assertFalse("Not enough resources in bank", facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WHEAT));
    }

    @Test
    public void testCanPlayMonument() throws Exception {
        assertTrue("Player can play monument", facade.canPlayMonument());

        game.setCurrentPlayer(game.getPlayers().get(1));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertTrue("Can always play monument card if have any", facade.canPlayMonument());

        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(2));
        assertFalse("Not your turn", facade.canPlayMonument());

        game.setCurrentPlayer(game.getPlayers().get(2));
        assertFalse("No monument card", facade.canPlayMonument());

        game.setCurrentPlayer(game.getPlayers().get(0));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(0));
        game.setGameState(GameState.Discarding);
        assertFalse("Not playing phase", facade.canPlayMonument());
    }

    @Test
    public void testCanPlayRoadBuilding() throws Exception {
        EdgeLocation p1Next1 = new EdgeLocation(-1, 1, EdgeDirection.North);
        EdgeLocation p1Next2 = new EdgeLocation(-2, 1, EdgeDirection.NorthEast);

        IPlayer localPlayer = game.getPlayers().get(0);
        GameModelFacade.instance().setLocalPlayer(localPlayer);

        assertTrue("Player should be able to play road building card", facade.canPlayRoadBuilding(p1Next1, p1Next2));
        assertFalse("Player should not be able to play road building card with roads in wrong order.",
                facade.canPlayRoadBuilding(p1Next2, p1Next1));

        game.setCurrentPlayer(game.getPlayers().get(2));
        assertFalse("Player should not be able to play out of turn", facade.canPlayRoadBuilding(p1Next1, p1Next2));

        game.setCurrentPlayer(localPlayer);

        EdgeLocation p1OtherValid = new EdgeLocation(1, 0, EdgeDirection.North);
        // place in two valid, but separate, locations
        assertTrue("Player should be able to play road building card", facade.canPlayRoadBuilding(p1Next1, p1OtherValid));
        assertTrue("Player should be able to play road building card", facade.canPlayRoadBuilding(p1OtherValid, p1Next1));

        // wrong game state
        game.setGameState(GameState.Discarding);
        assertFalse("Player should be not able to play card in Discarding", facade.canPlayRoadBuilding(p1Next1, p1Next2));
        assertFalse("Player should be not able to play card in Discarding", facade.canPlayRoadBuilding(p1OtherValid, p1Next1));
        game.setGameState(GameState.Playing);

        // take away their cards!
        while (localPlayer.getPlayableDevCards().getCount(DevCardType.ROAD_BUILD) > 0) {
           localPlayer.getPlayableDevCards().remove(DevCardType.ROAD_BUILD);
        }

        // no cards, no play
        assertFalse("Player has no road build cards to play, should not be able to.", facade.canPlayRoadBuilding(p1Next1, p1OtherValid));
        assertFalse("Player has no road build cards to play, should not be able to.", facade.canPlayRoadBuilding(p1OtherValid, p1Next1));

        // valid roads, but already played card
        EdgeLocation p2Next1 = new EdgeLocation(1, 1, EdgeDirection.SouthWest);
        EdgeLocation p2Next2 = new EdgeLocation(1, 1, EdgeDirection.South);
        game.setCurrentPlayer(game.getPlayers().get(1));
        GameModelFacade.instance().setLocalPlayer(game.getPlayers().get(1));
        assertFalse("Already played dev card, should not be able to play.", facade.canPlayRoadBuilding(p2Next1, p2Next2));
    }

    @Test
    public void testIsFreeRound() throws Exception {
        game.setGameState(GameState.Discarding);
        assertFalse("Should not a free round.", facade.isFreeRound());

        game.setGameState(GameState.FirstRound);
        assertTrue("Should be a free round.", facade.isFreeRound());
        game.setGameState(GameState.SecondRound);
        assertTrue("Should be a free round.", facade.isFreeRound());
    }

    //************************//
    // private helper methods //
    //************************//

    private IGame initAGame(String jsonTestFile) throws Exception {
        String emptyBoardJSON = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(jsonTestFile)));
        ModelInitializer initModel = new ModelInitializer();
        initModel.initializeClientModel(emptyBoardJSON, 0);
        return GameModelFacade.instance().getGame();
    }
}