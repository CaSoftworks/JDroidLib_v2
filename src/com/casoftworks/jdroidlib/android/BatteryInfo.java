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

import java.io.IOException;

/**
 * Contains information and manipulation methods for a given device's battery.
 * This class will give you detailed information about what is happening with a device's battery, including conversion methods
 * for native output.
 * @author simoncahill
 */
public class BatteryInfo {
    
    private final Device device;
    
    /**
     * Default constructor.
     * @param device The device to wrap with this class.
     */
    BatteryInfo(Device device) throws IOException, InterruptedException {
        this.device = device;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Battery variables" >
    private boolean acPowered = false;
    private boolean usbPowered = false;
    private boolean wirelesslyPowered = false;
    private int status = -1;
    private int health = -1;
    private boolean present = false;
    private int level = -1;
    private int scale = -1;
    private int voltage = -1;
    private double temperature = -1;
    private String technology = null;
    private boolean ledCharging = false;
    private boolean ledLowBattery = false;
    private int currentNow = -1;
    private boolean adaptiveFastChargingSettings = false;
    private boolean supportLogBatteryUsage = false;
    private boolean tablet = false;
    private double maxTemp = -1;
    private int maxCurrent = -1;
    private int asocEfs = -1;
    private int asocNow = -1;
    //<editor-fold defaultstate="collapsed" desc="Getter Methods" >
    /**
     * Gets a value indicating whether the battery is being powered via an AC (mains) outlet.
     * @return {@code true} if the battery is being powered via an AC (mains) outlet.
     * @throws IOException
     * @throws InterruptedException 
     */
    public boolean isAcPowered() throws IOException, InterruptedException { update(); return acPowered; }
    
    /**
     * Gets a value indicating whether the battery is being powered via a USB port.
     * @return {@code true} if the battery is being powered via a USB port.
     * @throws IOException
     * @throws InterruptedException 
     */
    public boolean isUsbPowered() throws IOException, InterruptedException { update(); return usbPowered; }
    
    /**
     * Gets a value indicating whether the battery is being powered wirelessly.
     * @return {@code true} if the battery is being powered wirelessly.
     * @throws IOException
     * @throws InterruptedException 
     */
    public boolean isWirelesslyPowered() throws IOException, InterruptedException { update(); return wirelesslyPowered; }
    
    /**
     * Gets a value indicating the current status of the battery.
     * The returned values can be parsed to string via the corresponding methods in this class.
     * @return An integer value indicating the current status of the battery.
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public int getStatus() throws IOException, InterruptedException { update(); return status; } 
    
    /**
     * Gets a value indicating the current health of the battery.
     * The returned values can be parsed to a string via the corresponding methods in this class.
     * @return An integer value indicating the current health of the battery.
     * @throws IOException
     * @throws InterruptedException 
     */
    public int getHealth() throws IOException, InterruptedException { update(); return health; }
    
    /**
     * Gets a value indicating whether the battery is present or not.
     * @return {@code true} if the battery is present.
     * @throws IOException
     * @throws InterruptedException 
     */
    public boolean isPresent() throws IOException, InterruptedException { update(); return present; }
    
    /**
     * Gets a value indicating the current level of the battery.
     * @return The charge percentage of the battery (0-100).
     * @throws IOException
     * @throws InterruptedException 
     */
    public int getLevel() throws IOException, InterruptedException { update(); return level; }
    
    /**
     * Gets a value indicating the scale of the battery.
     * @return The scale of the battery.
     * @throws IOException
     * @throws InterruptedException 
     */
    public int getScale() throws IOException, InterruptedException { update(); return scale; }
    
    /**
     * Gets a value indicating the current voltage output of the battery.
     * @return The voltage output of the battery (in mV).
     * @throws IOException
     * @throws InterruptedException 
     */
    public int getVoltage() throws IOException, InterruptedException { update(); return voltage; }
    
    /**
     * Gets a value indicating the current temperature of the battery.
     * @return The current temperature of the battery (in °C).
     * To convert this measurement to °F, use the corresponding method in this class.
     * @throws IOException
     * @throws InterruptedException 
     */
    public double getTemperature() throws IOException, InterruptedException { update(); return temperature; }
    
    /**
     * Gets a value indicating the technology used in the battery.
     * @return The technology used in this battery.
     * @throws IOException
     * @throws InterruptedException 
     */
    public String getTechnology() throws IOException, InterruptedException { return technology; } // No point updating, this won't change.
    
    /**
     * Gets a value indicating whether the LED will illuminate to indicate the device is charging.
     * @return {@code true} if the LED will illuminate to indicate the device is charging.
     * @throws IOException
     * @throws InterruptedException 
     */
    public boolean ledIndicatesCharging() throws IOException, InterruptedException { update(); return ledCharging; }
    
    /**
     * Gets a value indicating whether the LED will illuminate to indicate a low battery.
     * @return {@code true} if the LED will illuminate to indicate the device's battery is low on charge.
     * @throws IOException
     * @throws InterruptedException 
     */
    public boolean ledIndicatesLowBattery() throws IOException, InterruptedException { update(); return ledLowBattery; }
    
    /**
     * Gets a value indicating the current output (mA) of the battery.
     * @return A value indicating the current output of the battery.
     * @throws IOException
     * @throws InterruptedException 
     */
    public int getCurrentNow() throws IOException, InterruptedException { update(); return currentNow; }
    
    /**
     * Gets a value indicating whether this device has adaptive fast charging settings.
     * @return {@code true} if the device has adaptive fast charging settings.
     * @throws IOException
     * @throws InterruptedException 
     */
    public boolean hasAdaptiveFastChargingSettings() throws IOException, InterruptedException { return adaptiveFastChargingSettings; } // No point updating, this won't change.
    
    /**
     * Gets a value indicating whether this battery supports battery usage logging.
     * @return {@code true} if this device supports battery usage logging.
     * @throws IOException
     * @throws InterruptedException 
     */
    public boolean supportsBatteryUsageLogging() throws IOException, InterruptedException { update(); return supportLogBatteryUsage; }
    
    /**
     * Gets a value indicating whether this device is a tablet or not.
     * @return {@code true} if this device is a tablet.
     * @throws IOException
     * @throws InterruptedException 
     */
    public boolean isTablet() throws IOException, InterruptedException { return tablet; } // This definitely won't change...
    
    /**
     * Gets a value indicating the maximum temperature of this battery.
     * @return The maximum temperature of this battery.
     * @throws IOException
     * @throws InterruptedException 
     */
    public double getMaxTemperature() throws IOException, InterruptedException { return maxTemp; } // No point in... why do I bother?
    
    /**
     * Gets a value indicating the maximum current of this battery.
     * @return The maximum current of this battery.
     * @throws IOException
     * @throws InterruptedException 
     */
    public int getMaxCurrent() throws IOException, InterruptedException { return maxCurrent; }
    
    /**
     * I honestly have no idea what this is.
     * If you know, feel free to contribute.
     * @return
     * @throws IOException
     * @throws InterruptedException 
     */
    public int getAsocEfs() throws IOException, InterruptedException { update(); return asocEfs; }
    
    /**
     * I don't know what this is, either...
     * @return
     * @throws IOException
     * @throws InterruptedException 
     */
    public int getAsocNow() throws IOException, InterruptedException { update(); return asocNow; }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Conversaion Methods and consts" >
    /**
     * Converts a given temperature from °C to °F.
     * @param tempInC The temperature in °C.
     * @return The temperature in °F.
     */
    public double convertTempToFahrenheit(double tempInC) { return tempInC * 1.8 + 32; }
    
    //<editor-fold defaultstate="collapsed" desc="Battery health consts" >
    /**
     * The battery is cold.
     */
    public static final int BATTERY_HEALTH_COLD = 0x00000007;
    
    /**
     * The battery is dead.
     */
    public static final int BATTERY_HEALTH_DEAD = 0x00000004;
    
    /**
     * The battery is in good health.
     */
    public static final int BATTERY_HEALTH_GOOD = 0x00000002;
    
    /**
     * The battery is overheating! Turn device off immediately!
     */
    public static final int BATTERY_HEALTH_OVERHEAT = 0x00000003;
    
    /**
     * The battery is receiving too many volts! Unplug device immediately!
     */
    public static final int BATTERY_HEALTH_OVER_VOLTAGE = 0x00000005;
    
    /**
     * The health of the battery is unknown.
     */
    public static final int BATTERY_HEALTH_UNKNOWN = 0x00000001;
    
    /**
     * The battery is experiencing an unspecified failure.
     */
    public static final int BATTERY_HEALTH_UNSPECIFIED_FAILURE = 0x00000006;
    
    /**
     * Contains objects describing the different states a battery's health can be in.
     * To convert from native values to an object, use the {@link BatteryHealth#valueOf(int) }
     */
    public static enum BatteryHealth {
        /**
         * The battery is cold.
         */
        BATTERY_HEALTH_COLD,
        
        /**
         * The battery is dead.
         */
        BATTERY_HEALTH_DEAD,
        
        /**
         * The battery is in good health.
         */
        BATTERY_HEALTH_GOOD,
        
        /**
         * The battery is overheating. Turn off the device ASAP!
         */
        BATTERY_HEALTH_OVERHEAT,
        
        /**
         * The battery is receiving too many volts. Unplug the device ASAP!
         */
        BATTERY_HEALTH_OVER_VOLTAGE,
        
        /**
         * The battery's health is unknown.
         */
        BATTERY_HEALTH_UNKNOWN,
        
        /**
         * The battery is experiencing an unspecified failure.
         */
        BATTERY_HEALTH_UNSPECIFIED_FAILURE;
        
        /**
         * Converts a native value to an enum object.
         * @param nativeValue The native value to be converted.
         * @return The converted value.
         */
        public static BatteryHealth valueOf(int nativeValue) {
            switch (nativeValue) {
                case BatteryInfo.BATTERY_HEALTH_COLD:
                    return BATTERY_HEALTH_COLD;
                case BatteryInfo.BATTERY_HEALTH_DEAD:
                    return BATTERY_HEALTH_DEAD;
                case BatteryInfo.BATTERY_HEALTH_GOOD:
                    return BATTERY_HEALTH_GOOD;
                case BatteryInfo.BATTERY_HEALTH_OVERHEAT:
                    return BATTERY_HEALTH_OVERHEAT;
                case BatteryInfo.BATTERY_HEALTH_OVER_VOLTAGE:
                    return BATTERY_HEALTH_OVER_VOLTAGE;
                case BatteryInfo.BATTERY_HEALTH_UNKNOWN:
                    return BATTERY_HEALTH_UNKNOWN;
                case BatteryInfo.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                    return BATTERY_HEALTH_UNSPECIFIED_FAILURE;
                default:
                    return BATTERY_HEALTH_UNKNOWN;
            }
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Battery stats consts" >
    /**
     * The battery is currently charging.
     */
    public static final int BATTERY_STATUS_CHARGING = 0x00000002;
    
    /**
     * The battery is currently discharging.
     */
    public static final int BATTERY_STATUS_DISCHARGING = 0x00000003;
    
    /**
     * The battery is full.
     */
    public static final int BATTERY_STATUS_FULL = 0x00000005;
    
    /**
     * The battery is not charging.
     */
    public static final int BATTERY_STATUS_NOT_CHARGING = 0x00000004;
    
    /**
     * The status of the battery is unknown.
     */
    public static final int BATTERY_STATUS_UNKNOWN = 0x00000001;
    
    /**
     * An enum containing all of the possible battery statuses.
     * To convert from a native value to an easier-to-parse value,
     * use the {@link BatteryStatus#valueOf(int) }
     */
    public static enum BatteryStatus {
        /**
         * The battery is currently charging.
         */
        BATTERY_STATUS_CHARGING,
        
        /**
         * The battery is currently discharging.
         */
        BATTERY_STATUS_DISCHARGING,
        
        /**
         * The battery is full.
         */
        BATTERY_STATUS_FULL,
        
        /**
         * The battery is not charging.
         */
        BATTERY_STATUS_NOT_CHARGING,
        
        /**
         * The status of the battery is unknown.
         */
        BATTERY_STATUS_UNKNOWN;
        
        /**
         * Converts a native value to an enum value.
         * @param nativeValue The native value to be converted.
         * @return The converted value.
         */
        public static BatteryStatus valueOf(int nativeValue) {
            switch (nativeValue) {
                case BatteryInfo.BATTERY_STATUS_CHARGING:
                    return BATTERY_STATUS_CHARGING;
                case BatteryInfo.BATTERY_STATUS_DISCHARGING:
                    return BATTERY_STATUS_DISCHARGING;
                case BatteryInfo.BATTERY_STATUS_FULL:
                    return BATTERY_STATUS_FULL;
                case BatteryInfo.BATTERY_STATUS_NOT_CHARGING:
                    return BATTERY_STATUS_NOT_CHARGING;
                case BatteryInfo.BATTERY_STATUS_UNKNOWN:
                    return BATTERY_STATUS_UNKNOWN;
                default:
                    return BATTERY_STATUS_UNKNOWN;
            }
        }
    }
    //</editor-fold>
    
    
    //</editor-fold>
    //</editor-fold>
    
    private void update() throws IOException, InterruptedException {
        
    }
    
}
