package iahmadgad.json;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class JSONObject 
{
	private static HashMap<String, Object> object;
	
	public JSONObject()
	{
		object = new HashMap<String, Object>();
	}
	
	public JSONObject(String text)
	{
		object = new JSONParser(text).getHashMap();
	}
	
	public JSONObject(File file)
	{
		object = new JSONParser(file).getHashMap();
	}
	
	public <T> T toClass(Class<T> c)
	{
		return new JavaExtractor().extractClass(this, c);
	}
	
	public HashMap<String, Object> getNode()
	{
		return object;
	}
	
	public Set<Entry<String, Object>> entrySet()
	{
		return object.entrySet();
	}
	
	public void put(String key, Object value)
	{
		if(Validator.isValid(value)) object.put(key, value);
	}
	
	public void replace(String key, Object value)
	{
		if(Validator.isValid(value)) object.replace(key, value);
	}
	
	public Object get(String key)
	{
		return object.get(key);
	}
	
	public String getString(String key)
	{
		return (String) object.get(key);
	}
	
	public boolean getBoolean(String key)
	{
		return (boolean) object.get(key);
	}
	
	public int getInteger(String key)
	{
		return (int) object.get(key);
	}
	
	public double getDouble(String key)
	{
		return (double) object.get(key);
	}
	
	public JSONObject getJSONObject(String key)
	{
		return (JSONObject) object.get(key);
	}
	
	public JSONArray getJSONArray(String key)
	{
		return (JSONArray) object.get(key);
	}
	
	public <T> T toClass(String path, Class<T> c)
	{
		return new JavaExtractor().extractClass(getJSONObject(new JSONPointer(path)), c);
	}
	
	public JSONObject getJSONObject(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (JSONObject) pointer.getPointee();
	}
	
	public JSONArray getJSONArray(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (JSONArray) pointer.getPointee();
	}
}
