package iahmadgad.json;

public class ArrayExtractor 
{
	private static JSONArray array;
	
	public ArrayExtractor(JSONArray array)
	{
		ArrayExtractor.array = array;
	}
	
	public Object[] getArray()
	{
		return array.getNode().toArray();
	}
	
	public String[] getStringArray()
	{
		return array.getNode().toArray(new String[0]);
	}
	
	public boolean[] getBooleanArray()
	{
		Boolean temp1[] = array.getNode().toArray(new Boolean[0]);
		boolean temp2[] = new boolean[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	public double[] getDoubleArray()
	{
		Double temp1[] = array.getNode().toArray(new Double[0]);
		double temp2[] = new double[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
	
	public int[] getIntArray()
	{
		Integer temp1[] = array.getNode().toArray(new Integer[0]);
		int temp2[] = new int[temp1.length];
		for(int i = 0; i < temp1.length; i++) temp2[i] = temp1[i];
		return temp2;
	}
}
