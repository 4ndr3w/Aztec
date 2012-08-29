package lobos.andrew.aztec;

import java.io.IOException;

import lobos.andrew.aztec.plugin.Index;
import lobos.andrew.aztec.plugin.OtherPage;
import lobos.andrew.aztec.plugin.Plugin;

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
		
		registerPlugin(new Index());
		registerPlugin(new OtherPage());
		
		try { start(); }
		catch (IOException e)
		{
			System.out.println("Aztec failed to start!");
		}
	}

}
