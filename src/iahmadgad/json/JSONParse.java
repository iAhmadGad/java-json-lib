package iahmadgad.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONParse 
{
	public JSONParse(String string)
	{
		String source = "";
		for(int i = 0; i < string.length(); i++)
		{
			char c = string.charAt(i);
			if(c != '\s' && c != '\n') source += c;
		}
		parse(source);
	}
	
	public JSONParse(File file)
	{
		String string = "";
		try 
		{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				string += scanner.nextLine();
			}
			scanner.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		String source = "";
		for(int i = 0; i < string.length(); i++)
		{
			char c = string.charAt(i);
			if(c != '\s' && c != '\n') source += c;
		}
		parse(source);
	}
	
	public static HashMap<String, Object> getKeysAndValues() 
	{
		return keysAndValues;
	}
	
	private static HashMap<String, Object> keysAndValues;
	
	public static void parse(String source)
	{
		String array[] = source.split("[,{}]");
		ArrayList<String> pairs = new ArrayList<String>();
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] != "") pairs.add(array[i]);
		}
		keysAndValues = new HashMap<String, Object>();
		for(int i = 0; i < pairs.size(); i++)
		{
			String key = pairs.get(i).split(":")[0], value = pairs.get(i).split(":")[1];
			key = key.substring(1, key.length() - 1);
			if(value.contains("\"")) keysAndValues.put(key, value.substring(1, value.length() - 1));
			else if(value.compareTo("true") == 0) keysAndValues.put(key, true);
			else if(value.compareTo("false") == 0) keysAndValues.put(key, false);
			else if(value.contains(".")) keysAndValues.put(key, Double.parseDouble(value));
			else keysAndValues.put(key, Integer.parseInt(value));
		}
	}
}
