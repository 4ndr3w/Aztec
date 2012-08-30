package lobos.andrew.aztec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Thread {
	ServerSocket server = null;
	
	public Listener() throws IOException
	{
		server = new ServerSocket(Config.getInt("port"));
		start();
	}
	
	public void run()
	{
		while ( true )
		{
			try {
				Socket client = server.accept();
				new ClientHandler(client);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
