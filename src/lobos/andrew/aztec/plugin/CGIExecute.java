package lobos.andrew.aztec.plugin;

import java.io.IOException;
import java.util.Map;

import lobos.andrew.aztec.http.HTTPRequest;
import lobos.andrew.aztec.http.HTTPResponse;

public class CGIExecute extends Plugin {
	ProcessBuilder cgiApp;
	public CGIExecute(String binPath)
	{
		PATH = "/cgi";
		
		cgiApp = new ProcessBuilder(binPath);
	}

	@Override
	public HTTPResponse handle(HTTPRequest req) {
		Map<String, String> env = cgiApp.environment();
		env.put("PATH_TRANSLATED", req.getPath());
		try {
			Process cgiProcess = cgiApp.start();
		} catch (IOException e) {
			return new HTTPResponse(500, "CGI process failed to start.");
		}
		return new HTTPResponse(200, ".");
	}
}
