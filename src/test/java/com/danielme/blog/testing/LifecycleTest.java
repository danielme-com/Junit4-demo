package com.danielme.blog.testing;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LifecycleTest {

    private static final Logger logger = Logger.getLogger(LifecycleTest.class);

    @BeforeClass
    public static void setUpClass() {
        logger.info("");
    }

    @AfterClass
    public static void tearDownClass() {
        logger.info("");
    }

    @Before
    public void setUpTest() {
        logger.info("");
    }

    @After
    public void tearDownTest() {
        logger.info("");
    }

    @Test
    public void testNothing1() {
        logger.info("");
    }

    @Test
    public void testNothing2() {
        logger.info("");
    }

}