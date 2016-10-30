class Bid {
	private float bidAmount;
	private Auction auction;
	public User bidder;

	public Bid(float bidAmount, Auction auction, User user)  {
		this.bidAmount = bidAmount;
		this.auction = auction;
		this.bidder = bidder;
	}

	public float getAmount()  {
		return bidAmount;
	}

	public void SendMsg()  {
		bidder.sendMsg();
	}


}