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
package com.casoftworks.jdroidlib.interfaces;

import java.util.List;

/**
 * Command interface for JDroidLib.
 * Provides the basic structure for any command created by and used in JDroidLib.
 * This interface is implemented by 
 * {@link com.casoftworks.jdroidlib.interfaces.AndroidCommand}
 * and
 * {@link com.casoftworks.jdroidlib.interfaces.FastbootCommand}
 * @author Simon
 */
public interface ICommand {
    
    /**
     * Sets the command used by this class.
     * @param cmd The command to be executed.
     */
    void setCommand(String cmd);
    
    /**
     * Replaces the saved arguments in this class.
     * @param args The arguments to replace the old ones with
     */
    void setArgs(List<String> args);
    
    /**
     * Replaces the saved arguments in this class.
     * @param args The arguments to replace the old ones with
     */
    void setArgs(String... args);
    
    /**
     * Removes one or more arguments from the list of arguments saved in this 
     * class.
     * @param args The argument(s) to remove.
     * @return  A value indicating whether the removal of the arguments
     *          was successful or not.
     */
    boolean removeArgs(String... args);
    
    /**
     * Adds one or more arguments to this list of arguments saved in this class.
     * @param args The arguments to append.
     */
    void appendArgs(String... args);
    
    /**
     * Gets a {@link java.util.List} of type {@link java.lang.String} containing
     * all the arguments in this class.
     * @return The arguments in this class.
     */
    List<String> getArgs();
    
    /**
     * Gets a fully configured instance of a {@link java.lang.ProcessBuilder}
     * @return A ProcessBuilder
     */
    ProcessBuilder buildProcess();
    
    /**
     * Sets the device this command should be executed on.
     * @param device    The {@link com.casoftworks.jdroidlib.android.Device} this
     *                  command should be executed on.
     */
    void setDevice(Device device);
    
}