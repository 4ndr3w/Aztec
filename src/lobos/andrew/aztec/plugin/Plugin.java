package lobos.andrew.aztec.plugin;

import lobos.andrew.aztec.HTTPRequest;

public interface Plugin 
{
	public boolean handles(HTTPRequest req);
	public void handle(HTTPRequest req);
}
