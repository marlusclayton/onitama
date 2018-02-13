package br.com.marlus.onitama.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.common.io.Resources;
import com.google.gson.annotations.SerializedName;

import br.com.marlus.onitama.movement.Template;
import br.com.marlus.onitama.pieces.Piece;
import br.com.marlus.onitama.pieces.PieceType;
import br.com.marlus.onitama.pieces.Team;

public class Board {
	
	private static final String IMAGE_FILE = "image/board.png";
	public static final String SAVED_IMAGE_FILE = "./webapp/static/board.png";
	
	@SerializedName("board")
	public Piece[][] matrix = {};
	
	private int size = 25;
	private int square = (int) Math.sqrt(size);
	private int kingPostion = 2;
	private int redTeamRow = 0;
	private int blueTeamRow = 4;

	public Board() 
	{
		init();
	}

	public void init() {
		
		matrix = new Piece[5][5];
		
		initTeam(Team.RED, redTeamRow);
		
		for (int i = 5; i < 20; i++)
		{
			Location location = Location.from(i);
			matrix[location.row][location.col] = new Piece(PieceType.UNDEF, Team.UNDEF, location);
		}
		initTeam(Team.BLUE, blueTeamRow);
	}
	
	private void initTeam(Team team, int startRow) {
		int teamOffSet = square * startRow;
		
		for (int col = 0; col < square; col++)
		{
			Location location = Location.from(teamOffSet + col);
			PieceType pieceType = (location.col == kingPostion ? PieceType.KING : PieceType.PAWN);
			Piece piece = new Piece(pieceType, team, location);
			matrix[location.row][location.col] = piece;
		}
	}

	public Piece getPiece(Location location)
	{
		return getPiece(location.row, location.col);
	}
	
	public Piece getPiece(int row, int col)
	{
		return matrix[row][col];
	}
	
	public void print()
	{
		for ( int i = 0; i < size; i++)
		{
			Location location = Location.from(i);
			Piece piece = getPiece(location);
			String separator = (location.col == 4 ? "\r\n" : "; ");
			System.out.println(piece + separator);
		}
	}
	
	public void move(String from, String to, String templateName)
	{
		Location locationFrom = Location.from(from);
		Location locationTo = Location.from(to);
		move(locationFrom, locationTo, templateName);
	}
	
	public void move(Location from, Location to, String templateName)
	{
		Template template = Template.find(templateName);
		
		Piece pieceFrom = getPiece(from);
		if ( pieceFrom.isValidMovement(to, template) )
		{
			Piece undefPiece = new Piece(PieceType.UNDEF, Team.UNDEF, pieceFrom.currentLocation);
			set(undefPiece);

			pieceFrom.currentLocation = to;
			set(pieceFrom);
		}
	}
	
	private void set(Piece piece) {
		matrix[piece.currentLocation.row][piece.currentLocation.col] = piece;
	}
	
	public void generateImage()
	{
		try {
		    BufferedImage bi = ImageIO.read(Resources.getResource(IMAGE_FILE));
		    Graphics2D g2d = bi.createGraphics();

		    for (Piece[] pieces : matrix) {
				for (Piece piece : pieces) {
					Color color = ( piece.team == Team.RED ? Color.RED : Color.BLUE );
					g2d.setColor(color);
					
					int x = 60 + (141*piece.currentLocation.col);
					int y = 60 + (145*piece.currentLocation.row);
					
					switch (piece.pieceType) {
					case KING:
						g2d.setColor(Color.BLACK);
						g2d.fill(new Rectangle2D.Float(x, y, 100, 100));
						g2d.setColor(color);
						g2d.fill(new Rectangle2D.Float(x+5, y+5, 90, 90));
						
						break;

					case PAWN:
						g2d.setColor(Color.BLACK);
						g2d.fill(new Ellipse2D.Float(x, y, 100, 100));
						g2d.setColor(color);
						g2d.fill(new Ellipse2D.Float(x+5, y+5, 90, 90));
						
						break;
						
					default:
						break;
					}
				}
			}
		    
		    g2d.dispose();
		    
		    File outputfile = new File(SAVED_IMAGE_FILE);
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}