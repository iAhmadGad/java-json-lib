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
	 * <p>
	 * it equals null if JSONStringBuilder's node is JSONArray.
	 */
	private static JSONObject object;
	
	/**
	 * The main JSONArray.
	 * <p>
	 * it equals null if JSONStringBuilder's node is JSONObject.
	 */
	private static JSONArray array;
	
	/**
	 * The String of the JSONObject. 
	 */
	private static String JSONString = "";
	
	/**
	 * The main Constructor which assigns a JSONObject to the JSONStringBuilder.
	 * 
	 * @param node
	 */
	protected JSONStringBuilder(JSONObject node, int indentation, int spaceAroundColon)
	{
		object = node;
		array = null;
		this.indentation = indentation;
		this.spaceAroundColon = spaceAroundColon;
	}
	
	/**
	 * The main Constructor which assigns a JSONArray to the JSONStringBuilder.
	 * 
	 * @param node
	 */
	protected JSONStringBuilder(JSONArray node, int indentation, int spaceAroundColon)
	{
		array = node;
		object = null;
		this.indentation = indentation;
		this.spaceAroundColon = spaceAroundColon;
	}
	
	/**
	 * Indentation & space around colon.
	 */
	private int indentation, spaceAroundColon;
	
	/**
	 * Returns The String of the JSONObject or the JSONArray.
	 * 
	 * @return The String of the JSONObject or the JSONArray
	 */
	protected String getJSONString()
	{
		if(JSONString == "")
		{
			if(object == null) build(array, 0);
			else build(object, 0);
		}
		return JSONString;
	}
	
	/**
	 * A counter to know if value is the first value in ther JSONObject or the JSONArray.
	 * 
	 * @see #build(JSONObject, int)
	 * @see #build(JSONArray, int)
	 */
	private static byte counter = 0;
	
	/**
	 * Builds the JSONObject String.
	 * <p>
	 * It uses the int i to determine the indentation level when building the String, 
	 * & it uses recursion when there is another JSONObject or a JSONArray nested in it & recalls the method build again to build the nested node with indentation level of (i + 1).
	 * 
	 * @param object
	 * @param i
	 * @see #build(JSONArray, int)
	 */
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
	
	/**
	 * Builds the JSONArray String.
	 * <p>
	 * It uses the int i to determine the indentation level when building the String, 
	 * & it uses recursion when there is another JSONArray or JSONObject nested in it & recalls the method build again to build the nested node with indentation level of (i + 1).
	 * 
	 * @param array
	 * @param i
	 * @see #build(JSONObject, int)
	 */
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
	 * Returns indentation String.
	 * <p>
	 * consists of (i * indentation level) spaces.
	 * 
	 * @param i
	 * @return indentation String
	 */
	private String getIndentation(int i)
	{
		String string = "";
		for(int j = 0; j < i * indentation; j++) string += " ";
		return string;
	}
	
	/**
	 * Returns space around colon (:).
	 * 
	 * @return space around colon
	 */
	private String getSpaceAroundColon()
	{
		String string = "";
		for(int j = 0; j < spaceAroundColon; j++) string += " ";
		return string;
	}
}
