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

import java.util.HashMap;

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
        private final boolean _appendSlashToDirs = true;
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
        
    }
    //</editor-fold>
    
    private final Device device;
    
    /**
     * Default constructor.
     * @param device 
     */
    FileSystem(Device device) {
        this.device = device;
    }
    
    /**
     * Lists all the files in a given directory.
     * @param path The path from which to list the files.
     * @param options The options for the file/directory listing. Set to {@code null} to use the default options!
     * @return A {@link HashMap(String, ListingType)} containing all of the filesystem entries.
     */
    public HashMap<String, ListingType> listFiles(String path, ListingOptions options) {
        HashMap<String, ListingType> entries = new HashMap<>();
        
        return entries;
    }
    
}
