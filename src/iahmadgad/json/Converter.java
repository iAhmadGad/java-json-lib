package iahmadgad.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaExtractor 
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
		if(field.getType().toString().compareTo("class [Ljava.lang.Object;") == 0) return extractArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [Ljava.lang.String;") == 0) return extractStringArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [Z") == 0) return extractBooleanArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [D") == 0) return extractIntArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [D") == 0) return extractDoubleArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("interface java.util.List") == 0)
		{
			if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Object>") == 0) return extractList((JSONArray) fieldValue);
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.String>") == 0) return extractStringList((JSONArray) fieldValue);
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Integer>") == 0) return extractIntList((JSONArray) fieldValue);
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Double>") == 0) return extractDoubleList((JSONArray) fieldValue);
		}
		return fieldValue;
	}
	
	protected Object[] extractArray(JSONArray array)
	{
		return array.toArray();
	}
	
	protected String[] extractStringArray(JSONArray array)
	{
		return array.toArray(new String[0]);
	}
	
	protected boolean[] extractBooleanArray(JSONArray array)
	{
		Boolean temp1[] = array.toArray(new Boolean[0]);
		boolean temp2[] = new boolean[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	protected double[] extractDoubleArray(JSONArray array)
	{
		Double temp1[] = array.toArray(new Double[0]);
		double temp2[] = new double[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	protected int[] extractIntArray(JSONArray array)
	{
		Integer temp1[] = array.toArray(new Integer[0]);
		int temp2[] = new int[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	protected List<Object> extractList(JSONArray array)
	{
		return array.getNode();
	}
	
	protected List<String> extractStringList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<String> list = arrayList.stream().map(object -> (String) object).collect(Collectors.toList());
		return list;
	}
	
	protected List<Boolean> extractBooleanList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Boolean> list = arrayList.stream().map(object -> (Boolean) object).collect(Collectors.toList());
		return list;
	}
	
	protected List<Double> extractDoubleList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Double> list = arrayList.stream().map(object -> (Double) object).collect(Collectors.toList());
		return list;
	}
	
	protected List<Integer> extractIntList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Integer> list = arrayList.stream().map(object -> (Integer) object).collect(Collectors.toList());
		return list;
	}
}