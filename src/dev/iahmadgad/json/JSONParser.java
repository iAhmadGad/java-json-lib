package dev.iahmadgad.json;

/*
 * Java JSON Handler
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * JSONParser Class.
 * 
 * @author iAhmadGad
 * @version 0.3
 * @since 0.3
*/

public class JSONParser 
{
	/**
	 * The given source to be parsed.
	 * 
	 * @see #JSONParser(String)
	 * @see #readFile(File)
	 */
	private static String source = "";
	
	/**
	 * The source after deleting spaces and new lines.
	 * 
	 * @see #lineSource()
	 */
	private static String line = "";
	
	/**
	 * The main JSONObject the source presents (in case it presents a JSONObject).
	 */
	private static JSONObject object = new JSONObject();
	
	/**
	 * The main JSONArray the source presents (in case it presents a JSONArray).
	 */
	private static JSONArray array = new JSONArray();
	
	/**
	 * Constructor that parses the node from a text.
	 * 
	 * @param text
	 * @see #parse()
	 */
	protected JSONParser(String text)
	{
		source = text;
		lineSource();
		parse();
	}
	
	/**
	 * Constructor that parses the node from a file.
	 * 
	 * @param file
	 * @see #readFile(File)
	 * @see #parse()
	 */
	protected JSONParser(File file)
	{
		readFile(file);
		lineSource();
		parse();
	}
	
	/**
	 * Returns the main JSONObject node.
	 * 
	 * @return the main JSONObject node
	 */
	protected HashMap<String, Object> getHashMap()
	{
		return object.getNode();
	}
	
	/**
	 * Returns the main JSONArray node.
	 * 
	 * @return the main JSONArray node.
	 */
	protected ArrayList<Object> getArrayList()
	{
		return array.getNode();
	}
	
	/**
	 * Reads JSON source from a file.
	 * 
	 * @param file
	 */
	private static void readFile(File file)
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
	
	/**
	 * Deletes spaces & new lines from the source.
	 * 
	 * @see #line
	 */
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
	
	/**
	 * Determines if the source presents a JSONObject or a JSONArray then uses the appropriate method to parse it.
	 */
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
	
	/**
	 * Parses JSONObject from the source text.
	 * <p>
	 * It uses recursion when there is another JSONObject or JSONArray nested in it & calls another method to parse it.
	 * 
	 * @param text
	 * @return parsed JSONObject
	 * @see #parseJSONArray(String)
	 */
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
	
	/**
	 * Parses JSONObject from the source text.
	 * <p>
	 * It uses recursion when there is another JSONArray or JSONObject nested in it & calls another method to parse it.
	 * 
	 * @param text
	 * @return parsed JSONArray
	 * @see #parseJSONObject(String)
	 */
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