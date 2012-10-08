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
 * ITag.java
 *
 * Created on September 11, 2006, 6:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.XMLTagParser;

/**
 *
 * @author jasbaker
 */
public interface ITag
{
    
    public void clear();
    
    public String getName();
    public int getNameHashCode();
    
    public String getInnerText();
    public int getInnerTextHashCode();
    
    public void addInnerText(char[] buffer, int start, int length);    
    
    public ITag[] getChildTags();
    public void addChildTag(ITag tag);
    public boolean hasChildTags();    
        
    public int hashCode();
    
    public String toString();
}
