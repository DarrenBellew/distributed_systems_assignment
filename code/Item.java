
class Item  {
	private String itemName;
	private long timeStart;
	private float startingBid;
	private float minimumBid;
	private User auctioner;

	public Item(String itemName, float startingBid, float minimumBid, User auctioner)  {
		this.itemName = itemName;
		this.startingBid = startingBid;
		this.minimumBid = minimumBid;
		this.auctioner = auctioner;
	}

	public String getName()  {
		return(itemName);
	}

	public float getStartingBid()  {
		return startingBid;
	}
	public float getMinimumBid()  {
		return minimumBid;
	}

}