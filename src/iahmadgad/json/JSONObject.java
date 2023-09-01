package iahmadgad.json;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONObject 
{
	private static HashMap<String, Object> Object;
	
	public JSONObject()
	{
		Object = new HashMap<String, Object>();
	}
	
	public HashMap<String, Object> getNode()
	{
		return Object;
	}
	
	public void put(String key, Object value)
	{
		if(Validator.isValid(value)) Object.put(key, value);
	}
	
	public void replace(String key, Object value)
	{
		if(Validator.isValid(value)) Object.replace(key, value);
	}
	
	public Object get(String key)
	{
		return Object.get(key);
	}
	
	public String getString(String key)
	{
		return (String) Object.get(key);
	}
	
	public boolean getBoolean(String key)
	{
		return (boolean) Object.get(key);
	}
	
	public int getInteger(String key)
	{
		return (int) Object.get(key);
	}
	
	public double getDouble(String key)
	{
		return (double) Object.get(key);
	}
	
	public HashMap<String, Object> getJSONObject(String key)
	{
		return (HashMap<String, Object>) Object.get(key);
	}
	
	public ArrayList<Object> getJSONArray(String key)
	{
		return (ArrayList<Object>) Object.get(key);
	}
	
	public void add(JSONPointer pointer, Object object)
	{
		if(Validator.isValid(object)) getJSONArray(pointer).add(object);
	}
	
	public void set(JSONPointer pointer, int index, Object object)
	{
		if(Validator.isValid(object)) getJSONArray(pointer).set(index, object);
	}
	
	public void put(JSONPointer pointer, String key, Object value)
	{
		if(Validator.isValid(value)) getJSONObject(pointer).put(key, value);
	}
	
	public void replace(JSONPointer pointer, String key, Object value)
	{
		if(Validator.isValid(value)) getJSONObject(pointer).replace(key, value);
	}
	
	public Object get(JSONPointer pointer)
	{
		return pointer.getPointee();
	}
	
	public String getString(JSONPointer pointer)
	{
		return (String) pointer.getPointee();
	}
	
	public boolean getBoolean(JSONPointer pointer)
	{
		return (boolean) pointer.getPointee();
	}
	
	public int getInteger(JSONPointer pointer)
	{
		return (int) pointer.getPointee();
	}
	
	public double getDouble(JSONPointer pointer)
	{
		return (double) pointer.getPointee();
	}
	
	public HashMap<String, Object> getJSONObject(JSONPointer pointer)
	{
		return (HashMap<String, Object>) pointer.getPointee();
	}
	
	public ArrayList<Object> getJSONArray(JSONPointer pointer)
	{
		return (ArrayList<Object>) pointer.getPointee();
	}
}
