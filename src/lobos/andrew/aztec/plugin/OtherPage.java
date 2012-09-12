package lobos.andrew.aztec.plugin;

import lobos.andrew.aztec.http.HTTPRequest;
import lobos.andrew.aztec.http.HTTPResponse;

public class OtherPage extends Plugin {

	public OtherPage()
	{
		PATH = "^/other.+";
	}

	@Override
	public HTTPResponse handle(HTTPRequest req) {
		return new HTTPResponse(200, req.getPath()+"<br>"+req.getParam("test"));
	}
}
