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
 * LibraryFinder.java
 *
 * Created on September 7, 2006, 4:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.ItunesLibrary.Util;

import Com.WorldsWorstSoftware.Util.StringReplacer;
import java.io.*;

/**
 *
 * @author jasbaker
 */
public class LibraryFinder
{
    
    /** Creates a new instance of LibraryFinder */
    public LibraryFinder(PrintStream out, boolean debug)
    {
        if (out == null)
        {
            throw new RuntimeException("out is null");
        }
        
        this._out = out;
        this._debug = debug;
    }
    
    private boolean _debug = false;
    private PrintStream _out = null;
        
    public String FindLibrary()
    {        
        boolean osIsMacosx = System.getProperty("os.name").toLowerCase().startsWith("mac os x");
        String userHome = System.getProperty("user.home");
        String libraryFilename = "";
        
        if (_debug)
        {
            _out.println("Library Finder Detected " + ((osIsMacosx) ? "OS X" : "Windows"));
        }
        
        if (osIsMacosx)
        {
            //thanks to Jay of the indyJt weblog for os xitunes lib location hints
            //http://www.indyjt.com/blog/?p=51
            
            //test ~/Music/iTunes/iTunes Music Library.xml
            libraryFilename = createPath(userHome, "Music/iTunes/iTunes Music Library.xml");
            if (fileIsItunesLibrary(libraryFilename))
            {
                return libraryFilename;
            }
            
            //test ~/Documents/iTunes/iTunes Music Library.xml
            libraryFilename = createPath(userHome, "Documents/iTunes/iTunes Music Library.xml");
            if (fileIsItunesLibrary(libraryFilename))
            {
                return libraryFilename;
            }
        }
        else //not macosx
        {
            //test ~/My Documents/My Music/iTunes/FILE HERE.xml
            libraryFilename = createPath(userHome, "My Documents/My Music/iTunes/iTunes Music Library.xml");
            if (fileIsItunesLibrary(libraryFilename))
            {
                return libraryFilename;
            }
        }
        
        //no library was found, throw a runtime error describing the problem
        return "";
    }
    
    private String getUnableToLocateLibraryError(boolean macosx)
    {
        String value = "";
        String lineSeperator = System.getProperty("line.separator");
        value += "Unable to automatically locate your Itunes Library file." + lineSeperator;
        value += lineSeperator;
        value += "  The library is typically stored in one of the following" + lineSeperator;
        value += "     locations on a " + (macosx ? "Mac OS X" : "Windows") + " system:" + lineSeperator;
        value += lineSeperator;
        if (macosx)
        {
            value += "       ~/Music/iTunes/iTunes Music Library.xml" + lineSeperator;
            value += "       ~/Documents/iTunes/iTunes Music Library.xml" + lineSeperator;
        }
        else
        {
            value += "        ~\\My Documents\\My Music\\iTunes\\iTunes Music Library.xml" + lineSeperator;
        }
        value += lineSeperator;
        value += "  Please locate your Itunes Library file and specify it " + lineSeperator;
        value += "   explicitly using the -l command line argument." + lineSeperator;
        return value;
    }
    
    private String createPath(String basepath, String pathaddon)
    {        
        String newPathAddon = StringReplacer.replace(pathaddon, "/", File.separator);     
        return (new File(basepath)).getPath() + File.separator + newPathAddon;
    }
        
    public boolean fileIsItunesLibrary(String libraryFilename)
    {
        if (_debug)
        {
            _out.println(" Looking for library at: " + libraryFilename);
        }
        
        if (libraryFilename == null || libraryFilename.equals(""))
        {
            if (_debug)
            {
                _out.println("  Error: LibraryFilename is empty or null.");
            }
            return false;
        }
        
        File libraryFile = new File(libraryFilename);
        if (libraryFile == null)
        {
            if (_debug)
            {
                _out.println("  Error: LibraryFile object wasn't created.");
            }
            return false;
        }
        
        if (libraryFile.exists() == false)
        {
            if (_debug)
            {
                _out.println("  Error: File does not exist.");
            }
            return false;
        }
        
        if (libraryFile.isFile() == false)
        {
            if (_debug)
            {
                _out.println("  Error: File is not a file (probably a directory).");
            }
            return false;
        }
        
        if (libraryFile.canRead() == false)
        {
            if (_debug)
            {
                _out.println("  Error: File is not readable (check permissions).");
            }
            return false;
        }
        
        //test if file looks like a library (debug print error)
        return verifyItunesLibrary(libraryFilename);
    }
    
    private boolean verifyItunesLibrary(String libraryFilename)
    {
        //libraryFilename is verified as existing, and being readable by fileIsItunesLibrary()
        
        //the standard library file looks something like this at the top:
        //    <?xml version="1.0" encoding="UTF-8"?>
        //    <!DOCTYPE plist PUBLIC "-//Apple Computer//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
        //    <plist version="1.0">
        //        <dict>
        //            <key>Major Version</key><integer>1</integer>
        //            <key>Minor Version</key><integer>1</integer>
        //            <key>Application Version</key><string>6.0.5</string>
        //            <key>Features</key><integer>1</integer>
        //            <key>Music Folder</key><string>file://localhost/E:/itunes/</string>
        //            <key>Library Persistent ID</key><string>4EC2FAC25152379E</string>
        //            <key>Tracks</key>
        //
        // so all we're going to do is read a reasonable amount of the library header into a buffer
        //  and then do some quick checks on some identifying strings existing in the buffer
        try
        {
            FileReader libraryFile = new FileReader(libraryFilename);
            if (libraryFile == null)
            {
                if (_debug)
                {
                    _out.println("  Error: libraryFile FileReader is null");
                }
                
                return false;
            }
            
            char[] buffer = new char[1000];
            libraryFile.read(buffer, 0, 1000);
            libraryFile.close();
            
            String betterBuffer = new String(buffer);
            
            if (checkBufferForString(betterBuffer, "Apple Computer") &&
                checkBufferForString(betterBuffer, "<key>Major Version</key>") &&
                checkBufferForString(betterBuffer, "<key>Minor Version</key>") &&
                checkBufferForString(betterBuffer, "<key>Music Folder</key>") &&
                checkBufferForString(betterBuffer, "<key>Application Version</key>") &&
                checkBufferForString(betterBuffer, "<key>Tracks</key>"))
            {
                return true;
            }
            else
            {
                return false;
            }            
        }
        catch (Exception e)
        {
            if (_debug)
            {
                _out.println("  Error: Exception occurred:");
                _out.println(e.toString());
            }
            return false;
        }
    }
    
    private boolean checkBufferForString(String buffer, String needle)    
    {
        if (buffer.indexOf(needle) == -1)
        {
            if (_debug)
            {
                _out.println("  Error: Could not Find \"" + needle + "\"");
                _out.println();
                _out.println("  Buffer Checked Is:");
                _out.println(buffer);
                _out.println();
            }
            return false;
        }
        return true;
    }
}


