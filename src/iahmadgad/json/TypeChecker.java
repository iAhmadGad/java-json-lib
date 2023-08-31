package iahmadgad.json;

/**
 * TypeChecker Class
 * @author iAhmadGad
 * @version 0.2
 *
 */

public class TypeChecker 
{
	public TypeChecker()
	{
		
	}
	
	public static String getType(String variable)
	{
		if(isString(variable)) return "String";
		else if(isBoolean(variable)) return "boolean";
		else if(isDouble(variable)) return "Double";
		else if(isInteger(variable)) return "Integer";
		return null;
	}
	
	public static Object getVariable(String variable)
	{
		if(isString(variable)) return variable.substring(1, variable.length() - 1);
		else if(isBoolean(variable) && variable.compareTo("true") == 0) return true;
		else if(isBoolean(variable) && variable.compareTo("false") == 0) return false;
		else if(isDouble(variable)) return Double.parseDouble(variable);
		else if(isInteger(variable)) return Integer.parseInt(variable);
		return null;
	}
	
	public static boolean isString(String variable)
	{
		if(variable.charAt(0) == '"' && variable.charAt(variable.length() - 1) == '"') return true;
		return false;
	}
	
	public static boolean isBoolean(String variable)
	{
		if(variable.compareTo("true") == 0 || variable.compareTo("false") == 0) return true;
		return false;
	}
	
	public static boolean isDouble(String variable)
	{
		int point = 0;
		for(int i = 0; i < variable.length(); i++)
		{
			if(variable.charAt(i) == '.' && point == 0) point = 1;
			else if((!Character.isDigit(variable.charAt(i)) && variable.charAt(i) != '.') || variable.charAt(i) == '.' && point == 1) return false;
		}
		if(point == 0) return false;
		return true;
	}
	
	public static boolean isInteger(String variable)
	{
		for(int i = 0; i < variable.length(); i++)
		{
			if(!Character.isDigit(variable.charAt(i))) return false;
		}
		return true;
	}
}
