package shared.models;

/**
 * 
 * @author HojuneYoo
 * CatanModel is a big chunk of Class that holds everything about game and users
 */

public class CatanModel {
	
	private GameManager gameManager;
	private UserManager userManager;
	
	public GameManager getGameManager() {
		return gameManager;
	}
	
	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
}
