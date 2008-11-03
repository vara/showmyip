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
public interface InfoYourStatusIP {
    
    Date getDateLastUpdate();
    String getIP();
    String getWebSiteFromUpdate();
    WebSite getWebSiteClassFromUpdate();
}
