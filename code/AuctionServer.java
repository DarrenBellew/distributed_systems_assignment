import java.net.*;
import java.io.*;

public class AuctionServer implements Runnable {

	//array of Users
	private AuctionServerThread clients[] = new ChatServerThread[50];
	private ServerSocket server = null;
	private Thread thread = null;
	private int clientCount = 0;


	public AuctionServer(int port) n {
		try  {
			System.out.println("Binding to port " + port + ", please wait... ...");

			server = new ServerSocket(port);
			System.out.println("Server started: " + server.getInetAddress());
			start();
		}
		catch (IOException ioe) {
			System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
		}
	}

	public void run()  {
		while(thread != null)  {
			try  {
				System.out.println("Waiting for a client ... ...");
				addThread(server.accept());

				int pause = (int)(Math.random()*3000);
				Thread.sleep(pause);
			}
			catch (IOException ioe) {
				System.out.println("Server accept error: " + ioe);
				stop();
			}
			catch(InterruptedException e)  {
				System.out.println(e);
			}
		}
	}


	public void start()  {
		if(thread == null)  {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop()  {
		thread = null;
	}

	public int findClient(int ID)  {
		for (int i=0; i < clientCount; i++) {
			if(clients[i].getID() == ID)  {
				return i;
			}
		}

		return -1;
	}

	public synchronized void broadcast(String src, String input)  {
		for (int i=0; i < clientCount; i++)  {
			clients[i].send(src + ": " + input);
		}
		
		notifyAll();
	}

	public synchronized void remove(int ID)  {
		int pos = findClient(ID);
		if (pos >= 0)  {
			AuctionServerThread toTerminate = clients[pos];
			System.out.println("Removing client thread " + ID + " at " + pos);

			if(pos < clientCount-1)  {
				for(int i = pos+1; i<clientCount; i++)  {
					clients[i-1] = clients[i];
				}
			}
			clientCount--;

			try  {
				toTerminate.close();
			}
			catch(IOException ioe)  {
				System.out.println("Error closing thread: " + ioe);
			}

			toTerminate=null;
			System.out.println("Client " + pos + " removed");
			notifyAll();
		}
	}

	public void addThread(Socket socket)  {
		if(clientCount < clients.length)  {
			System.out.println("Client accepted: " + socket);
			clients[clientCount] = new AuctionClientThread(this, socket);

			try  {
				clients[clientCount].open();
				clients[clientCount].start();
				clientCount++;
			}
			catch(IOException ioe)  {
				System.out.println("Error opening thread: " + ioe);
			}
		}
		else {
			System.out.println("Client refused: maximum " + clients.length + " reached.");
		}
	} 

	public static void main(String[] args) {
		AuctionServer server = null;
		if (args.length != 1)  {
			System.out.println("USAGE: java ChatServer port");
		}
		else  {
			server = new ChatServer(Integer.parseInt(args[0]));
		}
	}	
}