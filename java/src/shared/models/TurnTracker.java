package shared.models;

public class TurnTracker {

    public TurnTracker() {
    	status = "FirstRound";
    	currentTurn = 0;
    	longestRoad = -1;
    	largestArmy = -1;
    }

    private String status;
    private int currentTurn;
    private int longestRoad;
    private int largestArmy;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public int getLongestRoad() {
        return longestRoad;
    }

    public void setLongestRoad(int longestRoad) {
        this.longestRoad = longestRoad;
    }

    public int getLargestArmy() {
        return largestArmy;
    }

    public void setLargestArmy(int largestArmy) {
        this.largestArmy = largestArmy;
    }


}
