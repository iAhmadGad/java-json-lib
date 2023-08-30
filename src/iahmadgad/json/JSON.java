package iahmadgad.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class JSON 
{
	public JSON()
	{
		JSONTree = new HashMap<String, Object>();
	}
	
	public JSON(String string)
	{
		JSONTree = new JSONParse(string).getKeysAndValues();
	}
	
	public JSON(File file)
	{
		JSONTree = new JSONParse(file).getKeysAndValues();
	}
	
	private static HashMap<String, Object> JSONTree;
	
	public static HashMap<String, Object> getJSONTree()
	{
		return JSONTree;
	}
	
	public Object get(String key)
	{
		
		return JSONTree.get(key);
	}
	
	public String getString(String key)
	{
		
		return (String) JSONTree.get(key);
	}
	
	public int getInteger(String key)
	{
		return (int) JSONTree.get(key);
	}
	
	public double getDouble(String key)
	{
		return (double) JSONTree.get(key);
	}
	
	public boolean getBoolean(String key)
	{
		return (boolean) JSONTree.get(key);
	}
	
	public void put(String key, Object value)
	{
		this.JSONTree.put(key, value);
	}
	
	public void putString(String key, String value)
	{
		this.JSONTree.put(key, value);
	}
	
	public void putInteger(String key, int value)
	{
		this.JSONTree.put(key, value);
	}
	
	public void putDouble(String key, double value)
	{
		this.JSONTree.put(key, value);
	}
	
	public void putBoolean(String key, boolean value)
	{
		this.JSONTree.put(key, value);
	}
}
