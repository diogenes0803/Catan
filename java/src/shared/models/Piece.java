package shared.models;

import shared.definitions.PieceType;

/**
 * Model for Pieces
 * @author HojuneYoo
 *
 */

public class Piece {
	
	private PieceType type;
	private int ownerPlayerIndex;
	
	public Piece(){}
	
	public Piece(PieceType type, int ownerPlayerId) {
		this.type = type;
		this.ownerPlayerIndex = ownerPlayerId;
	}
	public PieceType getType() {
		return type;
	}
	public void setType(PieceType type) {
		this.type = type;
	}
	public int getOwnerPlayerIndex() {
		return ownerPlayerIndex;
	}
	public void setOwnerPlayerIndex(int ownerPlayerId) {
		this.ownerPlayerIndex = ownerPlayerId;
	}

}
