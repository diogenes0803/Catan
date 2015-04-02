package server.model;

import java.util.ArrayList;
import java.util.List;

import shared.data.GameInfo;
import shared.data.PlayerInfo;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.HexType;
import shared.definitions.ResourceType;
import shared.models.Bank;
import shared.models.CatanMap;
import shared.models.DevCard;
import shared.models.Game;
import shared.models.HexTile;
import shared.models.Player;
import shared.models.ResCard;

public class ServerModel extends Game {
	public ServerModel() {
		super();
		initialization();
	}
	private void initialization() {
		this.setBank(createBank());
		
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
		HexTile[][] newHexArray = new HexTile[7][7];
		newHexArray[0+3][-2+3] = new HexTile(0, -2, HexType.DESERT, 0);
		newHexArray[0+3][-2+3] = new HexTile(0, -2, HexType.DESERT, 0);
		return newMap;
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
