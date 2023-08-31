package iahmadgad.json;

public class Validator 
{
	public Validator()
	{
		
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
	
	public static boolean isBoolean(Object object)
	{
		if(object.getClass().toString().compareTo("class java.lang.Boolean") == 0) return true;
		return false;
	}
}
