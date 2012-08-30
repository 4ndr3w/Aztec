package lobos.andrew.aztec.plugin;

import lobos.andrew.aztec.http.HTTPRequest;
import lobos.andrew.aztec.http.HTTPResponse;

public interface Plugin 
{
	public boolean handles(HTTPRequest req);
	public HTTPResponse handle(HTTPRequest req);
}
