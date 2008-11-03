/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Manager;

import java.io.EOFException;
import java.net.ConnectException;
import java.util.Date;
import showmyip.*;

/**
 *
 * @author vara
 */
public class UpdateManager extends Informant{
      
    private MyIpInformation ipInfo;    
    private AbstractControlManager control = new DefaultControlManager(new UpdateManagerListener());
    
    public UpdateManager(MyIpInformation ip){
	
	ipInfo = ip;	
    }
    
    public UpdateManager(){
	ipInfo = new MyIpInformation();	
    }
    
    private void createUpdateThread(){
	Thread th = new Thread(new Runnable() {

	    public void run() {
		
		control.setStatus(UpdateStatus.RUN);
		System.out.println("Start thread update manager");
		
		updateIp();//first time to update

		while(control.isLoopCheckedUM()){	    
		    try {		
			if(!GetInformationFromInternet.isConnected("http://www.googe.pl")){

			    //if connection refused

			    if(control.getStatus()!=UpdateStatus.CONNECTION_REFUSED){
				control.setStatus(UpdateStatus.CONNECTION_REFUSED);
				connectionRefused(new Message("Your IP last was : "+ipInfo.getMyIp(),
							"Connection Refused",
							Message.MessageType.WARNING)); 
			    }
			    System.out.println("\t"+new Date()+"\nConnection refused ...");

			}else{

			    if(control.getStatus()!=UpdateStatus.CONNECTION_REFUSED){

				//check when was the last time update

				long gapTime = control.getTimeLastUpdate()/1000;
				System.out.println("time gap update "+gapTime);
				if(gapTime>1800.0)
				    updateIp();
			    }else{

				control.setStatus(UpdateStatus.RUN);			
				updateIp();
				//if fun. updateIp no changed ip, then tooltip in sys try must update
				//
				sendMessage(new Message("Your Ip is : "+ipInfo.getMyIp(),"Update success !",Message.MessageType.INFO));
			    }
			}
			Thread.sleep(control.getIntervalForCheckConnection() * 1000);

		    } catch (InterruptedException ex) {}	
		}
		control.setStatus(UpdateStatus.STOP);
		System.out.println("End thread update manager");
	    }
	});
	th.start();
    }
    
    public AbstractControlManager getControlManager(){
	return control;
    }
    
    public void setControlManager(AbstractControlManager cm){
	control = cm;
    }
    
    private void updateIp(){
	control.setTimeLastUpdate(System.currentTimeMillis());
	try {
		    
	    System.out.println("Prepare to Update ip from "+ipInfo.getActualSite());
	    String newIp = GetInformationFromInternet.getIPFromSite(ipInfo);
	    System.out.println("Update ip success.");
	    if(!ipInfo.getMyIp().equals(newIp)){

		System.out.println("Ip address changed.\nOld ip "+ipInfo.getMyIp());
		ipInfo.setMyIp(newIp);
		ipInfo.setLastUpdate(new Date());
		System.out.println("New ip "+ipInfo.getMyIp()+"\nDate "+ipInfo.getLastUpdate());
		
		sendMessage(new Message("Your IP address Changed to "+ipInfo.getMyIp(),"Message !",
					Message.MessageType.INFO));
	    }else{
		System.out.println("Ip address no changed");
	    }

	} catch (ConnectException ex) {
	    System.out.println("\t"+new Date()+"\n"+ex.getMessage());
	    if(control.getStatus()!=UpdateStatus.CONNECTION_REFUSED){//informed us only one time 
		control.setStatus(UpdateStatus.CONNECTION_REFUSED);
		connectionRefused(new Message("last Your IP was : "+ipInfo.getMyIp(),
						"Connection Refused",
						Message.MessageType.WARNING)); 
	    }
	} catch (EOFException ex) {
		System.out.println("\t"+new Date()+"\n"+ex.getMessage());
	} catch (NoSuchFieldException ex) {
		System.out.println("\t"+new Date()+"\n"+ex.getMessage());
	}
    }    
    public enum UpdateStatus{
	RUN,
	STOP,
	CONNECTION_REFUSED
    }
   
    private class UpdateManagerListener  extends AbstractUpdateManagerListener{
	 
	public void start() {
	    if(control.getStatus()==UpdateStatus.STOP){	    
		createUpdateThread();	    
	    }
	}

	public void stop() {
	    if(control.getStatus()==UpdateStatus.RUN ||control.getStatus()==UpdateStatus.CONNECTION_REFUSED){
		control.setLoopCheckedUM(false);    
	    }
	}

	public Date getDateLastUpdate() {
	    return ipInfo.getLastUpdate();
	}

	public String getIP() {
	    return ipInfo.getMyIp();
	}

	public String getWebSiteFromUpdate() {
	    return ipInfo.getActualSite();
	}

	public WebSite getWebSiteClassFromUpdate() {
	    return ipInfo;
	}
    } 
}
