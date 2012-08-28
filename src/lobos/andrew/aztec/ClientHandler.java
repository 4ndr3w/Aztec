package lobos.andrew.aztec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

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
			Vector<String> headerData = new Vector<String>();
			System.out.println("Waiting for data...");
			while ( !reader.ready() && client.isConnected() ); // wait for data
			System.out.println("Got data...");
			while ( client.isConnected() )
			{
				String line = reader.readLine();
				if ( line == null)
					break;
						
				if ( line.equals("") )
				{
					System.out.println("End of headers!");
					HTTPRequest req = new HTTPRequest(headerData);
					writer.write("Content-type: text/plain\n");
					writer.write("\n");
					writer.write("You wanted: "+req.getPath()+" using method: "+req.type+"\n");
					writer.flush();
					
					writer.close();
					reader.close();
					
					client.close();
					System.out.println("broke out of while!");
					break;
				}
				headerData.add(line);
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		System.out.println("Client disconnected");
	}
	
}
