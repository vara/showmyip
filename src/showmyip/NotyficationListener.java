/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip;

/**
 *
 * @author vara
 */
public interface NotyficationListener {
    
    void sendMessage(String message);
    void ipChanged();
    void connectionRefused();
}
