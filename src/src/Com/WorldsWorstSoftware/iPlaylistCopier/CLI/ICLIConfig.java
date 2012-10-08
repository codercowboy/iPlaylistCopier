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
 * ICLIConfig.java
 *
 * Created on September 2, 2006, 7:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.CLI;

/**
 *
 * @author jason
 */
public interface ICLIConfig
{
    public boolean getDebugMode();
    public boolean getVerboseMode();
    public boolean getListMode();
    public boolean getListFilenameMode(); //overrides verbose mode & list mode
    public String getNameFormat();
    public String getPlaylistNameorId();
    public String getOutputPath();
    public String getLibraryLocation();   
    public void setListMode(boolean value);
}
