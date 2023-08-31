package iahmadgad.json;

import java.util.ArrayList;

public class JSONArray 
{
	private ArrayList<Object> Array;
	
	public JSONArray()
	{
		Array = new ArrayList<Object>();
	}
	
	public ArrayList<Object> get()
	{
		return Array;
	}
	
	public void put(Object object)
	{
		Array.add(object);
	}
}
