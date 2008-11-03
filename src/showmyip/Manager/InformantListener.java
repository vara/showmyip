/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package showmyip.Manager;

import showmyip.Message;

/**
 *
 * @author vara
 */
public interface InformantListener {
    
    void sendMessage(Message message);
    void connectionRefused(Message message);
}
