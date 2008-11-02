/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author vara
 */
public class Notyfication implements NotyficationListener{
    
    private final Collection<NotyficationListener> listeners = new ArrayList<NotyficationListener>();   
    
    public Notyfication(){
    }
    
    public void removeNotyficationListener(NotyficationListener l){
	if(l!=null)
	    listeners.remove(l);
    }
    
    public void addNotyficationListener(NotyficationListener l){
	if(l!=null)
	    listeners.add(l);
    }

    public void sendMessage(String message) {
	for (NotyficationListener nl : listeners) {
	    nl.sendMessage(message);
	}
    }

    public void ipChanged() {
	for (NotyficationListener nl : listeners) {
	    nl.ipChanged();
	}
    }

    public void connectionRefused() {
	for (NotyficationListener nl : listeners) {
	    nl.connectionRefused();
	}
    }
    
}
