package dev.iahmadgad.json;

/*
 * Java JSON Handler
 */

import java.util.ArrayList;

/**
 * JSONPointer Class.
 * <p>
 * JSONPointer is a pointer that points to some value in JSONObject or JSONArray.
 * 
 * @author iAhmadGad
 * @version 0.3
 * @since 0.3
*/

public class JSONPointer 
{
	/**
	 * The main JSONObject of this JSONPointer.
	 * <p>
	 * it equals null if JSONPointer's node is JSONArray.
	 */
	private static JSONArray array;
	
	/**
	 * The main JSONArray of this JSONPointer.
	 * <p>
	 * it equals null if JSONPointer's node is JSONObject.
	 */
	private static JSONObject object;
	
	/**
	 * The path of this JSONPointer.
	 * <p>
	 * JSONPointer uses this path to find its pointee.
	 */
	private static String path;
	
	/**
	 * The value which this pointer points to.
	 * <p>
	 * it can be any valid value that could be stored in JSONObjects & JSONArrays.
	 */
	private static Object pointee;
	
	/**
	 * The main Constructor that assigns JSONPointer's path and node.
	 * @param node
	 * @param path
	 */
	public JSONPointer(JSONArray node, String path)
	{
		array = node;
		object = null;
		JSONPointer.path = path;
	}
	
	/**
	 * The main Constructor that assigns JSONPointer's path and node.
	 * @param node
	 * @param path
	 */
	public JSONPointer(JSONObject node, String path)
	{
		object = node;
		array = null;
		JSONPointer.path = path;
	}
	
	/**
	 * The main Constructor that assigns JSONPointer's path.
	 * @param path
	 */
	public JSONPointer(String path)
	{
		JSONPointer.path = path;
	}
	
	/**
	 * Sets the main node (JSONObject) of the JSONPointer.
	 * 
	 * @param node
	 */
	protected void setNode(JSONArray node)
	{
		array = node;
		object = null;
	}
	
	/**
	 * Sets the main node (JSONArray) of the JSONPointer.
	 * 
	 * @param node
	 */
	protected void setNode(JSONObject node)
	{
		object = node;
		array = null;
	}
	
	/**
	 * Gets the pointee (object) stored in this JSONPointer.
	 * 
	 * @return
	 */
	protected Object getPointee()
	{
		findPointee();
		return pointee;
	}
	
	/**
	 * Finds the pointee (object) stored in this JSONPointer.
	 */
	private static void findPointee()
	{
		String[] locations = pathLocations();
		Object previous = null;
		Object current = (object == null) ? array : object;
		for(int i = 0; i < locations.length; i++)
		{
			previous = current;
			if(Validator.isJSONArray(previous))
			{
				current = ((JSONArray) previous).get(Integer.parseInt(locations[i]));
			}
			else if(Validator.isJSONObject(previous))
			{
				current = ((JSONObject) previous).get(locations[i]);
			}
		}
		pointee = current;
	}
	
	/**
	 * Parses the given path & splits it to an array of Strings presenting path locations.
	 * 
	 * @return an array of Strings presenting path locations
	 */
	private static String[] pathLocations()
	{
		path = (object.getId() != null && path.contains("#")) ? path.substring(object.getId().length() + 2) : path;
		String[] array = (path.contains(".")) ? path.split("[.\\[\\]]") : path.split("[/\\[\\]]");
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] != "") list.add(array[i]);
		}
		return list.toArray(new String[0]);
	}
}
