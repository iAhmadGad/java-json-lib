package iahmadgad.json;

public class Validator 
{
	public Validator()
	{
		
	}
	
	public static Object getVariable(String variable)
	{
		if(presentsString(variable)) return variable.substring(1, variable.length() - 1);
		else if(presentsBoolean(variable) && variable.compareTo("true") == 0) return true;
		else if(presentsBoolean(variable) && variable.compareTo("false") == 0) return false;
		else if(presentsDouble(variable)) return Double.parseDouble(variable);
		else if(presentsInteger(variable)) return Integer.parseInt(variable);
		return null;
	}
	
	public static String getPresentedType(String string)
	{
		if(presentsString(string)) return "String";
		else if(presentsBoolean(string)) return "boolean";
		else if(presentsDouble(string)) return "Double";
		else if(presentsInteger(string)) return "Integer";
		return null;
	}
	
	public static String getType(Object object)
	{
		if(isString(object)) return "String";
		else if(isBoolean(object)) return "boolean";
		else if(isDouble(object)) return "Double";
		else if(isInteger(object)) return "Integer";
		return null;
	}
	
	public static boolean isValid(Object object)
	{
		if(isJSONObject(object) || isJSONArray(object) || isString(object) || isInteger(object) || isDouble(object) || isBoolean(object)) return true;
		return false;
	}
	
	public static boolean isJSONObject(Object object)
	{
		if(object.getClass().toString().compareTo("class iahmadgad.json.JSONObject") == 0) return true;
		return false;
	}
	
	public static boolean isJSONArray(Object object)
	{
		if(object.getClass().toString().compareTo("class iahmadgad.json.JSONArray") == 0) return true;
		return false;
	}
	
	public static  boolean isString(Object object)
	{
		if(object.getClass().toString().compareTo("class java.lang.String") == 0) return true;
		return false;
	}
	
	public static boolean isBoolean(Object object)
	{
		if(object.getClass().toString().compareTo("class java.lang.Boolean") == 0) return true;
		return false;
	}
	
	public static boolean isInteger(Object object)
	{
		if(object.getClass().toString().compareTo("class java.lang.Integer") == 0) return true;
		return false;
	}
	
	public static boolean isDouble(Object object)
	{
		if(object.getClass().toString().compareTo("class java.lang.Double") == 0) return true;
		return false;
	}
	
	public static boolean presentsString(String string)
	{
		if(!presentsBoolean(string) && !presentsInteger(string) && !presentsDouble(string)) return true;
		return false;
	}
	
	public static boolean presentsBoolean(String string)
	{
		if(string.compareTo("true") == 0 || string.compareTo("false") == 0) return true;
		return false;
	}
	
	public static boolean presentsInteger(String string)
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
	
	public static boolean presentsDouble(String string)
	{
		return false;
	}
}
