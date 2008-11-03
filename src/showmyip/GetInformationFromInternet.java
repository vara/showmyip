/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vara
 */
public class GetInformationFromInternet {

    public GetInformationFromInternet(){
    }
    
    //simple function to check internet connection    
    public static boolean isConnected(String sUrl){
	
	try{
	//InetAddress inet = InetAddress.getByName(sUrl);
	//System.out.println("Adres hosta "+inet.getHostAddress());
	URL  url = new URL(sUrl);	
	URLConnection conn = url.openConnection();
	conn.connect();
	return true;
	}catch(Exception e){
	    System.out.println("\t"+new Date()+"\nTeraz brak polaczenia ");
	    return false;
	}
	
    }
    
    public static String getIPFromSite(WebSite web) throws ConnectException,
									 EOFException,
									 NoSuchFieldException{	
	String sUrl = web.getActualSite();
	String regexp = web.getActualRegexp();
	String tekst;
	try {
	    URL url = new URL(sUrl);
	    URLConnection connection = url.openConnection();

	    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    
	    while ((tekst = br.readLine()) != null) {		
		if(tekst.indexOf("My IP address:")!=-1){		    
		    Pattern pat = Pattern.compile(regexp);
		    Matcher matcher = pat.matcher(tekst);
		    if(matcher.find()){
			return matcher.group();			
		    }
		    throw new NoSuchFieldException("IP not found from "+sUrl);
		}   
	    }	    
	    
	} catch (Exception e) {
	    //e.printStackTrace();	    
	    throw new ConnectException("Can't connect to "+sUrl);
	}
	throw new EOFException("IP not found in Document html from "+sUrl);
    }
}
