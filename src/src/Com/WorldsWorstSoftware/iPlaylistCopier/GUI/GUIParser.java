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
 * GUIParser.java
 *
 * Created on September 14, 2006, 12:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.GUI;

import Com.WorldsWorstSoftware.ItunesLibrary.Parser.ItunesLibrary;
import Com.WorldsWorstSoftware.ItunesLibrary.Parser.LibraryTagParser;
import Com.WorldsWorstSoftware.XMLTagParser.IInterestingTagHandler;
import Com.WorldsWorstSoftware.XMLTagParser.ITag;

/**
 *
 * @author jasbaker
 */
public class GUIParser extends LibraryTagParser implements IInterestingTagHandler
{
    
    private String _libraryLocation = null;
    private IProgressHelper _progressHelper = null;
    
    public GUIParser(String libraryLocation, IProgressHelper progressHelper)
    {
        this._libraryLocation = libraryLocation;
        this._progressHelper = progressHelper;
    }
    
    public ItunesLibrary parse()
    {
        this._progressHelper.updateStatus("Reading Tracks..");
        return super.parseLibrary(_libraryLocation, false, false);
    }
    
    public void handleInterestingTag(int tagID, ITag tag)
    {
        
        if (this._currentMode == this.MODE_TRACKS)
        {
            if (this._trackCount % 200 == 0)
            {
                this._progressHelper.updateStatus("Reading Tracks.. " + _trackCount);
            }
        }
        else if (this._currentMode == this.MODE_PLAYLISTS)
        {
            if (this._playlistCount % 3 == 0)
            {
                this._progressHelper.updateStatus("Reading Playlists.. " + _playlistCount);
            }
            
        }
        super.handleInterestingTag(tagID, tag);
        
    }
    
    
}
