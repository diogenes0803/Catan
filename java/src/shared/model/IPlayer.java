package shared.model;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

import java.io.Serializable;
import java.util.Collection;


public interface IPlayer extends Serializable {
    // integers
    public void giveMonument();

    public int giveSoldier();

    public int giveVictoryPoints(int num);

    public int removeVictoryPoints(int num);

    // booleans
    public void setDiscarded(boolean actionCompleted);

    public void setPlayedDevCard(boolean actionCompleted);


    public int getVictoryPoints();

    public int calculateVictoryPoints();

    public void addVictoryPoints(int amount);

    public void setResources(IResourceBank rb);

    public void addResources(IResourceBank rb);

    public void addResources(int count, ResourceType type);

    public void removeResources(IResourceBank rb);

    // getters
    public int getId();

    public int getIndex();

    public String getName();

    public CatanColor getColor();

    public int getMonuments();

    public void incrementMonuments();

    public int getSoldiers();

    public void incrementSoldiers();

    public Collection<IRoad> getRoads();

    public Collection<ITown> getTowns();


    public boolean needsToDiscard();

    public boolean hasDiscarded();

    public boolean hasPlayedDevCard();


    public IPieceBank getPieceBank();


    public IRoad buildRoad(boolean free);


    public City buildCity(ITown settlement);


    public Settlement buildSettlement(boolean free);


    public void addTown(ITown town);


    public void addRoad(IRoad road);

    public IResourceBank getResources();


    public boolean hasResources();

    public IDevCardHand getNewDevCards();

    public IDevCardHand getPlayableDevCards();


    public void moveDevCards();


    public void playDevCard(DevCardType type);


    public IDevCardHand getAllDevCards();

    public boolean canBuyCity();


    public boolean canBuyRoad();


    public boolean canBuySettlement();


    public boolean canAcceptTrade(IResourceBank asking);


    public boolean canPlayDevCard();


    public boolean canPlayDevCard(DevCardType card);


    public boolean canAfford(IResourceBank purchase);


    public boolean canAffordTrade(IResourceBank trade);


    public void setColor(CatanColor m_color);

    public void discardCards(IResourceBank cards);
}
