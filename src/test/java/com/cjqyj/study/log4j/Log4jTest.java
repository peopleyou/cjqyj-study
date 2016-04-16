package com.cjqyj.study.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yuanyouz on 2016/4/16.
 */
public class Log4jTest {

    public static final Logger LOG = LoggerFactory.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        LOG.debug("test logger debug");
        LOG.error("test logger error");
    }

}
