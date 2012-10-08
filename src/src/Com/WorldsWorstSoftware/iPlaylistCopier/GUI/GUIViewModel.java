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
 * GUIViewModel.java
 *
 * Created on September 13, 2006, 3:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.GUI;

import Com.WorldsWorstSoftware.ItunesLibrary.Parser.ItunesLibrary;
import Com.WorldsWorstSoftware.ItunesLibrary.Parser.ItunesPlaylist;
import Com.WorldsWorstSoftware.ItunesLibrary.Parser.ItunesTrack;
import Com.WorldsWorstSoftware.ItunesLibrary.Util.LibraryFinder;
import java.util.ArrayList;

/**
 *
 * @author jasbaker
 */
public class GUIViewModel
{
    private String _libraryLocation = null;
    private String _trackNameFormat = null;
    private ItunesLibrary _library = null;
    private ItunesPlaylist _playlist = null;
    private ItunesTrack _exampleTrack = null;        
    
    private PlaylistItem[] _playlistItems = null;        
    
    public GUIViewModel()
    {
        
        _trackNameFormat = Com.WorldsWorstSoftware.ItunesLibrary.Util.Util.DefaultNameFormat;
        
        _exampleTrack = new ItunesTrack();
        _exampleTrack.setName("TrackName");
        _exampleTrack.setTrackNumber("01");
        _exampleTrack.setAlbum("Album");
        _exampleTrack.setArtist("Artist");                
    }
    
    public boolean setLibraryLocation(String libraryLocation)
    {
        LibraryFinder libraryFinder = new LibraryFinder(System.out, false);        
        if (libraryFinder.fileIsItunesLibrary(libraryLocation))
        {
            this._libraryLocation = libraryLocation;
            this._library = null;
            _playlistItems = null;
            _playlist = null;
            return true;
        }
        return false;
    }
    
    public void setLibrary(ItunesLibrary library)
    {
        this._library = library;
    }
    
    public void setPlaylist(PlaylistItem item)
    {
        if (item instanceof FakePlaylistItem)
        {
            return;
        }
        if (item != null)
        {
            this._playlist = item.getPlaylist();
        }
    }
    
    public ItunesPlaylist getPlaylist()
    {
        return this._playlist;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Track Name Format ">
    public void setTrackNameFormat(String value)
    {
        this._trackNameFormat = value;
    }
    
    public String getTrackNameFormat()
    {
        return this._trackNameFormat;
    }
    
    public String getExampleTrackName()
    {
        return Com.WorldsWorstSoftware.ItunesLibrary.Util.Util.formatTrackName(_exampleTrack, _trackNameFormat);
    }
    // </editor-fold>
    
    public boolean LibrarySelected()
    {
        return (_library != null);
    }
    
    public boolean PlaylistSelected()
    {
        return (LibrarySelected() && _playlist != null);
    }
    
    public String getLibraryLocation()
    {
        if (_libraryLocation == null || _libraryLocation.equals(""))
        {
            return "Please select your library..";
        }
        else
        {
            return _libraryLocation;
        }
    }
    
    public PlaylistItem[] getPlaylists()
    {
        if (_playlistItems == null)
        {
            if (LibrarySelected())
            {
                ArrayList playlists = _library.getPlaylists();
                
                _playlistItems = new PlaylistItem[playlists.size() + 1];
                
                _playlistItems[0] = new FakePlaylistItem("[Select A Playlist]");
                
                for (int i = 0; i < playlists.size(); i++)
                {                    
                    _playlistItems[i+1] = new PlaylistItem((ItunesPlaylist) playlists.get(i));
                }
            }
            else
            {
                //library not selected
                _playlistItems = new PlaylistItem[1];
                _playlistItems[0] = new FakePlaylistItem("[Select A Library First]");
            }
        }
        
        return _playlistItems;
    }
    
    public String[] getTracks()
    {
        //TODO: optimize this to not always be generating the track list..
        ArrayList tracks = new ArrayList();
        if (LibrarySelected() == false)
        {
            tracks.add("[Select A Library]");
        }
        else if (PlaylistSelected() == false)
        {
            tracks.add("[Select A Playlist]");
        }
        else
        {
            ArrayList trackList = _playlist.getTracks();
            for (int i = 0; i < trackList.size(); i++)
            {
                ItunesTrack track = (ItunesTrack) trackList.get(i);
                String trackName = Com.WorldsWorstSoftware.ItunesLibrary.Util.Util.formatTrackName(track, this.getTrackNameFormat());
                tracks.add(trackName);
            }
        }
        
        return (String[]) tracks.toArray(new String[0]);
    }
}
