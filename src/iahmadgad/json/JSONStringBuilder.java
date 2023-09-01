package iahmadgad.json;

public class JSONStringBuilder 
{
	public JSONStringBuilder(JSONObject object)
	{
		
	}
	
	public JSONStringBuilder(JSONArray array)
	{
		
	}
	
	private static String JSONString;
	
	public String getJSONString()
	{
		return JSONString;
	}
	
	public class Settings
	{
		private int indentation = 3, spaceAroundColon = 0;
		private Object[] defaults = {3, 0};
		
		public void setIndentation(int i)
		{
			indentation = i;
		}
		
		public void setSpaceAroundColon(int i)
		{
			indentation = i;
		}
		
		public void setDefault()
		{
			indentation = 3;
			spaceAroundColon = 0;
		}
	}
}
