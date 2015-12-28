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
import com.casoftworks.jdroidlib.util.ResourceManager;

import java.io.File;
import java.io.IOException;

/**
 * Abstract class Controller.
 * This class provides the base structure for the different controller classes
 * used in and by JDroidLib. 
 * This class is package-private, and as such can only be inherited by classes
 * in this package.
 * @author Simon
 */
abstract class Controller {
    
    protected final File executable;
    
    /**
     * Customisable constructor
     * @param executable 
     */
    protected Controller(File executable) {
        this.executable = executable;
    }
    
    /**
     * Default constructor.
     * @throws IOException 
     */
    protected Controller() throws IOException {
        this.executable = ResourceManager.getInstance().getAdb();
    }
    
    /**
     * Executes a given {@link com.casoftworks.jdroidlib.interfaces.ICommand}
     * derivative on the main (UI) thread. Using this method might block
     * the thread. 
     * @param cmd The command to be executed.
     * @return The full output of the command.
     * @throws IOException  This exception is thrown, if something goes wrong
     *                      during an IO operation or communication between
     *                      JDroidLib and any given (connected) device.
     */
    public abstract String executeCommandReturnOutput(ICommand cmd) throws IOException;
    
    /**
     * Executes a given {@link com.casoftworks.jdroidlib.interfaces.ICommand}
     * derivative on the main (UI) thread. Using this method might block
     * the thread. 
     * @param cmd The command to be executed.
     * @return The exit code of the binary
     * @throws IOException  This exception is thrown, if something goes wrong
     *                      during an IO operation or communication between
     *                      JDroidLib and any given (connected) device.
     */
    public abstract int executeCommandReturnExitCode(ICommand cmd) throws IOException;
    
    /**
     * Executes a given {@link com.casoftworks.jdroidlib.interfaces.ICommand}
     * derivative on the main (UI) thread. Using this method might block
     * the thread. 
     * This method does not return anything.
     * @param cmd The command to be executed.
     * @throws IOException  This exception is thrown, if something goes wrong
     *                      during an IO operation or communication between
     *                      JDroidLib and any given (connected) device.
     */
    public abstract void executeCommand(ICommand cmd) throws IOException;
    
    /**
     * Executes a given {@link com.casoftworks.jdroidlib.interfaces.ICommand}
     * derivative asynchronously. Using this method will <b >not</b> lock the
     * UI thread.
     * @param cmd The command to be executed.
     * @return The full output of the command.
     * @throws IOException  This exception is thrown, if something goes wrong
     *                      during an IO operation or communication between
     *                      JDroidLib and any given (connected) device.
     */
    public abstract String executeCommandReturnOutputAsync(ICommand cmd) throws IOException;
    
    /**
     * Executes a given {@link com.casoftworks.jdroidlib.interfaces.ICommand}
     * derivative asynchronously. Using this method will <b >not</b> lock the
     * UI thread.
     * @param cmd The command to be executed.
     * @return The exit code of the binary
     * @throws IOException  This exception is thrown, if something goes wrong
     *                      during an IO operation or communication between
     *                      JDroidLib and any given (connected) device.
     */
    public abstract int executeCommandReturnExitCodeAsync(ICommand cmd) throws IOException;
    
    /**
     * Executes a given {@link com.casoftworks.jdroidlib.interfaces.ICommand}
     * derivative asynchronously. Using this method will <b >not</b> lock the
     * UI thread.
     * This method does not return anything.
     * @param cmd The command to be executed.
     * @throws IOException  This exception is thrown, if something goes wrong
     *                      during an IO operation or communication between
     *                      JDroidLib and any given (connected) device.
     */
    public abstract void executeCommandAsync(ICommand cmd) throws IOException;
    
}
