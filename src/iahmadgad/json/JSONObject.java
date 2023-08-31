package iahmadgad.json;

import java.util.HashMap;

public class JSONObject 
{
	private static HashMap<String, Object> Object;
	
	public JSONObject()
	{
		Object = new HashMap<String, Object>();
	}
	
	public HashMap<String, Object> get()
	{
		return Object;
	}
	
	public void put(String string, Object object)
	{
		Object.put(string, object);
	}
}
