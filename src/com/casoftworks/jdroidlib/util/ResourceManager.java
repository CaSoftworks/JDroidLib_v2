/*
 * Copyright (C) 2015 CA Softworks.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.casoftworks.jdroidlib.util;

import java.io.*;

/**
 * ResourceManager. Manages the resources used and needed by JDroidLib.
 * This class will use resources provided by the library for the most part,
 * however this class may or may not download further resources, depending 
 * on whether they are needed or not.
 * All resources extracted by this class will be deleted when the JVM exits.
 * 
 * This is a singleton class. Only one instance of this class may exist at 
 * any given time the application is running!
 * 
 * @author Ca Softworks
 * @since 2.0
 */
public final class ResourceManager {
    
    //<editor-fold defaultstate="collapsed" desc="Static Members" >
    /**
     * Contains the running instance of this class.
     */
    private static ResourceManager _instance;
    
    /**
     * Gets an instance of this class.
     * If no instance is present (e.g.: the application has just started),
     * then this method will generate and return a new instance of this class.
     * Do <b>NOT</b> call 
     * {@link com.casoftworks.jdroidlib.util.ResourceManager#finalize()}
     * on this class!
     * @return An instance of 
     * {@link com.casoftworks.jdroidlib.util.ResourceManager}
     * @throws java.io.IOException  This exception is thrown, when something
     *                              goes wrong during the extraction of the
     *                              resources needed by JDroidLib.
     */
    public static ResourceManager getInstance() throws IOException { 
        return _instance == null ? 
                _instance = new ResourceManager() : _instance;
    }
    //</editor-fold>
    
    private final String _jdroidlibDir;
    private final String _resourceDir;
    private final File _jdroidlibPath;
    private final File _resourcePath;
    private final OperatingSystem _currentOS;
    private File adb;
    private File fastboot;
    
    /**
     * Class initializer.
     */
    {
        // Get resource path.
        // Paths vary depending on the OS.
        String win = "AppData#Roaming#com.ca-softworks#JDroidLib";
        String unix = ".jdroidlib";
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            _currentOS = is64Bit() ? OperatingSystem.WINDOWS_X64 : OperatingSystem.WINDOWS_X86;
            _jdroidlibDir = String.format("%0$s#%1$s", 
                                           System.getProperty("user.profile"), 
                                           win)
                    .replace("#", System.getProperty("path.separator"));
            _resourceDir = String.format("%0$s#%1$s", _jdroidlibDir, "res")
                    .replace("#", File.pathSeparator);
        } else {
            if (System.getProperty("os.name").toLowerCase().contains("linux"))
                _currentOS = OperatingSystem.LINUX_X86_X64;
            else 
                _currentOS = OperatingSystem.MAC_OS_X_X64;
            
            _jdroidlibDir = String.format("%0$s#%1$s", 
                                           System.getProperty("user.profile"), 
                                           unix)
                    .replace("#", File.pathSeparator);
            _resourceDir = String.format("%0$s#%1$s", _jdroidlibDir, "res")
                    .replace("#", File.pathSeparator);
        }
        _jdroidlibPath = new File(_jdroidlibDir);
        _resourcePath = new File(_resourceDir);
    }
    
    /**
     * Constructor for this class.
     * This constructor is private, as it is not to be called by members other
     * than the ones present in this class!
     * @throws java.io.IOException  This exception is thrown, when an error
     *                              occurs during the extraction of the
     *                              resources needed by JDroidLib.
     */
    private ResourceManager() throws IOException {
        checkDirectories();
        extractResources();
    }
    
    /**
     * Checks the directories and files used in this class for integrity, and 
     * makes sure everything is as it is expected to be.
     * This method will delete any files in the above directories!
     */
    private void checkDirectories() {
        if (!_jdroidlibPath.exists())
            _jdroidlibPath.mkdirs();
        
        if (!_resourcePath.exists())
            _resourcePath.mkdirs();
        else purgeFiles(_resourcePath);
    }
    
    /**
     * Recursively deletes all files and directories in a given path.
     * @param path The directory to purge. If this parameter is a file, instead
     * of a directory, the file will be deleted instead.
     */
    public void purgeFiles(File path) {
        if (path.isFile()) { path.delete(); return; }
        
        for (File file : path.listFiles())
            if (file.isFile())
                file.delete();
            else
                purgeFiles(file);
    }
    
    /**
     * Extracts the resources needed by JDroidLib to a pre-defined location on
     * the local computer.
     * @throws java.io.IOException This exception is thrown when something goes 
     * wrong while installing the resources to the local HDD
     */
    @SuppressWarnings({"ConvertToTryWithResources", "UnusedAssignment"}) 
    private void extractResources() throws IOException {
        // Extract resources depending on which operating system
        // is installed and is being used
        if (_currentOS == OperatingSystem.WINDOWS_X64 || 
            _currentOS == OperatingSystem.WINDOWS_X86) {
            String[] files = {
                "adb.exe", "AdbWinApi.dll", "AdbWinUsbApi.dll", "fastboot.exe",
                "dmtracedump.exe", "etc1tool.exe", "hprof-conv.exe"
            };
            
            for (String file : files) {
                String res = String.format("%0$s#%1$s", 
                        OperatingSystem.getResourcePackage(_currentOS), file)
                        .replace("#", File.pathSeparator);
                File path = new File(String.format("%0$s#%1$s", _resourceDir, 
                        file).replace("#", File.pathSeparator));
                
                InputStream iStream = getClass().getResourceAsStream(res);
                OutputStream oStream = new FileOutputStream(path);
                int readBytes = 0;
                byte[] buffer = new byte[4096];
                
                while ((readBytes = iStream.read(buffer)) > 0)
                    oStream.write(buffer, 0, readBytes);
                
                iStream.close();
                oStream.close();
            }
            
            adb = new File(String.format("%0$s#%1$s", _resourceDir, files[0])
                                 .replace("#", File.pathSeparator));
            fastboot = new File(String.format("%0$s#%1$s", _resourceDir, files[3])
                                 .replace("#", File.pathSeparator));
            
        } else {
            String[] files = {
                "adb", "fastboot", "dmtracedump", "etc1tool", "hprof-conv"
            };
            
            for (String file : files) {
                String res = String.format("%0$s#%1$s",
                        OperatingSystem.getResourcePackage(_currentOS), file)
                        .replace("#", File.pathSeparator);
                File path = new File(String.format("%0$s#%1$s", _resourceDir,
                        file).replace("#", File.pathSeparator));
                
                InputStream iStream = getClass().getResourceAsStream(res);
                OutputStream oStream = new FileOutputStream(path);
                int readBytes = 0;
                byte[] buffer = new byte[4096];
                
                while ((readBytes = iStream.read(buffer)) > 0)
                    oStream.write(buffer, 0, readBytes);
                
                iStream.close();
                oStream.close();
            }
            
            adb = new File(String.format("%0$s#%1$s", _resourceDir, files[0])
                                 .replace("#", File.pathSeparator));
            fastboot = new File(String.format("%0$s#%1$s", _resourceDir, files[1])
                                 .replace("#", File.pathSeparator));
        }
    }
    
    /**
     * Gets a value, indicating whether the operating system is 32- or 64-bit.
     * @return {@code true} if OS is 64-bit, {@code false} if otherwise.
     */
    private boolean is64Bit() {
        if (System.getProperty("os.name").contains("Windows")) {
            return (System.getenv("ProgramFiles(x86)") != null);
        } else {
            return (System.getProperty("os.arch").contains("64"));
        }
    }
    
    /**
     * Gets JDroidLib's working dir.
     * @return JDroidLib's working directory.
     */
    public File getJDroidLibPath() { return _jdroidlibPath; }
    
    /**
     * Gets JDroidLib's resource path.
     * @return The resource path.
     */
    public File getResourcePath() { return _resourcePath; }
    
    /**
     * Gets a {@link java.io.File} object pointing to the ADB binary being used by JDroidLib.
     * @return A pointer to the ADB file being used.
     */
    public File getAdb() { return adb; }
    
    /**
     * Gets a {@link java.io.File} object pointing to the fastboot binary being used by JDroidLib.
     * @return A pointer to the fastboot file being used.
     */
    public File getFastboot() { return fastboot; }
    
}
