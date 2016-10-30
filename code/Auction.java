import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;


class Auction {
	private Item item;
	private Bid highestBid;

	public Auction(Item item, Bid highestBid)  {
		this.item = item;
		this.highestBid=highestBid;

		timing = new Reminder(60, item.getName() + " auction has ENDED\n.
			Winning bidder is: " + highestBid.bidder.getName());

	}

	public Auction(Item item)  {
		this(item,null);
	}


	public boolean bidItem(Bid bid)  {
		float bidAmt = bid.getAmount();
		if(bidAmt > item.getMinimumBid() && bidAmt > item.getStartingBid())  {
			if(bidAmt > highestBid.getAmount())  {
				timing.reset();
			}
			else  {
				highestBid.bidder.sendMsg("Invalid bid");
			}
		}
		return true;
	}
}