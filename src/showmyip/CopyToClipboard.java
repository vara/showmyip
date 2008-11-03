/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package showmyip;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author wara
 */
public class CopyToClipboard implements ActionListener 
{

    protected static Clipboard clipboard;
    private final String data;

    public CopyToClipboard(String value) 
    {
        data = value;
    }

    private static void copyToClipboard(String p_) 
    {
        StringSelection data = new StringSelection(p_);
        getClipboard().setContents(data, data);
    }

    private static Clipboard getClipboard() 
    {
        if (clipboard == null) 
        {
            clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        }
        return clipboard;
    
    }

    public void actionPerformed(ActionEvent e) 
    {
        copyToClipboard(data);
    }
}
