package iahmadgad.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JSON 
{
	
	private static String source;
	
	public static String getSource() 
	{
		return source;
	}

	public static void setSource(String source) 
	{
		JSON.source = source;
	}
	
	public JSON(String string)
	{
		String source = "";
		for(int i = 0; i < string.length(); i++)
		{
			char c = string.charAt(i);
			if(c != '\s' && c != '\n') source += c;
		}
		setSource(source);
	}
	
	public JSON(File file)
	{
		String string = "";
		try 
		{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				string += scanner.nextLine();
			}
			scanner.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		String source = "";
		for(int i = 0; i < string.length(); i++)
		{
			char c = string.charAt(i);
			if(c != '\s' && c != '\n') source += c;
		}
		setSource(source);
	}
}
