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
 * SaxFakeEntityResolver.java
 *
 * Created on September 11, 2006, 9:13 AM
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
public class SaxFakeEntityResolver implements EntityResolver
{
    
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException
    {
        return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
    }
    
}
