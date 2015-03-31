package server.model;

import shared.data.GameInfo;
import shared.data.PlayerInfo;
import shared.definitions.CatanColor;
import shared.models.Game;
import shared.models.Player;

public class ServerModel extends Game {
	public ServerModel() {
		super();
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
			if (player != null)
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
