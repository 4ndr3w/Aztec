package lobos.andrew.aztec;

import java.io.IOException;

import lobos.andrew.aztec.http.HTTPRequestHandler;
import lobos.andrew.aztec.plugin.Plugin;
import lobos.andrew.aztec.plugin.StaticServer;

public class Aztec {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void registerPlugin(Plugin p)
	{
		HTTPRequestHandler.getInstance().registerPlugin(p);
	}
	
	public static void start() throws IOException
	{
		new Listener();
	}
	
	public static void main(String[] args) throws IOException {
		
		Config.init();
		
		Config.ensureDefined("port");
		Config.ensureDefined("docroot");
		registerPlugin(new StaticServer());
		try { start(); }
		catch (IOException e)
		{
			System.out.println("Aztec failed to start!");
		}
	}

}
