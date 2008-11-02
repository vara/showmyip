/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

/**
 *
 * @author vara
 */
public interface UpdateManagerListener {

    public void start();
    public void stop();
    public void setInterval(int interval);
    public int getInterval();
    public int getStatus();
    
}
