package iahmadgad.json;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListExtractor 
{
	private static JSONArray array;

	public ListExtractor(JSONArray array)
	{
		ListExtractor.array = array;
	}
	
	public List<Object> extractList()
	{
		return array.getNode();
	}
	
	public List<String> extractStringList()
	{
		ArrayList<Object> arrayList = array.getNode();
		List<String> list = arrayList.stream().map(object -> (String) object).collect(Collectors.toList());
		return list;
	}
	
	public List<Boolean> extractBooleanList()
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Boolean> list = arrayList.stream().map(object -> (Boolean) object).collect(Collectors.toList());
		return list;
	}
	
	public List<Double> extractDoubleList()
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Double> list = arrayList.stream().map(object -> (Double) object).collect(Collectors.toList());
		return list;
	}
	
	public List<Integer> extractIntList()
	{
		ArrayList<Object> arrayList = array.getNode();
		List<Integer> list = arrayList.stream().map(object -> (Integer) object).collect(Collectors.toList());
		return list;
	}
}
