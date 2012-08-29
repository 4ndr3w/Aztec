package lobos.andrew.aztec.plugin;

import lobos.andrew.aztec.HTTPRequest;
import lobos.andrew.aztec.HTTPResponse;

public interface Plugin 
{
	public boolean handles(HTTPRequest req);
	public HTTPResponse handle(HTTPRequest req);
}
