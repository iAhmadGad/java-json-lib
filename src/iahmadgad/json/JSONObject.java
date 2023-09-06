package iahmadgad.json;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class JSONObject 
{
	private HashMap<String, Object> node;
	private String id = null;
	
	
	public JSONObject()
	{
		node = new HashMap<String, Object>();
	}
	
	public JSONObject(String text)
	{
		node = new JSONParser(text).getHashMap();
	}
	
	public JSONObject(File file)
	{
		node = new JSONParser(file).getHashMap();
	}
	
	public <T> JSONObject(T object)
	{
		node = new Converter().toJSONObject(object.getClass(), object).getNode();
	}
	
	public <T> T toClass(Class<T> c)
	{
		return new Converter().toClass(this, c);
	}
	
	public HashMap<String, Object> getNode()
	{
		return node;
	}
	
	public String getId()
	{
		return id;
	}
	
	public Set<Entry<String, Object>> entrySet()
	{
		return node.entrySet();
	}
	
	public void put(String key, Object value)
	{
		if(key.compareTo("$id") == 0 && Validator.isString(value)) id = (String) value;
		if(Validator.isValid(value)) node.put(key, value);
	}
	
	public void replace(String key, Object value)
	{
		if(Validator.isValid(value)) node.replace(key, value);
	}
	
	public Object get(String key)
	{
		return node.get(key);
	}
	
	public String getString(String key)
	{
		return (String) node.get(key);
	}
	
	public boolean getBoolean(String key)
	{
		return (boolean) node.get(key);
	}
	
	public int getInt(String key)
	{
		return (int) node.get(key);
	}
	
	public double getDouble(String key)
	{
		return (double) node.get(key);
	}
	
	public JSONObject getJSONObject(String key)
	{
		return (JSONObject) node.get(key);
	}
	
	public JSONArray getJSONArray(String key)
	{
		return (JSONArray) node.get(key);
	}
	
	public Object get(JSONPointer pointer)
	{
		pointer.setObject(this);
		return pointer.getPointee();
	}
	
	public String getString(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (String) pointer.getPointee();
	}
	
	public boolean getBoolean(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (boolean) pointer.getPointee();
	}
	
	public int getInt(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (int) pointer.getPointee();
	}
	
	public double getDouble(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (double) pointer.getPointee();
	}
	
	public JSONObject getJSONObject(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (JSONObject) pointer.getPointee();
	}
	
	public JSONArray getJSONArray(JSONPointer pointer)
	{
		pointer.setObject(this);
		return (JSONArray) pointer.getPointee();
	}
}
