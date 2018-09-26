package com.danielme.blog.testing;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class UserServiceTest {

    private static final Logger logger = Logger.getLogger(UserServiceTest.class);

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public Timeout timeout = Timeout.millis(5000);

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
    public void getUsersByName() {
        List<String> usersByName = UserService.INSTANCE.getUsersByName("oin");
        assertEquals(2, usersByName.size());
        assertTrue("Gloin not found!!", usersByName.contains("Gloin"));
        assertTrue("Oin not found!!", usersByName.contains("Oin"));
    }

    @Test
    @Parameters({ "oin, 2", "balin, 1", "gimli, 0" })
    public void getUsersByNameParameterised(String name, int result) {
        List<String> usersByName = UserService.INSTANCE.getUsersByName(name);
        assertEquals(result, usersByName.size());
    }

    @Test
    public void getUsersByNameAssertJ() {
        List<String> usersByName = UserService.INSTANCE.getUsersByName("oin");
        assertThat(usersByName)
        .hasSize(2)
        .contains("Gloin", "Oin");
    }

    @Test
    public void testExceptionAssertJ() {
        assertThatThrownBy(() -> {
            UserService.INSTANCE.getUserByPosition(-1);
        })
        .isInstanceOf(IndexOutOfBoundsException.class)
        .hasMessageContaining("-1");
    }

    @Test
    public void getUserByPositionException1() {
        try {
            UserService.INSTANCE.getUserByPosition(-1);
            fail("Exception not thown");
        } catch (IndexOutOfBoundsException ex) {
            assertEquals("-1", ex.getMessage());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getUserByPositionException2() {
        UserService.INSTANCE.getUserByPosition(-1);
    }

    @Test
    public void getUserByPositionException3() {
        exception.expect(IndexOutOfBoundsException.class);
        exception.expectMessage("-1");

        UserService.INSTANCE.getUserByPosition(-1);
    }

    /*
     * @Test(timeout=5000) public void test2() { for (int i = 0; i < 99999; i++)
     * logger.info(""); }
     */

}
