package shared.models;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

import java.util.List;

/**
 * Model for Player. Player is a user who is in game
 *
 * @author HojuneYoo
 */

public class Player {

    private int playerId;
    private String name;
    private List<ResCard> resCards;
    private List<DevCard> devCards;
    private int victoryPoint;
    private List<Piece> availablePieces;
    private CatanColor color;
    private int numSoldierPlayed;
    private int numMonumentPlayed;
    private int sizeLongestRoad;
    private boolean playedDevCard;
    private boolean discarded;

    /**
     * Check if user have enough resources and pieces and is on turn to build a road
     *
     * @return true if possible false if not
     */
    public boolean canBuildRoad() {
        int brick = getResCount(ResourceType.BRICK);
        int wood = getResCount(ResourceType.WOOD);
        int roadBuildCard = getOldDevCount(DevCardType.ROAD_BUILD);
        if ((brick > 0 && wood > 0) || (roadBuildCard > 0) || TurnTracker.getInstance().getStatus().equals("FirstRound")
                || TurnTracker.getInstance().getStatus().equals("SecondRound")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if user have enough resources and pieces and is on turn to build a Settlement
     *
     * @return true if possible false if not
     */
    public boolean canBuildSettlement() {
        int brick = getResCount(ResourceType.BRICK);
        int wood = getResCount(ResourceType.WOOD);
        int sheep = getResCount(ResourceType.SHEEP);
        int wheat = getResCount(ResourceType.WHEAT);
        if (brick > 0 && wood > 0 && sheep > 0 && wheat > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Check if user have enough resources and pieces and is on turn to upgrade a settlement to city
     *
     * @return true if possible false if not
     * <p/>
     * TODO: Check if available settlements is less than the total settlements a player can have. If the player has less
     * than the total number possible, a settlement is in play. If availablePieces contains all settlements the player
     * cannot build a city as no settlement is available.
     */


    public boolean canBuildCity() {
        int ore = getResCount(ResourceType.ORE);
        int wheat = getResCount(ResourceType.WHEAT);
        if (ore >= 3 && wheat >= 2) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Check if user have enough resources and is on turn to buy a Development Card
     *
     * @return true if possible false if not
     */
    public boolean canBuyDevCard() {
        int ore = getResCount(ResourceType.ORE);
        int sheep = getResCount(ResourceType.SHEEP);
        int wheat = getResCount(ResourceType.WHEAT);
        if (ore > 0 && wheat > 0 && sheep > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * Check if user have enough resources and is on turn to buy a Development Card
     *
     * @return true if possible false if not
     */
    public boolean canPlayerDevCardType(DevCardType devCardType) {
        	   
	   		for(DevCard devCard : devCards)
	   		{
	   			if(devCard.getType() == devCardType)
	   			{
	   				if(devCard.isOld() == true)
	   				{
	   					System.out.println("Aqui dentro");
	   					return true;
	   				}
	   				
	   			}
	   		}
        	return false;
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

    public boolean hasPlayedDevCard() {
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

    public int getResCount(ResourceType type) {
        int count = 0;
        for (ResCard thisCard : resCards) {
            if (thisCard.getType() == type) {
                count++;
            }
        }
        return count;
    }

    public int getDevCount(DevCardType type) {
        int count = 0;
        for (DevCard thisCard : devCards) {
            if (thisCard.getType() == type) {
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

    public int getOldDevCount(DevCardType type) {
        int count = 0;
        for (DevCard thisCard : devCards) {
            if (thisCard.getType() == type && thisCard.isOld()) {
                count++;
            }
        }
        return count;
    }

    public boolean hasOldDevCard() {
        for (int i = 0; i < devCards.size(); i++) {
            if (devCards.get(i).isOld()) {
                return true;
            }
        }

        return false;
    }

    public boolean hasMonument() {
        for (int i = 0; i < devCards.size(); i++) {
            if (devCards.get(i).getType() == DevCardType.MONUMENT) {
                return true;
            }
        }

        return false;
    }
}
