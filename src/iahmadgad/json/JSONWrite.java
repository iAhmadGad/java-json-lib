package iahmadgad.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class JSONWrite 
{
	public JSONWrite(File file, JSON json, byte indentation)
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
	
	public static String indent(byte i)
	{
		String string = "";
		for(byte j = 0; j < i; i++) string += " ";
		return string;
	}
}
