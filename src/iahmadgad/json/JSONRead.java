package iahmadgad.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONRead 
{
	
	private static String source;
	
	public static String getSource() {
		return source;
	}

	public static void setSource(String source) {
		JSONRead.source = source;
	}
	
	public JSONRead(JSON json)
	{
		setSource(json.getSource());
		parse();
	}
	
	private static HashMap<String, Object> keysAndValues;
	
	public static void parse()
	{
		String array[] = getSource().split("[,{}]");
		ArrayList<String> pairs = new ArrayList<String>();
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] != "") pairs.add(array[i]);
		}
		keysAndValues = new HashMap<String, Object>();
		for(int i = 0; i < pairs.size(); i++)
		{
			String key = pairs.get(i).split(":")[0], value = pairs.get(i).split(":")[1];
			key = key.substring(1, key.length() - 1);
			if(value.contains("\"")) keysAndValues.put(key, value.substring(1, value.length() - 1));
			else if(value.compareTo("true") == 0) keysAndValues.put(key, true);
			else if(value.compareTo("false") == 0) keysAndValues.put(key, false);
			else if(value.contains(".")) keysAndValues.put(key, Double.parseDouble(value));
			else keysAndValues.put(key, Integer.parseInt(value));
		}
	}
	
	public Object get(String key)
	{
		
		return keysAndValues.get(key);
	}
	
	public String getString(String key)
	{
		
		return (String) keysAndValues.get(key);
	}
	
	public int getInteger(String key)
	{
		return (int) keysAndValues.get(key);
	}
	
	public double getDouble(String key)
	{
		return (double) keysAndValues.get(key);
	}
	
	public boolean getBoolean(String key)
	{
		return (boolean) keysAndValues.get(key);
	}
}
