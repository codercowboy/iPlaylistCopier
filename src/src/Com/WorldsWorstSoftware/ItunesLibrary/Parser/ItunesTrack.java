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
 * TrackType.java
 *
 * Created on November 6, 2005, 3:25 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package Com.WorldsWorstSoftware.ItunesLibrary.Parser;

/**
 *
 * @author jason
 */
public class ItunesTrack {
    
    private int _id = -1;
    private String _name = null;
    private String _artist = null;
    private String _trackNumber = null;
    private String _album = null;
    private int _totalTime = -1;
    private String _fileLocation = null;
    private long _size = 0;
    
    /** Creates a new instance of TrackType */
    public ItunesTrack() {        
    }
    
    public ItunesTrack(ItunesTrack obj)
    {
        this.setId(obj.getId());
        this.setName(obj.getName());
        this.setArtist(obj.getArtist());
        this.setTotalTime(obj.getTotalTime());
        this.setFileLocation(obj.getFileLocation());
        this.setTrackNumber(obj.getTrackNumber());
        this.setAlbum(obj.getAlbum());
        this.setSize(obj.getSize());
    }
    
    public int getId()
    {
        return _id;
    }
    
    public void setId(int value)
    {
        _id = value;
    }
    
    public String getName()
    {
        return _name;
    }
    
    public void setName(String value)
    {
        _name = value;
    }
    
    public String getArtist()
    {
        return _artist;
    }
    
    public void setArtist(String value)
    {
        _artist = value;
    }
    
    public String getTrackNumber()
    {
        return this._trackNumber;
    }
    
    public void setTrackNumber(String value)
    {
        this._trackNumber = value;
    }
    
    public String getAlbum()
    {
        return this._album;
    }
    
    public void setAlbum(String value)
    {
        this._album = value;
    }
    
    public long getSize()
    {
        return this._size;
    }
    
    public void setSize(long value)
    {
        this._size = value;
    }
    
    public int getTotalTime()
    {
        return _totalTime;
    }
    
    public void setTotalTime(int value)
    {
        _totalTime = value;
    }
    
    public String getFileLocation()
    {
        return _fileLocation;
    }
    
    public void setFileLocation(String value)
    {
        _fileLocation = value;        
    }
    
}
