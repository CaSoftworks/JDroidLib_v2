/*
 * Copyright (C) 2015 Ca Softworks.
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

/**
 *
 * @author Simon
 */
public class Device {
    
    //<editor-fold defaultstate="collapsed" desc="Properties and shit" >
    private final String serialNumber;
    
    
    //</editor-fold>
    
    /**
     * Default constructor. Package-private.
     * @param serialNumber The serial number of the connected device that is
     *                      to be associated with this instance.
     */
    Device(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    /**
     * Gets the serial number of the device that is associated with this class
     * instance.
     * @return The serial number.
     */
    public String getSerialNumber() {
        return serialNumber;
    }
    
}
