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
 * 
 * This exception is thrown when JDroidLib cannot find a busybox executable on a given device.
 * Some of JDroidLib's features require busybox to function.
 * 
 * Common remedies for this problem:
 *  -   Install busybox through your application. 
 *      JDroidLib might provide the functionality to install binary files to the system - if root access is provided.
 *      
 *  -   Notify the user of the problem and redirect them to a third-party application in the Google Play Store.
 *      The developers of JDroidLib recommend Steven Stericson's Busybox Installer application from the Play Store.
 * @author simoncahill
 */
public class BusyboxNotFoundException extends Exception {
    
    public BusyboxNotFoundException() { super(); }
    
    public BusyboxNotFoundException(String msg) { super(msg); }
    
}
