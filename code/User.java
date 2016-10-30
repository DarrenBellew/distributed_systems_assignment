public class User  {
	private String name;
	private Client client;
	private String u_ID;


	public User(String name, Client client)  {
		this.client = client;
	}

	public boolean bid(Bid bid)  {
		return true;
	}

	public boolean publishItem(Item item, float startingBid, float minimumBid)  {
		 return true;
	}

	public void disconnect()  {
		System.out.println("GOODBYE!");
	}

	public String getName()  {
		return name;
	}

	public void sendMsg(String msg)  {
		client.handle(msg);
	}
}