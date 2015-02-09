package shared.models;

import java.util.List;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

/**
 * Model for Player. Player is a user who is in game
 * @author HojuneYoo
 *
 */

public class Player {
	
	private int userId;
	private int playerId;
	private List<ResCard> resCards;
	private List<DevCard> devCards;
	private int victoryPoint;
	//private boolean isTheirTurn; unneccessary.
	private List<Piece> availablePieces;
	private CatanColor color;
	private int numKnightUses;
	private int sizeLongestRoad;
	
	/**
	 * Check if user has enough resources and pieces to buy road
	 * @return true if possible false if not
	 */
	public boolean canBuildRoad(){

		int brick = getResCount(ResourceType.BRICK);
		int wood = getResCount(ResourceType.WOOD);
			//int roadBuildCard = getUsableDevCount(DevCardType.ROAD_BUILD);
		if((brick > 0 && wood > 0)){
			return true;
		}
		
	    return false;
		
	}
	
	/**
	 * <p>Check if user have enough resources and pieces to build settlement.
	 </p>
	 * @return true if possible false if not
	 */
	public boolean canBuildSettlement(){
            //one of each of the following is needed:
			int brick = getResCount(ResourceType.BRICK);
			int wood = getResCount(ResourceType.WOOD);
			int sheep = getResCount(ResourceType.SHEEP);
			int wheat = getResCount(ResourceType.WHEAT);
			if(brick > 0 && wood > 0 && sheep > 0 && wheat > 0)
				return true;
			
		
			return false;
		
	}
	
	/**
	 * Check if user have enough resources and pieces and is on turn to upgrade a settlement to city
	 * @return true if possible false if not
	 */
	public boolean canBuildCity(){

			int ore = getResCount(ResourceType.ORE);
			int wheat = getResCount(ResourceType.WHEAT);
			if(ore >= 3 && wheat >= 2){
				return true;
			}
			
			return false;
		
	}
	
	/**
	 * Check if user have enough resources and is on turn to buy a Development Card
	 * @return true if possible false if not
	 */
	public boolean canBuyDevCard(){
		    //one of each reource
			int ore = getResCount(ResourceType.ORE);
			int sheep = getResCount(ResourceType.SHEEP);
			int wheat = getResCount(ResourceType.WHEAT);
			if(ore > 0 && wheat > 0 && sheep > 0){
				return true;
			}
			
			return false;
		
	}
	
	/**
	 * Check if user have enough resources and is on turn to trade resources
	 * @return true if possible false if not
	 */
	public boolean canTrade(){
		if( resCards.size() > 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	/**
	 * Check if user has enough point to win a game
	 * @return true if possible false if not
	 */
	public boolean canWinGame(){
		if(victoryPoint >= 10){
			return true;
		}
		else{
			return false;
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public List<ResCard> getResCards() {
		return resCards;
	}

	public void setResCards(List<ResCard> resCards) {
		this.resCards = resCards;
	}

	public List<DevCard> getDevCards() {
		return devCards;
	}

	public void setDevCards(List<DevCard> devCards) {
		this.devCards = devCards;
	}

	public int getVictoryPoint() {
		return victoryPoint;
	}

	public void setVictoryPoint(int victoryPoint) {
		this.victoryPoint = victoryPoint;
	}


	public List<Piece> getAvailablePieces() {
		return availablePieces;
	}

	public void setAvailablePieces(List<Piece> availablePieces) {
		this.availablePieces = availablePieces;
	}

	public CatanColor getColor() {
		return color;
	}

	public void setColor(CatanColor color) {
		this.color = color;
	}

	public int getNumKnightUses() {
		return numKnightUses;
	}

	public void setNumKnightUses(int numKnightUses) {
		this.numKnightUses = numKnightUses;
	}

	public int getSizeLongestRoad() {
		return sizeLongestRoad;
	}

	public void setSizeLongestRoad(int sizeLongestRoad) {
		this.sizeLongestRoad = sizeLongestRoad;
	}
	
	private int getResCount(ResourceType type){
		int count = 0;
		for(ResCard thisCard : resCards){
			if(thisCard.getType() == type){
				count++;
			}
		}
		return count;
	}
	
	public int getUsableDevCount(DevCardType type){
		int count = 0;
		for(DevCard thisCard : devCards){
			if(thisCard.getType() == type && thisCard.isUsable()){
				count++;
			}
		}
		return count;
	}
}
