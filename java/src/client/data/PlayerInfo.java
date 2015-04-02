package client.data;

import shared.definitions.*;
import shared.model.IPlayer;
import shared.model.Player;

import javax.imageio.event.IIOReadProgressListener;
import java.util.ArrayList;
import java.util.Collection;


public class PlayerInfo
{
    public static final int NO_PLAYER = Player.NO_PLAYER;
	
	private int id;
	private int playerIndex;
	private String name;
	private CatanColor color;
	
	public PlayerInfo()
	{
		setId(-1);
		setPlayerIndex(NO_PLAYER);
		setName("");
		setColor(CatanColor.WHITE);
	}

    public PlayerInfo(int id, int index, String name, CatanColor color) {
		setId(id);
		setPlayerIndex(index);
		setName(name);
		setColor(color);
	}

    public PlayerInfo(IPlayer player) {
        this(player.getId(), player.getIndex(), player.getName(), player.getColor());
    }
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getPlayerIndex()
	{
		return playerIndex;
	}
	
	public void setPlayerIndex(int playerIndex)
	{
		this.playerIndex = playerIndex;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public CatanColor getColor()
	{
		return color;
	}
	
	public void setColor(CatanColor color)
	{
		this.color = color;
	}


    public static PlayerInfo[] fromPlayers(Collection<IPlayer> players) {
        Collection<PlayerInfo> playerInfos = new ArrayList<>(players.size());
        for (IPlayer player : players) {
            playerInfos.add(new PlayerInfo(player));
        }

        assert playerInfos.size() == players.size();
        return playerInfos.toArray(new PlayerInfo[playerInfos.size()]);
    }

	@Override
	public int hashCode()
	{
		return 31 * this.id;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final PlayerInfo other = (PlayerInfo) obj;
		
		return this.id == other.id;
	}
}

