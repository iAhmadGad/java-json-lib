package iahmadgad.json;

public class ArrayExtractor 
{
	private static JSONArray array;
	
	public ArrayExtractor(JSONArray array)
	{
		ArrayExtractor.array = array;
	}
	
	public Object[] extractArray()
	{
		return array.toArray();
	}
	
	public String[] extractStringArray()
	{
		return array.toArray(new String[0]);
	}
	
	public boolean[] extractBooleanArray()
	{
		Boolean temp1[] = array.toArray(new Boolean[0]);
		boolean temp2[] = new boolean[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	public double[] extractDoubleArray()
	{
		Double temp1[] = array.toArray(new Double[0]);
		double temp2[] = new double[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	public int[] extractIntArray()
	{
		Integer temp1[] = array.toArray(new Integer[0]);
		int temp2[] = new int[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
}
