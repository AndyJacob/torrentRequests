package serverSide;
import java.io.*;
import java.net.*;

import javax.swing.SwingWorker;

public class Server extends SwingWorker<Void, String>
{
	private int port;
	public Server(int p){
		port = p;
	}
	public void start() throws IOException{
		String clientHostname;
		String magnetURI;

		ServerSocket servSocket = new ServerSocket(port);
		System.out.println(port);
		while(true)
		{
			Socket connectionSocket = servSocket.accept();
			BufferedReader inFromClient =
					new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			clientHostname = inFromClient.readLine();
			magnetURI = inFromClient.readLine();

			outToClient.writeBytes("OK");
		}
	}
	protected Void doInBackground() throws Exception {
		start();
		return null;
	}
}