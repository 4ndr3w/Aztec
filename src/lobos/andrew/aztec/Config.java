package lobos.andrew.aztec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Config {
	private static HashMap<String, HashMap<String,String>> config = new HashMap<String, HashMap<String,String>>();
	
	
	public static void init(String path) throws IOException
	{
		FileReader file = new FileReader(path);
		BufferedReader reader = new BufferedReader(file);
		while ( reader.ready() )
		{
			String[] line = reader.readLine().split(" ", 2);
			String[] configArgs = line[0].split(":");
			if ( line.length == 2 && configArgs.length == 2 )
			{
				if ( !config.containsKey(configArgs[0]) )
					config.put(configArgs[0], new HashMap<String,String>());
				config.get(configArgs[0]).put(configArgs[1], line[1]);
			}
		}
		reader.close();
		file.close();
	}
	
	public static boolean isDefined(String domain, String key)
	{
		if ( !config.containsKey(domain) )
			return false;
		if ( !config.get(domain).containsKey(key) )
			return false;
		return true;
	}
	
	public static int getInt(String domain, String key, int fallback)
	{
		return Integer.parseInt(getString(domain, key, String.valueOf(fallback)));
	}
	
	public static String getString(String domain, String key, String fallback)
	{
		if ( !config.containsKey(domain) )
			config.put(domain, new HashMap<String,String>());
		if ( !config.get(domain).containsKey(key) )
			config.get(domain).put(key, fallback);
		return config.get(domain).get(key);
	}
	
	public static HashMap<String, String> getDomain(String domain)
	{
		if ( !config.containsKey(domain) )
			config.put(domain, new HashMap<String,String>());
		return config.get(domain);
	}
	
	public static Set<String> getDomainSet()
	{
		return config.keySet();
	}
}
