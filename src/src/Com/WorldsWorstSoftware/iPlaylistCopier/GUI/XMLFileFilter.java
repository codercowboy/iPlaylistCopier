/*
 * Source code from iPlaylist Copier is (C) Jason Baker 2006
 * 
 * Please make an effort to document your additions to this source code file,
 * so future developers can give you credit where due.
 * 
 * Please include this copyright information in these source files when
 * redistributing source code. 
 *
 * Please make note of this copyright information in documentation for
 * binary redistributions that contain any or all of the source code. 
 *
 * If you are having any trouble understanding the meaning of this code
 * email jason directly at jason@onejasonforsale.com.
 *
 * Thanks, and happy coding!
 */

/*
 * XMLFileFilter.java
 *
 * Created on September 14, 2006, 1:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.GUI;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author jasbaker
 */
public class XMLFileFilter extends FileFilter
{
    
    /** Creates a new instance of XMLFileFilter */
    public XMLFileFilter()
    {
    }

    public boolean accept(File f)    
    {
        if (f.isDirectory())
        {
            //let the file chooser show directories
            return true;
        }
        
        String fileName = f.getName().toLowerCase();
        int pos = fileName.lastIndexOf(".xml");
        if (pos == -1)
        {
            //.xml not found in file name
            return false;
        }
        
        int len = fileName.length();
        if (pos != len - 4)
        {
            //the .xml is not at the end of the file
            return false;
        }
        
        return true;
        
        
    }

    public String getDescription()
    {
        return "itunes Library (*.xml)";
    }
    
}
