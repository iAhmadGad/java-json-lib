package iahmadgad.json;

import java.util.ArrayList;

public class JSONPointer 
{
	private static JSONArray array;
	private static JSONObject object;
	private static String path;
	private static Object pointee;
	
	public JSONPointer(JSONArray array, String path)
	{
		JSONPointer.array = array;
		JSONPointer.object = null;
		JSONPointer.path = path;
	}
	
	public JSONPointer(JSONObject object, String path)
	{
		JSONPointer.object = object;
		JSONPointer.array = null;
		JSONPointer.path = path;
	}
	
	public JSONPointer(String path)
	{
		JSONPointer.path = path;
	}
	
	public void setArray(JSONArray array)
	{
		JSONPointer.array = array;
		object = null;
	}
	
	public void setObject(JSONObject object)
	{
		JSONPointer.object = object;
		array = null;
	}
	
	public Object getPointee()
	{
		findPointee();
		return pointee;
	}
	
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
	
	private static String[] pathLocations()
	{
		String[] array = (path.contains(".")) ? path.split("[.\\[\\]]") : path.split("[/\\[\\]]");
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] != "") list.add(array[i]);
		}
		return list.toArray(new String[0]);
	}
}
