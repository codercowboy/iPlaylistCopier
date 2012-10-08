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
 * XMLInterestingTagParser.java
 *
 * Created on September 12, 2006, 3:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.XMLTagParser;

import java.io.ByteArrayInputStream;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author jasbaker
 */
public class XMLInterestingTagParser implements ITagHandler
{
    
    private XMLTagParser _parser = null;
    
    private InterestingTagManager _tagManager = null;
    private IInterestingTagHandler _tagHandler = null;
    
    public XMLInterestingTagParser(
            String xmlFileLocation,
            InterestingTagManager tagManager,
            IInterestingTagHandler tagHandler,
            boolean resolveEntities,
            boolean debug)
    {
        
        if (tagManager == null)
        {
            throw new RuntimeException("tagManager cannot be null");
        }
        
        this._tagManager = tagManager;
        
        if (tagHandler == null)
        {
            throw new RuntimeException("tagHandler cannot be null");
        }
        
        this._tagHandler = tagHandler;
        
        this._parser = new XMLTagParser(xmlFileLocation, this, resolveEntities, debug);        
    }
    
     public void setErrorHandler(ErrorHandler errorHandler)
    {
        this._parser.setErrorHandler(errorHandler);
    }
    
    public void parse() throws IOException, SAXException, ParserConfigurationException
    {
        if (this._tagManager.hasTags() == false)
        {
            throw new RuntimeException("tagManager must have tags added to it before parsing.");
        }                        
           
        this._parser.parse();
    }

    public void HandleTag(ITag tag)
    {
        int id = this._tagManager.getInterestingTagId(tag);
        if (id != InterestingTagManager.NO_INTERESTING_TAG)
        {
            this._tagHandler.handleInterestingTag(id, tag);
        }
    }    
}
