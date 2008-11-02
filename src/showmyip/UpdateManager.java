/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

/**
 *
 * @author vara
 */
public class UpdateManager extends Notyfication implements Runnable,UpdateManagerListener{

    public static final int RUN  = 0;
    public static final int STOP = 1;
    
    private int interval = 1; //unit minutes
    
    //UpdateManager default run when object was created
    private boolean loopThread = true;
    private int status = 0;
    
    private MyIpInformation ipInfo;
    
    public UpdateManager(MyIpInformation ip){
	
	ipInfo = ip;
	createUpdateThread();
    }

    public void run() {
	
	while(loopThread){
	    
	    try {
		try {
		    String newIp = GetInformationFromInternet.getIPFromSite(
			    ipInfo.getActualSite(), ipInfo.getActualRegexp());
		    System.out.println(""+newIp);
		    if(!ipInfo.getMyIp().equals(newIp)){
			ipInfo.setMyIp(newIp);
			//Date date = new Date();
			ipChanged();
		    }
		} catch (Exception ex) {
		    System.out.println(""+ex.getMessage());
		    sendMessage(ex.getMessage());
		}
		
		Thread.sleep(interval * 1000*2);

	    } catch (InterruptedException ex) {}	
	}
    }
    private void setStatus(int val){
	if(val>=0 && val<2)
	    status = val;
	else throw new ExceptionInInitializerError("Unknown value.");
    }
    private void setLoopThread(boolean val){
	loopThread = val;
    }
    private void createUpdateThread(){
	
	setLoopThread(true);
	Thread thread = new Thread(this);
	thread.setDaemon(true);
	thread.start();
    }
    
    public int getInterval(){
	return interval;
    }

    public void start() {
	if(getStatus()==UpdateManager.STOP){
	    createUpdateThread();
	    setStatus(UpdateManager.RUN);
	}
    }

    public void stop() {
	if(getStatus()==UpdateManager.RUN){
	    setLoopThread(false);
	    setStatus(UpdateManager.STOP);
	}
    }

    public void setInterval(int interval) {
	this.interval = interval;
    }

    public int getStatus() {
	return status;
    }
}
