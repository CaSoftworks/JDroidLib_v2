/*
 * Copyright (C) 2016 Ca Softworks.
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
package com.casoftworks.jdroidlib.android;

import com.casoftworks.jdroidlib.exception.FileListingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the filesystem of a given {@link Device}.
 * This class provides methods for filesystem manipulation of any given device.
 * @author simoncahill
 */
public class FileSystem {
    
    //<editor-fold defaultstate="collapsed" desc="Nested Stuff" >
    /**
     * An enumeration of the possible listing types.
     * This enum helps applications decide whether a filesystem entry is a file or directory.
     */
    public static enum ListingType {
        /**
         * The listed filesystem entry is a file.
         */
        FILE,
        
        /**
         * The listed filesystem entry is a directory.
         */
        DIRECTORY;
    }
    
    /**
     * This class contains different options for listing filesystem entries.
     * Instances (objects) of this class are used in conjunction with the
     * {@link FileSystem#listFiles(java.lang.String, com.casoftworks.jdroidlib.android.FileSystem.ListingOptions) } function.
     * This class also contains the default options, you may use these to display defaults in your application.
     * To get an instance of this class with default values, simply create a new instance of this class.
     */
    public static final class ListingOptions {
        
        //<editor-fold defaultstate="collapsed" desc="Variables and Consts" >
        private final boolean _showAsList = true;
        private boolean _showAllEntries = true;
        private boolean _hideDirectoryJumps = false;
        private boolean _followSymLinks = false;
        private boolean _appendSlashToDirs = true;
        private boolean _appendIndicatorToEntries = false;
        private boolean _humanReadableSizes = false;
        private boolean _sortInReverseOrder = false;
        private boolean _sortBySize = false;
        private boolean _sortByExtension = false;
        private boolean _sortByVersion = false;
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Getter/Setter Methods" >
        /**
         * Gets a value indicating whether the output will be shown as a list.
         * This value cannot be changed, as this is the method JDroidLib uses to parse the output of the command.
         * This method is here only for convenience to you.
         * @return {@code true}.
         */
        public boolean showAsList() { return _showAsList; }
        
        /**
         * Gets a value indicating whether all filesystem entries will be shown (e.g.: entries starting with a period (.)).
         * @return {@code true} if all entries will be listed.
         */
        public boolean showAllEntries() { return _showAllEntries; }
        
        /**
         * Gets a value indicating whether directory jumps (./..) will be hidden or not.
         * @return {@code true} if directory jumps will be hidden or not.
         */
        public boolean hideDirectoryJumps() { return _hideDirectoryJumps; }
        
        /**
         * Gets a value indicating whether symlinks will be followed or not.
         * @return {@code true} if symlinks will be followed.
         */
        public boolean followSymLinks() { return _followSymLinks; }
        
        /**
         * Gets a value indicating whether directories will have a slash (/) appended to them or not.
         * This value cannot be changed, as this is the method JDroidLib uses to parse the output of the command.
         * This method is here only for convenience to you.
         * @return {@code true}.
         */
        public boolean appendSlashToDirs() { return _appendSlashToDirs; }
        
        /**
         * Gets a value indicating whether indicators (one of: * / = @ |) will be appended to filenames or not.
         * @return {@code true} if indicators will be appended or not.
         */
        public boolean appendIndicatorToEntries() { return _appendIndicatorToEntries; }
        
        /**
         * Gets a value indicating whether human-readable values will be used to display the sizes of filesystem entries or not.
         * @return {@code true} if human-readable sizes will be shown or not.
         */
        public boolean showHumanReadableSizes() { return _humanReadableSizes; }
        
        /**
         * Gets a value indicating whether the list will be sorted in reverse order or not.
         * @return {@code true} if the list will be sorted in reverse order.
         */
        public boolean sortInReverseOrder() { return _sortInReverseOrder; }
        
        /**
         * Gets a value indicating whether the list will be sorted by the size of the filesystem entries.
         * @return {@code true} if the list will be sorted by filesystem entry size.
         */
        public boolean sortBySize() { return _sortBySize; }
        
        /**
         * Gets a value indicating whether the list will be sorted by file extension.
         * @return {@code true} if the list will be sorted by file extensions.
         */
        public boolean sortByExtension() { return _sortByExtension; }
        
        /**
         * Gets a value indicating whether the list will be sorted by version.
         * @return {@code true} if the list will be sorted by version.
         */
        public boolean sortByVersion() { return _sortByVersion; }
        
        /**
         * Sets a value whether to show all entries.
         * @param bool The new value.
         * @return The instance. (Useful for method-chaining.)
         */
        public ListingOptions showAllEntries(boolean bool) { _showAllEntries = bool; return this; }
        
        /**
         * Sets a value whether to hide directory jumps.
         * @param bool The new value.
         * @return The instance. (Useful for method-chaining.)
         */
        public ListingOptions hideDirectoryJumps(boolean bool) { _hideDirectoryJumps = bool; return this; }
        
        ListingOptions appendSlashToDirs(boolean bool) { _appendSlashToDirs = bool; return this; }
        
        /**
         * Sets a value whether to append indicators to entries (one of: * / = @).
         * @param bool The new value.
         * @return The instance. (Useful for method-chaining.)
         */
        public ListingOptions appendIndicatorToEntries(boolean bool) { _appendIndicatorToEntries = bool; return this; }
        
        /**
         * Sets a value whether to show human-readable file sizes.
         * @param bool The new value.
         * @return The instance. (Useful for method-chaining.)
         */
        public ListingOptions showHumanReadableSizes(boolean bool) { _humanReadableSizes = bool; return this; }
        
        /**
         * Sets a value whether to sort in reverse order.
         * @param bool The new value.
         * @return The instance. (Useful for method-chaining.)
         */
        public ListingOptions sortInReverseOrder(boolean bool) { _sortInReverseOrder = bool; return this; }
        
        /**
         * Sets a value whether to sort by size.
         * @param bool The new value.
         * @return The instance. (Useful for method-chaining.)
         */
        public ListingOptions sortBySize(boolean bool) { _sortBySize = bool; return this; }
        
        /**
         * Sets a value whether to sort by file extension.
         * @param bool The new value.
         * @return The instance. (Useful for method-chaining.)
         */
        public ListingOptions sortByExtension(boolean bool) { _sortByExtension = bool; return this; }
        
        /**
         * Sets a value whether to sort by version.
         * @param bool The new value.
         * @return The instance. (Useful for method-chaining.)
         */
        public ListingOptions sortByVersion(boolean bool) { _sortByVersion = bool; return this; }
        //</editor-fold>
        
        /**
         * Gets all of the desired options as a {@link List(String)}.
         * @return A List of String containing all the options.
         */
        public List<String> getOptionsAsList() {
            List<String> ops = new ArrayList<>();
            
            if (_showAsList) ops.add("-l");
            if (_showAllEntries) ops.add("-a");
            if (_hideDirectoryJumps) ops.add("-A");
            if (_followSymLinks) ops.add("-L");
            if (_appendSlashToDirs) ops.add("-p");
            if (_appendIndicatorToEntries) ops.add("-F");
            if (_humanReadableSizes) ops.add("-h");
            if (_sortInReverseOrder) ops.add("-r");
            if (_sortBySize) ops.add("-S");
            if (_sortByExtension) ops.add("-X");
            if (_sortByVersion) ops.add("-v");
            
            return ops;
        }
        
        /**
         * Gets the desired options as a String[].
         * @return A String array containing all of the needed/wanted options.
         */
        public String[] getOptionsAsArray() {
            String[] array = new String[12];
            return getOptionsAsList().toArray(array);
        }
        
        /**
         * Gets all of the desired options as a {@link List(String) }, including the path at the end of the list.
         * @param path The path which to include in the options.
         * @return A list containing all of the desired options including the path.
         */
        public List<String> getOptionsAsListIncludePath(String path) {
            List<String> ops = getOptionsAsList();
            ops.add(path);
            return ops;
        }
        
        /**
         * Gets a String[] containing all of the desired options, including the path.
         * @param path The path which to include to the options.
         * @return An array of Strings containing the desires options and the path.
         */
        public String[] getOptionsAsArrayIncludePath(String path) {
            String[] ops = new String[12];
            return getOptionsAsListIncludePath(path).toArray(ops);
        }
        
    }
    //</editor-fold>
    
    private final Device device;
    private final AndroidController androidController;
    
    /**
     * Default constructor.
     * @param device 
     */
    FileSystem(Device device) throws IOException, InterruptedException {
        this.device = device;
        androidController = AndroidController.getInstance();
    }
    
    /**
     * Lists all the files in a given directory.
     * @param path The path from which to list the files.
     * @param requireSuperUser Set to {@code true}, if super user privileges are required to scan a directory.
     * @param options The options for the file/directory listing. Set to {@code null} to use the default options!
     * @return A {@link HashMap(String, ListingType)} containing all of the filesystem entries.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws com.casoftworks.jdroidlib.exception.FileListingException
     */
    public HashMap<String, ListingType> listFiles(String path, boolean requireSuperUser, ListingOptions options) throws IOException, InterruptedException, FileListingException {
        HashMap<String, ListingType> entries = new HashMap<>();
        
        // Don't even think of using BusyBox in this method. Create a separate method for it.
        // Set listing options
        if (options == null)
            options = new ListingOptions()
                    .appendIndicatorToEntries(false)
                    .appendSlashToDirs(false)
                    .hideDirectoryJumps(true)
                    .showAllEntries(false)
                    .showHumanReadableSizes(false)
                    .sortByExtension(false)
                    .sortBySize(false)
                    .sortByVersion(false)
                    .sortInReverseOrder(false);
        
        AndroidCommand cmd = AndroidCommand.formAndroidShellCommand(device, requireSuperUser , "ls", options.getOptionsAsListIncludePath(path));
        String output = androidController.executeCommandReturnOutput(cmd);
        if (output.toLowerCase().contains("aborting.") || output.toLowerCase().contains("permission denied."))
            throw new FileListingException(output.replace("ls:", ""));
        
        try (BufferedReader reader = new BufferedReader(new StringReader(output))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\s");
                // Split string by whitespace. If the length of the array is 6, the entry is a directory.
                // If the length of the array is 7, the entry is a file.
                // File listings include the size of the file. With directories, this is left out as whitespace.
                
                if (split.length == 6)
                    // Entry is a directory - missing file size
                    entries.put(split[split.length - 1], ListingType.DIRECTORY);
                else
                    // Entry is a file - file size is present.
                    entries.put(split[split.length - 1], ListingType.FILE);
                
            }
            
        } catch (IOException ex) {
            System.err.println("An error has occurred within JDroidLib");
            ex.printStackTrace(System.err);
            throw ex;
        }
        
        return entries;
    }
    
    /**
     * Lists all of the filesystem entries in a given directory.
     * This function uses busybox! If busybox is not installed on the system, this method WILL throw an exception if the busybox binary could not be found!
     * @param path The path of which to list the entries.
     * @param requireSuperUser Set to {@code true} if super user privileges are required to peek in to the provided path.
     * @param options The options to use for entry listing. Set to {@code null} to use the default options.
     * @return This function returns a {@link HashMap(String, ListingType)} where the string is the name of the filesystem entry, 
     * and the {@link ListingType} describes whether the entry is a directory or a file.
     * @throws IOException
     * @throws InterruptedException
     * @throws FileListingException 
     */
    public HashMap<String, ListingType> listFilesUsingBusybox(String path, boolean requireSuperUser, ListingOptions options) throws IOException, InterruptedException, FileListingException {
        HashMap<String, ListingType> entries = new HashMap<>();
        
        if (options == null)
            options = new ListingOptions();
        
        List<String> ops = new ArrayList<>();
        ops.add("ls");
        ops.addAll(options.getOptionsAsListIncludePath(path));
        AndroidCommand cmd = AndroidCommand.formAndroidShellCommand(device, requireSuperUser, "busybox", ops);
        
        return entries;
    }
    
}
