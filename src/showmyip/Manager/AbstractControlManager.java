/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Manager;

import showmyip.Manager.UpdateManager.UpdateStatus;

/**
 *
 * @author vara
 */

public abstract class AbstractControlManager implements InterfaceUM,ControlUpdateManager{
	
    private int interval = 5; //units seconds
    private boolean loopCheckConnection = false;
    private UpdateStatus status = UpdateStatus.STOP;
    private long timeLastUpdate;
    private int intervalForUpdateIP = 1800; 
    public AbstractControlManager(){
	
    }
    
    public synchronized int getIntervalForCheckConnection() {
	return interval;
    }

    public void setIntervalForCheckConnection(int interval) {
	this.interval = interval;
    }

    public void setLoopCheckedUM(boolean val) {
	loopCheckConnection = val;
    }

    public synchronized boolean isLoopCheckedUM() {
	return loopCheckConnection;
    }

    public void setStatus(UpdateStatus status) {
	this.status = status;
    }

    public synchronized UpdateStatus getStatus() {
	return status;
    }

    public synchronized long getTimeLastUpdate() {
	return System.currentTimeMillis()-timeLastUpdate;
    }

    public void setTimeLastUpdate(long timeLastUpdate) {
	this.timeLastUpdate = timeLastUpdate;
    }

    public int getIntervalForUpdateIP() {
	return intervalForUpdateIP;
    }

    public void setIntervalForUpdateIP(int val) {
	intervalForUpdateIP = val;
    }
}