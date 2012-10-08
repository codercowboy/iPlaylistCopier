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
 * InterestingTagManager.java
 *
 * Created on September 12, 2006, 2:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.XMLTagParser;

import java.util.*;
/**
 *
 * @author jasbaker
 */
public class InterestingTagManager
{    
    public static final int NO_INTERESTING_TAG = -1;
    
    private HashMap _interestingNames = null; //holds tags w/ only interesting names
    private HashMap _interestingInnerTexts = null; //holds tags w/ only interesting texts
    private HashMap _interestingTags = null; //holds tags w/ both
    private boolean _hasTags = false;
    
    public InterestingTagManager()
    {
        this._interestingNames = new HashMap();
        this._interestingInnerTexts = new HashMap();
        this._interestingTags = new HashMap();
    }
    
    public void addInterestingTag(int id, ITag tag)
    {
        if (id == -1)
        {
            throw new RuntimeException("id cannot be -1");
        }
        
        if (tag == null)
        {
            throw new RuntimeException("tag cannot be null");
        }
        
        InterestingTag iTag = new InterestingTag(id, tag);
        
        String name = tag.getName();
        String innerText = tag.getInnerText();
        
        
        
        boolean nameIsInteresting = (name != null && !name.equals(""));

        boolean textIsInteresting = (innerText != null && !innerText.equals(""));
        
        if (!nameIsInteresting && !textIsInteresting)
        {
            throw new RuntimeException("tag name and innertext cannot be empty");
        }
        else if (nameIsInteresting && !textIsInteresting)
        {
            //only name is interesting, store in names hash map
            addTagToHashMap(_interestingNames, tag.getNameHashCode(), iTag);
        }
        else if (!nameIsInteresting && textIsInteresting)
        {
            //only text is interesting
            addTagToHashMap(_interestingInnerTexts, tag.getInnerTextHashCode(), iTag);
        }
        else
        {
            //both name and text are interesting
            addTagToHashMap(_interestingTags, tag.hashCode(), iTag);
        }
        
    }
    
    private void addTagToHashMap(HashMap map, int hashCode, InterestingTag tag)
    {
        Integer hashCodeInteger = new Integer(hashCode);
        
        if (map.containsKey(hashCodeInteger))
        {
            throw new RuntimeException("map " + map.getClass().getName() + " already contains value for key" + hashCode);
        }
        
        map.put(hashCodeInteger, tag);
        this._hasTags = true;
    }
    
    public int getInterestingTagId(ITag tag)
    {
        InterestingTag iTag = null;
        
        String name = tag.getName();
        String innerText = tag.getInnerText();        
        
        boolean nameIsInteresting = (name != null && !name.equals(""));
        boolean textIsInteresting = (innerText != null && !innerText.equals(""));
        
        if (nameIsInteresting)
        {
            //first see if there's an interesting tag w/ just the name.. 
            // this should be most common and inner text hash will be expensive
            iTag = (InterestingTag) this._interestingNames.get(new Integer(tag.getNameHashCode()));
        }
        
        if (iTag == null && textIsInteresting)
        {
            //if the tag still isn't found, maybe it's in the innertext tags
            iTag = (InterestingTag) this._interestingInnerTexts.get(new Integer(tag.getInnerTextHashCode()));                                    
        }
        
        if (iTag == null && nameIsInteresting && textIsInteresting)
        {
            //if the tag is still null, and it has a name and text, see if its interesting
            iTag = (InterestingTag) this._interestingTags.get(new Integer(tag.hashCode()));
        }
        
        if (iTag != null)
        {
            return iTag.getId();
        }
        else
        {
            return InterestingTagManager.NO_INTERESTING_TAG;
        }
        
    }
        
    public boolean hasTags()
    {
        return this._hasTags;
    }
    
}
