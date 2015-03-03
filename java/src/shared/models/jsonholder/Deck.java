package shared.models.jsonholder;

public class Deck {
    private int yearOfPlenty;
    private int monopoly;
    private int soldier;
    private int roadBuilding;
    private int monument;

    public Deck() {
    }

    public Deck(int yearOfPlenty, int monopoly, int soldier, int roadBuilding, int monument) {
        this.yearOfPlenty = yearOfPlenty;
        this.monopoly = monopoly;
        this.soldier = soldier;
        this.roadBuilding = roadBuilding;
        this.monument = monument;
    }

    public int getTotalDevNum() {
        return yearOfPlenty + monopoly + soldier + roadBuilding + monument;
    }

    public int getYearOfPlenty() {
        return yearOfPlenty;
    }

    public void setYearOfPlenty(int yearOfPlenty) {
        this.yearOfPlenty = yearOfPlenty;
    }

    public int getMonopoly() {
        return monopoly;
    }

    public void setMonopoly(int monopoly) {
        this.monopoly = monopoly;
    }

    public int getSoldier() {
        return soldier;
    }

    public void setSoldier(int soldier) {
        this.soldier = soldier;
    }

    public int getRoadBuilding() {
        return roadBuilding;
    }

    public void setRoadBuilding(int roadBuilding) {
        this.roadBuilding = roadBuilding;
    }

    public int getMonument() {
        return monument;
    }

    public void setMonument(int monument) {
        this.monument = monument;
    }
}
