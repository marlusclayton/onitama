package br.com.marlus.onitama.service;

import java.io.File;

import br.com.marlus.onitama.Onitama;
import br.com.marlus.onitama.board.Board;

public class ImageService {
	
	public File image()
	{
		Onitama onitama = new Onitama();
		onitama.load();
		onitama.save();
		onitama.image();
		
		File file = new File(Board.SAVED_IMAGE_FILE);
		return file;
	}

}
