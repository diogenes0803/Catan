package shared.models.jsonholder;

public class JsonBank {
    private int brick;
    private int wood;
    private int sheep;
    private int wheat;
    private int ore;

    public JsonBank() {
    }

    public JsonBank(int brick, int wood, int sheep, int wheat, int ore) {
        this.brick = brick;
        this.wood = wood;
        this.sheep = sheep;
        this.wheat = wheat;
        this.ore = ore;
    }

    public int getTotalResNum() {
        return brick + wood + sheep + wheat + ore;
    }

    public int getBrick() {
        return brick;
    }

    public void setBrick(int brick) {
        this.brick = brick;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getSheep() {
        return sheep;
    }

    public void setSheep(int sheep) {
        this.sheep = sheep;
    }

    public int getWheat() {
        return wheat;
    }

    public void setWheat(int wheat) {
        this.wheat = wheat;
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }
}
