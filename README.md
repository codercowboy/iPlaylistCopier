iPlaylistCopier
===============

Copies media files from your favorite iTunes playlist to a folder you choose.

* written by Jason Baker ([jason@onejasonforsale.com](mailto:jason@onejasonforsale.com))
* on github: [https://github.com/codercowboy/iPlaylistCopier](https://github.com/codercowboy/iPlaylistCopier)
* more info: [http://www.codercowboy.com](http://www.codercowboy.com)

Originally published on Jason's World's Worst Software website.

Description
===========

iPlaylist Copier gives you the flexibility to put your iTunes playlists on your MP3 player, even if your player is not an iPod. Simply select your favorite playlist and copy your media files to any disk or folder you choose. Use iTunes to manage your music and your playlists. Use iPlaylist Copier to set your playlists free!

Features:
* Copy media files from your playlists to any drive or folder
* Automatic iTunes Library Detection
* Format track names the way you like them
* A Command Line Interface (CLI) is included for power users
* Cross-platform, works on OS X or Windows

System Requirements
===================

* iPlaylist Copier is iTunes 7+ compatible. (As far as I know!)
* Windows: Java JRE 1.4 or higher, Itunes 6 or higher.
* Mac: Mac OS X 10.3 or higher, Itunes 6 or higher.

Basic Instructions
==================

Note: This project is dormant/old. It was created/published in 2007, back when iTunes was at version 7. It may or may not work with the latest versions of iTunes. You'll know it works if you see your iTunes playlists names and tracks pop up correctly in the application. 

If you want to use iPlaylistCopier on your computer, download the relevant zip file for windows or os x. 

The OSX version may not run on recent versions of OS X (such as Lion), instead you may want to download the windows version and just run it by double-clicking on the .jar file.

Installation/usage instructions are included in the zip files in a text file.

UPDATE: It turns out users CAN copy their media files from iTunes playlists by simply dragging and dropping from within the iTunes software, wish I had thought of that :). Thanks to iLounge.com users for the [tip](http://forums.ilounge.com/showthread.php?t=176936)!

Note for Windows Users
======================

iPlaylist Copier requires the Java JRE to run. Windows does not come with the JRE installed by default, so you may need to download and install the JRE in order to run iPlaylist Copier and other Java programs on your computer. You can download the JRE from the link below.

[JRE 1.5 for Windows](http://www.java.com/en/download/windows_xpi.jsp)

Project Notes
=============

The application is a Java 1.4 swing application (JRE 1.5 was not available for OS X 10.3 users), written when I was a couple years out of college. 

The basic idea is, find the itunes library xml file, parse it into java objects representing the library, and copy files using the format given by the user.

There are probably easier ways to do this parsing using other libraries out there for apple-style XML files, if you know of something, e-mail me, I'll note it here.   

The project file is an old netbeans 5.0 project, with some tie-ins to some netbeans helper classes netbeans provided.

If you'd like to fix something with the app, have at it, fork, contribute back, whatever. I'll give you credit here if you want, including linking to your fork. 

Some half-way-finished follow-on version of this code is over in the [iTunesUtilities](https://github.com/codercowboy/iTunesUtilities) project. I spent a little time wrapping the utility library XML reading code into a library in that project.

Reusable Components:
* Itunes Library XML Parser
* Itunes Playlist File Copier
* POJO objects representing Itunes Library, Playlists, and Tracks
* Cross-Platform Itunes Library Auto-Detector
* Simple String to RegEx method
* String Replacer [java 1.5 String.replace(String, String) w/ 1.4 code]
* Performance Timer Utility Class

Credit Where Credit Is Due
==========================

the ItunesUtilities source code benefits from the following examples, tutorials, or hints:

* [David Flanagen's xml sax parser example code](http://www.oreilly.com/catalog/jenut2/chapter/ch19.html)
* [Pawe³ Stobiñski's ignore DTD validation code](http://www.velocityreviews.com/forums/t139773-saxparser-ignore-ltdoctypegt-line.html)
* [gforman and shiva_in's file copying algorithm](http://www.experts-exchange.com/Programming/Programming_Languages/Java/Q_10245809.html)
* [Roedy Green's encode url code](http://mindprod.com/jgloss/urlencoded.html)
* [Linux Box Admin's invalid filename characters list](http://linuxboxadmin.com/articles/filefriction.php)
* [Jay of indyJt's os x iTunes library location hints](http://www.indyjt.com/blog/?p=51)
* [Roedy Green's java regex special characters list](http://mindprod.com/jgloss/regex.html)

Licensing
=========

Licensed with the [Apache license](http://en.wikipedia.org/wiki/Apache_license), which is a great license because, essentially it:
* a) covers liability - my code should work, but I'm not liable if you do something stupid with it
* b) allows you to copy, fork, and use the code, even commercially
* c) is [non-viral](http://en.wikipedia.org/wiki/Viral_license), that is, your derivative code doesn't *have to be* open source to use it

Other great licensing options for your own code: the BSD License, or the MIT License.

Here's the license:

Copyright (c) 2012, Coder Cowboy, LLC. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
* 1. Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.
* 2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.
  
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  
The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies,
either expressed or implied.

Disclaimer
==========

Jason Baker, World's Worst Software, Coder Cowboy, and iPlaylist Copier are not affiliated with or endorsed by Apple Computer in any way.