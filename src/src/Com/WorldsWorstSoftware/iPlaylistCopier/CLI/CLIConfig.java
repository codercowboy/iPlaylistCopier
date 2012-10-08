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
 * CLIConfig.java
 *
 * Created on September 2, 2006, 7:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.CLI;

import Com.WorldsWorstSoftware.ItunesLibrary.Util.Util;
import java.io.PrintStream;

/**
 *
 * @author jason
 */
public class CLIConfig implements ICLIConfig
{
    
    /** Creates a new instance of CLIConfig */
    public CLIConfig()
    {
        this.setNameFormat(Util.DefaultNameFormat);
    }        
    
    private boolean _debugMode = false;
    public boolean getDebugMode()
    {
        return _debugMode;
    }
    
    public void setDebugMode(boolean value)
    {
        _debugMode = value;
    }
    
    private boolean _verboseMode = false;
    public boolean getVerboseMode()
    {
        return _verboseMode;
    }
    
    public void setVerboseMode(boolean value)
    {
        _verboseMode = value;
    }
    
    private boolean _listMode = false;
    public boolean getListMode()
    {
        return _listMode;
    }
    
    public void setListMode(boolean value)
    {
        _listMode = value;
    }
    
    private boolean _listFilenameMode = false;
    public boolean getListFilenameMode()
    {
        return _listFilenameMode;
    }
    
    public void setListFilenameMode(boolean value)
    {
        _listFilenameMode = value;
    }
    
    private String _nameFormat = null;
    public String getNameFormat()
    {
        return _nameFormat;
    }
    
    public void setNameFormat(String value)
    {
        _nameFormat = value;
    }
    
    private String _playlistNameorId = null;
    public String getPlaylistNameorId()
    {
        return _playlistNameorId;
    }
    
    public void setPlaylistNameorId(String value)
    {
        _playlistNameorId = value;
    }
    
    private String _outputPath = null;
    public String getOutputPath()
    {
        return _outputPath;
    }
    
    public void setOutputPath(String value)
    {
        _outputPath = value;
    }
    
    private String _libraryLocation = null;
    public String getLibraryLocation()
    {
        return _libraryLocation;
    }
    
    public void setLibraryLocation(String value)
    {
        _libraryLocation = value;
    }        
    
    public String toString()
    {
        String value = new String();
        String lineSeperator = System.getProperty("line.separator");
        value += "Configuration Information:" + lineSeperator;        
        value += "\tLibrary Location is " + getFolderForPrinting(this.getLibraryLocation()) + lineSeperator;
        value += "\tPlaylist Name or ID is " + getFolderForPrinting(this.getPlaylistNameorId()) + lineSeperator;
        value += "\tName Format is " + getFolderForPrinting(this.getNameFormat()) + lineSeperator;
        value += "\tOutput Folder is " + getFolderForPrinting(this.getOutputPath()) + lineSeperator;
        value += lineSeperator;
        
        if (this.getDebugMode())
        {
            value += "\tDebug mode is ON." + lineSeperator;
        }
        value += "\tVerbose Mode is " + (this.getVerboseMode() ? "ON" : "OFF") + lineSeperator;
        value += "\tList Mode is " + (this.getListMode() ? "ON" : "OFF") + lineSeperator;        
        return value;
    }
    
    private String getFolderForPrinting(String folder)
    {
        if (folder == null || folder.equals(""))
        {
            return "[undefined]";
        }
        else
        {
            return "\"" + folder + "\"";
        }
    }
    
}
