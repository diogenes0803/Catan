package shared.models;

import java.util.List;

import shared.definitions.CatanColor;

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
	
	public boolean canBuildRoad(){
		return false;
	}
	
	public boolean canBuildSettlement(){
		return false;
	}
	
	public boolean canBuildCity(){
		return false;
	}
	
	public boolean canBuyDevCard(){
		return false;
	}
	
	public boolean canTrade(){
		return false;
	}
	
	public boolean canRollDice(){
		return false;
	}
	
	public boolean canWinGame(){
		return false;
	}
	
	public boolean canUseDevCard(){
		return false;
	}
	
	public void buildRoad(){
		return;
	}
	
	public void buildSettlement(){
		return;
	}
	
	public void buildCity(){
		return;
	}
	
	public void buyDevCard(){
		return;
	}
	
	public void tarde(){
		return;
	}
	
	public void rollDice(){
		return;
	}
	
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
