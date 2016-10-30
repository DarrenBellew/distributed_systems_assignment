import java.net.*;
import java.io.*;

class AuctionClientThread extends Thread {
	private Socket socket = null;
	private AuctionClient client = null;
	private DataInputStream streamIn = null;

	public AuctionClientThread(Chatclient client, Socket socket)  {
		this.client = client;
		this.socket = socket;
		open();
		start();
	}

	public void open()  {
		try  {
			streamIn = new DataInputStream(socket.getInputStream());
		}
		catch(IOException ioe)  {
			System.out.println("Error getting input stream: " + ioe);
			client.stop();
		}
	}

	public void close()  {
		try  {
			if(streamIn != null)  {
				streamIn.close();
			}
		}
		catch(IOException ioe)  {
			System.out.println("Error closing input stream: " + ioe);
		}
	}

	public void run()  {
		while(true && client != null)  {
			try  {
				client.handle(streamIn.readUTF());
			}
			catch(IOException ioe)  {
				client = null;
				System.out.println("Listening error: " + ioe.getMessage());
			}
		}
	}
}