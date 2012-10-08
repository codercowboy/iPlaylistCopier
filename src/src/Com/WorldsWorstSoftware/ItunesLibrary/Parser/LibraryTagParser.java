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
 * LibraryTagParser.java
 *
 * Created on September 11, 2006, 9:09 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


package Com.WorldsWorstSoftware.ItunesLibrary.Parser;

import Com.WorldsWorstSoftware.Util.PerformanceTimer;
import Com.WorldsWorstSoftware.XMLTagParser.IInterestingTagHandler;
import Com.WorldsWorstSoftware.XMLTagParser.ITag;
import Com.WorldsWorstSoftware.XMLTagParser.XMLInterestingTagParser;

/**
 *
 * @author jasbaker
 */
public class LibraryTagParser implements IInterestingTagHandler
{
    
    /** Creates a new instance of LibraryTagParser */
    public LibraryTagParser()
    {
    }
    
    private boolean _verbose = false;
    private static boolean _debug = false;
    private PerformanceTimer _performanceTimer = null;
    
    protected static final int MODE_NONE = 0;
    protected static final int MODE_TRACKS = 1;
    protected static final int MODE_PLAYLISTS = 2;
    
    protected ItunesLibrary _library = null;
    protected ItunesTrack _currentTrack = null;
    protected ItunesPlaylist _currentPlaylist = null;
    
    protected int _trackCount = 0;
    protected int _playlistCount = 0;
    
    protected int _currentMode = MODE_NONE;
    
    private int _lastTag = LibraryTagMap.TAG_NONE;
    
    private LibraryTagMap _tagMap = null;
    public ItunesLibrary parseLibrary(String XMLLibraryFileLocation, boolean verbose, boolean debug)
    {
        _performanceTimer = new PerformanceTimer();
        _performanceTimer.initialize();
        
        this._verbose = verbose;
        this._debug = debug;
        
        if (this._verbose)
        {
            System.out.println("Reading Itunes Library File: " + XMLLibraryFileLocation);
        }
        
        this._currentMode = MODE_NONE;
        this._lastTag = LibraryTagMap.TAG_NONE;
        this._library = new ItunesLibrary();
        _tagMap = new LibraryTagMap();
        
        XMLInterestingTagParser parser = null;
        
        //bug fix: dont let the parser bother apple.com w/ DTD validation requests
        //thanks to Pawe³ Stobiñski for example code: http://www.velocityreviews.com/forums/t139773-saxparser-ignore-ltdoctypegt-line.html
        parser = new XMLInterestingTagParser(
                XMLLibraryFileLocation,
                _tagMap.getInterestingTagManager(),
                (IInterestingTagHandler) this,
                false,
                this._debug);
        //end bug fix 09062006
        
        try
        {
            parser.parse();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
        
        if (this._verbose)
        {
            System.out.println(" " + _library.getPlaylists().size() + " Playlists");
            System.out.println();
            System.out.flush();
        }
        
        return this._library;
    }
    
    public void handleInterestingTag(int tagID, ITag tag)
    {
        switch (this._currentMode)
        {
            case MODE_NONE:
                if (tagID == LibraryTagMap.TAG_TRACKS)
                {
                    if (this._verbose)
                    {
                        System.out.print("  Reading Track Information");
                        System.out.flush();
                    }
                    
                    this._currentMode = MODE_TRACKS;
                    return;
                }
                break;
            case MODE_TRACKS:
                if (tagID == LibraryTagMap.TAG_PLAYLISTS)
                {
                    //add the last track to the library if it exists
                    if (_currentTrack != null)
                    {
                        _library.addTrack(_currentTrack);
                        this._trackCount++;
                    }
                    
                    if (this._verbose)
                    {
                        System.out.println(" " + _library.getTracks().size() + " Tracks");
                        
                        if (this._debug)
                        {
                            System.out.println("Track Parse Time: " + _performanceTimer.getTimeElapsedSinceLast());
                        }
                        
                        System.out.print("  Reading Playlist Information");
                        System.out.flush();
                    }
                    
                    this._currentMode = MODE_PLAYLISTS;
                    return;
                }
                this.handleTrackTag(tagID, tag);
                break;
            case MODE_PLAYLISTS:
                this.handlePlaylistTag(tagID, tag);
                break;
            default:
                throw new RuntimeException("Unknown Mode:" + _currentMode);
        }
    }
    
    
    
    public void handleTrackTag(int tagID, ITag tag)
    {
        switch (tagID)
        {
            case LibraryTagMap.TAG_TRACK_ID:
            case LibraryTagMap.TAG_NAME:
            case LibraryTagMap.TAG_ARTIST:
            case LibraryTagMap.TAG_ALBUM:
            case LibraryTagMap.TAG_SIZE:
            case LibraryTagMap.TAG_TOTAL_TIME:
            case LibraryTagMap.TAG_LOCATION:
                this._lastTag = tagID;
                break;
                
            case LibraryTagMap.TAG_INTEGER:
            case LibraryTagMap.TAG_STRING:
                this.handleTrackData(tag);
                break;
            default:
                throw new RuntimeException("Uhandled Track-Related Tag: " + tagID);
        }
    }
    
    private void handleTrackData(ITag tag)
    {
        switch (this._lastTag)
        {
            case LibraryTagMap.TAG_TRACK_ID:
                //the track id signifies a new track is being parsed
                if (_currentTrack != null)
                {
                    _library.addTrack(_currentTrack);
                    this._trackCount++;
                    
                    //print out a user-friendly "." every five hundred tracks to show progress
                    if (this._verbose && ((_library.getTracks().size() % 500) == 0))
                    {
                        System.out.print(".");
                        System.out.flush();
                    }
                }
                
                _currentTrack = new ItunesTrack();
                
                _currentTrack.setId(Integer.parseInt(tag.getInnerText()));
                break;
                
            case LibraryTagMap.TAG_NAME:
                _currentTrack.setName(tag.getInnerText());
                break;
                
            case LibraryTagMap.TAG_ARTIST:
                _currentTrack.setArtist(tag.getInnerText());
                break;
                
            case LibraryTagMap.TAG_ALBUM:
                _currentTrack.setAlbum(tag.getInnerText());
                break;
                
            case LibraryTagMap.TAG_SIZE:
                _currentTrack.setSize(Long.parseLong(tag.getInnerText()));
                break;
                
            case LibraryTagMap.TAG_TOTAL_TIME:
                _currentTrack.setTotalTime(Integer.parseInt(tag.getInnerText()));
                break;
                
            case LibraryTagMap.TAG_LOCATION:
                _currentTrack.setFileLocation(tag.getInnerText());
                break;
        }
        this._lastTag = LibraryTagMap.TAG_NONE;
    }
    
    public void handlePlaylistTag(int tagID, ITag tag)
    {
        
        switch (tagID)
        {
            case LibraryTagMap.TAG_NAME:
            case LibraryTagMap.TAG_PLAYLIST_ID:
            case LibraryTagMap.TAG_TRACK_ID:
                this._lastTag = tagID;
                break;
                
            case LibraryTagMap.TAG_INTEGER:
            case LibraryTagMap.TAG_STRING:
                this.handlePlaylistData(tag);
                break;
            default:
                throw new RuntimeException("Uhandled Playlist-Related Tag: " + tagID);
        }
    }
    
    private void handlePlaylistData(ITag tag)
    {
        switch (this._lastTag)
        {
            case LibraryTagMap.TAG_NAME:
                //the Name tag in playlist mode signifies a new playlist is being parsed
                if (_currentPlaylist != null)
                {
                    _library.addPlaylist(_currentPlaylist);
                    this._playlistCount++;
                    //print out a user-friendly "." every three playlists to show progress
                    if (this._verbose && ((_library.getPlaylists().size() % 3) == 0))
                    {
                        System.out.print(".");
                        System.out.flush();
                    }
                }
                
                _currentPlaylist = new ItunesPlaylist(_library);
                
                _currentPlaylist.setName(tag.getInnerText());
                break;
                
            case LibraryTagMap.TAG_PLAYLIST_ID:
                _currentPlaylist.setId(tag.getInnerText());
                break;
                
            case LibraryTagMap.TAG_TRACK_ID:
                _currentPlaylist.addTrackId(Integer.parseInt(tag.getInnerText()));
                break;
        }
        
        this._lastTag = LibraryTagMap.TAG_NONE;
    }
    
    
}
