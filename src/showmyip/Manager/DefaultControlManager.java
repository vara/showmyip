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
	
    private AbstractUpdateManagerListener updateManagerListener;
    
    public DefaultControlManager(AbstractUpdateManagerListener um){
	updateManagerListener = um;
    }
    
    public DefaultControlManager(){
	updateManagerListener =null;
    }   
    
    public Date getDateLastUpdate() {
	return getUpdateManagerListener().getDateLastUpdate();
    }

    public String getIP() {
	return getUpdateManagerListener().getIP();
    }

    public String getWebSiteFromUpdate() {
	return getUpdateManagerListener().getWebSiteFromUpdate();
    }

    public WebSite getWebSiteClassFromUpdate() {
	return getUpdateManagerListener().getWebSiteClassFromUpdate();
    }

    public void start() throws UMListenerNotInitException, CUMNotInitException{
	try {
	    
	    if (getUpdateManagerListener() != null)
		getUpdateManagerListener().start();
	    
	    else throw new UMListenerNotInitException("Update manager Listener not initialize");
	    
	    
	} catch (CUMNotInitException ex) {
	    throw ex;
	}
    }

    public void stop() throws UMListenerNotInitException{
	
	    if (getUpdateManagerListener() != null)
		getUpdateManagerListener().stop();
	    
	    else throw new UMListenerNotInitException("Update manager Listener not initialize");
	    
    }

    public AbstractUpdateManagerListener getUpdateManagerListener() {
	return updateManagerListener;
    }

    public void setUpdateManagerListener(AbstractUpdateManagerListener updateManagerListener) {
	this.updateManagerListener = updateManagerListener;
    }
}
