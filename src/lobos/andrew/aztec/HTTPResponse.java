package lobos.andrew.aztec;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

public class HTTPResponse {

	Vector<String> response = new Vector<String>();
	public HTTPResponse(int code, String strResponse, String content)
	{
		response.add("HTTP/1.1 "+code+" "+strResponse);
		addResponseHeader("Server", "Aztec");
		addResponseHeader("Connection", "close");
	}
	
	public HTTPResponse(int code, String strResponse, String content, String content_type)
	{
		this(code, strResponse, content);
		addResponseHeader("Content-Length", String.valueOf(content.length()));
		addResponseHeader("Content-type", content_type);

	}

	public void addResponseHeader(String name, String value)
	{
		response.add(name+": "+value+"\n");
	}
	
	public void send(Socket client) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	}
	
}
