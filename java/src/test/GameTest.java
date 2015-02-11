package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.models.Game;
import shared.models.TurnTracker;
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
	}

	@Test
	public void testCanRoleDice() {
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
	}

	@Test
	public void testCanUseDevCard() {
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
	}

}
