package iahmadgad.json;

import java.io.File;
import java.util.ArrayList;

public class JSONArray 
{
	private ArrayList<Object> array;
	
	public JSONArray()
	{
		array = new ArrayList<Object>();
	}
	
	public JSONArray(String text)
	{
		array = new JSONParser(text).getArrayList();
	}
	
	public ArrayList<Object> getNode()
	{
		return array;
	}
	
	public int size()
	{
		return array.size();
	}
	
	public Object[] toArray()
	{
		return array.toArray();
	}
	
	public <T> T[] toArray(T[] a)
	{
		return array.toArray(a);
	}
	
	public void add(Object object)
	{
		if(Validator.isValid(object)) array.add(object);
	}
	
	public void set(int index, Object object)
	{
		if(Validator.isValid(object)) array.set(index, object);
	}
	
	public Object get(int index)
	{
		return array.get(index);
	}
	
	public String getString(int index)
	{
		return (String) array.get(index);
	}
	
	public boolean getBoolean(int index)
	{
		return (boolean) array.get(index);
	}
	
	public int getInteger(int index)
	{
		return (int) array.get(index);
	}
	
	public double getDouble(int index)
	{
		return (double) array.get(index);
	}
	
	public JSONObject getJSONObject(int index)
	{
		return (JSONObject) array.get(index);
	}
	
	public JSONArray getJSONArray(int index)
	{
		return (JSONArray) array.get(index);
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
