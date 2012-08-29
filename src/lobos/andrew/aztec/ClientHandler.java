package lobos.andrew.aztec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
	Socket client = null;
	BufferedReader reader;
	BufferedWriter writer;
	public ClientHandler(Socket client)
	{
		this.client = client;
		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}
	
	public void run()
	{
		try {
			HTTPRequest req = HTTPRequest.fromSocket(client);
			String data = "request for "+req.getPath()+" directed at "+req.getHeader("Host")+"\n";
			HTTPResponse response = new HTTPResponse(200, "OK", data);
			response.send(client);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
