/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Manager;

import java.util.ArrayList;
import java.util.Collection;
import showmyip.Message;

/**
 *
 * @author vara
 */
public class Informant implements InformantListener{
    
    private final Collection<InformantListener> listeners = new ArrayList<InformantListener>();   
    
    public Informant(){
    }
    
    public void removeNotyficationListener(InformantListener l){
	if(l!=null)
	    listeners.remove(l);
    }
    
    public void addNotyficationListener(InformantListener l){
	if(l!=null)
	    listeners.add(l);
    }

    public void sendMessage(Message message) {
	for (InformantListener nl : listeners) {
	    nl.sendMessage(message);
	}
    }

    public void connectionRefused(Message message) {
	for (InformantListener nl : listeners) {
	    nl.connectionRefused(message);
	}
    }
    
}
