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
 * ParserThread.java
 *
 * Created on September 14, 2006, 11:05 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.GUI;

import Com.WorldsWorstSoftware.ItunesLibrary.Parser.ItunesLibrary;
import Com.WorldsWorstSoftware.ItunesLibrary.Parser.LibraryTagParser;

/**
 *
 * @author jasbaker
 */
public class ParserThread extends Thread
{
    private String _libraryLocation = null;
    private ItunesLibrary _library = null;
    private IProgressHelper _progressHelper = null;
    
    public ParserThread(String libraryLocation, IProgressHelper progressHelper)
    {
        _libraryLocation = libraryLocation;
        this._progressHelper = progressHelper;
    }
    
    public void run()
    {
        GUIParser parser = new GUIParser(_libraryLocation, _progressHelper);
        this._library = parser.parse();
        this._progressHelper.finishTask();
    }       
    
    public ItunesLibrary getLibrary()
    {
        return _library;
    }
    
}
