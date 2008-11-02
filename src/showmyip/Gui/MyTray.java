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

/**
 * @author vara
 */

public class MyTray extends TrayIcon
{
    private Image image;
    private PopupMenu popup;
        
    
    public MyTray(String imagePath)
    {
        super(Toolkit.getDefaultToolkit().getImage(imagePath));        
        init();
    }    
    public MyTray(Image image){
	super(image);
	init();
    }
    
    private void init(){
	
	popup = new PopupMenu();
	setImageAutoSize(true);
    }
    
    public void createPopup(MenuItem [] menuitem)
    {
        popup = new PopupMenu();
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
