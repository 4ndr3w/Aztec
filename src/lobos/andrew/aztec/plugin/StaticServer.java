package lobos.andrew.aztec.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import lobos.andrew.aztec.Config;
import lobos.andrew.aztec.http.HTTPRequest;
import lobos.andrew.aztec.http.HTTPResponse;

public class StaticServer extends Plugin {

	public StaticServer()
	{
		PATH = ".+";
	}
	
	@Override
	public HTTPResponse handle(HTTPRequest req) {
		String path = req.getPath();
		if ( path.substring(path.length()-1, path.length()).equals("/") )
			path += "index.html";
		File f = new File(Config.getString("docroot")+"/"+path);
		if ( f.exists() )
		{
			try {
				@SuppressWarnings("resource")
				FileReader reader = new FileReader(f);
				String fileData = "";
				while ( reader.ready() )
				{
					char[] data = new char[50];
					reader.read(data, 0, data.length);
					fileData += new String(data);
				}
				return new HTTPResponse(200, fileData);
			} catch (Exception e) {
				return new HTTPResponse(500, "<h1>Internal Server Error</h1>");
			}
		}
		return new HTTPResponse(404, "<h1>Not Found</h1>");
	}

}
