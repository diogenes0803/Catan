/**
 * 
 */
package server.facades;

import java.util.HashMap;
import java.util.Map;

import server.data.ServerInterface;
import shared.models.Game;

/**
 * @author campbeln
 *
 */
public interface Facade {
	
	ServerInterface server = null;

	static Map<Integer, Game> games = new HashMap<Integer, Game>();
	
}
