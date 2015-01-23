package shared.models;

import shared.definitions.DevCardType;

public class DevCard {
	
	private DevCardType type;
	private boolean canUse;
	
	public DevCardType getType() {
		return type;
	}
	public void setType(DevCardType type) {
		this.type = type;
	}
	public boolean isCanUse() {
		return canUse;
	}
	public void setCanUse(boolean canUse) {
		this.canUse = canUse;
	}
	
}
