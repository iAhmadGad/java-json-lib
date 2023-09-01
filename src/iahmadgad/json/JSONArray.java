package iahmadgad.json;

import java.util.ArrayList;

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
	
	public JSONObject getJSONObject(int index)
	{
		return (JSONObject) Array.get(index);
	}
	
	public JSONArray getJSONArray(int index)
	{
		return (JSONArray) Array.get(index);
	}
	
	public void add(JSONPointer pointer, Object object)
	{
		pointer.setArray(this);
		if(Validator.isValid(object)) getJSONArray(pointer).add(object);
	}
	
	public void set(JSONPointer pointer, int index, Object object)
	{
		pointer.setArray(this);
		if(Validator.isValid(object)) getJSONArray(pointer).set(index, object);
	}
	
	public void put(JSONPointer pointer, String key, Object value)
	{
		pointer.setArray(this);
		if(Validator.isValid(value)) getJSONObject(pointer).put(key, value);
	}
	
	public void replace(JSONPointer pointer, String key, Object value)
	{
		pointer.setArray(this);
		if(Validator.isValid(value)) getJSONObject(pointer).replace(key, value);
	}
	
	public Object get(JSONPointer pointer)
	{
		pointer.setArray(this);
		return pointer.getPointee();
	}
	
	public String getString(JSONPointer pointer)
	{
		pointer.setArray(this);
		return (String) pointer.getPointee();
	}
	
	public boolean getBoolean(JSONPointer pointer)
	{
		pointer.setArray(this);
		return (boolean) pointer.getPointee();
	}
	
	public int getInteger(JSONPointer pointer)
	{
		pointer.setArray(this);
		return (int) pointer.getPointee();
	}
	
	public double getDouble(JSONPointer pointer)
	{
		pointer.setArray(this);
		return (double) pointer.getPointee();
	}
	
	public JSONObject getJSONObject(JSONPointer pointer)
	{
		pointer.setArray(this);
		return (JSONObject) pointer.getPointee();
	}
	
	public JSONArray getJSONArray(JSONPointer pointer)
	{
		pointer.setArray(this);
		return (JSONArray ) pointer.getPointee();
	}
}
