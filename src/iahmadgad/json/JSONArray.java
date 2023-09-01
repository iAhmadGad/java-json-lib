package iahmadgad.json;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONArray 
{
	private ArrayList<Object> Array;
	
	public JSONArray()
	{
		Array = new ArrayList<Object>();
	}
	
	public ArrayList<Object> getNode()
	{
		return Array;
	}
	
	public void add(Object object)
	{
		if(Validator.isValid(object)) Array.add(object);
	}
	
	public void set(int index, Object object)
	{
		if(Validator.isValid(object)) Array.set(index, object);
	}
	
	public Object get(int index)
	{
		return Array.get(index);
	}
	
	public String getString(int index)
	{
		return (String) Array.get(index);
	}
	
	public boolean getBoolean(int index)
	{
		return (boolean) Array.get(index);
	}
	
	public int getInteger(int index)
	{
		return (int) Array.get(index);
	}
	
	public double getDouble(int index)
	{
		return (double) Array.get(index);
	}
	
	public HashMap<String, Object> getJSONObject(int index)
	{
		return (HashMap<String, Object>) Array.get(index);
	}
	
	public ArrayList<Object> getJSONArray(int index)
	{
		return (ArrayList<Object>) Array.get(index);
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
