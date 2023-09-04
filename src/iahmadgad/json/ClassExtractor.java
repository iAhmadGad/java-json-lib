package iahmadgad.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.io.ObjectOutputStream;

public class ClassExtractor 
{
	private static JSONObject object;
	
	ClassExtractor(JSONObject object)
	{
		ClassExtractor.object = object;
		
	}
	
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T extractClass(Class<T> c)
	{
		Object type = null;
		try 
		{
			type = c.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e1) 
		{
			e1.printStackTrace();
		}
		Field fields[] = type.getClass().getDeclaredFields();
		for(int i = 0; i < fields.length; i++)
		{
			Object current = object.get(fields[i].getName());
			String fieldProperties[] = fields[i].toString().split("\s");
			fields[i].setAccessible(true);
			try 
			{
				if(fieldProperties[0].compareTo("java.lang.Object[]") == 0)
				{
					fields[i].set(type, new ArrayExtractor((JSONArray) current).extractArray());
				}
				else if(fieldProperties[0].compareTo("java.lang.String[]") == 0)
				{
					fields[i].set(type, new ArrayExtractor((JSONArray) current).extractStringArray());
				}
				else if(fieldProperties[0].compareTo("boolean[]") == 0)
				{
					fields[i].set(type, new ArrayExtractor((JSONArray) current).extractBooleanArray());
				}
				else if(fieldProperties[0].compareTo("double[]") == 0)
				{
					fields[i].set(type, new ArrayExtractor((JSONArray) current).extractDoubleArray());
				}
				else if(fieldProperties[0].compareTo("int[]") == 0)
				{
					fields[i].set(type, new ArrayExtractor((JSONArray) current).extractIntArray());
				}
				else fields[i].set(type, current);
			} 
			catch (IllegalArgumentException | IllegalAccessException e) 
			{
				e.printStackTrace();
			}
		}
		return (T) type;
	}
}
