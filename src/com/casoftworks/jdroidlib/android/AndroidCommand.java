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

import com.casoftworks.jdroidlib.android.Device;
import com.casoftworks.jdroidlib.interfaces.ICommand;
import com.casoftworks.jdroidlib.util.ResourceManager;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a command to be executed by the ADB deamon on a given Android
 * device.
 * @author Simon
 */
public final class AndroidCommand implements ICommand {
    
    private String _command;
    private List<String> _args;
    private ProcessBuilder _builder;
    private Device _device;
    private boolean _runAsRoot;
    private final boolean _isShellCmd;
    
    /**
     * Forms a new AndroidCommand which can be executed with JDroidLib.
     * @param device        The {@link com.casoftworks.jdroidlib.android.Device}
     *                      to execute the command on.
     * @param executable    The executable to be called.
     * @param args          The arguments for the executable.
     * @param asShell       Run the command on the device's shell.
     * @param asRoot        Run the command as the root user.
     * @return              An instance of this class.
     */
    public static AndroidCommand formAndroidCommand(Device device, 
            String executable, List<String> args, boolean asShell, 
            boolean asRoot) {
        return new AndroidCommand(
                executable, asShell, asRoot, device, (String[])args.toArray()
        );
    }
    
    AndroidCommand(String cmd, boolean isShellCommand, boolean runAsRoot, Device device, String... args) {
        _command = cmd;
        _args = Arrays.asList(args);
        _builder = new ProcessBuilder();
        _device = device;
        _runAsRoot = runAsRoot;
        _isShellCmd = isShellCommand;
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
    public ProcessBuilder buildProcess() throws IOException { 
    
        _builder = new ProcessBuilder();
        
        // Build command
        List<String> command = new ArrayList<>();
        command.add(ResourceManager.getInstance().getAdb().getAbsolutePath());
        command.add("-s");
        command.add(_device.getSerial());
        
        if (isShellCommand()) {
            command.add("shell");
            if (runAsRoot())
                command.add("su");
        }
        
        command.add(this._command);
        getArgs().forEach((String arg) -> command.add(arg));
        
        _builder.command(command);
        _builder.directory(ResourceManager.getInstance().getJDroidLibPath());
        _builder.redirectErrorStream(true);
        
        return _builder; 
    }

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
    public void runAsRoot(boolean val) { this._runAsRoot = val; }
    
    /**
     * Gets a value indicating whether this command will run as root or not.
     * @return {@code true} if this command will run as root.
     */
    public boolean runAsRoot() { return _runAsRoot; }
    
    /**
     * Gets a value indicating whether this command will run on the device's 
     * or not.
     * @return 
     */
    public boolean isShellCommand() { return _isShellCmd; }
    
    /**
     * Gets the command (executable) on the device to be called and executed.
     * @return The command.
     */
    public String getCommand() {
        return this._command;
    }
}
