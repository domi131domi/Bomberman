package bomberman.server;

import java.io.Serializable;

public class Msg implements Serializable{
	private static final long serialVersionUID = 1L;
	private String systemMessage;
	private String textMessage;
	
	public Msg(String systemMessage) {
		this.systemMessage = systemMessage;
	}
	
	public String getSystemMessage() {
		return systemMessage;
	}
	
	public String getTextMessage() {
		return textMessage;
	}
}
