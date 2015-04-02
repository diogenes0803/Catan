package shared.model;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.*;


public class Game extends Observable implements IGame {
    static final long serialVersionUID = 0xDEADBEEF15C001L;

    // only used server side
    private String m_name;
    private Integer m_id;
    private Random m_random;

    private GameState m_state;
    private IPlayer m_currentPlayer;
    private List<IPlayer> m_players;
    private IResourceBank m_resourceBank;
    private IDevCardHand m_devCards;
    private ICatanMap m_map;
    private IPlayer m_longestRoad;
    private IPlayer m_largestArmy;
    private ITradeOffer m_tradeOffer;
    private ILog m_gameplayLog;
    private ILog m_chatHistory;
    private int m_version;
    private IPlayer m_winner;

    public Game() {
        m_random = new Random();
        m_name = null;
        m_id = null;
        reset();
    }


    public Game(String gameName, int id, boolean randomPorts, boolean randomTiles, boolean randomNumbers) throws ModelException {
        m_random = new Random();
        m_name = gameName;
        m_id = id;

        // TODO:use for server-side game construction from GameManager

        m_state = GameState.FirstRound;
        m_currentPlayer = null;
        m_players = new ArrayList<>();
        m_resourceBank = ResourceBank.generateInitial();
        m_devCards = DevCardHand.generateInitial();
        m_map = new MapGenerator(randomPorts, randomTiles, randomNumbers).generateMap();
        m_longestRoad = null;
        m_largestArmy = null;
        m_tradeOffer = null;
        m_gameplayLog = new Log();
        m_chatHistory = new Log();
        m_version = 0;
        m_winner = null;
    }

    @Override
    public void reset() {
        m_state = null;
        m_currentPlayer = null;
        m_players = null;
        m_resourceBank = null;
        m_devCards = null;
        m_map = null;
        m_longestRoad = null;
        m_largestArmy = null;
        m_tradeOffer = null;
        m_gameplayLog = null;
        m_chatHistory = null;
        m_version = -1;
        m_winner = null;
    }

    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public Integer getID() {
        return m_id;
    }

    @Override
    public Random getRandom() {
        return m_random;
    }

    @Override
    public int getModelVersion() {
        return m_version;
    }

        @Override
    public boolean isNotInitialized() {
        // only tradeOffer, winner, longestRoad, and largestArmy can be null
        return (
                m_state == null
             || m_currentPlayer == null
             || m_players == null
             || m_resourceBank == null
             || m_devCards == null
             || m_map == null
             || m_gameplayLog == null
             || m_chatHistory == null
             || m_version == -1
        );
    }

    @Override
    public IDevCardHand getDevCards() {
        return m_devCards;
    }

    @Override
    public void setDevCards(IDevCardHand devCards) {
        assert devCards != null;
        this.m_devCards = devCards;
        setChanged();
    }

    @Override
    public GameState getGameState() {
        return m_state;
    }

    @Override
    public void setGameState(GameState state) {
        assert state != null;
        m_state = state;
        setChanged();
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return m_currentPlayer;
    }

    @Override
    public void setCurrentPlayer(IPlayer currentPlayer) {
        assert currentPlayer != null;
        m_currentPlayer = currentPlayer;
        setChanged();
    }

    @Override
    public List<IPlayer> getPlayers() {
        return m_players;
    }

    @Override
    public IPlayer getPlayerByIndex(int index) throws ModelException {
        if (index == Player.NO_PLAYER) {
            return null;
        }
        else if (index < 0 || index >= m_players.size()) {
            throw new ModelException("Invalid player index: " + index);
        }
        return m_players.get(index);
    }

    @Override
    public IPlayer getPlayerByID(int id) throws ModelException {
        if (id != Player.NO_PLAYER) {
            for (IPlayer player : getPlayers()) {
                if (player.getId() == id) {
                    return player;
                }
            }
        }

        throw new ModelException("Invalid player ID " + id + " requested.");
    }

    @Override
    public void setPlayers(List<IPlayer> players) {
        assert players != null;
        m_players = players;
        setChanged();
    }

    @Override
    public IResourceBank getResourceBank() {
        return m_resourceBank;
    }

    @Override
    public void setResourceBank(IResourceBank bank) {
        assert bank != null;
        m_resourceBank = bank;
        setChanged();
    }

    @Override
    public ICatanMap getMap() {
        return m_map;
    }

    @Override
    public void setMap(ICatanMap map) {
        assert map != null;
        m_map = map;
        setChanged();
    }

    @Override
    public IPlayer getLongestRoad() {
        return m_longestRoad;
    }

    @Override
    public void setLongestRoad(IPlayer longestRoad) {
        m_longestRoad = longestRoad;
        setChanged();
    }

    @Override
    public IPlayer getLargestArmy() {
        return m_largestArmy;
    }

    @Override
    public void setLargestArmy(IPlayer largestArmy) {
        m_largestArmy = largestArmy;
        setChanged();
    }

    @Override
    public ITradeOffer getTradeOffer() {
        return m_tradeOffer;
    }

    @Override
    public void setTradeOffer(ITradeOffer tradeOffer) {
        m_tradeOffer = tradeOffer;
        setChanged();
    }

    @Override
    public ILog getGameplayLog() {
        return m_gameplayLog;
    }

    @Override
    public void setGameplayLog(ILog gameplayLog) {
        assert gameplayLog != null;
        m_gameplayLog = gameplayLog;
        setChanged();
    }

    @Override
    public ILog getChatHistory() {
        return m_chatHistory;
    }

    @Override
    public void setChatHistory(ILog chatHistory) {
        assert chatHistory != null;
        m_chatHistory = chatHistory;
        setChanged();
    }

    @Override
    public int getVersion() {
        return m_version;
    }

    @Override
    public void setVersion(int version) {
        m_version = version;
        setChanged();
    }

    @Override
    public void incrementVersion() {
        m_version++;
    }

    @Override
    public IPlayer getWinner() {
        return m_winner;
    }

    @Override
    public void setWinner(IPlayer winner) {
        assert winner != null;
        m_winner = winner;
        setChanged();
    }

    @Override
    public boolean isOfferingTrade(IPlayer player) {
        return m_tradeOffer != null && player.equals(m_tradeOffer.getSender());
    }

    @Override
    public boolean isBeingOfferedTrade(IPlayer player) {
        return m_tradeOffer != null && player.equals(m_tradeOffer.getReceiver());
    }

    @Override
    public boolean isPlaying(IPlayer player) {
        return playerAndGameState(player, GameState.Playing);
    }

    @Override
    public boolean isDiscarding(IPlayer player) {
        return (m_state == GameState.Discarding) && player.needsToDiscard();
    }

    @Override
    public boolean isRolling(IPlayer player) {
        return playerAndGameState(player, GameState.Rolling);
    }

    @Override
    public boolean isRobbing(IPlayer player) {
        return playerAndGameState(player, GameState.Robbing);
    }

    @Override
    public boolean isPlacingInitialPieces(IPlayer player) {
        return gameHasStarted()
               && (playerAndGameState(player, GameState.FirstRound) || playerAndGameState(player, GameState.SecondRound));
    }

    @Override
    public boolean gameHasStarted() {
        return m_players.size() == CatanConstants.NUM_PLAYERS;
    }

    private boolean playerAndGameState(IPlayer player, GameState state) {
        return player.equals(m_currentPlayer) && m_state == state;
    }

    @Override
    public boolean canPlaceRoad(IPlayer player, EdgeLocation edge) {
        if (!isPlayersTurn(player)) {
            return false;
        }

        // road placement rules are different for initial roads
        if (isFreeRound()) {
            return getMap().canPlaceInitialRoad(player, edge);
        }

        // if playing, check the map with normal rules
        if (getGameState() == GameState.Playing) {
            return getMap().canPlaceRoad(player, edge);
        }

        return false;
    }

    @Override
    public boolean canPlaceSettlement(IPlayer player, VertexLocation vertex) {
        assert vertex != null;

        // check if it's the player's turn
        if (!isPlayersTurn(player)) {
            return false;
        }

        // check if map is open (same logic for initial round and normal playing
        if (!getMap().canPlaceSettlement(player, vertex)) {
            return false;
        }

        // check if a normal gameplay
        if (getGameState() == GameState.Playing) {
            return player.canBuySettlement();
        }

        // assert that (if it's a free round) the player has enough pieces
        assert !isFreeRound() || player.getPieceBank().availableSettlements() > 0;

        // if it's a free round, they can place
        return isFreeRound();
    }

    @Override
    public boolean canBuildCity(IPlayer player, VertexLocation vertex) {
        assert vertex != null;

        if (getGameState() != GameState.Playing) {
            return false;
        }

        // is there a settlement at the vertex?
        boolean mapOpen = getMap().canPlaceCity(player, vertex);

        // does the player have enough resources?
        return mapOpen && (isFreeRound() || player.canBuyCity());
    }

    @Override
    public Collection<IPlayer> getRobbablePlayers(IPlayer player, HexLocation location) {
        return getMap().getRobbablePlayersOnTile(location, player);
    }

    @Override
    public Set<PortType> getPlayerPorts(IPlayer player) {
        assert player != null;
        Set<PortType> ports = getMap().getPlayersPorts(player);

        assert ports != null;

        return ports;
    }

    @Override
    public boolean canBuyCity(IPlayer player) {
        return isPlaying(player) && player.canBuyCity();
    }

    @Override
    public boolean canBuyRoad(IPlayer player) {
        return isPlaying(player) && player.canBuyRoad();
    }

    @Override
    public boolean canBuySettlement(IPlayer player) {
        return isPlaying(player) && player.canBuySettlement();
    }

    @Override
    public boolean canBuyDevCard(IPlayer player) {
        return isPlaying(player) && player.canAfford(Prices.DEV_CARD)
                && getDevCards().getCount() > 0;
    }

    @Override
    public boolean canAcceptTrade(IPlayer player) {
        // is there a trade offer?
        if (m_tradeOffer == null) {
            return false;
        }

        // these shouldn't matter, but testing anyway
        if (getGameState() != GameState.Playing || !getCurrentPlayer().equals(m_tradeOffer.getSender())) {
            return false;
        }

        // check that the trade is for the player and that they can afford it
        return  player.equals(m_tradeOffer.getReceiver())
                && player.canAffordTrade(m_tradeOffer.getOffer());
    }

    @Override
    public boolean canPlayDevCard(IPlayer player) {
        return isPlaying(player) && player.canPlayDevCard();
    }

    @Override
    public boolean canPlayMonopoly(IPlayer player) {
        return isPlaying(player)
                && player.canPlayDevCard(DevCardType.MONOPOLY);
    }

    @Override
    public boolean canPlaySoldier(HexLocation robberDestination, IPlayer player) {
        return isPlaying(player)
                && player.canPlayDevCard(DevCardType.SOLDIER) // has and can play card
                && !robberDestination.equals(getMap().getRobber()); // moved robber
    }

    @Override
    public boolean canPlayYearOfPlenty(IPlayer player, ResourceType r1, ResourceType r2) {

        return isPlaying(player) && player.canPlayDevCard(DevCardType.YEAR_OF_PLENTY)
                    && (r1.equals(r2) ? getResourceBank().getCount(r1) > 1 : getResourceBank().getCount(r1) > 0 && getResourceBank().getCount(r2) > 0);
    }

    @Override
    public boolean canPlayMonument(IPlayer player) {
        return isPlaying(player)
                && player.canPlayDevCard(DevCardType.MONUMENT);
    }

    @Override
    public boolean canPlayRoadBuilding(IPlayer player, EdgeLocation edge1, EdgeLocation edge2) {
        assert edge1 != null && edge2 != null;

        return isPlaying(player)
                && player.canPlayDevCard(DevCardType.ROAD_BUILD)
                && getMap().canPlaceTwoRoads(player, edge1, edge2);
    }

    @Override
    public boolean canMaritimeTrade(IPlayer player, ResourceType give, ResourceType receive, int ratio) {
        if (ratio < 2 || ratio > 4) {
            return false;
        }

        // does the player have the right port?
        if (ratio == 2 && !m_map.getPlayersPorts(player).contains(PortType.fromResourceType(give))) {
            return false;
        }
        else if (ratio == 3 && !m_map.getPlayersPorts(player).contains(PortType.THREE)) {
            return false;
        }

        // is it the player's turn?
        if(!isPlayersTurn(player)) {
            return false;
        }

        // does the player have enough resources?
        if(!player.getResources().canAfford(ratio, give)) {
            return false;
        }

        // does the game bank have enough to meet the request?
        if(!getResourceBank().canAfford(1, receive)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean hasLongestRoad(IPlayer player) {
        return player.equals(getLongestRoad());
    }

    @Override
    public boolean hasLargestArmy(IPlayer player) {
        return player.equals(getLargestArmy());
    }

    @Override
    public boolean isPlayersTurn(IPlayer player) {
        return player.equals(getCurrentPlayer());
    }

    // this method is just for determining from the GameState if it is a free round
    @Override
    public boolean isFreeRound() {
        GameState gs = getGameState();
        return (gs == GameState.FirstRound || gs == GameState.SecondRound);
    }

    @Override
    public void updateComplete() {
        assert !isNotInitialized();
        notifyObservers();
    }

    @Override
    public void rollNumber(int rollNumber) {
        if (rollNumber == 7) {
            boolean needToDiscard = false;
            for (IPlayer player : m_players) {
                if (player.needsToDiscard()) {
                    needToDiscard = true;
                    break;
                }
            }

            if (needToDiscard) {
                m_state = GameState.Discarding;
            }
            else {
                m_state = GameState.Robbing;
            }
        }
        else {
            m_map.distributeResources(rollNumber, m_resourceBank);
            m_state = GameState.Playing;
        }
    }

    @Override
    public void joinGame(IUser user, CatanColor playerColor) {
        assert canJoinGame(user, playerColor);

        setChanged();

        // checks if player is already in game, if so sets color and exits method
        for (IPlayer player : m_players) {
            if (player.getId() == user.getId()) {
                player.setColor(playerColor);
                return;
            }
        }

        assert m_players.size() < CatanConstants.NUM_PLAYERS : "Trying to join a full game!";

        // if player is not already in game they are added to the game
        IPlayer player = Player.createNewPlayer(user.getUsername(), user.getId(), playerColor, m_players.size());
        m_players.add(player);

        // if this is the first player added, make it their turn
        if (m_players.size() == 1) {
            m_currentPlayer = m_players.get(0);
        }
    }

    @Override
    public boolean canJoinGame(IUser user, CatanColor playerColor) {
        boolean playerIsInGame = false;

        // checks if color is being used by another player
        for (IPlayer player : m_players) {
            // check if this the user's player
            if (player.getId() != user.getId()) {
                // make sure no one else is using their requested color
                if (player.getColor() == playerColor) {
                    return false;
                }
            }
            else {
                assert !playerIsInGame : "Player is in the game multiple times!";
                playerIsInGame = true;
            }
        }

        // can join if there is still room, or the player has already joined
        return m_players.size() < CatanConstants.NUM_PLAYERS || playerIsInGame;
    }

    @Override
    public void robPlayer(IPlayer player, IPlayer victim, HexLocation hexLocation) {
        // Move the robber to the specified hex location
        m_map.moveRobber(hexLocation);

        // Choose and remove a random card from the victim's hand
        if (victim != null) {
            ResourceType resource = victim.getResources().drawRandom(m_random);

            // Add that random card to the player's hand
            player.getResources().add(1, resource);
        }
        assert verifyResourceAmount();

        m_state = GameState.Playing; // always goes to playing, whether after rolling 7 or playing card
    }

    @Override
    public void finishTurn() {
        // move old dev cards to new
        m_currentPlayer.moveDevCards();
        m_currentPlayer.setPlayedDevCard(false);

        // we could do state pattern, but that would involve some refactoring...
        switch (m_state) {
            case FirstRound:
                if (isLastPlayersTurn()) {
                    m_state = GameState.SecondRound;
                    // keep the same player (last player plays twice in a row in setup)
                }
                else {
                    m_currentPlayer = nextPlayer();
                }
                break;
            case SecondRound:
                if (isFirstPlayersTurn()) {
                    m_state = GameState.Rolling;
                }
                else {
                    assert m_currentPlayer.getIndex() != 0;
                    m_currentPlayer = m_players.get(m_currentPlayer.getIndex() - 1);
                }
                break;
            case Playing:
                // advance the turn to the next player
                // ideally to advance the index,
                // I take the given player index,
                // add 1 and mod by the number of players
                setCurrentPlayer(nextPlayer());
                // change state to the Rolling state for the next player to begin their turn
                m_state = GameState.Rolling;
                break;
            default:
                assert false : "Illegal time to finish turn!";
        }

    }

    @Override
    public void calculateVictoryPoints() {
        // update the longest/largest pointers
        calculateLargestArmy();
        calculateLongestRoad();

        for(IPlayer p : m_players) {
            p.calculateVictoryPoints();

            if (hasLongestRoad(p)) p.giveVictoryPoints(CatanConstants.POINTS_FOR_LONGEST_ROAD);
            if (hasLargestArmy(p)) p.giveVictoryPoints(CatanConstants.POINTS_FOR_LARGEST_ARMY);

            if(p.getVictoryPoints() >= CatanConstants.VICTORY_POINTS_TO_WIN) {
                setWinner(p);
            }
        }
    }

    private void calculateLongestRoad() {
        int longestRoad = m_map.getPlayersLongestRoad(m_longestRoad);

        for (IPlayer player : m_players) {
            int playerLongestRoad = m_map.getPlayersLongestRoad(player);

            if (playerLongestRoad > longestRoad && playerLongestRoad >= CatanConstants.MIN_ROADS_FOR_LONGEST_ROAD) {
                longestRoad = playerLongestRoad;
                m_longestRoad = player;
            }
        }
    }

    private void calculateLargestArmy() {
        int largestArmy = m_largestArmy != null ? m_largestArmy.getSoldiers() : 0; // could set to min soldiers - 1 instead of 0

        for (IPlayer player : m_players) {
            if (player.getSoldiers() > largestArmy && player.getSoldiers() >= CatanConstants.MIN_SOLDIERS_FOR_LARGEST_ARMY) {
                largestArmy = player.getSoldiers();
                m_largestArmy = player;
            }
        }
    }

    @Override
    public void discardCards(IPlayer player, IResourceBank cards) {
        player.discardCards(cards);
        m_resourceBank.add(cards);

        // if discarding is done, move to next phase and clear the discarding flags
        if (!someoneNeedsToDiscard()) {
            for (IPlayer p : m_players) {
                p.setDiscarded(false);
            }

            m_state = GameState.Robbing; // discarding always is followed by robbing
        }
    }

    @Override
    public boolean verifyResourceAmount() {
        IResourceBank allResources = new ResourceBank();
        allResources.add(m_resourceBank);

        for (IPlayer player : m_players) {
            allResources.add(player.getResources());
        }

        return allResources.getBrick() == CatanConstants.TOTAL_CARDS_PER_RESOURCE && allResources.getWood() == CatanConstants.TOTAL_CARDS_PER_RESOURCE && allResources.getSheep() == CatanConstants.TOTAL_CARDS_PER_RESOURCE
            && allResources.getWheat() == CatanConstants.TOTAL_CARDS_PER_RESOURCE && allResources.getOre() == CatanConstants.TOTAL_CARDS_PER_RESOURCE;
    }

    private boolean isFirstPlayersTurn() {
        return m_currentPlayer.getIndex() == 0;
    }

    private boolean isLastPlayersTurn() {
        return m_currentPlayer.getIndex() == CatanConstants.NUM_PLAYERS-1;
    }

    private IPlayer nextPlayer() {
        return getPlayers().get((m_currentPlayer.getIndex() + 1) % getPlayers().size());
    }

    private boolean someoneNeedsToDiscard() {
        for (IPlayer player : m_players) {
            if (player.needsToDiscard()) {
                return true;
            }
        }

        return false;
    }
}
