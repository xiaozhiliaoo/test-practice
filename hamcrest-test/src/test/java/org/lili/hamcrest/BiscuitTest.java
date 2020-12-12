package org.lili.hamcrest;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author lili
 * @date 2020/8/7 18:15
 * @notes
 */

public class BiscuitTest {
    @Test
    public void testEquals() {
        assertThat("this", true);
    }

    @Test
    public void testSquareRootOfMinusOneIsNotANumber() {
        assertThat(1, is(1));
    }

}
