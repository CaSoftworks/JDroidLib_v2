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
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

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
    public String executeCommandReturnOutput(ICommand cmd) throws IOException, InterruptedException {
        StringBuilder output = new StringBuilder();
        ProcessBuilder process = cmd.buildProcess();
        Process pr = process.start();
        String line;
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()))) {
            while ((line = reader.readLine()) != null)
                output.append(line);
        } catch (IOException ex) {
            System.err.println("An error has occurred within JDroidLib!");
            ex.printStackTrace(System.err);
            throw ex;
        }
        pr.waitFor(cmd.getTimeout(), TimeUnit.MILLISECONDS);
        return output.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int executeCommandReturnExitCode(ICommand cmd) throws IOException, InterruptedException {
        try {
            Process pr = cmd.buildProcess().start();
            if (pr.waitFor(cmd.getTimeout(), TimeUnit.MILLISECONDS))
                return pr.exitValue();
            else return -1;
        } catch (IOException | InterruptedException ex) {
            System.err.println("An error has occurred within JDroidLib!");
            ex.printStackTrace(System.err);
            throw ex;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand(ICommand cmd) throws IOException, InterruptedException {
        Process pr = cmd.buildProcess().start();
        pr.waitFor(cmd.getTimeout(), TimeUnit.MILLISECONDS);
    }

    private IOException executeCommandReturnNoOutputAsyncIOException;
    /**
     * {@inheritDoc}
     */
    @Override
    public Future<String> executeCommandReturnOutputAsync(ICommand cmd) throws IOException, InterruptedException, ExecutionException {
        
        executeCommandReturnNoOutputAsyncIOException = null;
        
        Future<String> future = new FutureTask<>(() -> {
            StringBuilder output = new StringBuilder();
            Process pr = cmd.buildProcess().start();
            String line;
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()))) {
                while ((line = reader.readLine()) != null) {
                    
                }
            } catch (IOException ex) {
                System.err.println("An error has occurred within JDroidLib");
                ex.printStackTrace(System.err);
                executeCommandReturnNoOutputAsyncIOException = ex;
                return null;
            }
            return output.toString();
        });
        
        if (executeCommandReturnNoOutputAsyncIOException != null)
            throw executeCommandReturnNoOutputAsyncIOException;
        
        return future;
    }

    private InterruptedException executeCommandReturnExitCodeAsyncInterruptedException;
    private IOException executeCommandReturnExitCodeAsyncIOException;
    /**
     * {@inheritDoc}
     */
    @Override
    public Future<Integer> executeCommandReturnExitCodeAsync(ICommand cmd) throws IOException, InterruptedException, ExecutionException {
        
        executeCommandReturnExitCodeAsyncInterruptedException = null;
        executeCommandReturnExitCodeAsyncIOException = null;
        
        Future<Integer> future = new FutureTask<>(() -> {
            int returnVal = -1;
            try {
                 Process pr = cmd.buildProcess().start();
                 pr.waitFor(cmd.getTimeout(), TimeUnit.MILLISECONDS);
                 returnVal = pr.exitValue();
            } catch (IOException | InterruptedException ex) {
                System.err.println("An error has occurred within JDroidLib");
                ex.printStackTrace(System.err);
                if (ex instanceof IOException)
                    executeCommandReturnExitCodeAsyncIOException = (IOException)ex;
                else executeCommandReturnExitCodeAsyncInterruptedException = (InterruptedException)ex;
            }
            return returnVal;
        });
        
        if (executeCommandReturnExitCodeAsyncIOException != null)
            throw executeCommandReturnExitCodeAsyncIOException;
        if (executeCommandReturnExitCodeAsyncInterruptedException != null)
            throw executeCommandReturnExitCodeAsyncInterruptedException;
        
        return future;
        
    }

    private IOException executeCommandAsyncIOException;
    private InterruptedException executeCommandAsyncInterruptedException;
    /**
     * {@inheritDoc}
     */
    @Override
    public Future<Void> executeCommandAsync(ICommand cmd) throws IOException, InterruptedException, ExecutionException {
        
        executeCommandAsyncIOException = null;
        executeCommandAsyncInterruptedException = null;
        
        Future future = new FutureTask(() -> {
            
            try {
                Process pr = cmd.buildProcess().start();
                pr.waitFor(cmd.getTimeout(), TimeUnit.MILLISECONDS);
            } catch (IOException | InterruptedException ex) {
                System.err.println("An error has occurred within JDroidLib");
                ex.printStackTrace(System.err);
                if (ex instanceof IOException)
                    executeCommandAsyncIOException = (IOException)ex;
                else executeCommandAsyncInterruptedException = (InterruptedException)ex;
            }
            
            return null;
        });
        
        if (executeCommandAsyncIOException != null)
            throw executeCommandAsyncIOException;
        if (executeCommandAsyncInterruptedException != null)
            throw executeCommandAsyncInterruptedException;
        
        return future;
        
    }
    //</editor-fold>
    
    /**
     * Updates the list of devices and gets a list of all devices connected to
     * the computer.
     * @return  A {@link java.util.List} containing all the devices connected
     *          to this computer.
     * @throws IOException 
     * @throws java.lang.InterruptedException 
     */
    public List<Device> getDevices() throws IOException, InterruptedException {
        updateDeviceList(); return deviceList;
    }
    
    /**
     * Updates the list of devices stored in this object.
     * If the device list is updated and devices are still connected, these 
     * instances will not be changed.
     * @throws IOException 
     */
    void updateDeviceList() throws IOException, InterruptedException {
        AndroidCommand cmd = AndroidCommand.formAndroidCommand(null, "devices", "-l");
        String output = executeCommandReturnOutput(cmd);
        
        String line;
        List<Device> newDeviceList = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new StringReader(output))) {
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().startsWith("list of") || line.isEmpty()) continue; // Start anew, no device listings (yet).
                String[] splitValues = line.trim().split("\\s"); // [Serial/IP] [state] [product:value] [model:modelID] [device:[deviceID]
                boolean deviceFound = false;
                for (Device device : this.deviceList) {
                    if (device.getSerialNumber().equals(splitValues[0])) {
                        device.setDeviceState(DeviceState.valueOf(splitValues[1]));
                        newDeviceList.add(device);
                        deviceFound = true; 
                        break;
                    }
                }
                if (deviceFound) continue;
                newDeviceList.add(new Device(splitValues[0], DeviceState.valueOf(splitValues[1])));
            }
        } catch (IOException ex) {
            System.err.println("An error has occurred within JDroidLib!");
            ex.printStackTrace(System.err);
            throw ex;
        } finally {
            if (!newDeviceList.isEmpty())
                this.deviceList = newDeviceList;
        }
        
    }
    
}
