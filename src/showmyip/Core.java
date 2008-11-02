/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

import showmyip.Gui.CoreGui;

/**
 *
 * @author vara
 */
public class Core {

    private MyIpInformation ipInfo = new MyIpInformation();
    
    public Core(){
	
	setIPProtocolVersion4(true);
	
	CoreGui gui = new CoreGui(ipInfo);
	UpdateManager um = new UpdateManager(ipInfo);
	um.addNotyficationListener(gui.getGuiListener());
    }
    
    public static void main(String[] args) {
	new Core();
    }
    
    public void setIPProtocolVersion4(boolean v4){
	
	System.setProperty("java.net.preferIPv4Stack", v4 ? "true":"false");		
    }
}
