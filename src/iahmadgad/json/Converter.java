package iahmadgad.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converter 
{
	protected <T> T toClass(JSONObject object, Class<T> c)
	{
		T type = null;
		try 
		{
			type = c.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e1) 
		{
			e1.printStackTrace();
		}
		Field fields[] = type.getClass().getDeclaredFields();
		for(Field field : fields)
		{
			Object current = object.get(field.getName());
			field.setAccessible(true);
			try 
			{
				field.set(type, getFieldValue(field, current));
			} 
			catch (IllegalArgumentException | IllegalAccessException e) 
			{
				e.printStackTrace();
			}
		}
		return type;
	}
	
	private Object getFieldValue(Field field, Object fieldValue)
	{
		if(field.getType().toString().compareTo("class [Ljava.lang.Object;") == 0) return toArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [Ljava.lang.String;") == 0) return toStringArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [Z") == 0) return toBooleanArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [D") == 0) return toIntArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("class [D") == 0) return toDoubleArray((JSONArray) fieldValue);
		else if(field.getType().toString().compareTo("interface java.util.List") == 0)
		{
			if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Object>") == 0) return toList((JSONArray) fieldValue);
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.String>") == 0) return toStringList((JSONArray) fieldValue);
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Integer>") == 0) return toIntegerList((JSONArray) fieldValue);
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Double>") == 0) return toDoubleList((JSONArray) fieldValue);
		}
		return fieldValue;
	}
	
	protected <T> JSONObject toJSONObject(Class<T> c, T tobject)
	{
		JSONObject object = new JSONObject();
		Field fields[] = c.getClass().getDeclaredFields();
		for(Field field: fields)
		{
			try 
			{
				object.put(field.getName(), getPairValue(field, field.get(tobject)));
			} catch (IllegalArgumentException | IllegalAccessException e) 
			{
				e.printStackTrace();
			}
		}
		return object;
	}
	
	private Object getPairValue(Field field, Object fieldValue)
	{
		if(field.getType().toString().contains("[")) return toArrayList((Object[]) fieldValue);
		else if(field.getType().toString().compareTo("interface java.util.List") == 0) return toArrayList((List<Object>) fieldValue);
		else return fieldValue;
	}
	
	protected <T> JSONArray toJSONArray(T[] tarray)
	{
		JSONArray array = new JSONArray();
		array.setNode(toArrayList(tarray)); 
		return array;
	}
	
	protected <T> JSONArray toJSONArray(List<T> list)
	{
		JSONArray array = new JSONArray();
		array.setNode(toArrayList(list)); 
		return array;
	}
	
	protected Object[] toArray(JSONArray array)
	{
		return array.toArray();
	}
	
	protected String[] toStringArray(JSONArray array)
	{
		return array.toArray(new String[0]);
	}
	
	protected boolean[] toBooleanArray(JSONArray array)
	{
		Boolean temp1[] = array.toArray(new Boolean[0]);
		boolean temp2[] = new boolean[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	protected double[] toDoubleArray(JSONArray array)
	{
		Double temp1[] = array.toArray(new Double[0]);
		double temp2[] = new double[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	protected int[] toIntArray(JSONArray array)
	{
		Integer temp1[] = array.toArray(new Integer[0]);
		int temp2[] = new int[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	protected List<Object> toList(JSONArray array)
	{
		return array.getNode();
	}
	
	protected List<String> toStringList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<String> list = arrayList.stream().map(object -> (String) object).collect(Collectors.toList());
		return list;
	}
	
	protected List<Boolean> toBooleanList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Boolean> list = arrayList.stream().map(object -> (Boolean) object).collect(Collectors.toList());
		return list;
	}
	
	protected List<Double> toDoubleList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Double> list = arrayList.stream().map(object -> (Double) object).collect(Collectors.toList());
		return list;
	}
	
	protected List<Integer> toIntegerList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Integer> list = arrayList.stream().map(object -> (Integer) object).collect(Collectors.toList());
		return list;
	}
	
	protected <T> ArrayList<Object> toArrayList(T[] array)
	{
		return new ArrayList<>(Arrays.asList(array));
	}
	
	protected <T> ArrayList<Object> toArrayList(List<T> list)
	{
		return (ArrayList<Object>) list;
	}
}