package test;

import com.google.gson.Gson;
import org.junit.Test;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.models.Game;
import shared.models.Port;
import shared.models.jsonholder.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertTrue;

public class ModelInitTest {

    @Test
    public void testBuildCatanGame() {
        System.out.println("Testing Model Initialization");
        System.out.println("--------------------------------------------");
        try {
            String filePath = new File("").getAbsolutePath();
            BufferedReader br = new BufferedReader(new FileReader(filePath + "/java/src/TestJSON.json"));
            Gson gson = new Gson();
            JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);
            System.out.println("Deserialize Done");
            Game thisGame = modelHolder.buildCatanGame();
            System.out.println("Comparing JsonModelHolder and GameModel");
            System.out.println("Check if all players are there");
            assertTrue(modelHolder.getPlayers().length == thisGame.getPlayers().length);
            System.out.println("Check if bank has correct numbers of resource and development cards");
            assertTrue(modelHolder.getBank().getTotalResNum() == thisGame.getBank().getResCards().size());
            assertTrue(modelHolder.getDeck().getTotalDevNum() == thisGame.getBank().getDevCards().size());
            System.out.println("Check if Roads are on right position");
            for (Road thisRoad : modelHolder.getMap().getRoads()) {
                int x = thisRoad.getLocation().getX();
                int y = thisRoad.getLocation().getY();
                String direction = thisRoad.getLocation().getDirection();
                int jsonModelHolderOwnerId = thisRoad.getOwner();
                int gameModelOwnerId = thisGame.getMap().getHexTileAt(new HexLocation(x, y))
                        .getEdgeAt(modelHolder.stringToEdgeDirection(direction)).getRoad().getOwnerPlayerIndex();
                assertTrue(jsonModelHolderOwnerId == gameModelOwnerId);
            }
            System.out.println("Check if Settlements are on right position");
            for (Settlement thisSettlement : modelHolder.getMap().getSettlements()) {
                int x = thisSettlement.getLocation().getX();
                int y = thisSettlement.getLocation().getY();
                String direction = thisSettlement.getLocation().getDirection();
                int jsonModelHolderOwnerId = thisSettlement.getOwner();
                int gameModelOwnerId = thisGame.getMap().getHexTileAt(new HexLocation(x, y))
                        .getVertexAt(modelHolder.stringToVertexDirection(direction)).getSettlement().getOwnerPlayerIndex();
                assertTrue(jsonModelHolderOwnerId == gameModelOwnerId);
            }
            System.out.println("Check if HexTiles have correct informations");
            for (Hex thisHex : modelHolder.getMap().getHexes()) {
                int x = thisHex.getLocation().getX();
                int y = thisHex.getLocation().getY();
                int gameHexNum = thisGame.getMap().getHexTileAt(new HexLocation(x, y)).getToken();
                assertTrue(thisHex.getNumber() == gameHexNum);
            }
            System.out.println("Check if Ports have correct informations");
            for (JsonPort thisPort : modelHolder.getMap().getPorts()) {
                int x = thisPort.getLocation().getX();
                int y = thisPort.getLocation().getY();
                String direction = thisPort.getDirection();
                EdgeLocation thisLoc = new EdgeLocation(new HexLocation(x, y), modelHolder.stringToEdgeDirection(direction));
                Port gamePort = thisGame.getMap().getPorts().get(thisLoc);
                if (thisPort.getResource() == null) {
                    assertTrue(gamePort.getType() == null);
                } else {
                    assertTrue(modelHolder.stringToPortType(thisPort.getResource()) == gamePort.getType());
                }
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
