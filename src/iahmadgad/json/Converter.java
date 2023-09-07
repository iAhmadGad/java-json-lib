package iahmadgad.json;

/*
 * Java JSON Handler
 */

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Convertor Class.
 * 
 * @author iAhmadGad
 * @version 0.3
 * @since 0.3
*/

public class Converter 
{
	/**
	 * Converts JSONObject to Java object.
	 * 
	 * @param <T>
	 * @param object
	 * @param c
	 * @return converted Java object
	 */
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
	
	/**
	 * Gets specified field value in Java object.
	 * 
	 * @param field
	 * @param fieldValue
	 * @return field value
	 */
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
	
	/**
	 * Converts Java object to JSONObject.
	 * 
	 * @param <T>
	 * @param c
	 * @param tobject
	 * @return converted JSONObject
	 */
	protected <T> JSONObject toJSONObject(Class<?> c, T tobject)
	{
		JSONObject object = new JSONObject();
		Field fields[] = tobject.getClass().getDeclaredFields();
		for(Field field: fields)
		{
			field.setAccessible(true);
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
	
	/**
	 * Gets specified pair value in JSONObject.
	 * 
	 * @param field
	 * @param fieldValue
	 * @return pair value
	 */
	private Object getPairValue(Field field, Object fieldValue)
	{
		if(field.getType().toString().contains("[")) return toArrayList((Object[]) fieldValue);
		else if(field.getType().toString().compareTo("interface java.util.List") == 0) return toJSONArray((List<Object>) fieldValue);
		else return fieldValue;
	}
	
	/**
	 * Converts array to JSONArray.
	 * 
	 * @param <T>
	 * @param tarray
	 * @return converted JSONArray
	 */
	protected <T> JSONArray toJSONArray(T[] tarray)
	{
		JSONArray array = new JSONArray();
		ArrayList<Object> node = toArrayList(tarray);
		if(Validator.isValid(node)) array.setNode(node); 
		return array;
	}
	
	/**
	 * Converts list to JSONArray.
	 * 
	 * @param <T>
	 * @param tlist
	 * @return converted JSONArray
	 */
	protected <T> JSONArray toJSONArray(List<T> tlist)
	{
		JSONArray array = new JSONArray();
		ArrayList<Object> node = toArrayList(tlist);
		if(Validator.isValid(node)) array.setNode(node);
		return array;
	}
	
	/**
	 * Converts JSONArray to array.
	 * 
	 * @param array
	 * @return converted array
	 */
	protected Object[] toArray(JSONArray array)
	{
		return array.toArray();
	}
	
	/**
	 * Converts JSONArray to array of Strings
	 * 
	 * @param array
	 * @return converted array of Strings
	 */
	protected String[] toStringArray(JSONArray array)
	{
		return array.toArray(new String[0]);
	}
	
	/**
	 * Converts JSONArray to array of booleans.
	 * 
	 * @param array
	 * @return converted array of booleans
	 */
	protected boolean[] toBooleanArray(JSONArray array)
	{
		Boolean temp1[] = array.toArray(new Boolean[0]);
		boolean temp2[] = new boolean[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	/**
	 * Converts JSONArray to array of doubles.
	 * 
	 * @param array
	 * @return converted array of doubles
	 */
	protected double[] toDoubleArray(JSONArray array)
	{
		Double temp1[] = array.toArray(new Double[0]);
		double temp2[] = new double[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	/**
	 * Converts JSONArray to array of ints.
	 * 
	 * @param array
	 * @return converted array of ints
	 */
	protected int[] toIntArray(JSONArray array)
	{
		Integer temp1[] = array.toArray(new Integer[0]);
		int temp2[] = new int[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	/**
	 * Converts JSONArray to list
	 * 
	 * @param array
	 * @return converted list
	 */
	protected List<Object> toList(JSONArray array)
	{
		return array.getNode();
	}
	
	/**
	 * Converts JSONArray to list of Strings
	 * 
	 * @param array
	 * @return converted list of Strings
	 */
	protected List<String> toStringList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<String> list = arrayList.stream().map(object -> (String) object).collect(Collectors.toList());
		return list;
	}
	
	/**
	 * Converts JSONArray to list of Booleans
	 * 
	 * @param array
	 * @return converted list of Booleans
	 */
	protected List<Boolean> toBooleanList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Boolean> list = arrayList.stream().map(object -> (Boolean) object).collect(Collectors.toList());
		return list;
	}
	
	/**
	 * Converts JSONArray to list of Doubles.
	 * 
	 * @param array
	 * @return converted list of Doubles
	 */
	protected List<Double> toDoubleList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Double> list = arrayList.stream().map(object -> (Double) object).collect(Collectors.toList());
		return list;
	}
	
	/**
	 * Converts JSONArray to list of Integers.
	 * 
	 * @param array
	 * @return converted list of Integers
	 */
	protected List<Integer> toIntegerList(JSONArray array)
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Integer> list = arrayList.stream().map(object -> (Integer) object).collect(Collectors.toList());
		return list;
	}
	
	/**
	 * Converts array to ArrayList.
	 * 
	 * @param <T>
	 * @param array
	 * @return converted ArrayList
	 */
	protected <T> ArrayList<Object> toArrayList(T[] array)
	{
		return new ArrayList<>(Arrays.asList(array));
	}
	
	/**
	 * Converts list to ArrayList.
	 * 
	 * @param <T>
	 * @param list
	 * @return converted ArrayList
	 */
	protected <T> ArrayList<Object> toArrayList(List<T> list)
	{
		return (ArrayList<Object>) list;
	}
}