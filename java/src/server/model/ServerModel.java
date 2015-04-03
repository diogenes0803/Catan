package server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shared.data.GameInfo;
import shared.data.PlayerInfo;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.models.Bank;
import shared.models.CatanMap;
import shared.models.DevCard;
import shared.models.Game;
import shared.models.HexTile;
import shared.models.Piece;
import shared.models.Player;
import shared.models.Port;
import shared.models.ResCard;

public class ServerModel extends Game {
	public ServerModel() {
		super();
		initialization();
	}
	private void initialization() {
		this.setBank(createBank());
		this.setMap(createMap());
	}
	
	private Bank createBank() {
		Bank newBank = new Bank();
		List<DevCard> devCards = new ArrayList<DevCard>();
		List<ResCard> resCards = new ArrayList<ResCard>();
		devCards.add(new DevCard(DevCardType.YEAR_OF_PLENTY, false));
		devCards.add(new DevCard(DevCardType.YEAR_OF_PLENTY, false));
		devCards.add(new DevCard(DevCardType.MONOPOLY, false));
		devCards.add(new DevCard(DevCardType.MONOPOLY, false));
		for(int i = 0; i < 14; i++) {
			devCards.add(new DevCard(DevCardType.SOLDIER, false));
		}
		devCards.add(new DevCard(DevCardType.ROAD_BUILD, false));
		devCards.add(new DevCard(DevCardType.ROAD_BUILD, false));
		for(int i = 0; i < 5; i ++) {
			devCards.add(new DevCard(DevCardType.MONUMENT, false));
		}
		for(int i=0; i < 24; i++) {
			resCards.add(new ResCard(ResourceType.BRICK));
			resCards.add(new ResCard(ResourceType.WOOD));
			resCards.add(new ResCard(ResourceType.SHEEP));
			resCards.add(new ResCard(ResourceType.WHEAT));
			resCards.add(new ResCard(ResourceType.ORE));
		}
		newBank.setDevCards(devCards);
		newBank.setResCards(resCards);
		return newBank;
	}
	
	private CatanMap createMap() {
		CatanMap newMap = new CatanMap();
		newMap.setHexTiles(createHexTiles());
		newMap.setPorts(createPorts());
		newMap.setRadius(3);
		newMap.setRobberLocation(new HexLocation(0, -2));
		newMap.getHexTileAt(new HexLocation(0, -2)).setHasRobber(true);
		newMap.getHexTileAt(new HexLocation(0, -2)).setRobber(new Piece(PieceType.ROBBER, -1));
		return newMap;
	}
	
	private HexTile[][] createHexTiles() {
		HexTile[][] newHexArray = new HexTile[7][7];
		newHexArray[0+3][-2+3] = new HexTile(0, -2, HexType.DESERT, 0);
		newHexArray[1+3][-2+3] = new HexTile(1, -2, HexType.BRICK, 4);
		newHexArray[2+3][-2+3] = new HexTile(2, -2, HexType.WOOD, 11);
		newHexArray[-1+3][-1+3] = new HexTile(-1, -1, HexType.BRICK, 8);
		newHexArray[0+3][-1+3] = new HexTile(0, -1, HexType.WOOD, 3);
		newHexArray[1+3][-1+3] = new HexTile(1, -1, HexType.ORE, 9);
		newHexArray[2+3][-1+3] = new HexTile(2, -1, HexType.SHEEP, 12);
		newHexArray[-2+3][0+3] = new HexTile(-2, 0, HexType.ORE, 5);
		newHexArray[-1+3][0+3] = new HexTile(-1, 0, HexType.SHEEP, 10);
		newHexArray[0+3][0+3] = new HexTile(0, 0, HexType.WHEAT, 11);
		newHexArray[1+3][0+3] = new HexTile(1, 0, HexType.BRICK, 5);
		newHexArray[2+3][0+3] = new HexTile(2, 0, HexType.WHEAT, 6);
		newHexArray[-2+3][1+3] = new HexTile(-2, 1, HexType.WHEAT, 2);
		newHexArray[-1+3][1+3] = new HexTile(-1, 1, HexType.SHEEP, 9);
		newHexArray[0+3][1+3] = new HexTile(0, 1, HexType.WOOD, 4);
		newHexArray[1+3][1+3] = new HexTile(1, 1, HexType.SHEEP, 10);
		newHexArray[-2+3][2+3] = new HexTile(-2, 2, HexType.WOOD, 6);
		newHexArray[-1+3][2+3] = new HexTile(-1, 2, HexType.ORE, 3);
		newHexArray[0+3][0+3] = new HexTile(0, 2, HexType.WHEAT, 8);
		return newHexArray;
	}
	
	private HashMap<EdgeLocation, Port> createPorts() {
		HashMap<EdgeLocation, Port> ports = new HashMap<EdgeLocation, Port>();
		ports.put(new EdgeLocation(new HexLocation(-3,2), EdgeDirection.NorthEast), 
				new Port(2, PortType.WOOD, new EdgeLocation(new HexLocation(-3,2), EdgeDirection.NorthEast)));
		
		ports.put(new EdgeLocation(new HexLocation(-2,3), EdgeDirection.NorthEast), 
				new Port(2, PortType.BRICK, new EdgeLocation(new HexLocation(-2,3), EdgeDirection.NorthEast)));
		
		ports.put(new EdgeLocation(new HexLocation(1,-3), EdgeDirection.South), 
				new Port(2, PortType.ORE, new EdgeLocation(new HexLocation(1,-3), EdgeDirection.South)));
		
		ports.put(new EdgeLocation(new HexLocation(0,3), EdgeDirection.North), 
				new Port(3, PortType.THREE, new EdgeLocation(new HexLocation(0,3), EdgeDirection.North)));
		
		ports.put(new EdgeLocation(new HexLocation(3,-3), EdgeDirection.SouthWest), 
				new Port(3, PortType.THREE, new EdgeLocation(new HexLocation(3,-3), EdgeDirection.SouthWest)));
		
		ports.put(new EdgeLocation(new HexLocation(3,-1), EdgeDirection.NorthWest), 
				new Port(2, PortType.SHEEP, new EdgeLocation(new HexLocation(3,-1), EdgeDirection.NorthWest)));
		
		ports.put(new EdgeLocation(new HexLocation(-3,0), EdgeDirection.SouthEast), 
				new Port(3, PortType.THREE, new EdgeLocation(new HexLocation(-3,0), EdgeDirection.SouthEast)));
		
		ports.put(new EdgeLocation(new HexLocation(-1,-2), EdgeDirection.South), 
				new Port(2, PortType.WHEAT, new EdgeLocation(new HexLocation(-1,-2), EdgeDirection.South)));
		
		ports.put(new EdgeLocation(new HexLocation(2,1), EdgeDirection.NorthWest), 
				new Port(3, PortType.WHEAT, new EdgeLocation(new HexLocation(2,1), EdgeDirection.NorthWest)));
		
		return ports;
	}
	
	public GameInfo toGameInfo() {
		GameInfo thisGameInfo = new GameInfo();
		thisGameInfo.setId(this.getGameId());
		thisGameInfo.setTitle(this.getGameTitle());
		Player[] players = this.getPlayers();
		for(Player thisPlayer : players) {
			if(thisPlayer != null) {
				PlayerInfo thisPlayerInfo = new PlayerInfo();
				thisPlayerInfo.setColor(CatanColor.getStringColor(thisPlayer.getColor()));
				thisPlayerInfo.setId(thisPlayer.getPlayerId());
				thisPlayerInfo.setName(thisPlayer.getName());
				thisPlayerInfo.setPlayerIndex(thisPlayer.getIndex());
				thisGameInfo.addPlayer(thisPlayerInfo);
			}
		}
		
		return thisGameInfo;
	}
	
	public boolean addPlayer(Player thisPlayer) {
		int currentNumPlayers = this.getNumberOfPlayers();
		if(currentNumPlayers > 3) {
			return false;
		}
		
		thisPlayer.setIndex(currentNumPlayers);
		Player[] tempPlayers = this.getPlayers();
		for(Player player : tempPlayers) {
			if (player != null && player.getName() != null)
			{
				if(thisPlayer.getName().equals(player.getName())) {
					return true;
				}
				else if(thisPlayer.getColor() == player.getColor()) {
					return false;
				}
			}
		}
		tempPlayers[currentNumPlayers] = thisPlayer;
		setPlayers(tempPlayers);
		setNumberOfPlayers(currentNumPlayers+1);
		return true;
	}
}
