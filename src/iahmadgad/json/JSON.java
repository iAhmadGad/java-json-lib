package iahmadgad.json;

import java.io.File;
import java.util.HashMap;

/**
 * JSON Class
 * @author iAhmadGad
 * @version 0.2
 *
 */

public class JSON 
{
	public JSON()
	{
		JSONTree = new HashMap<String, Object>();
	}
	
	public JSON(String string)
	{
		new JSONParse(string);
		JSONTree = JSONParse.getJSONTree();
	}
	
	public JSON(File file)
	{
		new JSONParse(file);
		JSONTree = JSONParse.getJSONTree();
	}
	
	private HashMap<String, Object> JSONTree;
	
	public HashMap<String, Object> getJSONTree()
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
	
	public void replace(String key, Object value)
	{
		this.JSONTree.replace(key, value);
	}
	
	public void replaceString(String key, String value)
	{
		this.JSONTree.replace(key, value);
	}
	
	public void replaceInteger(String key, int value)
	{
		this.JSONTree.replace(key, value);
	}
	
	public void replaceDouble(String key, double value)
	{
		this.JSONTree.replace(key, value);
	}
	
	public void replaceBoolean(String key, boolean value)
	{
		this.JSONTree.replace(key, value);
	}
}
