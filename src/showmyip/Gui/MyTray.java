/*
 * MyTray.java
 *
 * Created on 15 stycze� 2008, 04:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package showmyip.Gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author vara
 */

public class MyTray extends TrayIcon
{
    private Image image;
    private PopupMenu popup;
        
    
    public MyTray(String imagePath)
    {	super(Toolkit.getDefaultToolkit().getImage(imagePath));        	
	init();
    }    
    public MyTray(Image image){
	super(image);
	init();
    }
    
    private void init(){
	
	popup = new PopupMenu();
	//setImageAutoSize(true);
	addMouseListener(new MouseListener() {

	    public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Clicked \nLocation on screen "+e.getLocationOnScreen()+
				    "\nLocation On source component"+e.getPoint());
		
	    }

	    public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed");
	    }

	    public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
	    }

	    public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered");
	    }

	    public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited ");
	    }
	});
    }
    
    public void createPopup(MenuItem [] menuitem,String menuName)
    {
        popup = new PopupMenu(menuName);	
        if(menuitem!=null)
            for(int i=0;i<menuitem.length;i++)
            {
                popup.add(menuitem[i]);
            }
        setPopupMenu(popup);
    }
    
    public void ustawImage(String path)
    {
        image = Toolkit.getDefaultToolkit().getImage(path);
        setImage(image);     
    }  
    
}
