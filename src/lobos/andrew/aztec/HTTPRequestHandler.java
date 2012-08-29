package lobos.andrew.aztec;

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
	
	public void handle(HTTPRequest req)
	{
		Iterator<Plugin> it = plugins.iterator();
		while ( it.hasNext() )
		{
			Plugin thisPlugin = it.next();
			if ( thisPlugin.handles(req) )
				thisPlugin.handle(req);
		}
	}
}
