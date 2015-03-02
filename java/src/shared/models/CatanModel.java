package shared.models;

/**
 * 
 * @author HojuneYoo
 * CatanModel is a big chunk of Class that holds everything about game and users
 */

public class CatanModel {
	private static CatanModel instance = new CatanModel();
	private GameManager gameManager;
	private UserManager userManager;
	private int version;
	
	public CatanModel(){
	    setVersion(-1);
	    gameManager = new GameManager();
	    userManager = null;
	}
	
	public static CatanModel getInstance() {
		return instance;
	}
	
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    public static void setInstance(CatanModel model) {
    	instance.gameManager.setGame(model.getGameManager().getGame());
    }
	
}
