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
 * CopierThread.java
 *
 * Created on September 15, 2006, 10:23 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.GUI;

import Com.WorldsWorstSoftware.ItunesLibrary.Parser.ItunesPlaylist;

/**
 *
 * @author jasbaker
 */
public class CopierThread extends Thread
{
    private IProgressHelper _progressHelper = null;    
    private GUICopier _guiCopier = null;
    
    public CopierThread(ItunesPlaylist playlist, String nameFormat, String outputFolder, IProgressHelper progressHelper )
    {
        this._progressHelper = progressHelper;
        this._guiCopier = new GUICopier(playlist, nameFormat, outputFolder, progressHelper);                
    }
    
     public void run()
    {
        this._guiCopier.copyPlaylist();
        this._progressHelper.finishTask();
    }   
    
}
