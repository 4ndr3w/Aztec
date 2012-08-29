package lobos.andrew.aztec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

public class HTTPRequest {
	String path;
	String type;
	public HTTPRequest(Vector<String> headerData)
	{
		Iterator<String> it = headerData.iterator();
		
		while ( it.hasNext() )
		{
			String thisHeader = it.next();
			String[] spaceSplit = thisHeader.split(" ");
			if ( spaceSplit[0].equals("GET") )
			{
				type = "GET";
				path = spaceSplit[1];
			}
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
	
	public String getPath()
	{
		return path;
	}

}
