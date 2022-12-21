package com.danielme.blog.testing;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

@FixMethodOrder
public class LifecycleTest {

    private static final Logger logger = LogManager.getLogger(LifecycleTest.class);

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