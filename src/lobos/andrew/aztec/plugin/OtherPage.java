package lobos.andrew.aztec.plugin;

import lobos.andrew.aztec.HTTPRequest;
import lobos.andrew.aztec.HTTPResponse;

public class OtherPage implements Plugin {

	@Override
	public boolean handles(HTTPRequest req) {
		return req.getPath().equals("/other");
	}

	@Override
	public HTTPResponse handle(HTTPRequest req) {
		return new HTTPResponse(200, "OK", "Hello from another plugin!");
	}

}
