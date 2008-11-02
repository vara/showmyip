/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

import java.util.Date;

/**
 *
 * @author vara
 */
public class MyIpInformation extends WebSite{

    private String myIp = "";
    private Date lastUpdate = new Date();
    
    public MyIpInformation(){
    }

    public String getMyIp() {
	return myIp;
    }

    public void setMyIp(String myIp) {
	this.myIp = myIp;
    }

    public Date getLastUpdate() {
	return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
	this.lastUpdate = lastUpdate;
    }
}
