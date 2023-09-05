package iahmadgad.json;

import java.lang.reflect.Field;

public class Validator 
{
	protected Validator()
	{
		
	}
	
	protected static Object getFieldValue(Field field, Object fieldValue)
	{
		if(field.getType().toString().compareTo("class [Ljava.lang.Object;") == 0) return new ArrayExtractor((JSONArray) fieldValue).extractArray();
		else if(field.getType().toString().compareTo("class [Ljava.lang.String;") == 0) return new ArrayExtractor((JSONArray) fieldValue).extractStringArray();
		else if(field.getType().toString().compareTo("class [Z") == 0) return new ArrayExtractor((JSONArray) fieldValue).extractBooleanArray();
		else if(field.getType().toString().compareTo("class [D") == 0) return new ArrayExtractor((JSONArray) fieldValue).extractIntArray();
		else if(field.getType().toString().compareTo("class [D") == 0) return new ArrayExtractor((JSONArray) fieldValue).extractDoubleArray();
		else if(field.getType().toString().compareTo("interface java.util.List") == 0)
		{
			if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Object>") == 0) return new ListExtractor((JSONArray) fieldValue).extractList();
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.String>") == 0) return new ListExtractor((JSONArray) fieldValue).extractStringList();
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Integer>") == 0) return new ListExtractor((JSONArray) fieldValue).extractIntList();
		    else if(field.getGenericType().toString().compareTo("java.util.List<java.lang.Double>") == 0) return new ListExtractor((JSONArray) fieldValue).extractDoubleList();
		}
		return fieldValue;
	}
	
	protected static String getType(Object object)
	{
		if(isString(object)) return "String";
		else if(isBoolean(object)) return "boolean";
		else if(isDouble(object)) return "Double";
		else if(isInteger(object)) return "Integer";
		return null;
	}
	
	protected static boolean isValid(Object object)
	{
		if(isJSONObject(object) || isJSONArray(object) || isString(object) || isInteger(object) || isDouble(object) || isBoolean(object)) return true;
		return false;
	}
	
	protected static boolean isJSONObject(Object object)
	{
		if(object.getClass().toString().compareTo("class iahmadgad.json.JSONObject") == 0) return true;
		return false;
	}
	
	protected static boolean isJSONArray(Object object)
	{
		if(object.getClass().toString().compareTo("class iahmadgad.json.JSONArray") == 0) return true;
		return false;
	}
	
	protected static  boolean isString(Object object)
	{
		if(object.getClass().toString().compareTo("class java.lang.String") == 0) return true;
		return false;
	}
	
	protected static boolean isBoolean(Object object)
	{
		if(object.getClass().toString().compareTo("class java.lang.Boolean") == 0) return true;
		return false;
	}
	
	protected static boolean isInteger(Object object)
	{
		if(object.getClass().toString().compareTo("class java.lang.Integer") == 0) return true;
		return false;
	}
	
	protected static boolean isDouble(Object object)
	{
		if(object.getClass().toString().compareTo("class java.lang.Double") == 0) return true;
		return false;
	}
	
	protected static Object getVariable(String string)
	{
		if(presentsString(string)) return string.substring(1, string.length() - 1);
		else if(presentsBoolean(string) && string.compareTo("true") == 0) return true;
		else if(presentsBoolean(string) && string.compareTo("false") == 0) return false;
		else if(presentsDouble(string)) return Double.parseDouble(string);
		else if(presentsInteger(string)) return Integer.parseInt(string);
		return null;
	}
	
	protected static String getPresentedType(String string)
	{
		if(presentsString(string)) return "String";
		else if(presentsBoolean(string)) return "boolean";
		else if(presentsDouble(string)) return "Double";
		else if(presentsInteger(string)) return "Integer";
		return null;
	}
	
	protected static boolean presentsString(String string)
	{
		if(!presentsBoolean(string) && !presentsInteger(string) && !presentsDouble(string)) return true;
		return false;
	}
	
	protected static boolean presentsBoolean(String string)
	{
		if(string.compareTo("true") == 0 || string.compareTo("false") == 0) return true;
		return false;
	}
	
	protected static boolean presentsDouble(String string)
	{
		int point = 0;
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i) == '.' && point == 0) point = 1;
			else if((!Character.isDigit(string.charAt(i)) && string.charAt(i) != '.') || string.charAt(i) == '.' && point == 1) return false;
		}
		if(point == 0) return false;
		return true;
	}
	
	protected static boolean presentsInteger(String string)
	{
		for(int i = 0; i < string.length(); i++)
		{
			if(!Character.isDigit(string.charAt(i))) return false;
		}
		return true;
	}
}
