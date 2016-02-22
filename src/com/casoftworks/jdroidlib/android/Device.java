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

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Simon
 */
public class Device {
    
    private final AndroidController androidController;
    
    //<editor-fold defaultstate="collapsed" desc="Properties and shit" >
    private final String serialNumber;
    private DeviceState deviceState;
    
    //</editor-fold>
    
    /**
     * Default constructor. Package-private.
     * @param serialNumber  The serial number of the connected device that is
     *                      to be associated with this instance.
     * @throws IOException  
     */
    Device(String serialNumber) throws IOException {
        this.serialNumber = serialNumber;
        this.deviceState = DeviceState.UNKNOWN;
        androidController = AndroidController.getInstance();
    }
    
    /**
     * Recommended constructor. Package-private.
     * @param serialNumber      The serial number of the IP address of the
     *                          connected device that is to be associated
     *                          with this object.
     * @param deviceState       The state the device is currently in.
     * @throws IOException
     */
    Device(String serialNumber, DeviceState deviceState) throws IOException {
        this.serialNumber = serialNumber;
        this.deviceState = deviceState;
        androidController = AndroidController.getInstance();
    }
    
    /**
     * Gets the serial number of the device that is associated with this class
     * instance.
     * @return The serial number.
     */
    public String getSerialNumber() {
        return serialNumber;
    }
    
    /**
     * Gets the state the device is currently in.
     * @return  The {@link com.casoftworks.jdroidlib.android.DeviceState} of this
     *          object.
     * @throws IOException 
     */
    public DeviceState getDeviceState() throws IOException {
        androidController.updateDeviceList();
        return deviceState;
    }
    
    /**
     * Sets the state of this object (device).
     * This method is package-private and should never be called by third-party
     * applications/classes!
     * @param state The new state of this object (device).
     */
    void setDeviceState(DeviceState state) { this.deviceState = state; }
    
    /**
     * Gets a value indicating whether this device has authorized this computer
     * or not.
     * If a device has not authorized a computer, said computer CANNOT access it
     * via ADB!
     * @return 
     */
    public boolean isAuthorized() { 
        return !deviceState.equals(DeviceState.UNAUTHORIZED); 
    }
    
}
