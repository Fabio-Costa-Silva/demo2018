package com.rakuten.framework.driver;

/**
 * Created by FabioCosta
 */
public class DriverManager {

    private final static String KILL = "taskkill /F /IM ";

    public static boolean setDriverLocation(String driverProperty, String driversPath) throws Exception {
        Boolean result = false;
        try{
            System.setProperty(driverProperty, driversPath);
            result = true;
        } catch (Exception e){
            result = false;
        }
       return result;
    }

    public static void killProcess(String serviceName) throws Exception {
        Runtime.getRuntime().exec(KILL + serviceName);
    }
}
