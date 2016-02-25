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
package com.casoftworks.jdroidlib.exception;

/**
 * JDroidLib exception.
 * This exception is usually thrown when an error occurs while listing files in a directory on a device.
 * 
 * Usual remedies include (but are not limited to):
 *  - Checking to see if the correct listing method was used.
 *      Some options for listing are only available when BusyBox is installed on the device.
 *      To make things easier for developers, there are several methods for listing filesystem entries on a device.
 *      Ensure your application is using the correct method.
 *  - Checking if sufficient rights were granted.
 *      Some directories cannot be scanned without sufficient rights, such as the /data/ partition. 
 *      This partition is usually protected and  can usually only be scanned by a super (root) user.
 * 
 * For further details, please refer to the message that was output by the exception.
 * @author simoncahill
 */
public class FileListingException extends Exception {
    
    public FileListingException() { super(); }
    
    public FileListingException(String msg) { super(msg); }
    
}
