/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Gui;

import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import showmyip.Manager.InformantListener;
import showmyip.*;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import showmyip.Manager.DefaultControlManager;
import showmyip.Manager.UpdateManager.UpdateStatus;

/**
 *
 * @author vara
 */
public class CoreGui{

    private MyTray mytray;
    private GuiListener guilistener = new GuiListener();
    private DefaultControlManager cm;
    public CoreGui(DefaultControlManager dcm){
	
	cm = dcm;
	
	if(SystemTray.isSupported()){
	    
	    SystemTray st =SystemTray.getSystemTray();
	    ImageIcon image = CoreGui.createNavigationIcon("ipIconTray2");
	    if(image!=null)
		try {
		    mytray = new MyTray(image.getImage());
		    
		    MenuItem [] mItems = new MenuItem[]{new MenuItem("Copy IP to clipboard"),new MenuItem("Exit Program")};
		    
		    mItems[0].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			    	    
			    String ip = cm.getIP();
			    new CopyToClipboard(ip).actionPerformed(e);			    
			}
		    });
		    
		    mItems[1].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			    	    
			    cm.stop();
			    while(cm.getStatus()!=UpdateStatus.STOP){
				try {				    
				    //wait to end thread about cm.getIntervalForCheckConnection()
				    Thread.sleep(cm.getIntervalForCheckConnection()*1000);
				} catch (InterruptedException ex) { }
			    }			    
			    System.out.println("End of live application !");
			    System.exit(0);			
			}
		    });
		    
		    mytray.createPopup(mItems, "Menu");
		    
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
