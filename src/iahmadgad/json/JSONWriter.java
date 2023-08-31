package iahmadgad.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * JSONWrite Class
 * @author iAhmadGad
 * @version 0.2
 *
 */

public class JSONWriter 
{
	
	private static File file;
	
	public JSONWriter(File file)
	{
		JSONWriter.file = file;
	}
	public void write(JSON json, int indentation)
	{
		HashMap<String, Object> tree = json.getJSONTree();
		try 
		{
			FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);
			writer.write("{\n");
			for(Entry pair : tree.entrySet())
			{
				if(pair.getValue().getClass().toString().compareTo("class java.lang.String") == 0) writer.write(indent(indentation) + "\"" + pair.getKey() + "\":\"" + pair.getValue() + "\",\n");
			    else writer.write(indent(indentation) + "\"" + pair.getKey() + "\":" + pair.getValue() + ",\n");
			}
			writer.write("}");
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static String indent(int i)
	{
		String string = "";
		for(byte j = 0; j < i; j++) string += " ";
		return string;
	}
}
