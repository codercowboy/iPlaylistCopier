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
 * PlaylistItem.java
 *
 * Created on September 13, 2006, 3:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.GUI;

import Com.WorldsWorstSoftware.ItunesLibrary.Parser.ItunesPlaylist;
import Com.WorldsWorstSoftware.ItunesLibrary.Util.ItunesPrinter;

/**
 *
 * @author jasbaker
 */
public class PlaylistItem
{
    private ItunesPlaylist _playlist = null;    
    
    public PlaylistItem(ItunesPlaylist playlist)
    {
        this._playlist = playlist;
    }
    
    public ItunesPlaylist getPlaylist()
    {
        return _playlist;
    }
    
    public String toString()    
    {        
        String value = _playlist.getName() + "   [";
        value += _playlist.getTrackIds().size() + " tracks - ";
        value += ItunesPrinter.getPrintableItunesTime(_playlist.getTotalTime()) + " - ";        
        value += ItunesPrinter.getPrintableFileSize(_playlist.getTotalSize()) + "]";
        return value;
    }
    
}
