package br.com.marlus.onitama.pieces;

import com.google.gson.annotations.SerializedName;

public enum PieceType 
{
	@SerializedName("pawn") PAWN, 
	@SerializedName("king") KING, 
	@SerializedName("undef") UNDEF;

}
