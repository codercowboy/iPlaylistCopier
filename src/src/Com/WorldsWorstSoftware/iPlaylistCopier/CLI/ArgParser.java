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
 * ArgParser.java
 *
 * Created on September 2, 2006, 8:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.CLI;

import Com.WorldsWorstSoftware.ItunesLibrary.Util.Util;
import Com.WorldsWorstSoftware.Util.BSDLicense;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @author jason
 */
public class ArgParser
{
    
    /** Creates a new instance of ArgParser */
    public ArgParser()
    {
    }
    
    public static CLIConfig processArgs(String[] args)
    {
        CLIConfig config = new CLIConfig();
        if (args.length == 0)
        {
            printUsage();
            return null;
        }
        
        for (int i = 0; i < args.length; i++)
        {
            if (!processArg(args[i], config))
            {
                return null;
            }
        }
                
        return config;
    }
    
    private static boolean processArg(String arg, CLIConfig config)
    {
        String lowerArg = arg.toLowerCase();
        if (lowerArg.indexOf('-') != 0)
        {
            //the arg does not start with a '-', this must be PLAYLIST or OUTPUTPATH
            if (config.getPlaylistNameorId() == null || config.getPlaylistNameorId().equals(""))
            {
                //first non switch arg is the playlist..
                config.setPlaylistNameorId(arg);
                return true;
            }
            else if (config.getOutputPath() == null || config.getOutputPath().equals(""))
            {
                //second non switch arg is the outputpath                        
                config.setOutputPath(arg);
                return true;
            }
            else
            {
                //there can only be two non-switch args, tell the user they messed up
                throw new RuntimeException("Syntax Error, iPlaylistCopier does not undersand this command: " + arg);
            }
        }
        
        //arg is a switch, remove leading -
        lowerArg = lowerArg.substring(1);
        
        if (lowerArg.indexOf("name-format") == 0)
        {
            int pos = arg.indexOf('=');
            if (pos == -1)
            {
                throw new RuntimeException("Syntax Error, iPlaylistCopier does not undersand this command: " + arg);
            }
            
            config.setNameFormat(arg.substring(pos + 1));
        }
        
        if (lowerArg.indexOf("l=") == 0)
        {
            //library has been specified
            int pos = arg.indexOf('=');
            if (pos == -1)
            {
                throw new RuntimeException("Syntax Error, iPlaylistCopier does not undersand this command: " + arg);
            }
            
            config.setLibraryLocation(arg.substring(pos + 1));
            return true;
        }
        
        if (lowerArg.equals("help"))
        {
            printUsage();
            return false;
        }
        
        if (lowerArg.equals("v"))
        {
            config.setVerboseMode(true);
            return true;
        }
        
        if (lowerArg.equals("debug"))
        {
            config.setDebugMode(true);
            return true;
        }
        
        if (lowerArg.equals("list"))
        {
            config.setListMode(true);
            return true;
        }
        
        if (lowerArg.equals("version"))
        {
            System.out.println(getVersion());
            return false;
        }
        
        //if we havent returned before this point, the user has passed in an unhandled arg
        throw new RuntimeException("Syntax Error, iPlaylistCopier does not undersand this command: " + arg);
    }
    
    public static String getVersion()
    {
        return "iPlaylist Copier v1.0 (October 2006)";        
    }
    
    private static void printUsage()
    {
        PrintStream out = System.out;
        out.println();
        out.println("NAME");
        out.println("  iPlaylist Copier - Copies playlist files from an Itunes Library");
        out.println();
        out.println("USAGE");
        out.println("  java -jar iPlaylistCopier.jar [options] PLAYLIST OUTPUTPATH");
        out.println();
        out.println("  PLAYLIST      Specifies the Itunes playlist name or id");
        out.println("  OUTPUTPATH    Specifies the output directory for copied tracks");
        out.println();
        out.println("OPTIONS");
        out.println("  -l=[library file location]");
        out.println("              Specify the Itunes library location.");
        out.println("                Examples:");
        out.println("                  -l=\"c:\\Music\\Itunes Library.xml\"");
        out.println("                  -l=\"~/Music/Itunes Library.xml\"");
        out.println();
        out.println("  -list       List tracks for specified playlist, or list playlists");
        out.println("              when no playlist is specified");
        out.println();
        /* version 2.0 maybe..
        out.println("  -list-files List track filenames for specified playlist, one per line");
        out.println();
         */
        out.println("  -v          Explain what's being done.");
        out.println();
        out.println("  -name-format=[name format]");
        out.println("              Format track filenames with specified formatting string");
        out.println("              [Default formatting string is \"" + Util.DefaultNameFormat + "\"]");
        out.println();
        out.println("              FORMATTING STRING OPTIONS:");
        out.println("                %# will be replaced with the track number");
        out.println("                %A will be replaced with the track artist");
        out.println("                %N will be replaced with the track name");
        out.println("                %R will be replaced with the track album");
        out.println();
        /* version 2.0 maybe..
        out.println("  -config=[config file]");
        out.println("              Read configuration from specified file");
        out.println("                [Configuration file should specify one option per line]");
        out.println();
        out.println("              CONFIGURATION FILE OPTIONS:");
        out.println("                library=[library file]");
        out.println("                outputpath=[output path]");
        out.println("                nameformat=[name format]");
        out.println();
         */
        out.println("  -help       Print this information and exit");
        out.println();
        out.println("  -version    Print version information and exit");
        out.println();
        out.println("DIAGNOSTICS");        
        out.println("  Debug mode can be turned on by specifing a \"-debug\" option.");
        out.println();
        out.println("AUTHOR");
        out.println("  Written by Jason Baker.");
        out.println();
        out.println("REPORTING BUGS");
        out.println("  Report bugs to jason@onejasonforsale.com.");
        out.println();
        out.println("COPYRIGHT");
        out.print(BSDLicense.getBSDLicense("Jason Baker", "2006", "World's Worst Software"));
        out.println();
        out.println("DISCLAIMER");
        out.println("  Jason Baker, World's Worst Software, and iPlaylist Copier are");
        out.println("  not affiliated with or endorsed by Apple Computer in any way.");
        out.println();
        out.println("FUTURE REVISIONS");
        out.println("  Future revisions of iPlaylist Copier can be obtained from");
        out.println("    http://www.worldsworstsoftware.com");
        out.println();        
    }
    
}
