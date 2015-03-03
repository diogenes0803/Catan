package shared.communicator;

/**
 * Dumb data holder
 *
 * @author Jonathan
 */
public class SaveGameResults extends ResponseBodyContainer {
    String status;

    public SaveGameResults(String status) {
        this.status = status;
    }

    public SaveGameResults() {
        // TODO Auto-generated constructor stub
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
