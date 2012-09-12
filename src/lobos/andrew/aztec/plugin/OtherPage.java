package lobos.andrew.aztec.plugin;

import lobos.andrew.aztec.http.HTTPRequest;
import lobos.andrew.aztec.http.HTTPResponse;

public class OtherPage implements Plugin {

	@Override
	public boolean handles(HTTPRequest req) {
		return req.getPath().matches("^/other.+");
		
	}

	@Override
	public HTTPResponse handle(HTTPRequest req) {
		return new HTTPResponse(200, req.getPath()+"<br>"+req.getParam("test"));
	}
}
