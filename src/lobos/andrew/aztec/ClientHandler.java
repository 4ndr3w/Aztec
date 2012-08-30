package lobos.andrew.aztec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import lobos.andrew.aztec.http.HTTPRequest;
import lobos.andrew.aztec.http.HTTPRequestHandler;
import lobos.andrew.aztec.http.HTTPResponse;
import lobos.andrew.aztec.plugin.Index;

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
		HTTPRequestHandler.getInstance().registerPlugin(new Index());
		start();
	}
	
	public void run()
	{
		try {
			HTTPRequest req = HTTPRequest.fromSocket(client);
			HTTPResponse response = HTTPRequestHandler.getInstance().handle(req);
			response.send(client);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
