package clientSide;
import java.io.*;
import java.net.*;

public class Client
{
	public static void main(String argv[]) throws Exception
	{
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
		Socket clientSocket = new Socket("localhost", 1234);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		while(true){
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//		sentence = inFromUser.readLine();
			outToServer.writeBytes(java.net.InetAddress.getLocalHost().getHostName() + '\n');
			outToServer.writeBytes("magnet:?xt=urn:btih:f127b282300b8379168018fc57e65b57f7066fd6&dn=The.Expendables.2.2012.720p.BluRay.x264-AVSHD+%5BPublicHD%5D+&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Ftracker.publicbt.com%3A80&tr=udp%3A%2F%2Ftracker.istole.it%3A6969&tr=udp%3A%2F%2Ftracker.ccc.de%3A80" + '\n');

			modifiedSentence = inFromServer.readLine();
		}
	}
}