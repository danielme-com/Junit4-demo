package com.danielme.blog.testing;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class UserServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public Timeout timeout = Timeout.millis(5000);

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
        assertThat(usersByName)
                .hasSize(result);
    }

    @Test
    public void testGetUsersByNameAssertJ() {
        UserService userService = UserService.INSTANCE;

        List<String> usersByName = userService.getUsersByName("oin");

        assertThat(usersByName)
                .containsExactlyInAnyOrder("Gloin", "Oin");
    }

    @Test
    public void testGetUserByPositionExceptionFail() {
        try {
            UserService.INSTANCE.getUserByPosition(-1);
            fail("Exception not thown");
        } catch (IndexOutOfBoundsException ex) {
            assertThat(ex.getMessage())
                    .contains("-1");
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
