package br.com.marlus.onitama.service;

import br.com.marlus.onitama.Onitama;

public class OnitamaService {

	public void move(String from, String to, String template) {
		Onitama onitama = new Onitama();
		onitama.load();
		onitama.move(from, to, template);
		onitama.save();
	}
	
}
