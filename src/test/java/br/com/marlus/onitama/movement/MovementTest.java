package br.com.marlus.onitama.movement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.com.marlus.onitama.board.Location;
import br.com.marlus.onitama.pieces.Piece;
import br.com.marlus.onitama.pieces.PieceType;
import br.com.marlus.onitama.pieces.Team;

public class MovementTest {

	@Test
	public void testValidBlueTigerMovement() {
		Location location = new Location(4,2);
		Piece piece = new Piece(PieceType.KING, Team.BLUE, location);
		
		Location locationTo = new Location(2, 2);
		
		Template template = Template.find("tiger");
		
		boolean isValid = piece.isValidMovement(locationTo, template);

		assertThat(isValid, is( equalTo( Boolean.TRUE )));
	}

	@Test
	public void testInvalidBlueTigerMovement() {
		Location location = new Location(4,2);
		Piece piece = new Piece(PieceType.KING, Team.BLUE, location);
		
		Location locationTo = new Location(3, 2);
		
		Template template = Template.find("tiger");
		
		boolean isValid = piece.isValidMovement(locationTo, template);
		
		assertThat(isValid, is( equalTo( Boolean.FALSE )));
	}
	
	@Test
	public void testValidRedTigerMovement() {
		Location location = new Location(0,2);
		Piece piece = new Piece(PieceType.KING, Team.RED, location);
		
		Location locationTo = new Location(2, 2);
		
		Template template = Template.find("tiger");
		
		boolean isValid = piece.isValidMovement(locationTo, template);
		
		assertThat(isValid, is( equalTo( Boolean.TRUE )));
	}
	
	@Test
	public void testInvalidRedTigerMovement() {
		Location location = new Location(0,2);
		Piece piece = new Piece(PieceType.KING, Team.RED, location);
		
		Location locationTo = new Location(1, 2);
		
		Template template = Template.find("tiger");
		
		boolean isValid = piece.isValidMovement(locationTo, template);
		
		assertThat(isValid, is( equalTo( Boolean.FALSE )));
	}
	
	@Test
	public void testValidBlueCrabMovement() {
		Location location = new Location(4,2);
		Piece piece = new Piece(PieceType.KING, Team.BLUE, location);
		
		Location locationTo = new Location(3, 2);
		
		Template template = Template.find("crab");
		
		boolean isValid = piece.isValidMovement(locationTo, template);
		
		assertThat(isValid, is( equalTo( Boolean.TRUE )));
	}
	
	@Test
	public void testValidBlueMonkeyDiagonalMovement() {
		Location location = new Location(4,2);
		Piece piece = new Piece(PieceType.KING, Team.BLUE, location);
		
		Location locationTo = new Location(3, 1);
		
		Template template = Template.find("monkey");
		
		boolean isValid = piece.isValidMovement(locationTo, template);
		
		assertThat(isValid, is( equalTo( Boolean.TRUE )));
	}
	
	@Test
	public void testInvalidBlueMonkeyDiagonalMovement() {
		Location location = new Location(4,2);
		Piece piece = new Piece(PieceType.KING, Team.BLUE, location);
		
		Location locationTo = new Location(3, 2);
		
		Template template = Template.find("monkey");
		
		boolean isValid = piece.isValidMovement(locationTo, template);
		
		assertThat(isValid, is( equalTo( Boolean.FALSE )));
	}
	
}
