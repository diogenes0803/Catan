package shared.communicator;

/**
 * Dumb data holder
 *
 * @author Jonathan
 */
public class JoinGameParams {
    private int id;
    private String color;

    public JoinGameParams(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
