package com.icoffee.utils;

public class MathMethodsUtil {

    /**
     * округляем до сотых
     */
    public static double roundDouble(double value){
        return Math.round(value * 100.0) / 100.0;
    }
}
