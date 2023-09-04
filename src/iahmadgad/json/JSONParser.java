package iahmadgad.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class JSONParser 
{
	private static String source = "";
	private static String line = "";
	private static JSONObject object = new JSONObject();
	private static JSONArray array = new JSONArray();
	
	protected JSONParser(String text)
	{
		source = text;
		lineSource();
		parse();
	}
	
	protected JSONParser(File file)
	{
		readFile(file);
		lineSource();
		parse();
	}
	
	protected HashMap<String, Object> getHashMap()
	{
		return object.getNode();
	}
	
	protected ArrayList<Object> getArrayList()
	{
		return array.getNode();
	}
	
	protected static void readFile(File file)
	{
		try 
		{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				source += scanner.nextLine();
			}
			scanner.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void lineSource()
	{
		boolean avoidSpaceAndNewLine = true;
		for(int i = 0; i < source.length(); i++)
		{
			if(source.charAt(i) == '\"' && avoidSpaceAndNewLine) avoidSpaceAndNewLine = false;
			else if(source.charAt(i) == '\"' && !avoidSpaceAndNewLine) avoidSpaceAndNewLine = true;
			if(source.charAt(i) != '\s' && source.charAt(i) != '\n' && avoidSpaceAndNewLine) line += source.charAt(i);
			else if(!avoidSpaceAndNewLine) line += source.charAt(i);
		}
	}
	
	private static void parse()
	{
		if(line.charAt(0) == '{')
		{
			line = line.substring(1);
			object = parseJSONObject(line);
			array = null;
		}
		else
		{
			line = line.substring(1);
			array = parseJSONArray(line);
			object = null;
		}
	}
	
	private static JSONObject parseJSONObject(String text)
	{
		JSONObject object = new JSONObject();
		String key = "", value = "";
		for(int i = 0; i < line.length(); i++)
		{
			if(line.charAt(i) == ',' && line.charAt(i - 1) != '\\')
			{
				key = "";
				value = "";
			}
			else if(line.charAt(i) == '\"')
			{
				i++;
				while(line.charAt(i) != '\"')
				{
					key += line.charAt(i);
					i++;
				}
			}
			else if (line.charAt(i) == ':' && line.charAt(i + 1) != '{' && line.charAt(i + 1) != '[')
			{
				i++;
				while(line.charAt(i) != ',' && line.charAt(i) != '}')
				{
					value += line.charAt(i);
					i++;
				}
				object.put(key, Validator.getVariable(value));
				i--;
			}
			else if(line.charAt(i) == '{')
			{
				line = line.substring(i + 1);
				object.put(key, parseJSONObject(line));
				key = "";
				i = 0;
			}
			else if(line.charAt(i) == '[')
			{
				line = line.substring(i + 1);
				object.put(key, parseJSONArray(line));
				key = "";
				i = 0;
			}
			else if(line.charAt(i) == '}' + 1 && line.length() >= 2)
			{
				line = line.substring(i + 1);
				break;
			}
			else if(line.charAt(i) == '}' && line.length() <= 2) break;
		}
		return object;
	}
	
	private static JSONArray parseJSONArray(String text)
	{
		JSONArray array = new JSONArray();
		String value = "";
		for(int i = 0; i < line.length(); i++)
		{
			if(line.charAt(i) != ',' && line.charAt(i) != '{' &&  line.charAt(i) != '[' && line.charAt(i) != ']')
			{
				while(line.charAt(i) != ',' && line.charAt(i) != ']')
				{
					value += line.charAt(i);
					i++;
				}
				array.add(Validator.getVariable(value));
				i--;
			}
			else if(line.charAt(i) == ',') value = "";
			else if(line.charAt(i) == '{')
			{
				line = line.substring(i + 1);
				array.add(parseJSONObject(line));
				i = 0;
			}
			else if(line.charAt(i) == '[')
			{
				line = line.substring(i + 1);
				array.add(parseJSONArray(line));
				i = 0;
			}
			else if(line.charAt(i) == ']' && line.length() >= 2)
			{
				line = line.substring(i + 1);
				break;
			}
			else if(line.charAt(i) == ']' && line.length() <= 2) break;
		}
		return array;
	}
}
