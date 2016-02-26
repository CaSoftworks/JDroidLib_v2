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
 * JDroidLib Exception.
 * 
 * This exception is thrown if a given device is found to not have root access when it is needed.
 * Note: Some of JDroidLib's features require that a device is rooted and/or has third-party software installed.
 * 
 * This exception will generally be thrown if the su binary cannot be found on a given device.
 * JDroidLib will attempt to search multiple directories to find the su binary.
 * 
 * Common remedies for this problem:
 *  -   Notify the user of the application that root access is required for certain features of this library.
 *      Provide them with the means of rooting their device (AT THEIR OWN RISK!).
 *  -   Make sure the su binary isn't installed in an exotic folder on the device.
 *      If you find this to be true, check if JDroidLib provides the means to use the new location.
 *      Should JDroidLib not provide these means, you will likely have to write your own helper functions using JDroidLib,
 *      or you can add the functionality to JDroidLib as a user contribution. 
 * @author simoncahill
 */
public class DeviceNotRootedException extends Exception {
    
    public DeviceNotRootedException() { super(); }
    
    public DeviceNotRootedException(String msg) { super(msg); }
    
}
