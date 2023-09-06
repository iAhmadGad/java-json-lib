package iahmadgad.json;

import java.util.ArrayList;
import java.util.List;

public class JSONArray 
{
	private ArrayList<Object> node;
	
	public JSONArray()
	{
		node = new ArrayList<Object>();
	}
	
	public JSONArray(String text)
	{
		node = new JSONParser(text).getArrayList();
	}
	
	public <T> JSONArray(T[] array)
	{
		node = new Converter().toJSONArray(array).getNode();
	}
	
	public <T> JSONArray(List<T> list)
	{
		node = new Converter().toJSONArray(list).getNode();
	}
	
	public Object[] toArray()
	{
		return node.toArray();
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
		return node;
	}
	
	protected void setNode(ArrayList<Object> array)
	{
		this.node = array; 
	}
	
	public int size()
	{
		return node.size();
	}
	
	public <T> T[] toArray(T[] a)
	{
		return node.toArray(a);
	}
	
	public void add(Object object)
	{
		if(Validator.isValid(object)) node.add(object);
	}
	
	public void set(int index, Object object)
	{
		if(Validator.isValid(object)) node.set(index, object);
	}
	
	public Object get(int index)
	{
		return node.get(index);
	}
	
	public String getString(int index)
	{
		return (String) node.get(index);
	}
	
	public boolean getBoolean(int index)
	{
		return (boolean) node.get(index);
	}
	
	public int getInt(int index)
	{
		return (int) node.get(index);
	}
	
	public double getDouble(int index)
	{
		return (double) node.get(index);
	}
	
	public JSONObject getJSONObject(int index)
	{
		return (JSONObject) node.get(index);
	}
	
	public JSONArray getJSONArray(int index)
	{
		return (JSONArray) node.get(index);
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
