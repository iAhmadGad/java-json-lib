package iahmadgad.json;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	
	public Object[] toArray()
	{
		return array.toArray();
	}
	
	public String[] toStringArray()
	{
		return new JavaExtractor().extractStringArray(this);
	}
	
	public boolean[] toBooleanArray()
	{
		return new JavaExtractor().extractBooleanArray(this);
	}
	
	public int[] toIntArray()
	{
		return new JavaExtractor().extractIntArray(this);
	}
	
	public double[] toDoubleArray()
	{
		return new JavaExtractor().extractDoubleArray(this);
	}
	
	public List<String> toStringList()
	{
		return new JavaExtractor().extractStringList(this);
	}
	
	public List<Boolean> toBooleanList()
	{
		return new JavaExtractor().extractBooleanList(this);
	}
	
	public List<Integer> toIntegerList()
	{
		return new JavaExtractor().extractIntegerList(this);
	}
	
	public List<Double> toDoubleList()
	{
		return new JavaExtractor().extractDoubleList(this);
	}
	
	public ArrayList<Object> getNode()
	{
		return array;
	}
	
	public int size()
	{
		return array.size();
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
