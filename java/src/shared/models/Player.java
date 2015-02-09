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
	private String name;
	private List<ResCard> resCards;
	private List<DevCard> devCards;
	private int victoryPoint;
	private boolean isOnTurn;
	private List<Piece> availablePieces;
	private CatanColor color;
	private int numSoldierPlayed;
	private int numMonumentPlayed;
	private int sizeLongestRoad;
	private boolean playedDevCard;
	private boolean discarded;
	
	/**
	 * Check if user have enough resources and pieces and is on turn to build a road
	 * @return true if possible false if not
	 */
	public boolean canBuildRoad(){
		if(isOnTurn){
			int brick = getResCount(ResourceType.BRICK);
			int wood = getResCount(ResourceType.WOOD);
			int roadBuildCard = getUsableDevCount(DevCardType.ROAD_BUILD);
			if((brick > 0 && wood > 0) || (roadBuildCard > 0)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	/**
	 * Check if user have enough resources and pieces and is on turn to build a Settlement
	 * @return true if possible false if not
	 */
	public boolean canBuildSettlement(){
		if(isOnTurn){
			int brick = getResCount(ResourceType.BRICK);
			int wood = getResCount(ResourceType.WOOD);
			int sheep = getResCount(ResourceType.SHEEP);
			int wheat = getResCount(ResourceType.WHEAT);
			if(brick > 0 && wood > 0 && sheep > 0 && wheat > 0){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	/**
	 * Check if user have enough resources and pieces and is on turn to upgrade a settlement to city
	 * @return true if possible false if not
	 */
	public boolean canBuildCity(){
		if(isOnTurn){
			int ore = getResCount(ResourceType.ORE);
			int wheat = getResCount(ResourceType.WHEAT);
			if(ore >= 3 && wheat >= 2){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	/**
	 * Check if user have enough resources and is on turn to buy a Development Card
	 * @return true if possible false if not
	 */
	public boolean canBuyDevCard(){
		if(isOnTurn){
			int ore = getResCount(ResourceType.ORE);
			int sheep = getResCount(ResourceType.SHEEP);
			int wheat = getResCount(ResourceType.WHEAT);
			if(ore > 0 && wheat > 0 && sheep > 0){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	/**
	 * Check if user have enough resources and is on turn to trade resources
	 * @return true if possible false if not
	 */
	public boolean canTrade(){
		if(isOnTurn && resCards.size() > 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Check if user is on turn to roll a dice
	 * @return true if possible false if not
	 */
	public boolean canRollDice(){
		if(isOnTurn){
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

	public boolean isOnTurn() {
		return isOnTurn;
	}

	public void setOnTurn(boolean isOnTurn) {
		this.isOnTurn = isOnTurn;
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
	
	public int getNumSoldierPlayed() {
		return numSoldierPlayed;
	}

	public void setNumSoldierPlayed(int numSoldierPlayed) {
		this.numSoldierPlayed = numSoldierPlayed;
	}

	public int getNumMonumentPlayed() {
		return numMonumentPlayed;
	}

	public void setNumMonumentPlayed(int numMonumentPlayed) {
		this.numMonumentPlayed = numMonumentPlayed;
	}

	public int getSizeLongestRoad() {
		return sizeLongestRoad;
	}

	public void setSizeLongestRoad(int sizeLongestRoad) {
		this.sizeLongestRoad = sizeLongestRoad;
	}
	
	public boolean isPlayedDevCard() {
		return playedDevCard;
	}

	public void setPlayedDevCard(boolean playedDevCard) {
		this.playedDevCard = playedDevCard;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
	public boolean isDiscarded() {
		return discarded;
	}

	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}

	private int getUsableDevCount(DevCardType type){
		int count = 0;
		for(DevCard thisCard : devCards){
			if(thisCard.getType() == type && thisCard.isUsable()){
				count++;
			}
		}
		return count;
	}
}
