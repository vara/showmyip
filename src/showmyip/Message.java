/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

import java.util.Date;

/**
 *
 * @author vara
 */
public class Message {

    public enum MessageType {
        ERROR,
        WARNING,
        INFO,
        NONE
    };
    
    private String content="";
    private MessageType messageType = MessageType.NONE;
    private String title="";
    private String date = new Date().toString();
    
    public Message(String msg,String title,MessageType mt){
	
	setContent(msg);
	setTitle(title);
	setMessageType(mt);
    }
    
    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public MessageType getMessageType() {
	return messageType;
    }

    public void setMessageType(MessageType messageType) {
	this.messageType = messageType;
    }
    
    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }
    
    public String getDateWhenCreatedMessage(){
	return date;
    }
}
