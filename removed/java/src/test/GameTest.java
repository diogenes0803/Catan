package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.*;
import shared.models.jsonholder.JsonModelHolder;

import com.google.gson.Gson;

public class GameTest {

    @Test
    public void testCanBuildRoad() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
        TurnTracker.getInstance().setCurrentTurn(1);
        assertFalse("Error: User was permitted to build road ontop of an exsting road.",
                thisGame.canBuildRoad(new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.South)));
        System.out.println("Testing User has enough resources and location is good");
        assertTrue("Error: user could not build road.",thisGame.canBuildRoad(new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.SouthWest)));
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanPlayDevCard() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
        TurnTracker.getInstance().setCurrentTurn(2);
        assertFalse("Error: user was allowed to play a new Development Card.",
                thisGame.canPlayDevCard());
        System.out.println("Testing user has Development Cards");
        TurnTracker.getInstance().setCurrentTurn(1);
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
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
        ResourceList offer = new ResourceList(-100,-100,-100,-100,-100);
        assertFalse("Error: user was permitted to offer an invalid trade.",
                thisGame.canOfferTrade(offer));
        System.out.println("Testing user has enough cards.");
        TurnTracker.getInstance().setCurrentTurn(1);
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
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
        TradeOffer offer = new TradeOffer(1, 0, new ResourceList(-100,-100,-100,-100,-100));
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
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
        TurnTracker.getInstance().setStatus("Rolling");
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
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
                thisGame.canBuildSettlement(new VertexLocation(new HexLocation(0,1), VertexDirection.West)));
        System.out.println("Testing user has enough resources, but there's already a settlement there");
        TurnTracker.getInstance().setCurrentTurn(1);
        assertFalse("Error: user was permitted to build settlement on pre-existing settlement.",
                thisGame.canBuildSettlement(new VertexLocation(new HexLocation(-1,-1), VertexDirection.SouthWest)));
        System.out.println("Testing user building settlement too close to friendly settlement");
        assertFalse("Error: user was permitted to build settlement too close to pre-existing settlement.",
                thisGame.canBuildSettlement(new VertexLocation(new HexLocation(-1,-1), VertexDirection.SouthEast)));
        System.out.println("Testing user has enough resources, and location is good");
        assertTrue("Error: user was not permitted to build settlement in valid location.",
                thisGame.canBuildSettlement(new VertexLocation(new HexLocation(-1,-1), VertexDirection.East)));
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanUpgradeToCity() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
                thisGame.canUpgradeToCity(new VertexLocation(new HexLocation(0,1), VertexDirection.SouthEast)));
        System.out.println("Testing user has enough resources, but no settlement exists");
        TurnTracker.getInstance().setCurrentTurn(1);
        assertFalse("Error: user was permitted to build a city in invalid location.",
                thisGame.canUpgradeToCity(new VertexLocation(new HexLocation(-1,-1), VertexDirection.East)));
        System.out.println("Testing user has enough resources and valid location");
        assertTrue("Error: user was not permitted to build a city in a valid location.",
                thisGame.canUpgradeToCity(new VertexLocation(new HexLocation(-1,-1), VertexDirection.SouthWest)));
        System.out.println("");
        System.out.println("");

    }

    @Test
    public void testCanBuyDevCard() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
        TurnTracker.getInstance().setCurrentTurn(1);
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
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
                thisGame.canMoveRobber(new HexLocation(0,0)));
        System.out.println("Testing invalid location to move robber");
        thisGame.setDice(7);
        assertFalse("Error: user was permitted to move robber to invalid location.",
                thisGame.canMoveRobber(new HexLocation(0, -2)));
        System.out.println("Testing valid location to move robber");
        assertTrue("Error: user was not permitted to move robber to valid location.",
                thisGame.canMoveRobber(new HexLocation(0,0)));

        System.out.println("Testing for moving robber to same tile");

        assertFalse("Error: User was able to place robber on same tile.", thisGame.canMoveRobber(thisGame.getMap().getRobberLocation()));
//        System.out.println("Testing the ability to rob players.\n Player1 moves robber to tile where Player2 and Player3 are present.");
//        System.out.println("Creating Players. . .");
        /*
        Player[] players = {new Player(), new Player(), new Player(), new Player()};
        players[0].setPlayerId(1);
        players[1].setPlayerId(2);
        players[2].setPlayerId(3);
        players[3].setPlayerId(4);
        thisGame.setPlayers(players);
        */
        //List<ResCard> tempList = new ArrayList<>();
//        System.out.println("Giving a card to player 3. . .");
//        thisGame.getPlayers()[2].getResCards().add(new ResCard(ResourceType.ORE));
//        thisGame.getPlayers()[1].setPlayerId(2);
//        thisGame.getPlayers()[2].setPlayerId(3);
//        System.out.print("Adding settlement of Player3 to Hex");

//        Piece piece = new Piece();
//        piece.setOwnerPlayerId(3);
        // For now, because Piece is on a Vertex we may assume it is a type of settlement


//        HexTile hex = new HexTile(0,0,ResourceType.ORE,1);
        //hex.setLocation(new HexLocation(0,0));
        //thisGame.getMap().getHexTiles()[0][0] = hex;
        //thisGame.getMap().getHexTiles()[0][0].getVertexAt(VertexDirection.East).setSettlement(piece);
        //       hex.getVertexAt(VertexDirection.West).setSettlement(piece);


        System.out.println("Check if we can rob Player3 \n(Note: this checks if Player3 has a settlement there to validate if Player3 can be selected)");
        //assertTrue("Error: Player3 was not able to be targeted", thisGame.canRobPlayer(thisGame.getMap().getHexTiles()[0][0], thisGame.getPlayers()[2]));
        assertTrue("Error: Player3 was not able to be targeted", thisGame.canRobPlayer(hex, thisGame.getPlayers()[2]));

        //tempList.add(new ResCard(ResourceType.ORE));
        //players[2].setResCards(tempList);
        System.out.println("Attempt to steal from Player2. . .");
        assertFalse("Error: Player1 was able to rob Player2 who is not on Hex", thisGame.canRobPlayer(thisGame.getMap().getHexTiles()[0][0], thisGame.getPlayers()[1]));
        System.out.println("Add settlement for Player2");
        piece.setOwnerPlayerId(2);
        thisGame.getMap().getHexTiles()[0][0].getVertexAt(VertexDirection.West).setSettlement(piece);
        assertFalse("Error: Player1 was able to rob Player2 who had no cards", thisGame.canRobPlayer(thisGame.getMap().getHexTiles()[0][0],thisGame.getPlayers()[1]));

        //thisGame.setPlayers();
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testCanFinishTurn() {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
        TurnTracker.getInstance().setStatus("Rolling");
        assertFalse("Error: user was permitted to end turn during incorrect phase.",
                thisGame.canFinishTurn());
        System.out.println("Testing status is Playing");
        TurnTracker.getInstance().setStatus("Playing");
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
            br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
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
                TurnTracker.getInstance().getCurrentTurn() == playerId);
        System.out.println("Testing it is not the user's turn");
        playerId = 1;
        assertFalse("Error: game indicated it was the user's turn",
                TurnTracker.getInstance().getCurrentTurn() == playerId);
        System.out.println("");
        System.out.println("");
    }

}
