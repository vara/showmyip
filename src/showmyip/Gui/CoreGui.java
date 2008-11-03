/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Gui;

import showmyip.Manager.InformantListener;
import showmyip.*;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author vara
 */
public class CoreGui{

    private MyTray mytray;
    private GuiListener guilistener = new GuiListener();
    
    public CoreGui(){
	
	if(SystemTray.isSupported()){
	    
	    SystemTray st =SystemTray.getSystemTray();
	    ImageIcon image = CoreGui.createNavigationIcon("ipIconTray2");
	    if(image!=null)
		try {
		    mytray = new MyTray(image.getImage());
		    st.add(mytray);
		    
		} catch (AWTException ex) {
		    System.out.println(""+ex.getMessage());
		}
	    else{
		
	    }
	    
	}else{
	    System.out.println("System try not suported");
	    System.exit(1);
	}
    }
    
    public InformantListener getGuiListener(){
	return guilistener;
    }
    
    private void displayMessage(Message message){
	
	if(mytray!=null){
	    mytray.displayMessage(message.getTitle(),message.getContent(),
			MyTray.MessageType.valueOf(message.getMessageType().name()));
	    mytray.setToolTip(message.getContent());
	}
    }
    
    protected static ImageIcon createNavigationIcon(String imageName) {
        String imgLocation = "resources/icons/"
                             + imageName
                             + ".png";
        URL imageURL = Core.class.getResource(imgLocation);

        if (imageURL == null) {
            System.err.println("Resource not found: "
                               + imgLocation);
            return null;
        } else {
            return new ImageIcon(imageURL);
	   
        }
    }    
    
    private class GuiListener implements InformantListener{

	public void sendMessage(Message message) {
	    displayMessage(message);	    
	}

	public void connectionRefused(Message message) {
	    displayMessage(message);
	}	
    }    
}
