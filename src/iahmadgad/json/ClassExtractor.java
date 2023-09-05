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
			fields[i].setAccessible(true);
			try 
			{
				fields[i].set(type, Validator.getFieldValue(fields[i], current));
			} 
			catch (IllegalArgumentException | IllegalAccessException e) 
			{
				e.printStackTrace();
			}
		}
		return (T) type;
	}
}
