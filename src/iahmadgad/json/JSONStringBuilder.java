package iahmadgad.json;

/*
 * Java JSON Handler
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;

/**
 * JSONStringBuilder Class.
 * <p>
 * JSONStringBuilder builds a String for JSONObject to be used or to be written in some file.
 * 
 * @author iAhmadGad
 * @version 0.3
 * @since 0.3
*/

public class JSONStringBuilder 
{
	/**
	 * The main JSONObject.
	 */
	private static JSONObject object;
	
	/**
	 * The String of the JSONObject. 
	 */
	private static String JSONString = "";
	
	/**
	 * The main Constructor which assigns some JSONObject to the JSONStringBuilder.
	 * @param object
	 */
	public JSONStringBuilder(JSONObject object)
	{
		JSONStringBuilder.object = object;
	}
	
	/**
	 * Returns The String of the JSONObject.
	 * @return The String of the JSONObject
	 */
	public String getJSONString()
	{
		if(JSONString == "") build(object, 0);
		return JSONString;
	}
	
	private static byte counter = 0;
	
	private void build(JSONObject object, int i)
	{
		JSONString += getIndentation(i) + "{\n";
		counter = 0;
		for(Entry entry: object.entrySet())
		{
			JSONString += getIndentation(i + 1) + (counter != 0 ? ',' : "") + "\"" + entry.getKey() + "\"" + getSpaceAroundColon() + ':' + getSpaceAroundColon();
			if(Validator.isJSONArray(entry.getValue()))
			{
				build((JSONArray) entry.getValue(), i + 1);
				JSONString += "\n";
				counter++;
			}
			else if(Validator.isJSONObject(entry.getValue()))
			{
				build((JSONObject) entry.getValue(), i + 1);
				JSONString += "\n";
				counter++;
			}
			else
			{
				JSONString += (Validator.isString(entry.getValue()) ? "\"" + entry.getValue() + "\"" : entry.getValue()) + "\n";
				counter++;
			}
		}
		JSONString += getIndentation(i) + "}\n";
	}
	
	private void build(JSONArray array, int i)
	{
		JSONString += getIndentation(i);
		JSONString += "[\n";
		for(int j = 0; j < array.size(); j++)
		{
			if(Validator.isJSONArray(array.get(j)))
			{
				build((JSONArray) array.get(j), i + 1);
				JSONString += "\n";
			}
			else if(Validator.isJSONObject(array.get(j)))
			{
				build((JSONObject) array.get(j), i + 1);
				JSONString += "\n";
			}
			else 
			{
				JSONString += getIndentation(i + 1) + (array.size() - j != array.size() ? "," : "") + (Validator.isString(array.get(j)) ? "\"" + array.get(j) + "\"" : array.get(j)) + "\n";
			}
		}
		JSONString += getIndentation(i) + "]\n";
	}
	
	/**
	 * Writes the JSONObjectString in a file.
	 * @param file
	 */
	public void write(File file)
	{
		try 
		{
			FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);
			writer.write(getJSONString());
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private int indentation = 5;
	
	private int spaceAroundColon = 0;
	
	/**
	 * Sets indentation with some int value.
	 * @param i
	 */
	public void setIndentation(int i)
	{
		indentation = i;
	}
	
	/**
	 * Sets indentation with some Enum value.
	 * @param e
	 */
	public void setIndentation(SBEnum e)
	{
		if (e == SBEnum.DEFAULT) indentation = 5;
		else if(e == SBEnum.NONE) indentation = 0;
	}
	
	private String getIndentation(int i)
	{
		String string = "";
		for(int j = 0; j < i * indentation; j++) string += " ";
		return string;
	}
	
	/**
	 * Sets space around colon with some int value.
	 * @param i
	 */
	public void setSpaceAroundColon(int i)
	{
		spaceAroundColon = i;
	}
	
	/**
	 * Sets space around colon with some Enum value.
	 * @param e
	 */
	public void setSpaceAroundColon(SBEnum e)
	{
		if (e == SBEnum.DEFAULT) spaceAroundColon = 0;
		else if(e == SBEnum.NONE) spaceAroundColon = 0;
	}
	
	private String getSpaceAroundColon()
	{
		String string = "";
		for(int j = 0; j < spaceAroundColon; j++) string += " ";
		return string;
	}
	
	/**
	 * Sets all settings to their deffaults.
	 */
	public void setDefault()
	{
		indentation = 3;
		spaceAroundColon = 0;
	}
	
	public enum SBEnum 
	{
		DEFAULT,
		NONE
	}
}
