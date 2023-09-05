package iahmadgad.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;

public class JSONStringBuilder 
{
	private static JSONObject object;
	private static String JSONString = "";
	private static byte counter = 0;
	
	public JSONStringBuilder(JSONObject object)
	{
		JSONStringBuilder.object = object;
	}
	
	public String getJSONString()
	{
		if(JSONString == "") build(object, 0);
		return JSONString;
	}
	
	private void build(JSONObject object, int i)
	{
		JSONString += Settings.getIndentation(i) + "{\n";
		counter = 0;
		for(Entry entry: object.entrySet())
		{
			JSONString += Settings.getIndentation(i + 1) + (counter != 0 ? ',' : "") + "\"" + entry.getKey() + "\"" + Settings.getSpaceAroundColon() + ':' + Settings.getSpaceAroundColon();
			if(Validator.isJSONArray(entry.getValue()))
			{
				build((JSONArray) entry.getValue(), i + 1);
				JSONString += "\n";
				counter++;
			}
			else if(Validator.isJSONObject(entry.getValue()))
			{
				build((JSONObject) entry.getValue(), i + 1);
				JSONString += "\n";
				counter++;
			}
			else
			{
				JSONString += (Validator.isString(entry.getValue()) ? "\"" + entry.getValue() + "\"" : entry.getValue()) + "\n";
				counter++;
			}
		}
		JSONString += Settings.getIndentation(i) + "}\n";
	}
	
	private void build(JSONArray array, int i)
	{
		JSONString += Settings.getIndentation(i);
		JSONString += "[\n";
		for(int j = 0; j < array.size(); j++)
		{
			if(Validator.isJSONArray(array.get(j)))
			{
				build((JSONArray) array.get(j), i + 1);
				JSONString += "\n";
			}
			else if(Validator.isJSONObject(array.get(j)))
			{
				build((JSONObject) array.get(j), i + 1);
				JSONString += "\n";
			}
			else 
			{
				JSONString += Settings.getIndentation(i + 1) + (array.size() - j != array.size() ? "," : "") + (Validator.isString(array.get(j)) ? "\"" + array.get(j) + "\"" : array.get(j)) + "\n";
			}
		}
		JSONString += Settings.getIndentation(i) + "]\n";
	}
	
	public void write(File file)
	{
		try 
		{
			FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);
			writer.write(getJSONString());
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public class Settings
	{
		private static int indentation = 5;
		private static int spaceAroundColon = 0;
		
		public static void setIndentation(int i)
		{
			indentation = i;
		}
		
		public static void setIndentation(SBEnum e)
		{
			if (e == SBEnum.DEFAULT) indentation = 5;
		}
		
		public static String getIndentation(int i)
		{
			String string = "";
			for(int j = 0; j < i * indentation; j++) string += " ";
			return string;
		}
		
		public static void setSpaceAroundColon(int i)
		{
			spaceAroundColon = i;
		}
		
		public static void setSpaceAroundColon(SBEnum e)
		{
			if (e == SBEnum.DEFAULT) spaceAroundColon = 0;
		}
		
		public static String getSpaceAroundColon()
		{
			String string = "";
			for(int j = 0; j < spaceAroundColon; j++) string += " ";
			return string;
		}
		
		public void setDefault()
		{
			indentation = 3;
			spaceAroundColon = 0;
		}
	}
	
	public enum SBEnum 
	{
		DEFAULT
	}
}
