package lobos.andrew.aztec.http;

import java.util.Iterator;
import java.util.Vector;

import lobos.andrew.aztec.plugin.Plugin;

public class HTTPRequestHandler {
	
	protected static HTTPRequestHandler instance = new HTTPRequestHandler();
	
	public static HTTPRequestHandler getInstance()
	{
		return instance;
	}
	
	Vector<Plugin> plugins = new Vector<Plugin>();
	
	public HTTPRequestHandler()
	{
		
	}
	
	public void registerPlugin(Plugin plugin)
	{
		plugins.add(plugin);
	}
	
	public HTTPResponse handle(HTTPRequest req)
	{
		{
			Iterator<Plugin> it = plugins.iterator();
			while ( it.hasNext() )
			{
				Plugin thisPlugin = it.next();
				if ( thisPlugin.handles(req) )
					return thisPlugin.handle(req);
			}
		}
		
		String debugData = req.getMethod()+" "+req.getPath()+"<br>";
		debugData += "Host: "+req.getHost()+"<br>";
		debugData += "<h3>Client Headers</h3>";
		Iterator<String> it = req.headers.keySet().iterator(); 
		while ( it.hasNext() )
		{
			String d = it.next();
			debugData += d+": "+req.headers.get(d)+"<br>";
		}

		return new HTTPResponse(500, "<h1>500 - Internal Server Error</h1>No plugins where able to handle your request.<br>"+debugData);
		
	}
}
