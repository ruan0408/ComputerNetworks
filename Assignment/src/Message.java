import java.io.Serializable;

public abstract class Message implements Serializable{
	
	private static final long serialVersionUID = 83527766437600941L;
	private int senderId;
	private int receiverId;
	
	public Message(int senderId, int receiverId) {
		this.senderId = senderId;
		this.receiverId = receiverId;
	}
	
	/*
	 * Each type of message will hold the code that should be executed by the receiving peer.
	 * This way I could eliminate awful switches/if's.
	 * The receiving server (TCP or UDP) will pass it's own peer as parameter and execute the
	 * message action.
	 */
	public void	executeAction(Peer receivingPeer) {}
	
	public int 	getSenderId() {return senderId;}
	public int 	getReceiverId() {return receiverId;}
	public int 	getFile() {return 0;}
	public int getFirstSucessor() {return 0;}
	public int getSecondSucessor() {return 0;}
	public void setSenderId(int id) {senderId = id;}
	public void setReceiverId(int id) {receiverId = id;}
}
