package lobos.andrew.aztec.plugin;

import lobos.andrew.aztec.http.HTTPRequest;
import lobos.andrew.aztec.http.HTTPResponse;

public class Index implements Plugin {

	@Override
	public boolean handles(HTTPRequest req) {
		return req.getPath().equals("/");
	}

	@Override
	public HTTPResponse handle(HTTPRequest req) {
		if ( req.getMethod().equals("GET") )
			return new HTTPResponse(200, "<form action='' method='post'><input type='text' name='example'><input type='submit'></form><br>"+req.getMethod());
		else
			return new HTTPResponse(200, "Data: "+req.getParam("example"));
	}

}
