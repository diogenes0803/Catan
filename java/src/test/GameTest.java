package test;

import com.google.gson.Gson;
import org.junit.Test;
import shared.definitions.HexType;
import shared.locations.*;
import shared.models.*;
import shared.models.jsonholder.JsonModelHolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void testCanBuildRoad() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);
        Game thisGame = modelHolder.buildCatanGame();
        System.out.println("Testing CanBuildRoad");
        System.out.println("--------------------------------------------");

        System.out.println("Testing User doesn't have enough resources.");
        assertFalse("Error: user was permitted to build road.",
                thisGame.canBuildRoad(new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.SouthWest)));

        System.out.println("Testing User has enough resources but there is a road already.");
        thisGame.getTurnTracker().setCurrentTurn(1);
        assertFalse("Error: User was permitted to build road ontop of an exsting road.",
                thisGame.canBuildRoad(new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.South)));

        System.out.println("Testing User has enough resources and location is good");
        assertTrue("Error: user could not build road.", thisGame.canBuildRoad(new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.SouthWest)));

        System.out.println("Testing User has no resources, but valid location in setup round 1");
        thisGame.getTurnTracker().setStatus("FirstRound");
        thisGame.getTurnTracker().setCurrentTurn(3);
        assertTrue("Error: user could not build road during setup phase 1.", thisGame.canBuildRoad(new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.South)));

        System.out.println("Testing User has no resources, but valid location in setup round 2");
        thisGame.getTurnTracker().setStatus("SecondRound");
        assertTrue("Error: user could not build road during setup phase 2.", thisGame.canBuildRoad(new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.South)));

        System.out.println("Testing setup round 2, but invalid location");
        assertFalse("Error: user was allowed to build road in invalid location", thisGame.canBuildRoad(new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.SouthWest)));

        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanPlayDevCard() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);
        Game thisGame = modelHolder.buildCatanGame();

        System.out.println("Testing CanPlayDevCard");
        System.out.println("--------------------------------------------");
        System.out.println("Testing User has no Development Cards");
        assertFalse("Error: user was allowed to play a Development Card.",
                thisGame.canPlayDevCard());
        System.out.println("Testing User has a new Devlopment Card");
        thisGame.getTurnTracker().setCurrentTurn(2);
        assertFalse("Error: user was allowed to play a new Development Card.",
                thisGame.canPlayDevCard());
        System.out.println("Testing user has Development Cards");
        thisGame.getTurnTracker().setCurrentTurn(1);
        assertTrue("Error: user was not permitted to play a Development Card.",
                thisGame.canPlayDevCard());
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanOfferTrade() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);
        Game thisGame = modelHolder.buildCatanGame();

        System.out.println("Testing CanOfferTrade");
        System.out.println("--------------------------------------------");
        System.out.println("Testing user doesn't have enough resources");
        ResourceList offer = new ResourceList(-100, -100, -100, -100, -100);
        assertFalse("Error: user was permitted to offer an invalid trade.",
                thisGame.canOfferTrade(offer));
        System.out.println("Testing user has enough cards.");
        thisGame.getTurnTracker().setCurrentTurn(1);
        assertTrue("Error: user was not permitted to offer a valid trade.",
                thisGame.canOfferTrade(offer));
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanAcceptTrade() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);
        Game thisGame = modelHolder.buildCatanGame();

        System.out.println("Testing CanAcceptTrade");
        System.out.println("--------------------------------------------");
        System.out.println("Testing no trade has been offered");
        assertFalse("Error: user was permitted to accept a non-existing trade.",
                thisGame.canAcceptTrade(0));
        System.out.println("Testing user doesn't have enough resources");
        TradeOffer offer = new TradeOffer(1, 0, new ResourceList(-100, -100, -100, -100, -100));
        thisGame.setTradeOffer(offer);
        assertFalse("Error: user was permitted to accept an invalid trade.",
                thisGame.canAcceptTrade(0));
        System.out.println("Testing valid trade offer");
        offer = new TradeOffer(1, 0, new ResourceList(0, 1, 1, 0, 1));
        thisGame.setTradeOffer(offer);
        assertTrue("Error: user was not permitted to accept a valid trade.",
                thisGame.canAcceptTrade(0));
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanRollDice() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);
        Game thisGame = modelHolder.buildCatanGame();

        System.out.println("Testing CanRollDice");
        System.out.println("--------------------------------------------");
        System.out.println("Testing status isn't Rolling");
        assertFalse("Error: user was permitted to roll during the wrong phase.",
                thisGame.canRollDice());
        System.out.println("Testing status is Rolling");
        thisGame.getTurnTracker().setStatus("Rolling");
        assertTrue("Error: user wasn't permitted to roll during the rolling phase.",
                thisGame.canRollDice());
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanBuildSettlement() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);

        Game thisGame = modelHolder.buildCatanGame();

        System.out.println("Testing CanBuildSettlement");
        System.out.println("--------------------------------------------");
        System.out.println("Testing user doesn't have enough resources");
        assertFalse("Error: user was permitted to build settlement with insufficient resources.",
                thisGame.canBuildSettlement(new VertexLocation(new HexLocation(0, 1), VertexDirection.West)));
        System.out.println("Testing user has enough resources, but there's already a settlement there");
        thisGame.getTurnTracker().setCurrentTurn(1);
        assertFalse("Error: user was permitted to build settlement on pre-existing settlement.",
                thisGame.canBuildSettlement(new VertexLocation(new HexLocation(-1, -1), VertexDirection.SouthWest)));
        System.out.println("Testing user building settlement too close to friendly settlement");
        assertFalse("Error: user was permitted to build settlement too close to pre-existing settlement.",
                thisGame.canBuildSettlement(new VertexLocation(new HexLocation(-1, -1), VertexDirection.SouthEast)));
        System.out.println("Testing user has enough resources, and location is good");
        assertTrue("Error: user was not permitted to build settlement in valid location.",
                thisGame.canBuildSettlement(new VertexLocation(new HexLocation(-1, -1), VertexDirection.East)));
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanUpgradeToCity() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);

        Game thisGame = modelHolder.buildCatanGame();

        System.out.println("Testing CanUpgradeToCity");
        System.out.println("--------------------------------------------");
        System.out.println("Testing user doesn't have enough resources");
        assertFalse("Error: user was permitted to build settlement with insufficient resources.",
                thisGame.canUpgradeToCity(new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast)));
        System.out.println("Testing user has enough resources, but no settlement exists");
        thisGame.getTurnTracker().setCurrentTurn(1);
        assertFalse("Error: user was permitted to build a city in invalid location.",
                thisGame.canUpgradeToCity(new VertexLocation(new HexLocation(-1, -1), VertexDirection.East)));
        System.out.println("Testing user has enough resources and valid location");
        assertTrue("Error: user was not permitted to build a city in a valid location.",
                thisGame.canUpgradeToCity(new VertexLocation(new HexLocation(-1, -1), VertexDirection.SouthWest)));
        System.out.println("");
        System.out.println("");

    }

    @Test
    public void testCanBuyDevCard() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);

        Game thisGame = modelHolder.buildCatanGame();

        System.out.println("Testing CanBuyDevCard");
        System.out.println("--------------------------------------------");
        System.out.println("Testing User doesn't have enough resources");
        assertFalse("Error: user was permitted to buy a Devolepment Card.",
                thisGame.canBuyDevCard());
        System.out.println("Testing user has enough resources");
        thisGame.getTurnTracker().setCurrentTurn(1);
        assertTrue("Error: user was not permitted to buy a Development Card.",
                thisGame.canBuyDevCard());
        System.out.println("Testing User has enough resources, but there are no Devlopment cards");
        List<DevCard> newList = new ArrayList<DevCard>();
        thisGame.getBank().setDevCards(newList);
        assertFalse("Error: user was permitted to buy a Development Card.",
                thisGame.canBuyDevCard());
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanMoveRobber() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);

        Game thisGame = modelHolder.buildCatanGame();

        System.out.println("Testing CanMoveRobber");
        System.out.println("--------------------------------------------");
        System.out.println("Testing dice aren't correct");
        assertFalse("Error: user was permitted to move robber.",
                thisGame.canMoveRobber(new HexLocation(0, 0)));
        System.out.println("Testing invalid location to move robber");
        thisGame.setDice(7);
        assertFalse("Error: user was permitted to move robber to invalid location.",
                thisGame.canMoveRobber(new HexLocation(0, -2)));
        System.out.println("Testing valid location to move robber");
        assertTrue("Error: user was not permitted to move robber to valid location.",
                thisGame.canMoveRobber(new HexLocation(0, 0)));

        System.out.println("Testing for moving robber to same tile");

        assertFalse("Error: User was able to place robber on same tile.", thisGame.canMoveRobber(thisGame.getMap().getRobberLocation()));


        Piece piece = new Piece();
        piece.setOwnerPlayerIndex(thisGame.getPlayers()[2].getPlayerId());
        HexTile hex = new HexTile(0, 0, HexType.ORE, 1);
        hex.getVertexAt(VertexDirection.NorthWest).setSettlement(piece);

        System.out.println("Check if we can rob Player3 \n(Note: this checks if Player3 has a settlement there to validate if Player3 can be selected)");
        //assertTrue("Error: Player3 was not able to be targeted", thisGame.canRobPlayer(thisGame.getMap().getHexTiles()[0][0], thisGame.getPlayers()[2]));
        assertTrue("Error: Player3 was not able to be targeted", thisGame.canRobPlayer(hex, thisGame.getPlayers()[2]));
        //tempList.add(new ResCard(ResourceType.ORE));
        //players[2].setResCards(tempList);
        System.out.println("Attempt to steal from Player2. . .");
        assertFalse("Error: Player1 was able to rob Player2 who is not on Hex", thisGame.canRobPlayer(hex, thisGame.getPlayers()[1]));
        System.out.println("Add settlement for Player2");
        piece.setOwnerPlayerIndex(thisGame.getPlayers()[1].getPlayerId());
        hex.getVertexAt(VertexDirection.West).setSettlement(piece);
        thisGame.getPlayers()[1].setResCards(new ArrayList<ResCard>());
        assertFalse("Error: Player1 was able to rob Player2 who had no cards", thisGame.canRobPlayer(hex, thisGame.getPlayers()[1]));

        //thisGame.setPlayers();
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanFinishTurn() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);

        Game thisGame = modelHolder.buildCatanGame();

        System.out.println("Testing CanFinishTurn");
        System.out.println("--------------------------------------------");
        System.out.println("Testing status isn't Playing");
        thisGame.getTurnTracker().setStatus("Rolling");
        assertFalse("Error: user was permitted to end turn during incorrect phase.",
                thisGame.canFinishTurn());
        System.out.println("Testing status is Playing");
        thisGame.getTurnTracker().setStatus("Playing");
        assertTrue("Error: user was not permitted to end turn.",
                thisGame.canFinishTurn());
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testIsPlayerTurn() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);

        Game thisGame = modelHolder.buildCatanGame();

        System.out.println("Testing IsPlayerTurn");
        System.out.println("--------------------------------------------");
        int playerId = 0;

        System.out.println("Testing it is the user's turn");
        assertTrue("Error: game indicated it was not user's turn.",
        		 thisGame.getTurnTracker().getCurrentTurn() == playerId);
        System.out.println("Testing it is not the user's turn");
        playerId = 1;
        assertFalse("Error: game indicated it was the user's turn",
        		 thisGame.getTurnTracker().getCurrentTurn() == playerId);
        System.out.println("");
        System.out.println("");
    }

}
