package iahmadgad.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Extractor 
{
	protected <T> T extractClass(JSONObject object ,Class<T> c)
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
				fields[i].set(type, getFieldValue(fields[i], current));
			} 
			catch (IllegalArgumentException | IllegalAccessException e) 
			{
				e.printStackTrace();
			}
		}
		return (T) type;
	}
	
	private Object getFieldValue(Field field, Object fieldValue)
	{
		Converter converter = new Converter();
		if(field.getType().toString().compareTo("class [Ljava.lang.Object;") == 0) return converter.toArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [Ljava.lang.String;") == 0) return converter.toStringArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [Z") == 0) return converter.toBooleanArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [D") == 0) return converter.toIntArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [D") == 0) return converter.toDoubleArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("interface java.util.List") == 0)
		{
			if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Object>") == 0) return converter.toList((JSONArray) fieldValue);
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.String>") == 0) return converter.toStringList((JSONArray) fieldValue);
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Integer>") == 0) return converter.toIntegerList((JSONArray) fieldValue);
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Double>") == 0) return converter.toDoubleList((JSONArray) fieldValue);
		}
		return fieldValue;
	}
}
