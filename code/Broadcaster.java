public class Broadcaster {


	public Broadcaster(AuctionServerThread clients, String src, String message)  {

		for(i=0; i<clients.length -1; i++)  {
			if(clients[i].ID == )
				clients[i].send(src, message);
		}
	}

}