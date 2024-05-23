package edu.school21.calc.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerController {
    private static final Logger logger = LoggerFactory.getLogger(LoggerController.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }
}
