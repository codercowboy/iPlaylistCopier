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
 * GUICopier.java
 *
 * Created on September 15, 2006, 10:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.iPlaylistCopier.GUI;

import Com.WorldsWorstSoftware.ItunesLibrary.Parser.ItunesPlaylist;
import Com.WorldsWorstSoftware.ItunesLibrary.Util.FileCopier;
import Com.WorldsWorstSoftware.ItunesLibrary.Util.PlaylistCopier;
import java.util.Calendar;

/**
 *
 * @author jasbaker
 */
public class GUICopier extends PlaylistCopier
{
    
    private IProgressHelper _progressHelper = null;
    private Calendar _lastUpdateTime = null;
    private StringBuffer _errors = null;
    
    /** Creates a new instance of GUICopier */
    public GUICopier(ItunesPlaylist playlist, String nameFormat, String outputFolder, IProgressHelper progressHelper)
    {
        super(playlist, outputFolder, nameFormat);
        this._progressHelper = progressHelper;
        _errors = new StringBuffer();
        _lastUpdateTime = Calendar.getInstance();
    }
    
    public void copyPlaylist()
    {
        super.copyPlaylist();
        if (_errors.length() > 0)
        {
            this._progressHelper.updateError(_errors.toString());
            _errors.delete(0, _errors.length());
        }
    }
    
    protected void updateCopyStatus(String copyStatus)
    {
        Calendar currentTime = Calendar.getInstance();
        long difference = currentTime.getTimeInMillis() - _lastUpdateTime.getTimeInMillis();
        if (difference > 250)
        {
            //only update every 1/4 second
            this._progressHelper.updateStatus(copyStatus);
            if (_errors.length() > 0)
            {
                this._progressHelper.updateError(_errors.toString());
                _errors.delete(0, _errors.length());
            }
            _lastUpdateTime = currentTime;
        }
        
        
    }
    
    protected void handleException(Exception e)
    {
        if (_fileName != null)
        {
            this._errors.append("Error While Copying \"" + _fileName + "\"..\n");
        }
        this._errors.append(e.getMessage() + "\n\n");
    }
    
}
