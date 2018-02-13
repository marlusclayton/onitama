package br.com.marlus.onitama.pieces;

import com.google.gson.annotations.SerializedName;

import br.com.marlus.onitama.board.Location;
import br.com.marlus.onitama.movement.Template;

public class Piece {
	
	@SerializedName("piece")
	public PieceType pieceType;

	@SerializedName("team")
	public Team team;
	
	@SerializedName("location")
	public Location currentLocation;
	
	public Piece(PieceType pieceType, Team team, Location currentLocation) {
		this.pieceType = pieceType;
		this.team = team;
		this.currentLocation = currentLocation;
	}
	
	public boolean isKing()
	{
		return pieceType == PieceType.KING;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s) at %d,%d", pieceType.toString(), team.toString(), currentLocation.row, currentLocation.col);
	}
	
	public boolean isValidMovement(Location locationTo, Template movement)
	{
		int[][] template = (team == Team.RED ? movement.getInvertedTemplate() : movement.getTemplate() );
		
		int currentRow = this.currentLocation.row;
		int currentCol = this.currentLocation.col;
		
		int rowTo = locationTo.row;
		int colTo = locationTo.col;
		
		int rowToCheck = 2 - (currentRow - rowTo);
		int colToCheck = 2 - (currentCol - colTo);  
		
		return ((currentRow != rowTo || currentCol != colTo) && template[rowToCheck][colToCheck] == 1);
	}
}
