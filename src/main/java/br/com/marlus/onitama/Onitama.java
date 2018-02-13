package br.com.marlus.onitama;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.marlus.onitama.board.Board;
import br.com.marlus.onitama.pieces.Piece;

public class Onitama {

	private static final String SAVE_GAME = "onitama.json";
	
	private static final Type SAVE_TYPE = new TypeToken<Piece[][]>() {}.getType();
	
	public Board board = new Board();
	
	public void save()
	{
		try {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(board.matrix, SAVE_TYPE);
			
			Writer fileWriter = new FileWriter(SAVE_GAME);
            fileWriter.write(json);
            fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		try {
			Reader reader = new FileReader(SAVE_GAME);
			Gson gson = new GsonBuilder().create();
			Piece[][] fromJson = gson.fromJson(reader, SAVE_TYPE);
			if (fromJson != null) {
				board.matrix = fromJson;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void move(String from, String to, String templateName)
	{
		board.move(from, to, templateName);
	}
	
	public void image()
	{
		board.generateImage();
	}
}
