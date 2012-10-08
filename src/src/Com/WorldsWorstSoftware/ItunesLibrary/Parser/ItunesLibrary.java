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
 * LibraryType.java
 *
 * Created on November 6, 2005, 3:47 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package Com.WorldsWorstSoftware.ItunesLibrary.Parser;
import java.util.*;
/**
 *
 * @author jason
 */
public class ItunesLibrary {
    
    private HashMap _tracks = new HashMap();
    private ArrayList _playlists = new ArrayList();
    /** Creates a new instance of LibraryType */
    public ItunesLibrary() {
    }
    
    public void addTrack(ItunesTrack track)
    {        
        _tracks.put(String.valueOf(track.getId()), track);
    }
    
    public ItunesTrack getTrackById(int trackId)
    {
        if (!_tracks.containsKey(String.valueOf(trackId)))
        {
            throw new RuntimeException ("Can't find the track with id: " + trackId);
        }
        return (ItunesTrack) _tracks.get(String.valueOf(trackId));
    }
    
    public void addPlaylist(ItunesPlaylist playlist)
    {
        _playlists.add(playlist);
    }
    
    public ArrayList getPlaylists()
    {
        return _playlists;
    }
    
    public ItunesPlaylist findPlayList(String playlistNameorID)
    {
        Iterator it = this.getPlaylists().iterator();
        while (it.hasNext())
        {
            ItunesPlaylist playlist = (ItunesPlaylist) it.next();
            if (playlist.getName().equals(playlistNameorID))
            {
                return playlist;
            }
            if (playlist.getId().equals(playlistNameorID))
            {
                return playlist;
            }
        }
        throw new RuntimeException("Cannot find playlist with name or id \"" + playlistNameorID + "\"");
    }
    
    public HashMap getTracks()
    {
        return _tracks;
    }
}
