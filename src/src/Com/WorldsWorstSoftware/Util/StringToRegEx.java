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
 * StringToRegExConverter.java
 *
 * Created on September 8, 2006, 12:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.Util;

/**
 *
 * @author jason
 */
public class StringToRegEx
{
    
    /**
     * Creates a new instance of StringToRegExConverter
     */
    public StringToRegEx()
    {
    }
    
    // oops, started development in jdk 1.5 which provides a nice String.replace(String,String) method
    // then discovered mac os x 10.3 can only have jdk 1.4.x on it, 
    // so i've got to work w/ String.replaceAll(regex, String)
    public static String Convers(String value)
    {
        String newValue = new String(value);
        //put escape commands before special characters in the new regex
        //Roedy Green lists the special chars as . - + * ? ( ) [ ] { } \ | $ ^ < =
        //http://mindprod.com/jgloss/regex.html 
        
        //there's probably a more robust way to do this in a one shot regex, but i'm not too great w/ regex stuff
        newValue = newValue.replaceAll("\\.", "\\.");
        newValue = newValue.replaceAll("\\-", "\\-");
        newValue = newValue.replaceAll("\\+", "\\+");
        newValue = newValue.replaceAll("\\*", "\\*");
        newValue = newValue.replaceAll("\\?", "\\?");
        newValue = newValue.replaceAll("\\(", "\\(");
        newValue = newValue.replaceAll("\\)", "\\)");
        newValue = newValue.replaceAll("\\[", "\\[");
        newValue = newValue.replaceAll("\\]", "\\]");
        newValue = newValue.replaceAll("\\{", "\\{");
        newValue = newValue.replaceAll("\\}", "\\}");
        newValue = newValue.replaceAll("\\\\", "\\\\\\\\");
        newValue = newValue.replaceAll("\\|", "\\|");
        newValue = newValue.replaceAll("\\$", "\\$");
        newValue = newValue.replaceAll("\\^", "\\^");
        newValue = newValue.replaceAll("\\<", "\\<");
        newValue = newValue.replaceAll("\\=", "\\=");
        
        return newValue;
    }
    
}
