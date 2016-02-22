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
package com.casoftworks.jdroidlib.android;

/**
 *
 * @author simoncahill
 */
public enum DeviceState {
        /**
         * The devices is online.
         */
        ONLINE,

        /**
         * The device is offline.
         */
        OFFLINE,

        /**
         * The device is in recovery mode.
         */
        RECOVERY,

        /**
         * The device is currently in fastboot.
         */
        FASTBOOT,

        /**
         * The device's state is currently unknown.
         */
        UNKNOWN,
        
        /**
         * The device has not yet been authorized and cannot be used.
         */
        UNAUTHORIZED;
}
