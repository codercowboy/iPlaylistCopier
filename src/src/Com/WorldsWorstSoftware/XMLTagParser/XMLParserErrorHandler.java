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
 * XMLParserErrorHandler.java
 *
 * Created on September 11, 2006, 9:03 AM
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
 *
 *  this is the default error handler code shown in the great o'reilly sax parser example
 *  from java examples in a nutshell By David Flanagan
 *  http://www.oreilly.com/catalog/jenut2/chapter/ch19.html
 */
public class XMLParserErrorHandler implements ErrorHandler
{
    
    private boolean _debug = false;
    /** Creates a new instance of XMLParserErrorHandler */
    public XMLParserErrorHandler(boolean debug)
    {
        this._debug = debug;
    }
    
    /** This method is called when warnings occur */
    public void warning(SAXParseException exception)
    {
        if (this._debug)
        {
            System.err.println("WARNING: line " + exception.getLineNumber() + ": "+
                    exception.getMessage());
        }
    }
    
    /** This method is called when errors occur */
    public void error(SAXParseException exception)
    {
        if (this._debug)
        {
            System.err.println("ERROR: line " + exception.getLineNumber() + ": " +
                    exception.getMessage());
        }
    }
    
    /** This method is called when non-recoverable errors occur. */
    public void fatalError(SAXParseException exception) throws SAXException
    {
        if (this._debug)
        {
            System.err.println("FATAL: line " + exception.getLineNumber() + ": " +
                    exception.getMessage());
        }
        
        throw(exception);
    }
    
}
