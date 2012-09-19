package lobos.andrew.aztec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import lobos.andrew.aztec.http.ErrorFactory;
import lobos.andrew.aztec.http.HTTPRequest;
import lobos.andrew.aztec.http.HTTPRequestHandler;
import lobos.andrew.aztec.http.HTTPResponse;

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
			HTTPResponse response = null;
			try
			{
				HTTPRequest req = HTTPRequest.fromSocket(client);
				response = HTTPRequestHandler.getInstance().handle(req);
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				response = ErrorFactory.internalServerError("handle() threw exception");
			}
			response.send(client);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
