package com.github.frame.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CalculatorUtil {

    public static final DecimalFormat df = new DecimalFormat("0.00");

    public static final String ZERO = "0";

    public static final int NO_SCALE = 0;

    public static final int DEFALUT_SCALE = 2;

    public static final int MAX_SCALE = 10;

    public static String add(String d1, String d2) {
        return new BigDecimal(d1).add(new BigDecimal(d2)).toString();
    }

    public static String sub(String d1, String d2) {
        return new BigDecimal(d1).subtract(new BigDecimal(d2)).toString();
    }

    public static String multi(String d1, String d2) {
        return new BigDecimal(d1).multiply(new BigDecimal(d2)).toString();
    }

    public static String multi(String d1, String d2, int scale, int roundType) {
        return new BigDecimal(d1).multiply(new BigDecimal(d2)).setScale(scale, roundType).toString();
    }

    public static String div(String d1, String d2, int roundType) {
        return new BigDecimal(d1).divide(new BigDecimal(d2), MAX_SCALE, roundType).toString();
    }

    public static String div(String d1, String d2, int scale, int roundType) {
        return new BigDecimal(d1).divide(new BigDecimal(d2), scale, roundType).toString();
    }

    public static boolean isPositive(String d1) {
        return compareTo(d1, ZERO) > 0 ? true : false;
    }

    public static int compareTo(String d1, String d2) {
        return new BigDecimal(d1).compareTo(new BigDecimal(d2));
    }

    public static String scale(String d1, int scale, int roundType) {
        return new BigDecimal(d1).setScale(scale, roundType).toString();
    }

    public static String negate(String d1) {
        return new BigDecimal(d1).multiply(new BigDecimal(-1)).toString();
    }

}