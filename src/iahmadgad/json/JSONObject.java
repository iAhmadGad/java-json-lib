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
	
	public void add(JSONPointer pointer, Object object)
	{
		pointer.setObject(this);
		if(Validator.isValid(object)) getJSONArray(pointer).add(object);
	}
	
	public void set(JSONPointer pointer, int index, Object object)
	{
		pointer.setObject(this);
		if(Validator.isValid(object)) getJSONArray(pointer).set(index, object);
	}
	
	public void put(JSONPointer pointer, String key, Object value)
	{
		pointer.setObject(this);
		if(Validator.isValid(value)) getJSONObject(pointer).put(key, value);
	}
	
	public void replace(JSONPointer pointer, String key, Object value)
	{
		pointer.setObject(this);
		if(Validator.isValid(value)) getJSONObject(pointer).replace(key, value);
	}
	
	public Object get(JSONPointer pointer)
	{
		pointer.setObject(this);
		return pointer.getPointee();
	}
	
	public String getString(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (String) pointer.getPointee();
	}
	
	public boolean getBoolean(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (boolean) pointer.getPointee();
	}
	
	public int getInteger(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (int) pointer.getPointee();
	}
	
	public double getDouble(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (double) pointer.getPointee();
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
