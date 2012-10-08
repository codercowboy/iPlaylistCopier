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
 * PlayListType.java
 *
 * Created on November 6, 2005, 3:32 PM
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
public class ItunesPlaylist
{
    
    private String _name = null;
    private String _id = null;
    private  ArrayList _tracks = new ArrayList();
    private ArrayList _trackIds = new ArrayList();
    private ItunesLibrary _library = null;
    private long _totalTime = 0;
    private long _totalSize = 0;
    /** Creates a new instance of PlayListType */
    
    public ItunesPlaylist(ItunesLibrary library)
    {
        if (library == null)
        {
            throw new RuntimeException("argument 'library' cannot be null");
        }                
        
        _library = library;
    }
    
    public String getName()
    {
        return _name;
    }
    
    public void setName(String value)
    {
        _name = value;
    }
    
    public String getId()
    {
        return this._id;
    }
    
    public void setId(String value)
    {
        this._id = value;
    }
    
    public ArrayList getTracks()
    {
        populateTracksArray();
        return _tracks;
    }
    
    public ArrayList getTrackIds()
    {
        return _trackIds;
    }
    
    public void addTrackId(int trackID)
    {
        _trackIds.add(String.valueOf(trackID));
    }
    
    public long getTotalTime()
    {
        populateTracksArray();
        return _totalTime;
    }
    
    public long getTotalSize()
    {
        populateTracksArray();
        return _totalSize;
    }
    
    private void populateTracksArray()
    {
        if (_tracks.size() == 0 && _trackIds.size() != 0)
        {
            long trackNumber = 1;
            int bufferedTrackNumberSpaces = String.valueOf(_trackIds.size()).length();        
            //populate track array with tracks from library
            Iterator it = _trackIds.iterator();
            while (it.hasNext())
            {
                int trackId = Integer.parseInt((String) it.next());
                ItunesTrack track = _library.getTrackById(trackId);
                _totalTime += track.getTotalTime();
                _totalSize += track.getSize();
                //make a copy of the track because the tracknumber is diff for diff playlists
                ItunesTrack trackCopy = new ItunesTrack(track);
                trackCopy.setTrackNumber(this.getBufferedLong(trackNumber, bufferedTrackNumberSpaces));
                _tracks.add(trackCopy);
                trackNumber++;
            }
        }
    }
    
    private static String getBufferedLong(long num, int numberOfSpaces)
    {
        String value = String.valueOf(num);
        while (value.length() < numberOfSpaces)
        {
            value = "0" + value;
        }
        return value;
    }
    
    
}
