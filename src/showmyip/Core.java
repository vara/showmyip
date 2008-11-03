/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

import java.security.Security;
import showmyip.Manager.UpdateManager;
import showmyip.Gui.CoreGui;
import showmyip.Manager.DefaultControlManager;

/**
 *
 * @author vara
 */
public class Core {
    
    public Core(){
	
	setIPProtocolVersion4(true);
	disableNetworkCache();
	
	
	UpdateManager um = new UpdateManager();
	DefaultControlManager dcm = (DefaultControlManager) um.getControlManager();
	
	CoreGui gui = new CoreGui(dcm);
	um.addNotyficationListener(gui.getGuiListener());
	
	
	dcm.setLoopCheckedUM(true);
	dcm.start();
	
	System.out.println(""+dcm.getStatus());
    }
    
    public static void main(String[] args) {
	new Core();
    }
    
    public void setIPProtocolVersion4(boolean v4){
	
	System.setProperty("java.net.preferIPv4Stack", v4 ? "true":"false");		
    }
    public void disableNetworkCache(){
	Security.setProperty("networkaddress.cache.ttl" , "0");	
	Security.setProperty("networkaddress.cache.negative.ttl","0");
	//System.setProperty("sun.net.client.defaultConnectTimeout", "2000"); //2 seconds timeout
	//System.setProperty("sun.net.client.defaultReadTimeout", "2000"); //2 seconds timeout
    }
}
