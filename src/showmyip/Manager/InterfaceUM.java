/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Manager;

/**
 *
 * @author vara
 */
public interface InterfaceUM {
    
    int getIntervalForCheckConnection();
    void setIntervalForCheckConnection(int interval);
    int getIntervalForUpdateIP();
    void setIntervalForUpdateIP(int val);
    void setLoopCheckedUM(boolean val);
    boolean isLoopCheckedUM();
    void setStatus(UpdateManager.UpdateStatus status);
    UpdateManager.UpdateStatus getStatus();
    long getTimeLastUpdate();
    void setTimeLastUpdate(long val);
    AbstractUpdateManagerListener getUpdateManagerListener();
    void setUpdateManagerListener(AbstractUpdateManagerListener updateManagerListener);
}
