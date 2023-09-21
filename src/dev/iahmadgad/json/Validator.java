package dev.iahmadgad.json;

/*
 * Java JSON Handler
 */

import java.util.ArrayList;

/**
 * Validator Class.
 * 
 * @author iAhmadGad
 * @version 0.3
 * @since 0.3
*/

public class Validator 
{
	/**
	 * Gets type of the specified object.
	 * @param object
	 * @return type of of the specified object
	 */
	protected static String getType(Object object)
	{
		if(isString(object)) return "String";
		else if(isBoolean(object)) return "boolean";
		else if(isDouble(object)) return "Double";
		else if(isInteger(object)) return "Integer";
		else if(isJSONObject(object)) return "JSONObject";
		else if(isJSONArray(object)) return "JSONArray";
		return null;
	}
	
	/**
	 * Checks if ArrayList is valid to be converted to JSONArray.
	 * 
	 * @param list
	 * @return true if it's valid & false if it's not
	 */
	protected static boolean isValid(ArrayList<Object> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(!isValid(list.get(i))) return false;
		}
		return true;
	}
	
	/**
	 * Checks if object is valid to be put into JSONObject or added to JSONArray.
	 * 
	 * @param object
	 * @return true if it's valid & false if it's not
	 */
	protected static boolean isValid(Object object)
	{
		if(isJSONObject(object) || isJSONArray(object) || isString(object) || isInteger(object) || isDouble(object) || isBoolean(object)) return true;
		return false;
	}
	
	/**
	 * Checks if object is JSONObject.
	 * 
	 * @param object
	 * @return true if it's a JSONObject & false if it's not
	 */
	protected static boolean isJSONObject(Object object)
	{
		if(object.getClass().toString().compareTo("class dev.iahmadgad.json.JSONObject") == 0) return true;
		return false;
	}
	
	/**
	 * Checks if object is JSONArray.
	 * 
	 * @param object
	 * @return true if it's a JSONArray & false if it's not
	 */
	protected static boolean isJSONArray(Object object)
	{
		if(object.getClass().toString().compareTo("class dev.iahmadgad.json.JSONArray") == 0) return true;
		return false;
	}
	
	/**
	 * Checks if object is String.
	 * 
	 * @param object
	 * @return true if it's a String & false if it's not
	 */
	protected static  boolean isString(Object object)
	{
		if(object.getClass().toString().compareTo("class java.lang.String") == 0) return true;
		return false;
	}
	
	/**
	 * Checks if value is boolean.
	 * 
	 * @param value
	 * @return true if it's a boolean & false if it's not
	 */
	protected static boolean isBoolean(Object value)
	{
		if(value.getClass().toString().compareTo("class java.lang.Boolean") == 0) return true;
		return false;
	}
	
	/**
	 * Checks if value is int.
	 * 
	 * @param value
	 * @return true if it's an int & false if it's not
	 */
	protected static boolean isInteger(Object value)
	{
		if(value.getClass().toString().compareTo("class java.lang.Integer") == 0) return true;
		return false;
	}
	
	/**
	 * Checks if value is double.
	 * 
	 * @param value
	 * @return true if it's a double & false if it's not
	 */
	protected static boolean isDouble(Object value)
	{
		if(value.getClass().toString().compareTo("class java.lang.Double") == 0) return true;
		return false;
	}
	
	/**
	 * Gets the variable presented by String.
	 * 
	 * @param string
	 * @return the variable presented by String
	 */
	protected static Object getVariable(String string)
	{
		if(presentsString(string)) return string.substring(1, string.length() - 1);
		else if(presentsBoolean(string) && string.compareTo("true") == 0) return true;
		else if(presentsBoolean(string) && string.compareTo("false") == 0) return false;
		else if(presentsDouble(string)) return Double.parseDouble(string);
		else if(presentsInteger(string)) return Integer.parseInt(string);
		return null;
	}
	
	/**
	 * Gets the variable type presented by String.
	 * 
	 * @param string
	 * @return the variable type presented by String
	 */
	protected static String getPresentedType(String string)
	{
		if(presentsString(string)) return "String";
		else if(presentsBoolean(string)) return "boolean";
		else if(presentsDouble(string)) return "Double";
		else if(presentsInteger(string)) return "Integer";
		return null;
	}
	
	/**
	 * Checks if string presents a String value
	 * 
	 * @param string
	 * @return true if it presents a String value & false if it's not
	 */
	protected static boolean presentsString(String string)
	{
		if(!presentsBoolean(string) && !presentsInteger(string) && !presentsDouble(string)) return true;
		return false;
	}
	
	/**
	 * Checks if string presents a boolean value
	 * 
	 * @param string
	 * @return true if it presents a String value & false if it's not
	 */
	protected static boolean presentsBoolean(String string)
	{
		if(string.compareTo("true") == 0 || string.compareTo("false") == 0) return true;
		return false;
	}
	
	/**
	 * Checks if string presents a double value
	 * 
	 * @param string
	 * @return true if it presents a double value & false if it's not
	 */
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
	
	/**
	 * Checks if string presents an int value
	 * 
	 * @param string
	 * @return true if it presents an int value & false if it's not
	 */
	protected static boolean presentsInteger(String string)
	{
		for(int i = 0; i < string.length(); i++)
		{
			if(!Character.isDigit(string.charAt(i))) return false;
		}
		return true;
	}
}
