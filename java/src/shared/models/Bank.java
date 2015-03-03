package shared.models;

import java.util.List;

/**
 * @author HojuneYoo Bank holds all resource cards and development cards that are not belongs to any players When user
 *         wants to build something then they have to pay their resources or development cards to the bank Bank also can
 *         be used when user wants to trade resources
 */

public class Bank {

    private List<ResCard> resCards;
    private List<DevCard> devCards;

    public Bank() {

    }

    public Bank(List<ResCard> resCards, List<DevCard> devCards) {
        this.resCards = resCards;
        this.devCards = devCards;
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


}
