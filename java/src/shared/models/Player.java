package shared.models;

import java.util.List;
import shared.definitions.CatanColor;

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
	private boolean isOnTurn;
	private List<Piece> availablePieces;
	private CatanColor color;
	private int numKnightUses;
	private int sizeLongestRoad;
	
	/**
	 * Check if user have enough resources and pieces and is on turn to build a road
	 * @return true if possible false if not
	 */
	public boolean canBuildRoad(){
		return false;
	}
	
	/**
	 * Check if user have enough resources and pieces and is on turn to build a Settlement
	 * @return true if possible false if not
	 */
	public boolean canBuildSettlement(){
		return false;
	}
	
	/**
	 * Check if user have enough resources and pieces and is on turn to upgrade a settlement to city
	 * @return true if possible false if not
	 */
	public boolean canBuildCity(){
		return false;
	}
	
	/**
	 * Check if user have enough resources and is on turn to buy a Development Card
	 * @return true if possible false if not
	 */
	public boolean canBuyDevCard(){
		return false;
	}
	
	/**
	 * Check if user have enough resources and is on turn to trade resources
	 * @return true if possible false if not
	 */
	public boolean canTrade(){
		return false;
	}
	
	/**
	 * Check if user is on turn to roll a dice
	 * @return true if possible false if not
	 */
	public boolean canRollDice(){
		return false;
	}
	
	/**
	 * Check if user has enough point to win a game
	 * @return true if possible false if not
	 */
	public boolean canWinGame(){
		return false;
	}
	
	/**
	 * Check if user have a Development card that is available to use and is on turn
	 * @return true if possible false if not
	 */
	public boolean canUseDevCard(){
		return false;
	}
	
	/**
	 * Build a road on map
	 * @pre Players must on their turn and must have enough resources and pieces
	 * @post Players must on their turn and must lost resources and pieces
	 */
	public void buildRoad(){
		return;
	}
	
	/**
	 * Build a settlement on map
	 * @pre Player must have enough resources and pieces
	 * @post Player must lost resources and pieces
	 */
	public void buildSettlement(){
		return;
	}
	
	/**
	 * Upgrade settlement to city
	 * @pre Players must on their turn and must have enough resources and pieces
	 * @post Players must on their turn and must lost resources and pieces
	 */
	public void buildCity(){
		return;
	}
	
	/**
	 * Buy DevCard from Bank
	 * @pre Players must on their turn and must have enough resources and bank must have DevCard left
	 * @post Players must on their turn and must lost resources and bank must lost a DevCard
	 */
	public void buyDevCard(){
		return;
	}
	
	/**
	 * Trade Resources with other player or bank or harbor
	 * @pre Players must on their turn and have enough resources to trade
	 * @post Players must gain and lost different kind of resources
	 */
	public void tarde(){
		return;
	}
	
	/**
	 * Roll dices
	 * @pre Players must on their turn
	 * @post Dices must have new values
	 */
	public void rollDice(){
		return;
	}
	
	/**
	 * User Development Card
	 * @pre Players must on their turn
	 * @post Players must have lost their card that's been used. DevCard effect must been applied in game
	 */
	public void useDevCard(){
		return;
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
	
}
