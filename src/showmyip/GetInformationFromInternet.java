/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vara
 */
public class GetInformationFromInternet {

    public GetInformationFromInternet(){
    }
    
    public static String getIPFromSite(String sUrl,String regexp) throws Exception{
	
	URL url;
	String tekst;
	try {
	    url = new URL(sUrl);
	    URLConnection connection = url.openConnection();

	    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    
	    while ((tekst = br.readLine()) != null) {		
		if(tekst.indexOf("My IP address:")!=-1){		    
		    Pattern pat = Pattern.compile(regexp);
		    Matcher matcher = pat.matcher(tekst);
		    if(matcher.find()){
			return matcher.group();			
		    }
		    throw new Exception("IP not found from "+sUrl);
		}   
	    }	    
	    
	} catch (Exception e) {
	    //e.printStackTrace();	    
	    throw new Exception("Can't connect to "+sUrl);
	}
	throw new Exception("IP not found from "+sUrl);
    }
}
