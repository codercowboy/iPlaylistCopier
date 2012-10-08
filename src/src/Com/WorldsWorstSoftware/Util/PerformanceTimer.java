
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
 * PerformanceTimer.java
 *
 * Created on September 8, 2006, 10:59 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.Util;
import java.util.*;
/**
 *
 * @author jasbaker
 */
public class PerformanceTimer
{
    private Calendar initialTime = null;
    private Calendar lastTime = null;    
    
    public PerformanceTimer()
    {
        initialTime = Calendar.getInstance();
        lastTime = initialTime;
    }
    
    public void initialize()
    {
        //this should be used to prevent lazy initialization from screwing up our timer
        initialTime = Calendar.getInstance();
        lastTime = initialTime;
    }
    
    public String getTimeElapsed()
    {
        lastTime = Calendar.getInstance();
        return formatTime(lastTime.getTimeInMillis() - initialTime.getTimeInMillis());
    }
    
    public String getTimeElapsedSinceLast()
    {
        Calendar newLastTime = Calendar.getInstance();
        String value = formatTime(newLastTime.getTimeInMillis() - lastTime.getTimeInMillis());
        lastTime = newLastTime;
        return value;
    }
    
    private String formatTime(long timeInMillis)
    {
        if (timeInMillis > 1000)
        {
            return (timeInMillis / 1000) + "." + (timeInMillis % 1000) + " s";
        }
        else
        {
            return timeInMillis + " ms";
        }
    }
    
}
