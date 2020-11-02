package com.danielme.blog.testing;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

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
    public void testGetUsersByName() {
        List<String> usersByName = UserService.INSTANCE.getUsersByName("oin");
        assertEquals(2, usersByName.size());
        assertTrue("Gloin not found!!", usersByName.contains("Gloin"));
        assertTrue("Oin not found!!", usersByName.contains("Oin"));
    }

    @Test
    @Parameters({"oin, 2", "balin, 1", "gimli, 0"})
    public void testGetUsersByNameParameterised(String name, int result) {
        List<String> usersByName = UserService.INSTANCE.getUsersByName(name);
        assertEquals(result, usersByName.size());
    }

    @Test
    public void testGetUsersByNameAssertJ() {
        List<String> usersByName = UserService.INSTANCE.getUsersByName("oin");
        assertThat(usersByName)
                .hasSize(2)
                .containsExactlyInAnyOrder("Gloin", "Oin");
    }

    @Test
    public void testGetUserByPositionExceptionFail() {
        try {
            UserService.INSTANCE.getUserByPosition(-1);
            fail("Exception not thown");
        } catch (IndexOutOfBoundsException ex) {
            assertEquals("-1", ex.getMessage());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetUserByPositionExceptionAnnotation() {
        UserService.INSTANCE.getUserByPosition(-1);
    }

    @Test
    public void testGetUserByPositionExceptionRule() {
        exception.expect(IndexOutOfBoundsException.class);
        exception.expectMessage("-1");

        UserService.INSTANCE.getUserByPosition(-1);
    }

    @Test
    public void testExceptionAssertJUnit413() {
        assertThrows(IndexOutOfBoundsException.class, () -> UserService.INSTANCE.getUserByPosition(-1));
    }

    @Test
    public void testExceptionAssertJ() {
        assertThatThrownBy(() -> UserService.INSTANCE.getUserByPosition(-1))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("-1");
    }

    /*
     * @Test(timeout=5000) public void test2() { for (int i = 0; i < 99999; i++)
     * logger.info(""); }
     */

}
