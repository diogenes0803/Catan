/**
 *
 */
package shared.models;

/**
 * Object used when proposing trades.  Negative ints indicate resources being offered. Positive ints indicate resources
 * being requested.
 *
 * @author campbeln
 */
public class ResourceList {

    private int bricks;
    private int ores;
    private int sheep;
    private int wheat;
    private int wood;

    public ResourceList(int bricks, int ores, int sheep, int wheat, int wood) {
        this.bricks = bricks;
        this.ores = ores;
        this.sheep = sheep;
        this.wheat = wheat;
        this.wood = wood;
    }

    public int getBricks() {
        return bricks;
    }

    public void setBricks(int bricks) {
        this.bricks = bricks;
    }

    public int getOres() {
        return ores;
    }

    public void setOres(int ores) {
        this.ores = ores;
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

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

}
