package com.github.frame.util;

import org.apache.log4j.Logger;

public class FrameConstant {

    private static Logger logger = Logger.getLogger(FrameConstant.class);

    public static final String LOG_ID_KEY = "LOG_ID";

    public static final String QUARTZ_KEY = "QUARTZ";

    public static final String CACHE_NAMESPACE = "PROTOTYPE";

    public enum CACHE_CYCLE {
        ;
        public static final int SHORT = 1 * 60 * 60;

        public static final int MIDDLE = 8 * 60 * 60;

        public static final int LONG = 24 * 60 * 60;
    }

}