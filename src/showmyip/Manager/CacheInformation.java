/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Manager;

/**
 *
 * @author vara
 */
public class CacheInformation {
    
    private StringBuffer sb = new StringBuffer();
    
    public CacheInformation(){
	
	for(int i=0;i<10;i++){
	    sb.append(""+i+"\n");
	}
	
	System.out.println(""+sb);
    }
    public static void main(String[] args){
	new CacheInformation();
    }
}
