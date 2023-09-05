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
	
	public <T> JSONArray(T[] array)
	{
		this.array = new Converter().toArrayList(array);
	}
	
	public <T> JSONArray(List<T> list)
	{
		this.array = new Converter().toArrayList(list);
	}
	
	public Object[] toArray()
	{
		return array.toArray();
	}
	
	public String[] toStringArray()
	{
		return new Converter().toStringArray(this);
	}
	
	public boolean[] toBooleanArray()
	{
		return new Converter().toBooleanArray(this);
	}
	
	public int[] toIntArray()
	{
		return new Converter().toIntArray(this);
	}
	
	public double[] toDoubleArray()
	{
		return new Converter().toDoubleArray(this);
	}
	
	public List<String> toStringList()
	{
		return new Converter().toStringList(this);
	}
	
	public List<Boolean> toBooleanList()
	{
		return new Converter().toBooleanList(this);
	}
	
	public List<Integer> toIntegerList()
	{
		return new Converter().toIntegerList(this);
	}
	
	public List<Double> toDoubleList()
	{
		return new Converter().toDoubleList(this);
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
	
	public int getInt(int index)
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
	
	public int getInt(JSONPointer pointer)
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
