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
 * Tag.java
 *
 * Created on September 11, 2006, 6:22 AM
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
public class Tag implements ITag
{
    public static int count = 0;
    
    private int EMPTY_NAME_HASH_CODE = 0;
    
    private String _name = null;
    private int _nameHashCode = 0;
    
    private StringBuffer _innerText = null;
    private int _innerTextHashCode = 0;
    
    private LinkedList _childTags = null;
    
    private int _hashCode = 0;
    
    public Tag()
    {
        this(null, null);
    }
    
    public Tag(String name, String innerText)
    {
        count++;
        this.setName(name);
        this.setInnerText(innerText);
        this.EMPTY_NAME_HASH_CODE = Util.stringToHashCode("");
    }
    
    public void clear()
    {
        _name = null;
        _nameHashCode = 0;
        
        _innerText = null;
        _innerTextHashCode = 0;
        
        _childTags = null;
        
        _hashCode = 0;
    }
    
    private void clearInnerText()
    {
        _innerText = null;
        _innerTextHashCode = 0;
    }
    
    public String getName()
    {
        if (_name == null)
        {
            return "";
        }
        return _name;
    }
    
    public void setName(String value)
    {
        this._name = value;
        this._nameHashCode = Util.stringToHashCode(value);
    }
    
    public int getNameHashCode()
    {
        return _nameHashCode;
    }
    
    
    public String getInnerText()
    {
        if (_innerText != null)
        {
            return _innerText.toString();
        }
        else if (this.hasChildTags())
        {
            StringBuffer value = new StringBuffer();
            int len = this._childTags.size();
            for (int i = 0; i < len; i++)
            {
                value.append(((ITag) this._childTags.get(i)).toString());
            }
            return value.toString();
        }
        else
        {
            //no inner text & no child tags
            return "";
        }
    }
    
    public void setInnerText(String value)
    {
        
        if (value != null)
        {
            this.addInnerText(value.toCharArray(), 0, value.length());
        }
    }
    
    public void addInnerText(char[] buffer, int start, int length)
    {
        this._innerTextHashCode = 0;
        if (_innerText == null)
        {
            if (this.hasChildTags())
            {
                ITag innerTextTag = (ITag) this._childTags.getLast();
                if (innerTextTag.getNameHashCode() == this.EMPTY_NAME_HASH_CODE)
                {
                    //the last child tag is just inner text, add it here
                    innerTextTag.addInnerText(buffer, start, length);
                }
                else
                {
                    //we need to add a new child tag for inner text
                    Tag newChild = new Tag();
                    newChild.addInnerText(buffer, start, length);
                    this._childTags.add(newChild);
                }
            }
            else
            {
                this._innerText = new StringBuffer();
                this._innerText.append(buffer, start, length);
            }
        } //innertext == null
        else
        {
            this._innerText.append(buffer, start, length);
        }
        
        
        
    }
    
    
    public int getInnerTextHashCode()
    {
        if (_innerTextHashCode == 0)
        {
            _innerTextHashCode = Util.stringToHashCode(this.getInnerText());
        }
        return _innerTextHashCode;
    }
    
    
    
    public ITag[] getChildTags()
    {
        return (ITag[]) _childTags.toArray();
    }
    
    
    public void addChildTag(ITag tag)
    {
        //if there is inner text, convert that into a child tag and add it first.
        
        if (this._childTags == null)
        {
            this._childTags = new LinkedList();
        }
        
        String innerText = this.getInnerText();
        if (innerText != null && !innerText.equals(""))
        {
            Tag innerTextTag = new Tag(null, innerText);
            this._childTags.add(innerTextTag);
            this.clearInnerText();
        }
        
        this._childTags.add(tag);
    }
    
    public boolean hasChildTags()
    {
        return (_childTags != null && _childTags.size() > 0);
    }
    
    public int hashCode()
    {
        if (_hashCode == 0)
        {
            _hashCode = Util.stringToHashCode(this.toString());
        }
        return _hashCode;
    }
    
    public String toString()
    {
        StringBuffer value = new StringBuffer();
        if (this.getNameHashCode() != this.EMPTY_NAME_HASH_CODE)
        {
            value.append("<" + this.getName() + ">");
        }
        
        value.append(this.getInnerText());
        
        if (this.getNameHashCode() != this.EMPTY_NAME_HASH_CODE)
        {
            value.append("</" + this.getName() + ">");
        }
        return value.toString();
    }
    
    
    
}
