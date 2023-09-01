package iahmadgad.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

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
		findPointee();
	}
	
	public JSONPointer(JSONObject object, String path)
	{
		JSONPointer.object = object;
		JSONPointer.array = null;
		JSONPointer.path = path;
		findPointee();
	}
	
	public Object getPointee()
	{
		return pointee;
	}
	
	private static void findPointee()
	{
		String[] locations = pathLocations();
		Object previous;
		Object current = (object == null) ? array : object;
		for(int i = 0; i < locations.length - 1; i++)
		{
			previous = current;
			if(locations[i].contains("["))
			{
				current = ((JSONArray) previous).get(Validator.getJSONArrayIndex(locations[i]));
			}
			else
			{
				current = ((JSONObject) previous).get(locations[i]);
			}
		}
		pointee = current;
	}
	
	private static String[] pathLocations()
	{
		return path.split("[.]");
	}
}
