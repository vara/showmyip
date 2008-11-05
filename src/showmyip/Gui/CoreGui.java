/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Gui;

import java.awt.event.ActionListener;
import java.io.IOException;
import showmyip.Manager.InformantListener;
import showmyip.*;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;
import showmyip.Manager.DefaultControlManager;
import showmyip.Manager.UMListenerNotInitException;
import showmyip.Manager.UpdateManager.UpdateStatus;
import showmyip.Gui.GraphicsUtilities;

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
	       
	    Dimension iconSize = st.getTrayIconSize();
	    ImageIcon image = CoreGui.createNavigationIcon("ipIconTray2",iconSize.width,
					iconSize.height);
	    
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
			    try {

				cm.stop();
				
			    } catch (UMListenerNotInitException ex) {}
			    
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
    
    protected static ImageIcon createNavigationIcon(String imageName,int w,int h){

	String imgLocation = "resources/icons/"
                             + imageName
                             + ".png";
        URL imageURL = Core.class.getResource(imgLocation);
	BufferedImage buffImg=null;		
	try {
	    buffImg = GraphicsUtilities.loadCompatibleImage(imageURL);	    
	} catch (IOException ex) {
	    System.out.println(""+ex.getMessage());
	    return null;
	}	
	Image scaledImg = buffImg.getScaledInstance(w, h, 
						    Image.SCALE_REPLICATE);
	
	//BufferedImage scaledImg = GraphicsUtilities.createThumbnail(buffImg,w,h);
	return new ImageIcon(scaledImg);
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
	    
        } else { return new ImageIcon(imageURL); }
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
