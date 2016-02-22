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

import com.casoftworks.jdroidlib.interfaces.ICommand;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * AndroidController class. Extends {@link com.casoftworks.jdroidlib.android.Controller}.
 * This class is a singleton class, only <b ><u >one</u></b> instance of this
 * class may exist at any given time.
 * This class controls all interactions between JDroidLib, your application,
 * and any given (connected) device, fully booted in the Android OS.
 * This class cannot be inherited.
 * @author Simon
 */
public final class AndroidController extends Controller {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Stuff" >
    /** Contains the singleton instance of this class. */
    private static AndroidController instance;

    /**
     * Singleton method.
     * If necessary, spawns a new instance of the {@link com.casoftworks.jdroidlib.android.AndroidController} class.
     * @return Returns the instance of this class.
     * @throws IOException  This exception is thrown,
     *                      if something goes wrong during the initialisation
     *                      of JDroidLib.
     */
    public static AndroidController getInstance() throws IOException {
        return instance == null ? instance = new AndroidController() : instance;
    }
    //</editor-fold>
    
    private List<Device> deviceList;
    
    /**
     * Default constructor for this class.
     * This constructor can not be called by any other members, than the 
     * singleton function in this class.
     * @throws IOException 
     */
    private AndroidController() throws IOException { super(); }
    
    //<editor-fold defaultstate="collapsed" desc="Methods implemented from Controller" >
    /**
     * {@inheritDoc}
     */
    @Override
    public String executeCommandReturnOutput(ICommand cmd) throws IOException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int executeCommandReturnExitCode(ICommand cmd) throws IOException {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand(ICommand cmd) throws IOException {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeCommandReturnOutputAsync(ICommand cmd) throws IOException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int executeCommandReturnExitCodeAsync(ICommand cmd) throws IOException {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommandAsync(ICommand cmd) throws IOException {
        
    }
    //</editor-fold>
    
    public List<Device> getDevices() throws IOException {
        updateDeviceList(); return deviceList;
    }
    
    private void updateDeviceList() throws IOException {
        AndroidCommand cmd = AndroidCommand.formAndroidCommand(null, "devices", "-l");
        String output = executeCommandReturnOutput(cmd);
        
        String line = null;
        
        try (BufferedReader reader = new BufferedReader(new StringReader(output))) {
            while ((line = reader.readLine()) != null) {
                
            }
        } catch (IOException ex) {
            System.err.println("An error has occurred within JDroidLib!");
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
}
