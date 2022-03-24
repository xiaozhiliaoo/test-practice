package org.lili.mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExtensionTest {

    @Mock
    private Extension extension;

    @BeforeEach
    void setUp() {
        when(extension.hello()).thenReturn("alibaba");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void hello() {
        assertThat(extension.hello()).isEqualToIgnoringCase("alibaba");
    }
}