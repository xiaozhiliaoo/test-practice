package org.lili.mockito;

/**
 * @author lili
 * @date 2022/3/22 14:49
 */
public class ArticleManager {
    private ArticleDatabase database;
    private ArticleCalculator calculator;

    public void setDatabase(ArticleDatabase database) {
        this.database = database;
    }

    public void setCalculator(ArticleCalculator calculator) {
        this.calculator = calculator;
    }

    public void initiateArticle(String name) {
        System.out.println("initiateArticle:" + name);
        System.out.println(database.articleDatabase());
        System.out.println(calculator.articleCalculator());
    }
}
