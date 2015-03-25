/**
 * 
 */
package server.facades;

import java.util.HashMap;
import java.util.Map;

import server.Server;
import server.data.ServerInterface;
import shared.models.Game;

/**
 * @author campbeln
 *
 */
public interface Facade {
	
	static Map<Integer, Game> games = new HashMap<Integer, Game>();

}
