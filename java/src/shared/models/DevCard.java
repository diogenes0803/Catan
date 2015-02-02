package shared.models;

import shared.definitions.DevCardType;

/**
 * 
 * @author HojuneYoo
 * Model of Development Card
 */

public class DevCard {
	
	private DevCardType type;
	private boolean usable;
	
	public DevCardType getType() {
		return type;
	}
	public void setType(DevCardType type) {
		this.type = type;
	}
	public boolean isUsable() {
		return usable;
	}
	public void setUsable(boolean canUse) {
		this.usable = canUse;
	}
	
}
