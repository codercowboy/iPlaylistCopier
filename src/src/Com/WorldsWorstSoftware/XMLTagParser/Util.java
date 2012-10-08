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
 * Util.java
 *
 * Created on September 11, 2006, 1:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.XMLTagParser;

/**
 *
 * @author jasbaker
 */
public class Util
{
    
    public static int stringToHashCode(String value)
    {
        int result = 17;
        if (value != null)
        {
            int len = value.length();
            for (int i = 0; i < len; i ++)
            {
                result += 37 * result + (int) value.charAt(i);
            }
        }
        return result;
    }
    
}
