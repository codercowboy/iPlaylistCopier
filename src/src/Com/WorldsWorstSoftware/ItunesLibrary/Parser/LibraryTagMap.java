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
 * LibraryTagMap.java
 *
 * Created on September 11, 2006, 9:23 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/* for our purposes, we are interested in the following subset of the itunes library DOM:
 * we will ignore the meaningless dict and array stuff since they carry multiple meanings

<plist version="1.0">
<dict>
        <key>Application Version</key><string>6.0.1</string>
        <key>Music Folder</key><string>file://localhost/F:/itunes/</string>
        <key>Tracks</key>
        <dict>
                <key>41</key>
                <dict>
                        <key>Track ID</key><integer>41</integer>
                        <key>Name</key><string>The Hollow</string>
                        <key>Artist</key><string>A Perfect Circle</string>
                        <key>Album</key><string>Mer de Noms</string>
                        <key>Size</key><integer>4299529</integer>
                        <key>Total Time</key><integer>179043</integer>
                        <key>Location</key><string>file://localhost/F:/itunes/A%20Perfect%20Circle/Mer%20de%20Noms/01%20The%20Hollow.mp3</string>
                </dict>
        </dict>
        <key>Playlists</key>
        <array>
                <dict>
                        <key>Name</key><string>Library</string>
                        <key>Playlist ID</key><integer>12360</integer>
                        <array>
                                <dict>
                                        <key>Track ID</key><integer>3201</integer>
                                </dict>
                                <dict>
                                        <key>Track ID</key><integer>3202</integer>
                                </dict>
 
                        </array>
                </dict>
 
        </array>
</dict>
</plist>
 
 */

package Com.WorldsWorstSoftware.ItunesLibrary.Parser;

import Com.WorldsWorstSoftware.XMLTagParser.InterestingTagManager;
import Com.WorldsWorstSoftware.XMLTagParser.Tag;
import java.util.HashMap;

/**
 *
 * @author jasbaker
 */
class LibraryTagMap
{
    private InterestingTagManager _tagManager = null;
    
    public static final int TAG_NONE = -1;
    public static final int TAG_TRACKS = 0;
    public static final int TAG_TRACK_ID = 1;
    public static final int TAG_NAME = 2;
    public static final int TAG_ARTIST = 3;
    public static final int TAG_ALBUM = 4;
    public static final int TAG_SIZE = 5;
    public static final int TAG_TOTAL_TIME = 6;
    public static final int TAG_LOCATION = 7;
    public static final int TAG_PLAYLISTS = 8;
    public static final int TAG_PLAYLIST_ID = 9;
    public static final int TAG_INTEGER = 10;
    public static final int TAG_STRING = 11;
    public static final int TOTAL_TAG_COUNT = 12;        
    
    /** Creates a new instance of LibraryTagMap */
    public LibraryTagMap()
    {        
        InterestingTagManager tagMgr = new InterestingTagManager();
        Tag tag = null;
        int id = -1;
        
        
        
        id = this.TAG_TRACKS;
        tag = new Tag("key", "Tracks");
        tagMgr.addInterestingTag(id, tag);
        
        id = this.TAG_TRACK_ID;
        tag = new Tag("key", "Track ID");        
        tagMgr.addInterestingTag(id, tag);
        
        id = this.TAG_NAME;
        tag = new Tag("key", "Name");        
        tagMgr.addInterestingTag(id, tag);
       
        id = this.TAG_ARTIST;
        tag = new Tag("key", "Artist");        
        tagMgr.addInterestingTag(id, tag);
        
        id = this.TAG_ALBUM;
        tag = new Tag("key", "Album");        
        tagMgr.addInterestingTag(id, tag);
        
        id = this.TAG_SIZE;
        tag = new Tag("key", "Size");        
        tagMgr.addInterestingTag(id, tag);
        
        id = this.TAG_TOTAL_TIME;
        tag = new Tag("key", "Total Time");        
        tagMgr.addInterestingTag(id, tag);
        
        id = this.TAG_LOCATION;
        tag = new Tag("key", "Location");        
        tagMgr.addInterestingTag(id, tag);
        
        id = this.TAG_PLAYLISTS;
        tag = new Tag("key", "Playlists");        
        tagMgr.addInterestingTag(id, tag);
        
        id = this.TAG_PLAYLIST_ID;
        tag = new Tag("key", "Playlist ID");        
        tagMgr.addInterestingTag(id, tag);            
        
        id = this.TAG_INTEGER;
        tag = new Tag("integer", "");        
        tagMgr.addInterestingTag(id, tag);
        
        id = this.TAG_STRING;
        tag = new Tag("string", "");     
        tagMgr.addInterestingTag(id, tag);
        
        this._tagManager = tagMgr;
    }
    
    public InterestingTagManager getInterestingTagManager()
    {
        return this._tagManager;
    }
    
   
    
}
