package shared.models;

import shared.definitions.DevCardType;

/**
 * 
 * @author HojuneYoo
 * Model of Development Card
 */

public class DevCard {
	
	private DevCardType type;
	private boolean old;
	
	public DevCard(){}
	public DevCard(DevCardType type, boolean old) {
		this.type = type;
		this.old = old;
	}
	
	public DevCardType getType() {
		return type;
	}
	public void setType(DevCardType type) {
		this.type = type;
	}
	public boolean isOld() {
		return old;
	}
	public void setOld(boolean old) {
		this.old = old;
	}
	
}
