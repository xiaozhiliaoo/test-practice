package org.lili.assertj;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


/**
 * @author lili
 * @date 2020/11/25 23:20
 * @notes
 */
class MainTest {

    @Test
    public void test() {

        assertThat("lili").isEqualTo("lili");


        assertThat("Frodo").startsWith("Fro")
                .endsWith("do")
                .isEqualToIgnoringCase("frodo");

        assertThat(1 == 1).isTrue();

        Object expected = new Object();
        assertThat(expected).isSameAs(expected);


        assertThat("bean").isNotNull();


        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> {
            int i = 1 / 0;
        });

        Map<String,String> map = new HashMap<>();
        map.put("lili","233333333");
        assertThat(map).containsExactly(entry("lili", "233333333"));


//        assertThatIllegalArgumentException().isThrownBy();

    }

}