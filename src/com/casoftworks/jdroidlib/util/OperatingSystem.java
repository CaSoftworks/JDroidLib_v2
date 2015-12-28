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

/**
 * Contains all the different operating systems JDroidLib will run on. 
 * If your OS is not present in this enum, JDroidLib will not run.
 * @author Simon
 */
public enum OperatingSystem {
    
    /** Windows, ix86 (32-bit operating system). */
    WINDOWS_X86,
    /** Windows, ix64 (64-bit operating system) */
    WINDOWS_X64,
    /** Linux, ix86 and ix64 */
    LINUX_X86_X64,
    /** Mac OS X, ix64 */
    MAC_OS_X_X64;
    
    /**
     * Gets the resource package path for the desired OS.
     * @param os The OS for which to get the resource package.
     * @return The path.
     */
    public static String getResourcePackage(OperatingSystem os) {
        return String.format("/com/casoftworks/jdroidlib/res/%0$s", String.valueOf(os));
    }
}
