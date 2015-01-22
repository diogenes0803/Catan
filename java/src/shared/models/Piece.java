package shared.models;

import shared.definitions.PieceType;

public class Piece {
	
	private PieceType type;
	private int ownerPlayerId;
	
	public PieceType getType() {
		return type;
	}
	public void setType(PieceType type) {
		this.type = type;
	}
	public int getOwnerPlayerId() {
		return ownerPlayerId;
	}
	public void setOwnerPlayerId(int ownerPlayerId) {
		this.ownerPlayerId = ownerPlayerId;
	}

}
