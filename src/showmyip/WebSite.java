/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

/**
 *
 * @author vara
 */
public class WebSite {

    private String actualSite = "http://www.ip-adress.com";
    private String actualRegexp = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
    
    public WebSite(){
    }

    public String getActualSite() {
	return actualSite;
    }

    public void setActualSite(String actualSite) {
	this.actualSite = actualSite;
    }

    public String getActualRegexp() {
	return actualRegexp;
    }

    public void setActualRegexp(String actualRegexp) {
	this.actualRegexp = actualRegexp;
    }
}
