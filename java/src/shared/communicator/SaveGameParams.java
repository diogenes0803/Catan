package shared.communicator;

/**
 * Dumb data holder
 *
 * @author Jonathan
 */
public class SaveGameParams {
    int id;
    String name;

    public SaveGameParams(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
