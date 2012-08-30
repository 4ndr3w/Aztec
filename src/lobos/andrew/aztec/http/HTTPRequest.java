package lobos.andrew.aztec.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class HTTPRequest {
	String path;
	String type;
	HashMap<String, String> headers = new HashMap<String,String>();
	public HTTPRequest(Vector<String> headerData)
	{
		Iterator<String> it = headerData.iterator();
		
		String[] spaceSplit = it.next().split(" ");
		type = spaceSplit[0];
		path = spaceSplit[1];
		
		while ( it.hasNext() )
		{
			String thisHeader[] = it.next().split(": ");
			headers.put(thisHeader[0], thisHeader[1]);
		}
	}
	
	public static HTTPRequest fromSocket(Socket client) throws IOException
	{	
		Vector<String> headerData = new Vector<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		while ( !reader.ready() && client.isConnected() );
		while ( client.isConnected() )
		{
			String line = reader.readLine();
			if ( line == null )
				break;
			if ( line.equals("") )
				break;
			headerData.add(line);
		}
		return new HTTPRequest(headerData);
	}
	
	public String getHeader(String name)
	{
		if ( !headers.containsKey(name) )
			return "";
		return headers.get(name);
	}
	
	public String getPath()
	{
		return path;
	}
	
	public String getMethod()
	{
		return type;
	}

}
