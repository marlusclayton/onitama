package br.com.marlus.onitama.board;

import com.google.gson.annotations.SerializedName;

public class Location {
	
	@SerializedName("row")
	public int row;
	
	@SerializedName("col")
	public int col;
	
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public static Location from(Integer integer)
	{
		Location location = null;
		if (integer >= 0 && integer <= 25)
		{
			int row = integer / 5;
			int col = integer % 5;
			
			location = new Location(row, col);
		}
		return location;
	}
	
	public static Location from(String string)
	{
		char charRow = (char) (string.toUpperCase().charAt(0) - 17);
		
		int row = Integer.valueOf(String.valueOf(charRow));
		int col = Integer.valueOf(String.valueOf(string.charAt(1))) - 1;
		
		return new Location(row, col);
	}
	
	public static Integer from(Location location)
	{
		return location.row * location.col;
	}
}
