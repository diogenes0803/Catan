package client.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameInfo
{
	private int id;
	private String title;
	private List<PlayerInfo> players;
	
	public GameInfo()
	{
		setId(-1);
		setTitle("");
		players = new ArrayList<PlayerInfo>();
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void addPlayer(PlayerInfo newPlayer)
	{
		players.add(newPlayer);
	}

	public List<PlayerInfo> getPlayers()
	{
		return Collections.unmodifiableList(players);
	}

    public boolean isFull() {
        return players.size() == 4;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            if (this == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (o instanceof GameInfo) {
                if (((GameInfo) o).getId() == getId()) {
                    if (((GameInfo) o).getTitle().equals(getTitle())) {
                        if (((GameInfo) o).getPlayers().equals(getPlayers())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
}

