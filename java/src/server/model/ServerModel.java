package server.model;

import shared.data.GameInfo;
import shared.data.PlayerInfo;
import shared.definitions.CatanColor;
import shared.models.Game;
import shared.models.Player;

public class ServerModel extends Game {
	public GameInfo toGameInfo() {
		GameInfo thisGameInfo = new GameInfo();
		thisGameInfo.setId(this.getGameId());
		thisGameInfo.setTitle(this.getGameTitle());
		for(Player thisPlayer : this.getPlayers()) {
			PlayerInfo thisPlayerInfo = new PlayerInfo();
			thisPlayerInfo.setColor(CatanColor.getStringColor(thisPlayer.getColor()));
			thisPlayerInfo.setId(thisPlayer.getPlayerId());
			thisPlayerInfo.setName(thisPlayer.getName());
			thisPlayerInfo.setPlayerIndex(thisPlayer.getIndex());
			thisGameInfo.addPlayer(thisPlayerInfo);
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
		tempPlayers[currentNumPlayers] = thisPlayer;
		setPlayers(tempPlayers);
		setNumberOfPlayers(currentNumPlayers++);
		return true;
	}
}
