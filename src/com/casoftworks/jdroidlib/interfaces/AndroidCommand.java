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

import java.util.Arrays;
import java.util.List;

/**
 * Represents a command to be executed by the ADB deamon on a given Android
 * device.
 * @author Simon
 */
public abstract class AndroidCommand implements ICommand {
    
    private String _command;
    private List<String> _args;
    private ProcessBuilder _builder;
    private Device _device;
    private boolean _runAsRoot;
    
    AndroidCommand(String cmd, boolean runAsRoot, Device device, String... args) {
        _command = cmd;
        _args = Arrays.asList(args);
        _builder = new ProcessBuilder();
        _device = device;
        _runAsRoot = runAsRoot;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Implemented methods from ICommand" >
    /**
     * {@inheritDoc}
     * @param cmd 
     */
    @Override
    public void setCommand(String cmd) { this._command = cmd; }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void setArgs(List<String> args) { this._args = args; }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void setArgs(String... args) { this._args = Arrays.asList(args); }

    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean removeArgs(String... args) {
        for (String arg : args)
            _args.remove(arg);
        for (String arg : args)
            if (_args.contains(arg)) return false;
        return true;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void appendArgs(String... args) { _args.addAll(Arrays.asList(args));
}

    /**
     * {@inheritDoc} 
     */
    @Override
    public List<String> getArgs() { return _args; }

    /**
     * {@inheritDoc} 
     */
    @Override
    public ProcessBuilder buildProcess() { return _builder; }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void setDevice(Device device) { this._device = device; }
    
    //</editor-fold>
    
    /**
     * Sets a value in this class, determining whether this command should be 
     * executed with root permissions or not.
     * @param val A value indicating whether to execute as root or not.
     */
    public void setRunAsRoot(boolean val) { this._runAsRoot = val; }
    
    /**
     * Gets a value indicating whether this command will run as root or not.
     * @return {@code true} if this command will run as root.
     */
    public boolean willRunAsRoot() { return _runAsRoot; }
}
