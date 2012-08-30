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
		Iterator<Plugin> it = plugins.iterator();
		while ( it.hasNext() )
		{
			Plugin thisPlugin = it.next();
			if ( thisPlugin.handles(req) )
				return thisPlugin.handle(req);
		}
		return new HTTPResponse(500, "Internal Server Error", "No plugins where able to handle your request.");
	}
}
