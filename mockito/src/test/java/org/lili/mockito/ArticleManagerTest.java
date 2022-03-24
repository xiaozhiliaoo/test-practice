package org.lili.mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ArticleManagerTest {

    @Mock
    private ArticleCalculator calculator;


    @Mock(name = "database")
    private ArticleDatabase dbMock; // note the mock name attribute

    @Mock
    private ArticleManager manager;

    @InjectMocks
    private ArticleManager injectManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMock() {
        //manager.initiateArticle("alibaba");
        doCallRealMethod().when(manager).initiateArticle("aaaaa");
    }

    @Test
    void testInjectMock() {
        injectManager.initiateArticle("alibaba");
    }


    @AfterEach
    void tearDown() {

    }
}