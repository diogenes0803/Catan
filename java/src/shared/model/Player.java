package shared.model;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Player implements IPlayer {
    public static final int NO_PLAYER = -1; // a player index that represents a null player

    private int m_id;
    private int m_index;
    private int m_victoryPoints;
    private int m_monuments;
    private int m_soldiers;
    private boolean m_discarded;
    private boolean m_playedDevCard;
    private String m_name;
    private CatanColor m_color;
    private IPieceBank m_pieceBank;
    private IResourceBank m_resources;
    private IDevCardHand m_newDevCards;
    private IDevCardHand m_playableDevCards;
    private Collection<IRoad> m_roads;
    private Collection<ITown> m_towns;

    public Player(int id, int index, int victoryPoints, int monuments, int soldiers, boolean discarded,
                  boolean playedDevCard, String name, CatanColor color, IPieceBank pieceBank, IResourceBank resources,
                  IDevCardHand newDevCards, IDevCardHand playableDevCards) throws ModelException {

        assert name != null && pieceBank != null && resources != null && newDevCards != null && playableDevCards != null;

        if (victoryPoints < 0 || monuments < 0 || soldiers < 0) {
            throw new ModelException("Attempted to initialize a player with invalid arguments.");
        }

        this.m_id = id;
        this.m_index = index;
        this.m_victoryPoints = victoryPoints;
        this.m_monuments = monuments;
        this.m_soldiers = soldiers;
        this.m_discarded = discarded;
        this.m_playedDevCard = playedDevCard;
        this.m_name = name;
        this.m_color = color;
        this.m_pieceBank = pieceBank;
        this.m_resources = resources;
        this.m_newDevCards = newDevCards;
        this.m_playableDevCards = playableDevCards;

        this.m_roads = new ArrayList<>();
        this.m_towns = new ArrayList<>();
    }


    public Player(String name, int id, CatanColor color, int index) {
        m_id = id;
        m_index = index;
        m_color = color;
        m_name = name;

        m_victoryPoints = 0;
        m_monuments = 0;
        m_soldiers = 0;
        m_discarded = false;
        m_playedDevCard = false;
        m_pieceBank = new PieceBank();
        m_resources = new ResourceBank();
        m_newDevCards = new DevCardHand();
        m_playableDevCards = new DevCardHand();
        m_roads = new ArrayList<>();
        m_towns = new ArrayList<>();
    }


    public static Player createNewPlayer(String name, int id, CatanColor color, int index) {
        Player player = new Player(name, id, color, index);
        player.m_pieceBank = PieceBank.generateInitial();

        return player;
    }

    public final static Player NULL_PLAYER = new Player("No player", NO_PLAYER, CatanColor.RED, NO_PLAYER);


    @Override
    public int getVictoryPoints() {
        return m_victoryPoints;
    }

    @Override
    public void addVictoryPoints(int amount) {
        m_victoryPoints += amount;
    }


    @Override
    public IPieceBank getPieceBank() {
        return m_pieceBank;
    }


    @Override
    public IRoad buildRoad(boolean free) {
        assert m_pieceBank.availableRoads() > 0;

        if (!free) {
            assert canBuyRoad();
            m_resources.subtract(Prices.ROAD);
        }

        IRoad road = new Road(this);
        m_pieceBank.takeRoad();
        m_roads.add(road);

        return road;
    }

    @Override
    public City buildCity(ITown settlement) {
        assert settlement instanceof Settlement;
        assert m_towns.contains(settlement);
        assert m_pieceBank.availableCities() > 0;
        assert canBuyCity();

        m_resources.subtract(Prices.CITY);

        City city = new City(this);
        m_pieceBank.swapSettlementForCity();
        m_towns.remove(settlement);
        m_towns.add(city);

        return city;
    }

    @Override
    public Settlement buildSettlement(boolean free) {
        assert m_pieceBank.availableSettlements() > 0;

        if (!free) {
            assert canBuySettlement();
            m_resources.subtract(Prices.SETTLEMENT);
        }

        Settlement settlement = new Settlement(this);
        m_pieceBank.takeSettlement();
        m_towns.add(settlement);

        return settlement;
    }


    @Override
    public void addTown(ITown town) {
        assert this.equals(town.getOwner());
        m_towns.add(town);
    }


    @Override
    public void addRoad(IRoad road) {
        assert this.equals(road.getOwner());
        m_roads.add(road);
    }
//
    // Getters //
    //

    @Override
    public int getId() {
        return m_id;
    }

    @Override
    public int getIndex() {
        return m_index;
    }

    @Override
    public int getMonuments() {
        return m_monuments;
    }

    @Override
    public void incrementMonuments() {
        ++m_monuments;
    }

    @Override
    public int getSoldiers() {
        return m_soldiers;
    }

    @Override
    public void incrementSoldiers() { ++m_soldiers; }

    @Override
    public Collection<IRoad> getRoads() {
        return Collections.unmodifiableCollection(m_roads);
    }

    @Override
    public Collection<ITown> getTowns() {
        return Collections.unmodifiableCollection(m_towns);
    }

    @Override
    public boolean needsToDiscard() {
        return m_resources.getCount() > CatanConstants.MAX_SAFE_RESOURCES && !m_discarded;
    }

    @Override
    public boolean hasDiscarded() {
        return m_discarded;
    }

    @Override
    public boolean hasPlayedDevCard() {
        return m_playedDevCard;
    }

    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public CatanColor getColor() {
        return m_color;
    }

    @Override
    public IResourceBank getResources() {
        return m_resources;
    }

    @Override
    public void addResources(IResourceBank rb) {
        m_resources.add(rb);
    }

    @Override
    public void addResources(int count, ResourceType type) {
        m_resources.add(count, type);
    }

    @Override
    public void removeResources(IResourceBank rb) {
        m_resources.subtract(rb);
    }

    @Override
    public boolean hasResources() {
        return m_resources.getCount() > 0;
    }

    @Override
    public IDevCardHand getNewDevCards() {
        return m_newDevCards;
    }

    @Override
    public IDevCardHand getPlayableDevCards() {
        return m_playableDevCards;
    }

    @Override
    public void moveDevCards() {
        m_newDevCards.transferAllCardsToHand(m_playableDevCards);
    }

    @Override
    public void playDevCard(DevCardType type) {
        assert m_playableDevCards.getCount(type) > 0 : "There are no dev cards of type " + type + " to use!";
        assert type == DevCardType.MONUMENT || !m_playedDevCard : "The player already has played a dev card!";

        m_playableDevCards.remove(type);

        if (type != DevCardType.MONUMENT) {
            m_playedDevCard = true;
        }
    }


    @Override
    public IDevCardHand getAllDevCards() {
        return m_playableDevCards.sum(m_newDevCards);
    }

    @Override
    public boolean canAfford(IResourceBank purchase) {
        return m_resources.canAfford(purchase);
    }


    @Override
    public boolean canBuyCity() {
        return m_pieceBank.availableCities() > 0 && m_resources.canAfford(Prices.CITY);
    }


    @Override
    public boolean canBuyRoad() {
        return m_pieceBank.availableRoads() > 0 && m_resources.canAfford(Prices.ROAD);
    }


    @Override
    public boolean canBuySettlement() {
        return m_pieceBank.availableSettlements() > 0 && m_resources.canAfford(Prices.SETTLEMENT);
    }


    @Override
    public boolean canAcceptTrade(IResourceBank asking) {
        return m_resources.canAfford(asking);
    }


    @Override
    public boolean canPlayDevCard() {
        for (DevCardType card : DevCardType.values()) {
            if (canPlayDevCard(card)) {
                return true;
            }
        }

        return false;
    }


    @Override
    public boolean canPlayDevCard(DevCardType card) {
        // check that player has the card
        if (m_playableDevCards.getCount(card) <= 0) {
            return false;
        }

        // they must have two available road pieces for the road building card
        if (card == DevCardType.ROAD_BUILD && m_pieceBank.availableRoads() < 2) {
            return false;
        } // IN OFFICIAL RULES, can only play monuments if they will give you 10 victory points, but can play any time in spec
        else if (card == DevCardType.MONUMENT) {
            //return (m_victoryPoints + m_playableDevCards.getCount(DevCardType.MONUMENT)) >= CatanConstants.VICTORY_POINTS_TO_WIN;
            return true;
        }

        // true if have not played a card yet
        return !m_playedDevCard;
    }


    @Override
    public boolean canAffordTrade(IResourceBank trade) {
        return m_resources.canAfford(trade.negate());
    }


    //
    // Setters //
    //

    // integers
    @Override
    public void giveMonument() { m_monuments++; }
    @Override
    public int giveSoldier() { return ++m_soldiers;}
    @Override
    public int giveVictoryPoints(int num) { return m_victoryPoints += num;}

    @Override
    public int removeVictoryPoints(int num) {
        assert num >= m_victoryPoints;
        return m_victoryPoints -= num;
    }

    @Override
    public int calculateVictoryPoints() {
        m_victoryPoints = 0;
        for (ITown town : m_towns) {
            m_victoryPoints += town.getVictoryPoints();
        }

        m_victoryPoints += m_monuments;

        return m_victoryPoints;
    }

    // booleans
    @Override
    public void setDiscarded(boolean actionCompleted) { m_discarded = actionCompleted; }
    @Override
    public void setPlayedDevCard(boolean actionCompleted) { m_playedDevCard = actionCompleted; }

    @Override
    public void setResources(IResourceBank rb) { m_resources = rb; }

    @Override
    public void setColor(CatanColor m_color) {
        this.m_color = m_color;
    }

    @Override
    public void discardCards(IResourceBank cards) {
        assert canAfford(cards) : "Player tried to discard more than they had.";
        assert !m_discarded : "Player already discarded!";

        m_resources.subtract(cards);
        m_discarded = true;
    }
}
