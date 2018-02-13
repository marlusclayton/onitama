package br.com.marlus.onitama.movement;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Template {
	
	public String name;
	public int[][] template;
	
	private static final String CORE = "movement/template-core.json";
	private static final String SENSEI_PATH = "movement/template-core.json";
	private static final Type MOVEMENT_TYPE = new TypeToken<List<Template>>() {}.getType();
	private static List<Template> templates = new ArrayList<Template>();
	
	public int[][] getTemplate()
	{
		return template;
	}
	
	public int[][] getInvertedTemplate()
	{
	    return rotate(rotate(this.template));
	}

	private int[][] rotate(int[][] matrix) {
		final int rowSize = matrix.length;
	    final int colSize = matrix[0].length;
	    int[][] rotatedMatrix = new int[colSize][rowSize];
	    for (int r = 0; r < rowSize; r++) {
	        for (int c = 0; c < colSize; c++) {
	        	rotatedMatrix[c][rowSize-1-r] = matrix[r][c];
	        }
	    }
	    return rotatedMatrix;
	}

	public static void load()
	{
		if (templates.isEmpty()) 
		{
			load(CORE);
		}
	}

	public static void load(String template)
	{
		Gson gson = new Gson();
		try {
			String jsonTemplate = Resources.toString(Resources.getResource(CORE), Charset.defaultCharset());
			
			List<Template> movements = gson.fromJson(jsonTemplate, MOVEMENT_TYPE);
			
			templates.addAll(movements);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Template find(String name)
	{
		load();

		for (Template template : templates) {
			if (template.name.equalsIgnoreCase(name)) {
				return template;
			}
		}
		return null;
	}
	
	public static List<Template> getAll()
	{
		load();
		
		return templates;
	}
}
