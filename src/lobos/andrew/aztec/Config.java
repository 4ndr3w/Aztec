package lobos.andrew.aztec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Config {
	static private String configPath = "/AztecConfig.ini";
	private static HashMap<String, String> config = new HashMap<String, String>();
	
	
	public static void init() throws IOException
	{
		FileReader file = new FileReader(configPath);
		BufferedReader reader = new BufferedReader(file);
		while ( reader.ready() )
		{
			String[] line = reader.readLine().split(" ", 2);
			config.put(line[0], line[1]);
		}
		reader.close();
		file.close();
	}
	
	public static boolean isDefined(String key)
	{
		return config.containsKey(key);
	}
	
	public static void ensureDefined(String key)
	{
		if ( !isDefined(key) )
		{
			System.out.println("You must define the "+key+" option in the config file.");
			System.exit(0);
		}
	}
	
	public static int getInt(String key)
	{
		return Integer.parseInt(getString(key));
	}
	
	public static String getString(String key)
	{
		return config.get(key);
	}
}
