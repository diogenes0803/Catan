package shared.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * Used to pass game information into views<br> <br> PROPERTIES:<br> <ul> <li>Id: Unique game ID</li> <li>Title: Game
 * title (non-empty string)</li> <li>Players: List of players who have joined the game (can be empty)</li> </ul>
 */
public class GameInfo {
	
	@Expose private String title;
    @Expose private int id;
    @Expose private List<PlayerInfo> players;

    public GameInfo() {
        setId(-1);
        setTitle("");
        players = new ArrayList<PlayerInfo>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addPlayer(PlayerInfo newPlayer) {
        players.add(newPlayer);
    }

    public List<PlayerInfo> getPlayers() {
        return Collections.unmodifiableList(players);
    }
    
    public int findPlayerIndexById(int playerId) {
    	for(int i = 0; i < players.size(); i++) {
    		PlayerInfo info = players.get(i);
    		if(playerId == info.getId()) {
    			return i;
    		}
    	}
    	return -1;
    }
}

