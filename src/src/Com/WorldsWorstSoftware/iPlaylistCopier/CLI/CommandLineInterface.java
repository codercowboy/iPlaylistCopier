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
 * Main.java
 *
 * Created on November 6, 2005, 3:24 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.CLI;

import Com.WorldsWorstSoftware.ItunesLibrary.Parser.ItunesLibrary;
import Com.WorldsWorstSoftware.ItunesLibrary.Parser.ItunesPlaylist;
import Com.WorldsWorstSoftware.ItunesLibrary.Parser.LibraryTagParser;
import Com.WorldsWorstSoftware.ItunesLibrary.Util.ItunesPrinter;
import Com.WorldsWorstSoftware.ItunesLibrary.Util.LibraryFinder;
import Com.WorldsWorstSoftware.ItunesLibrary.Util.PlaylistCopier;
import Com.WorldsWorstSoftware.iPlaylistCopier.GUI.GraphicalUserInterface;
import Com.WorldsWorstSoftware.Util.PerformanceTimer;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 *
 * @author jason
 */
public class CommandLineInterface
{
    
    /** Creates a new instance of Main */
    public CommandLineInterface()
    {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            GraphicalUserInterface.main(args);
            return;
        }
        
        PerformanceTimer performanceTimer = new PerformanceTimer();
        performanceTimer.initialize();
        CLIConfig cliConfig = new CLIConfig();
        cliConfig.setDebugMode(true); //just until the argparser can turn it off
        try
        {
            cliConfig = ArgParser.processArgs(args);
            if (cliConfig != null && cliConfig.getDebugMode())
            {
                System.out.println("Argument Processing Time: " + performanceTimer.getTimeElapsed());
            }
            if (cliConfig != null)
            {
                executeProgram(cliConfig);
            }
        }
        catch (Exception e)
        {
            printError(e, cliConfig);
        }
        finally
        {
            if (cliConfig != null && cliConfig.getDebugMode())
            {
                System.out.println("Execution Time: " + performanceTimer.getTimeElapsed());
            }
        }
    }
    
    public static void printError(Throwable e, ICLIConfig cliConfig)
    {
        
        if (cliConfig != null && cliConfig.getDebugMode())
        {
            //print out the configuration for the user
            System.out.println("");
            System.out.println(cliConfig.toString());
        }
        
        System.out.println("Error: " + e.getMessage());
        if (cliConfig != null && cliConfig.getDebugMode())
        {
            e.printStackTrace(System.out);
            
            if (e.getCause() != null)
            {
                System.out.println();
                System.out.println("CAUSED BY");
                System.out.println();
                printError(e.getCause(), cliConfig);
            }
        }
        System.out.println();
        
        //print message directing user to use -help arg
        System.out.println("Try \"java -jar iPlaylistCopier.jar -help\" for usage instructions.");
        System.out.println();
        
        return;
    }
    
    public static void executeProgram(ICLIConfig cliConfig)
    {
        PerformanceTimer performanceTimer = new PerformanceTimer();
        performanceTimer.initialize();
        
        if (cliConfig == null)
        {
            throw new RuntimeException("cliConfig is null.");
        }
        
        //if no playlist is specified, set configuration into list mode.
        if (cliConfig.getPlaylistNameorId() == null || cliConfig.getPlaylistNameorId().equals(""))
        {
            cliConfig.setListMode(true);
        }
        
        //FUTURE: 2.0 if listfilenamemode is on, turn verbose mode off
            
        if (cliConfig.getDebugMode())
        {
            System.out.println("Parser Config Initialization Time: " + performanceTimer.getTimeElapsedSinceLast());
        }
        
        //parse the library
        ItunesLibrary library = getLibrary(cliConfig.getLibraryLocation(), cliConfig.getVerboseMode(), cliConfig.getDebugMode());
        
        if (cliConfig.getDebugMode())
        {
            System.out.println("Library Discovery & Parse Time: " + performanceTimer.getTimeElapsedSinceLast());
            System.out.println(Com.WorldsWorstSoftware.XMLTagParser.Tag.count + " tags allocated");
            System.out.println(Com.WorldsWorstSoftware.XMLTagParser.XMLTagParser.charactersCalls + " character() calls");
        }
        
        //retrieve the playlist if it's specified
        ItunesPlaylist playlist = null;
        String playlistNameorId = cliConfig.getPlaylistNameorId();
        if (playlistNameorId != null && playlistNameorId.equals("") == false)
        {
            try
            {
                playlist = library.findPlayList(playlistNameorId);
            }
            catch (Exception e)
            {
                printError(e, cliConfig);
                return;
            }
            
            if (cliConfig.getDebugMode())
            {
                System.out.println("Find Playlist Time: " + performanceTimer.getTimeElapsedSinceLast());
            }
        }
        
        
        
        //FUTURE: if we're in list filename mode, just list track filenames
        if (cliConfig.getListFilenameMode())
        {
            if (playlist == null)
            {
                throw new RuntimeException("Cannot list filenames, no playlist was specified.");
            }
            //list the filenames out for the given playlist, then exit
            ItunesPrinter.printTrackFilenames(System.out, playlist);
            
            if (cliConfig.getDebugMode())
            {
                System.out.println("Print Filename List Time: " + performanceTimer.getTimeElapsedSinceLast());
            }
            
            return;
        }
        
        //if we're in list mode, display stuff
        if (cliConfig.getListMode())
        {
            if (playlist != null)
            {
                //if playlist specified list tracks
                ItunesPrinter.printTracks(System.out, playlist, cliConfig.getNameFormat());
            }
            else
            {
                //if no playlist specified list playlists
                ItunesPrinter.printPlayLists(System.out, library, true);
            }
            
            if (cliConfig.getDebugMode())
            {
                System.out.println("Print List Time: " + performanceTimer.getTimeElapsedSinceLast());
            }
            
            //exit
            return;
        }
        
        if (playlist == null)
        {
            throw new RuntimeException("Cannot copy files, no playlist was specified.");
        }
        
        //prepare the copier config object        
        PlaylistCopier copier = new PlaylistCopier(playlist, cliConfig.getOutputPath(), cliConfig.getNameFormat(), cliConfig.getVerboseMode(), cliConfig.getDebugMode());
        
        //do the copy
        copier.copyPlaylist();
        
        if (cliConfig.getDebugMode())
        {
            System.out.println("File Copy Time: " + performanceTimer.getTimeElapsedSinceLast());
        }
    }
    
    public static ItunesLibrary getLibrary(String XMLLibraryFileLocation, boolean verbose, boolean debug)
    {
        try
        {
            LibraryFinder libraryFinder = new LibraryFinder(System.out, debug);
            
            //see if the library specified is valid            
            if (!libraryFinder.fileIsItunesLibrary(XMLLibraryFileLocation))
            {
                //if not, tell the user and attempt to auto detect it
                if (XMLLibraryFileLocation != null && !XMLLibraryFileLocation.equals(""))
                {
                    System.out.println("  Invalid Library Specified: " + XMLLibraryFileLocation);
                }
                
                System.out.println("  Attempting to auto detect Itunes Library..");
                XMLLibraryFileLocation = libraryFinder.FindLibrary();
                System.out.println("  Using Itunes Library: " + XMLLibraryFileLocation);                                
            }
            //Parse the library w/ the tag parser..
            LibraryTagParser parser = new LibraryTagParser();
            return parser.parseLibrary(XMLLibraryFileLocation, verbose, debug);            
        }
        catch (Exception e)
        {
            if (e instanceof RuntimeException)
            {
                throw (RuntimeException) e;
            }
            else
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
    
}

