package bmbremaster.server;

import java.io.Serializable;

public class Msg implements Serializable{
	private static final long serialVersionUID = 1L;
	private String textMessage;
	
	public Msg() {
		
	}
	
	public Msg(String textMessage) {
		this.textMessage = textMessage;
	}
	
	public String getTextMessage() {
		return textMessage;
	}
	
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	
	public void clear() {
		this.textMessage = null;
	}
}
