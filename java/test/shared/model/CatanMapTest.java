package shared.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;

import static org.junit.Assert.*;

public class CatanMapTest {
    private CatanMap map = null;
    private IPlayer player1 = null;
    private IPlayer player2 = null;

    /* Generate hex types based on the location */
    private HexType calculateType(HexLocation loc) {
        switch (loc.hashCode() % 6) {
            case 0: return HexType.BRICK;
            case 1: return HexType.WOOD;
            case 2: return HexType.WHEAT;
            case 3: return HexType.ORE;
            case 4: return HexType.SHEEP;
            case 5: return HexType.DESERT;
            default:
                assert false;
                return null;
        }
    }

    /* Generate a tile for testing */
    private IHex generateTile(HexLocation hex) {
        if (calculateType(hex) == HexType.DESERT) {
            return new Hex(calculateType(hex), hex, Hex.DESERT_NUMBER);
        }
        else {
            return new Hex(calculateType(hex), hex, (hex.hashCode() % 6 + 1) * 2);
        }
    }

    @Before
    public void setUp() throws Exception {
        // set up the tiles
        Collection<HexLocation> hexes = new ArrayList<>();

        for (int y =  0; y <= 2; ++y) hexes.add(new HexLocation(-2, y));
        for (int y = -1; y <= 2; ++y) hexes.add(new HexLocation(-1, y));
        for (int y = -2; y <= 2; ++y) hexes.add(new HexLocation( 0, y));
        for (int y = -2; y <= 1; ++y) hexes.add(new HexLocation( 1, y));
        for (int y = -2; y <= 0; ++y) hexes.add(new HexLocation( 2, y));

        Map<HexLocation, IHex> tiles = new HashMap<>();
        for (HexLocation hex : hexes) {
             tiles.put(hex, generateTile(hex));
        }

        // place ports
        Map<EdgeLocation, PortType> ports = new HashMap<>();
        ports.put(new EdgeLocation(-2, 0, EdgeDirection.NorthWest), PortType.BRICK);
        ports.put(new EdgeLocation(1, -2, EdgeDirection.North), PortType.THREE);
        ports.put(new EdgeLocation(2, -1, EdgeDirection.SouthEast), PortType.WOOD);
        ports.put(new EdgeLocation(0, 2, EdgeDirection.South), PortType.SHEEP);
        ports.put(new EdgeLocation(-1, 2, EdgeDirection.SouthWest), PortType.ORE);

        map = new CatanMap(tiles, ports);

        // two people to play the game
        player1 = new Player("Dave", 1337, CatanColor.BLUE, 0);
        player2 = new Player("Hal", 1010, CatanColor.RED, 0);
    }

    @After
    public void tearDown() throws Exception {
        map = null;
        player1 = player2 = null;
    }

    @Test
    public void testGetPlayersPorts() throws Exception {
        // no ports yet
        assertEquals(0, map.getPlayersPorts(player1).size());

        // nearby settlement, but not on port
        map.placeSettlement(new Settlement(player1), new VertexLocation(-2, 0, VertexDirection.East));

        assertEquals(0, map.getPlayersPorts(player1).size());
        assertEquals(0, map.getPlayersPorts(player2).size());

        // place settlement by one port
        map.placeSettlement(new Settlement(player1), new VertexLocation(-2, 0, VertexDirection.NorthWest)); //BRICK

        assertEquals(1, map.getPlayersPorts(player1).size());
        assertTrue(map.getPlayersPorts(player1).contains(PortType.BRICK));
        assertEquals(0, map.getPlayersPorts(player2).size());

        // get a few ports for one player, none for other
        map.placeSettlement(new Settlement(player1), new VertexLocation(1, -2, VertexDirection.NorthEast)); //THREE
        map.placeSettlement(new Settlement(player1), new VertexLocation(-1, 2, VertexDirection.SouthWest)); //ORE
        map.placeCity(new City(player1), new VertexLocation(-1, 2, VertexDirection.SouthWest)); //replace w/ city

        map.placeSettlement(new Settlement(player1), new VertexLocation(0, 2, VertexDirection.West)); //no port
        map.placeCity(new City(player1), new VertexLocation(0, 2, VertexDirection.West)); //make city
        map.placeSettlement(new Settlement(player2), new VertexLocation(0, 0, VertexDirection.NorthEast)); //no port

        assertEquals("Player has wrong number of ports.", 3, map.getPlayersPorts(player1).size());
        assertTrue("Wrong port types returned.", map.getPlayersPorts(player1).contains(PortType.BRICK));
        assertTrue("Wrong port types returned.", map.getPlayersPorts(player1).contains(PortType.THREE));
        assertTrue("Wrong port types returned.", map.getPlayersPorts(player1).contains(PortType.ORE));

        assertFalse("Wrong port types returned.", map.getPlayersPorts(player1).contains(PortType.WOOD));
        assertFalse("Wrong port types returned.", map.getPlayersPorts(player1).contains(PortType.WHEAT));
        assertFalse("Wrong port types returned.", map.getPlayersPorts(player1).contains(PortType.SHEEP));

        assertEquals("Player has wrong number of ports.", 0, map.getPlayersPorts(player2).size());

    }

    @Test
    public void testCanPlaceInitialRoad() throws Exception {
        // can place a floating, lonely road
        EdgeLocation edge = new EdgeLocation(0, 0, EdgeDirection.North);
        assertTrue(map.canPlaceInitialRoad(player1, edge));
        assertTrue(map.canPlaceInitialRoad(player1, edge.getEquivalentEdge()));
        assertTrue(map.canPlaceInitialRoad(player2, edge));
        assertTrue(map.canPlaceInitialRoad(player2, edge.getEquivalentEdge()));

        // cannot place a road next to any town (towns are placed second)
        map.placeSettlement(new Settlement(player1), new VertexLocation(0, 0, VertexDirection.NorthWest));
        assertFalse("Should not have been able to place road.", map.canPlaceInitialRoad(player1, edge));
        assertFalse("Should not have been able to place road.", map.canPlaceInitialRoad(player1, edge.getEquivalentEdge()));
        assertFalse("Should not have been able to place road.", map.canPlaceInitialRoad(player2, edge));
        assertFalse("Should not have been able to place road.", map.canPlaceInitialRoad(player2, edge.getEquivalentEdge()));
        assertFalse("Should not have been able to place road.", map.canPlaceInitialRoad(player1, new EdgeLocation(0, -1, EdgeDirection.SouthWest)));
        assertFalse("Should not have been able to place road.", map.canPlaceInitialRoad(player1, new EdgeLocation(-1, 0, EdgeDirection.NorthEast)));

        // a nearby but ok place to put a road
        assertTrue(map.canPlaceInitialRoad(player1, new EdgeLocation(-1, 0, EdgeDirection.South)));
        assertTrue(map.canPlaceInitialRoad(player2, new EdgeLocation(-1, 0, EdgeDirection.South)));
    }

    @Test
    public void testCanPlaceRoad() throws Exception {
        // cannot place "floating roads" (except for first round)
        EdgeLocation edge = new EdgeLocation(0, 0, EdgeDirection.North);
        assertFalse(map.canPlaceRoad(player1, edge));
        assertFalse(map.canPlaceRoad(player1, edge.getEquivalentEdge()));

        // test normal placing
        map.placeSettlement(new Settlement(player1), new VertexLocation(0, -2, VertexDirection.NorthEast));
        EdgeLocation edgeN = new EdgeLocation(0, -2, EdgeDirection.North);
        assertTrue(map.canPlaceRoad(player1, edgeN));
        assertTrue(map.canPlaceRoad(player1, edgeN.getEquivalentEdge()));

        EdgeLocation edgeNE = new EdgeLocation(0, -2, EdgeDirection.NorthEast);
        assertTrue(map.canPlaceRoad(player1, edgeNE));
        assertTrue(map.canPlaceRoad(player1, edgeNE.getEquivalentEdge()));

        // cannot place on water (even if connected to settlement)
        EdgeLocation edgeWater = new EdgeLocation(0, -3, EdgeDirection.SouthEast);
        assertFalse(map.canPlaceRoad(player1, edgeWater));
        assertFalse(map.canPlaceRoad(player1, edgeWater.getEquivalentEdge()));
        assertFalse(map.canPlaceRoad(player2, edgeWater));
        assertFalse(map.canPlaceRoad(player2, edgeWater.getEquivalentEdge()));

        // can't use opponents cities
        assertFalse(map.canPlaceRoad(player2, edgeN));
        assertFalse(map.canPlaceRoad(player2, edgeN.getEquivalentEdge()));
        assertFalse(map.canPlaceRoad(player2, edgeNE));
        assertFalse(map.canPlaceRoad(player2, edgeNE.getEquivalentEdge()));
        assertFalse(map.canPlaceRoad(player2, edgeWater));
        assertFalse(map.canPlaceRoad(player2, edgeWater.getEquivalentEdge()));

        // a road to build off of
        map.placeRoad(new Road(player1), edgeN);

        // cannot place roads on other roads
        assertFalse(map.canPlaceRoad(player1, edgeN));
        assertFalse(map.canPlaceRoad(player2, edgeN));

        // test connecting to a road
        EdgeLocation edgeNW = new EdgeLocation(0, -2, EdgeDirection.NorthWest);
        assertTrue(map.canPlaceRoad(player1, edgeNW));
        assertTrue(map.canPlaceRoad(player1, edgeNW.getEquivalentEdge()));

        // near but not touching
        EdgeLocation edgeSW = new EdgeLocation(0, -2, EdgeDirection.SouthWest);
        assertFalse(map.canPlaceRoad(player1, edgeSW));
        assertFalse(map.canPlaceRoad(player1, edgeSW.getEquivalentEdge()));

        // no water road connections
        EdgeLocation edgeWater2 = new EdgeLocation(0, -3, EdgeDirection.SouthWest);
        assertFalse(map.canPlaceRoad(player1, edgeWater2));
        assertFalse(map.canPlaceRoad(player1, edgeWater2.getEquivalentEdge()));

        // other player cannot connect to the road
        assertFalse(map.canPlaceRoad(player2, edgeNW));
        assertFalse(map.canPlaceRoad(player2, edgeNW.getEquivalentEdge()));

        // okay to connect to opponent road if also connected to own road/settlement
        map.placeSettlement(new Settlement(player2), new VertexLocation(0, -2, VertexDirection.West));
        assertTrue(map.canPlaceRoad(player2, edgeNW));
        assertTrue(map.canPlaceRoad(player2, edgeNW.getEquivalentEdge()));

        // player can connect to opponent's city if has own road
        assertTrue(map.canPlaceRoad(player1, edgeNW));
        assertTrue(map.canPlaceRoad(player1, edgeNW.getEquivalentEdge()));

        // player cannot build a road through an opponent's town
        map.placeRoad(new Road(player1), edgeNW);
        assertFalse(map.canPlaceRoad(player1, edgeSW));
        assertFalse(map.canPlaceRoad(player1, edgeSW.getEquivalentEdge()));
        assertFalse(map.canPlaceRoad(player1, new EdgeLocation(-1, -1, EdgeDirection.North)));
    }

    /* Test canPlaceSettlement method */
    @Test
    public void testCanPlaceSettlement() throws Exception {
        // cannot place a settlement with no roads
        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(0, 0, VertexDirection.NorthEast)));
        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(1, -1, VertexDirection.West)));
        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(0, -1, VertexDirection.SouthEast)));

        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(1, 1, VertexDirection.SouthEast)));

        IRoad road = new Road(player1);
        map.placeRoad(road, new EdgeLocation(0, 0, EdgeDirection.NorthEast));

        // simple placement next to a road
        assertTrue(map.canPlaceSettlement(player1, new VertexLocation(0, 0, VertexDirection.NorthEast)));
        assertTrue(map.canPlaceSettlement(player1, new VertexLocation(1, -1, VertexDirection.West)));
        assertTrue(map.canPlaceSettlement(player1, new VertexLocation(0, -1, VertexDirection.SouthEast)));

        map.placeSettlement(new Settlement(player1), new VertexLocation(0, 0, VertexDirection.NorthEast));

        // test whether a settlement can be placed where there already is a settlement
        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(0, 0, VertexDirection.NorthEast)));
        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(1, -1, VertexDirection.West)));
        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(0, -1, VertexDirection.SouthEast)));

        // test whether settlements can be placed right next to each other
        assertNotNull(map.getTownAt(new VertexLocation(0, 0, VertexDirection.NorthEast)));
        assertNull(map.getTownAt(new VertexLocation(0, 0, VertexDirection.East)));

        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(0, 0, VertexDirection.East)));
        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(1, -1, VertexDirection.SouthWest)));
        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(1, 0, VertexDirection.NorthWest)));

        assertFalse(map.canPlaceSettlement(player2, new VertexLocation(0, 0, VertexDirection.SouthEast)));
        assertFalse(map.canPlaceSettlement(player2, new VertexLocation(0, 1, VertexDirection.NorthEast)));
        assertFalse(map.canPlaceSettlement(player2, new VertexLocation(1, 0, VertexDirection.West)));

        // opponent's roads do not count for placement (player1 can place, not player2)
        map.placeRoad(new Road(player1), new EdgeLocation(0, 0, EdgeDirection.SouthEast));
        assertTrue(map.canPlaceSettlement(player1, new VertexLocation(0, 0, VertexDirection.SouthEast)));
        assertTrue(map.canPlaceSettlement(player1, new VertexLocation(0, 1, VertexDirection.NorthEast)));
        assertTrue(map.canPlaceSettlement(player1, new VertexLocation(1, 0, VertexDirection.West)));

        assertFalse(map.canPlaceSettlement(player2, new VertexLocation(0, 0, VertexDirection.SouthEast)));
        assertFalse(map.canPlaceSettlement(player2, new VertexLocation(0, 1, VertexDirection.NorthEast)));
        assertFalse(map.canPlaceSettlement(player2, new VertexLocation(1, 0, VertexDirection.West)));

        // now, give player2 a road, so placement is valid for player2
        map.placeRoad(new Road(player2), new EdgeLocation(0, 1, EdgeDirection.NorthEast));

        assertTrue(map.canPlaceSettlement(player2, new VertexLocation(0, 0, VertexDirection.SouthEast)));
        assertTrue(map.canPlaceSettlement(player2, new VertexLocation(0, 1, VertexDirection.NorthEast)));
        assertTrue(map.canPlaceSettlement(player2, new VertexLocation(1, 0, VertexDirection.West)));

        // test whether a settlement can be placed where there is already a city
        map.placeSettlement(new Settlement(player1), new VertexLocation(2, -2, VertexDirection.NorthEast));
        assertFalse(map.canPlaceSettlement(player1, new VertexLocation(2, -2, VertexDirection.NorthEast)));
        assertFalse(map.canPlaceSettlement(player2, new VertexLocation(2, -2, VertexDirection.NorthEast)));
    }

    @Test
    public void testCanPlaceCity() throws Exception {
         // cannot place a city where there is nothing
        assertFalse(map.canPlaceCity(player1, new VertexLocation(0, 0, VertexDirection.NorthEast)));
        assertFalse(map.canPlaceCity(player1, new VertexLocation(1, -1, VertexDirection.West)));
        assertFalse(map.canPlaceCity(player1, new VertexLocation(0, -1, VertexDirection.SouthEast)));

        assertFalse(map.canPlaceCity(player1, new VertexLocation(1, 1, VertexDirection.SouthEast)));

        IRoad road = new Road(player1);
        map.placeRoad(road, new EdgeLocation(0, 0, EdgeDirection.NorthEast));

        // simple placement next to a road with no settlement (valid for settlement, not city)
        assertFalse(map.canPlaceCity(player1, new VertexLocation(0, 0, VertexDirection.NorthEast)));
        assertFalse(map.canPlaceCity(player1, new VertexLocation(1, -1, VertexDirection.West)));
        assertFalse(map.canPlaceCity(player1, new VertexLocation(0, -1, VertexDirection.SouthEast)));

        map.placeSettlement(new Settlement(player1), new VertexLocation(0, 0, VertexDirection.NorthEast));

        // Now that there is a settlement, should be valid for placement
        assertTrue(map.canPlaceCity(player1, new VertexLocation(0, 0, VertexDirection.NorthEast)));
        assertTrue(map.canPlaceCity(player1, new VertexLocation(1, -1, VertexDirection.West)));
        assertTrue(map.canPlaceCity(player1, new VertexLocation(0, -1, VertexDirection.SouthEast)));

        // verify that one player cannot upgrade another player's settlement
        map.placeRoad(new Road(player2), new EdgeLocation(0, 1, EdgeDirection.NorthEast));
        assertTrue(map.canPlaceSettlement(player2, new VertexLocation(0, 0, VertexDirection.SouthEast)));
        map.placeSettlement(new Settlement(player2), new VertexLocation(0, 0, VertexDirection.SouthEast));

        assertTrue(map.canPlaceCity(player2, new VertexLocation(0, 0, VertexDirection.SouthEast)));
        assertTrue(map.canPlaceCity(player2, new VertexLocation(0, 1, VertexDirection.NorthEast)));
        assertTrue(map.canPlaceCity(player2, new VertexLocation(1, 0, VertexDirection.West)));

        assertFalse(map.canPlaceCity(player1, new VertexLocation(0, 0, VertexDirection.SouthEast)));
        assertFalse(map.canPlaceCity(player1, new VertexLocation(0, 1, VertexDirection.NorthEast)));
        assertFalse(map.canPlaceCity(player1, new VertexLocation(1, 0, VertexDirection.West)));

        // test whether a city can be placed on another city
        map.placeCity(new City(player2), new VertexLocation(1, 0, VertexDirection.West));
        assertFalse(map.canPlaceCity(player2, new VertexLocation(0, 0, VertexDirection.SouthEast)));
        assertFalse(map.canPlaceCity(player2, new VertexLocation(0, 1, VertexDirection.NorthEast)));
        assertFalse(map.canPlaceCity(player2, new VertexLocation(1, 0, VertexDirection.West)));
        assertFalse(map.canPlaceCity(player1, new VertexLocation(0, 0, VertexDirection.SouthEast)));
        assertFalse(map.canPlaceCity(player1, new VertexLocation(0, 1, VertexDirection.NorthEast)));
        assertFalse(map.canPlaceCity(player1, new VertexLocation(1, 0, VertexDirection.West)));
    }

    @Test
    public void testGetPlayersOnTile() throws Exception {
        IPlayer player3 = new Player("John", 123, CatanColor.BLUE, 3);

        assertEquals("Players returned from empty map/tile.", 0, map.getRobbablePlayersOnTile(new HexLocation(2, -2), null).size());

        map.placeSettlement(new Settlement(player1), new VertexLocation(2, -2, VertexDirection.West));

        // test having one player on a tile
        Set<IPlayer> playersOnTile = map.getRobbablePlayersOnTile(new HexLocation(2, -2), null);

        assertFalse("Player has no resources and so is not robbable.", playersOnTile.contains(player1));
        assertFalse("Found wrong player on tile.", playersOnTile.contains(player2));
        assertFalse("Found wrong player on tile.", playersOnTile.contains(player3));

        player1.setResources(new ResourceBank(0, 0, 0, 0, 1));
        playersOnTile = map.getRobbablePlayersOnTile(new HexLocation(2, -2), null);
        assertTrue("Failed to find player (with resources) on tile.", playersOnTile.contains(player1));
        assertFalse("Found wrong player on tile.", playersOnTile.contains(player2));
        assertFalse("Found wrong player on tile.", playersOnTile.contains(player3));

        // test having three different players on a tile
        map.placeSettlement(new Settlement(player1), new VertexLocation(0, 0, VertexDirection.West));
        map.placeSettlement(new Settlement(player2), new VertexLocation(0, 0, VertexDirection.NorthEast));
        map.placeSettlement(new Settlement(player3), new VertexLocation(0, 0, VertexDirection.SouthEast));


        // only player1 has resources
        playersOnTile = map.getRobbablePlayersOnTile(new HexLocation(0, 0), null);

        assertEquals("Wrong number of players found.", 1, playersOnTile.size());
        assertTrue("Failed to find player on tile.", playersOnTile.contains(player1));
        assertFalse("Found player with no resources on tile.", playersOnTile.contains(player2));
        assertFalse("Found player with no resources on tile.", playersOnTile.contains(player3));

        // all players have resources
        player2.setResources(new ResourceBank(3231, 34870, 218390, 384093740, 8309));
        player3.setResources(new ResourceBank(1, 0, 0, 0, 0));

        playersOnTile = map.getRobbablePlayersOnTile(new HexLocation(0, 0), null);

        assertEquals("Wrong number of players found.", 3, playersOnTile.size());
        assertTrue("Failed to find player on tile.", playersOnTile.contains(player1));
        assertTrue("Failed to find player on tile.", playersOnTile.contains(player2));
        assertTrue("Failed to find player on tile.", playersOnTile.contains(player3));
    }

    @Test
    public void testCanPlaceTwoRoads() throws Exception {
        EdgeLocation start = new EdgeLocation(0, 0, EdgeDirection.South);
        EdgeLocation next1 = new EdgeLocation(0, 0, EdgeDirection.SouthWest);
        EdgeLocation next2 = new EdgeLocation(-1, 1, EdgeDirection.North);

        map.placeRoad(new Road(player1), start);
        assertTrue("Should be able to place two roads starting from existing.", map.canPlaceTwoRoads(player1, next1, next2));
        assertFalse("Should be not be able to place two roads in wrong order.", map.canPlaceTwoRoads(player1, next2, next1));
        assertFalse("Should be not be able to place the same two roads.", map.canPlaceTwoRoads(player1, next1, next1));

        assertFalse("Opponent should not be able to connect roads.", map.canPlaceTwoRoads(player2, next1, next2));
    }

    @Test
    public void testDistributeResources() throws Exception {
        map.placeSettlement(new Settlement(player1), new VertexLocation(0, 0, VertexDirection.NorthWest));
        map.placeSettlement(new Settlement(player2), new VertexLocation(0, 0, VertexDirection.SouthEast));
        map.placeCity(new City(player2), new VertexLocation(0, 0, VertexDirection.SouthEast));

        // Hex Location, Number, ResourceType
        // (-1, 0), 2, BRICK
        // (0, -1), 2, BRICK
        // (0, 0), 4, WOOD
        // (0, 1), 6, WHEAT
        // (1, 0), 6, WHEAT

        // To get tile info, you can just call generateTile(hexLocation of tile)
//        Used to get tile info:
//        Collection<IHex> tiles = map.getTiles();
//        for(IHex tile : tiles) {
//            HexLocation location = new HexLocation(1, 0);
//            if(tile.location().equals(location)) {
//                System.out.println(tile.resource());
//                System.out.println(tile.numberToken());
//            }
//        }

        assertEquals("Player1 should have no resources", 0, player1.getResources().getCount());
        assertEquals("Player2 should have no resources", 0, player2.getResources().getCount());
        map.distributeResources(2, new ResourceBank(0, 0, 0, 0, 0));
        map.distributeResources(4, new ResourceBank(0, 0, 0, 0, 0));
        map.distributeResources(6, new ResourceBank(0, 0, 0, 0, 0));
        assertEquals("Player1 should still have no resources", 0, player1.getResources().getCount());
        assertEquals("Player2 should still have no resources", 0, player2.getResources().getCount());
        map.distributeResources(4, new ResourceBank(19, 19, 19, 19, 19));
        assertEquals("Player1 should have 1 resource", 1, player1.getResources().getCount());
        assertEquals("Player1 should have 1 wood", 1, player1.getResources().getCount(ResourceType.WOOD));
        assertEquals("Player2 should have 2 wood", 2, player2.getResources().getCount(ResourceType.WOOD));
        map.distributeResources(2, new ResourceBank(19, 19, 19, 19, 19));
        assertEquals("Player1 should have 3 resources", 3, player1.getResources().getCount());
        assertEquals("Player1 should have 1 wood and 2 brick", 1, player1.getResources().getCount(ResourceType.WOOD));
        assertEquals("Player1 should have 1 wood and 2 brick", 2, player1.getResources().getCount(ResourceType.BRICK));
        assertEquals("Player2 should have 2 wood", 2, player2.getResources().getCount());
        map.distributeResources(6, new ResourceBank(19, 19, 19, 19, 19));
        assertEquals("Player2 should have 2 wood and 4 wheat", 6, player2.getResources().getCount());
        assertEquals("Player2 should have 2 wood and 4 wheat", 4, player2.getResources().getCount(ResourceType.WHEAT));
        assertEquals("Player2 should have 2 wood and 4 wheat", 2, player2.getResources().getCount(ResourceType.WOOD));
        map.distributeResources(6, new ResourceBank(0, 0, 0, 0, 0));
        assertEquals("Player2 should still have 6 resources", 6, player2.getResources().getCount());
        assertEquals("Player2 should still have 2 wood", 2, player2.getResources().getCount(ResourceType.WOOD));
        assertEquals("Player2 should still have 4 wheat", 4, player2.getResources().getCount(ResourceType.WHEAT));
        map.distributeResources(2, new ResourceBank(0, 1, 0, 1, 0));
        assertEquals("Player2 should still have 6 resources", 6, player2.getResources().getCount());
        assertEquals("Player1 should have gotten 0 more brick", 2, player1.getResources().getCount(ResourceType.BRICK));
        map.distributeResources(6, new ResourceBank(0, 1, 0, 1, 0));
        assertEquals("Player2 should have gotten 0 more wheat", 4, player2.getResources().getCount(ResourceType.WHEAT));
        assertEquals("Player2 should still have 6 resources", 6, player2.getResources().getCount());

        map.distributeResources(2, new ResourceBank(1, 1, 1, 3, 1));
        map.distributeResources(6, new ResourceBank(1, 1, 1, 3, 1));
        map.distributeResources(4, new ResourceBank(1, 1, 1, 3, 1));
        assertEquals("Player1 should still have 3 resources", 3, player1.getResources().getCount());
        assertEquals("Player2 should still have 6 resources", 6, player2.getResources().getCount());
    }
}
