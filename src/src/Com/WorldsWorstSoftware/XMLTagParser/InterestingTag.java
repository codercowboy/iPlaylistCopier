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
 * InterestingTag.java
 *
 * Created on September 12, 2006, 2:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.XMLTagParser;

/**
 *
 * @author jasbaker
 */
class InterestingTag
{
    
    /** Creates a new instance of InterestingTag */
    public InterestingTag(int id, ITag tag)
    {
        this._id = id;
        this._tag = tag;
    }
    
    private int _id = -1;
    private ITag _tag = null;
    
    public int getId()
    {
        return _id;
    }
    
    public ITag getTag()
    {
        return _tag;
    }
    
}
