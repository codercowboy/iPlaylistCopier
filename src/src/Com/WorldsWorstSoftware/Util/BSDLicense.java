
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
 * BSDLicensePrinter.java
 *
 * Created on September 7, 2006, 5:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Com.WorldsWorstSoftware.Util;

/**
 *
 * @author jasbaker
 */
public class BSDLicense
{
    
    /** Creates a new instance of BSDLicensePrinter */
    public BSDLicense()
    {
    }
    
    public static String getBSDLicense(String author, String year, String organization)
    {
        String license = bsdLicense;
        license = StringReplacer.replace(license, "<OWNER>", author);
        license = StringReplacer.replace(license, "<YEAR>", year);
        license = StringReplacer.replace(license, "<ORGANIZATION>", organization);
        license = StringReplacer.replace(license, "\n", System.getProperty("line.separator"));
        return license;
    }
    
    //license retrieved from http://www.opensource.org/licenses/bsd-license.php on September 7, 2006
    private static String bsdLicense =
            "  Copyright (c) <YEAR>, <OWNER>\n"
            + "  All rights reserved.\n\n"
            + "  Redistribution and use in source and binary forms, with or without \n"
            + "  modification, are permitted provided that the following conditions \n"
            + "  are met:\n\n"
            + "     * Redistributions of source code must retain the above copyright \n"
            + "       notice, this list of conditions and the following disclaimer.\n\n"
            + "     * Redistributions in binary form must reproduce the above copyright \n"
            + "       notice, this list of conditions and the following disclaimer in \n"
            + "       the documentation and/or other materials provided with the \n"
            + "       distribution.\n\n"
            + "     * Neither the name of the <ORGANIZATION> nor the names of its \n"
            + "       contributors may be used to endorse or promote products derived \n"
            + "       from this software without specific prior written permission.\n\n"
            + "  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \n"
            + "  \"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT \n"
            + "  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR \n"
            + "  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT \n"
            + "  OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, \n"
            + "  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT\n"
            + "  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  \n"
            + "  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON  \n"
            + "  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR  \n"
            + "  TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF  \n"
            + "  THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH \n"
            + "  DAMAGE.\n";
}
