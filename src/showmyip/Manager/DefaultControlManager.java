/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Manager;

import java.util.Date;
import showmyip.WebSite;

/**
 *
 * @author vara
 */
public class DefaultControlManager extends AbstractControlManager{
	
    private AbstractUpdateManagerListener updateManager;
    
    public DefaultControlManager(AbstractUpdateManagerListener um){
	updateManager = um;
    }

    public Date getDateLastUpdate() {
	return updateManager.getDateLastUpdate();
    }

    public String getIP() {
	return updateManager.getIP();
    }

    public String getWebSiteFromUpdate() {
	return updateManager.getWebSiteFromUpdate();
    }

    public WebSite getWebSiteClassFromUpdate() {
	return updateManager.getWebSiteClassFromUpdate();
    }

    public void start() {
	updateManager.start();
    }

    public void stop() {
	updateManager.stop();
    }
}
