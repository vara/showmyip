/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Gui;

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

    private MyIpInformation ipInfo = null;
    private MyTray mytray;
    private GuiListener guilistener = new GuiListener();
    
    public CoreGui(MyIpInformation ipInfo){
	this.ipInfo = ipInfo;
	
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
    public NotyficationListener getGuiListener(){
	return guilistener;
    }
    
    private class GuiListener implements NotyficationListener{

	public void sendMessage(String message) {
	    System.out.println(""+message);
	}

	public void ipChanged() {
	    System.out.println("ip changed");
	    mytray.displayMessage("ip changed",""+ipInfo.getMyIp(),MyTray.MessageType.NONE);
	    mytray.setToolTip(""+ipInfo.getMyIp());
	}

	public void connectionRefused() {	   
	    System.out.println("Connection refused");
	}
	
    }
}
