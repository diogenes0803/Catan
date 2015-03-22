package shared.data;

import shared.definitions.CatanColor;
import shared.models.Player;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Used to pass player information into views<br> <br> PROPERTIES:<br> <ul> <li>Id: Unique player ID</li>
 * <li>PlayerIndex: Player's order in the game [0-3]</li> <li>Name: Player's name (non-empty string)</li> <li>Color:
 * Player's color (cannot be null)</li> </ul>
 */
public class PlayerInfo {

    private int id;
    private int playerIndex;
    private String name;
    private CatanColor color;

    public PlayerInfo() {
        setId(-1);
        setPlayerIndex(-1);
        setName("");
        setColor(CatanColor.WHITE);
    }

    public PlayerInfo(int id, int index, String name, CatanColor color) {
        setId(id);
        setPlayerIndex(index);
        setName(name);
        setColor(color);
    }

    public PlayerInfo(Player player) {
        this(player.getPlayerId(), player.getIndex(), player.getName(), player.getColor());
    }

    /**
     * Get an array of PlayerInfos from a collection of Player objects.
     */
    public static PlayerInfo[] fromPlayers(Collection<Player> players) {
        Collection<PlayerInfo> playerInfos = new ArrayList<>(players.size());
        for (Player player : players) {
            playerInfos.add(new PlayerInfo(player));
        }

        assert playerInfos.size() == players.size();
        return playerInfos.toArray(new PlayerInfo[playerInfos.size()]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CatanColor getColor() {
        return color;
    }

    public void setColor(CatanColor color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        return 31 * this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerInfo other = (PlayerInfo) obj;

        return this.id == other.id;
    }
}

