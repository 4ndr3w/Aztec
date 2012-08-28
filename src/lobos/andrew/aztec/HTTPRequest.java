package lobos.andrew.aztec;

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
	
	public String getPath()
	{
		return path;
		
	}

}
