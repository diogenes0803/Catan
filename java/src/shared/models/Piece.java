package shared.models;

import shared.definitions.PieceType;

/**
 * Model for Pieces
 * @author HojuneYoo
 *
 */

public class Piece {
	
	private PieceType type;
	private int ownerPlayerId;
	
	public Piece(){}
	
	public Piece(PieceType type, int ownerPlayerId) {
		this.type = type;
		this.ownerPlayerId = ownerPlayerId;
	}
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
