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
 * XMLTagParser.java
 *
 * Created on September 11, 2006, 6:21 AM
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
public class XMLTagParser extends org.xml.sax.HandlerBase
{        
    private ITagHandler _tagHandler = null;
    private String _xmlFileLocation = null;
    private ErrorHandler _errorHandler;
    private boolean _resolveEntities = false;
    private boolean _debug = false;
    private Tag _currentTag = null;
    
    /** Creates a new instance of XMLTagParser */
    public XMLTagParser(
            String xmlFileLocation,
            ITagHandler tagHandler,            
            boolean resolveEntities,
            boolean debug)
    {
        if (tagHandler == null)
        {
            throw new RuntimeException("tagHandler cannot be null.");
        }        
        
        this._tagHandler = tagHandler;        
        this._xmlFileLocation = xmlFileLocation;
        this._resolveEntities = resolveEntities;
        this._errorHandler = new XMLParserErrorHandler(debug);
        this._debug = debug;
    }
    
    
    public void setErrorHandler(ErrorHandler errorHandler)
    {
        if (errorHandler == null)
        {
            throw new RuntimeException("errorHandler cannot be null.");
        }
        
        this._errorHandler = errorHandler;
    }
    
    public void parse() throws IOException, SAXException, ParserConfigurationException
    {
        
        // parser stuff figured out with great ease thanks to java examples in a nutshell By David Flanagan
        // http://www.oreilly.com/catalog/jenut2/chapter/ch19.html
        
        // Create a JAXP "parser factory" for creating SAX parsers
        javax.xml.parsers.SAXParserFactory spf = SAXParserFactory.newInstance();
        
        // Configure the parser factory for the type of parsers we require
        spf.setValidating(false);  // No validation required
        
        // Now use the parser factory to create a SAXParser object
        // Note that SAXParser is a JAXP class, not a SAX class
        javax.xml.parsers.SAXParser sp = spf.newSAXParser();
        
        // Create a SAX input source for the file argument
        org.xml.sax.InputSource input = new InputSource(new FileReader(this._xmlFileLocation));
        
        // Give the InputSource an absolute URL for the file, so that
        // it can resolve relative URLs in a <!DOCTYPE> declaration, e.g.
        input.setSystemId("file://" + new File(this._xmlFileLocation).getAbsolutePath());
        
        // Finally, tell the parser to parse the input and notify the handler
        // sp.parse(input, this);
        
        // Instead of using the SAXParser.parse() method, which is part of the
        // JAXP API, we could also use the SAX1 API directly.  Note the
        // difference between the JAXP class javax.xml.parsers.SAXParser and
        // the SAX1 class org.xml.sax.Parser
        //
        org.xml.sax.Parser parser = sp.getParser();  // Get the SAX parser
        
        if (this._resolveEntities)
        {
            parser.setEntityResolver(this);
        }
        else
        {
            parser.setEntityResolver(new SaxFakeEntityResolver());
        }
        
        parser.setDocumentHandler(this);          // Set main handler
        parser.setErrorHandler(_errorHandler);             // Set error handler
        parser.parse(input);                         // Parse!
    }
    
    
    
    public void startElement(String name, AttributeList attributes)
    {
        if (_currentTag == null)
        {
            _currentTag = new Tag();
        }
        
        _currentTag.clear();
        
        _currentTag.setName(name);
        
        //FUTURE: we should parse the attributes into the tag
    }        
    
    public void endElement(String name)
    {
        //FUTURE: we could do some sanity checking here to make sure the end tag name matches the tag on the top of the stack
        
        if (_currentTag != null)
        {        
            this._tagHandler.HandleTag(_currentTag);                        
        }
    }
    
    //provided for perfomance testing
    public static int charactersCalls = 0;
    
    public void characters(char[] buffer, int start, int length)
    {
        charactersCalls++;
        
        //put char data into the tag
        if (_currentTag != null)
        {
            _currentTag.addInnerText(buffer, start, length);
        }
    }
    
}
