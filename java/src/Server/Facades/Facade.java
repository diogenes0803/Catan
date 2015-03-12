package Server.Facades;

import java.util.HashMap;

import shared.models.Game;

public interface Facade {
	
	public static HashMap<Integer, Game> games = new HashMap<Integer, Game>();
	
}
