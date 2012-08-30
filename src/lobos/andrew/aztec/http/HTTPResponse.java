package lobos.andrew.aztec.http;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

public class HTTPResponse {

	Vector<String> response = new Vector<String>();
	String content;
	public HTTPResponse(int code, String strResponse, String content)
	{
		response.add("HTTP/1.1 "+code+" "+strResponse+"\n");
		addResponseHeader("Content-Length", String.valueOf(content.length()));
		addResponseHeader("Content-type", "text/html");
		addResponseHeader("Server", "Aztec");
		addResponseHeader("Connection", "close");
		this.content = content;
	}
	
	public HTTPResponse(int code, String strResponse, String content, String content_type)
	{
		response.add("HTTP/1.1 "+code+" "+strResponse+"\n");
		addResponseHeader("Content-Length", String.valueOf(content.length()));
		addResponseHeader("Content-type", content_type);
		addResponseHeader("Server", "Aztec");
		addResponseHeader("Connection", "close");
		this.content = content;
	}

	public void addResponseHeader(String name, String value)
	{
		response.add(name+": "+value+"\n");
	}
	
	public void send(Socket client) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		Iterator<String> it = response.iterator();
		
		while (it.hasNext())
			writer.write(it.next());
		
		writer.write("\n"+content);
		
		writer.flush();
	}
	
}
