package lobos.andrew.aztec.plugin;

import lobos.andrew.aztec.http.HTTPRequest;
import lobos.andrew.aztec.http.HTTPResponse;

public abstract class Plugin 
{
	public String PATH = "";
	
	public boolean handles(HTTPRequest req)
	{
		return req.getPath().matches(PATH);
	}
	
	public abstract HTTPResponse handle(HTTPRequest req);
}
