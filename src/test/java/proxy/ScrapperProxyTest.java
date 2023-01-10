package proxy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class ScrapperProxyTest {
    @Test
    public void testProxy() throws SQLException, IOException, InterruptedException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:HtmlCache");
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE Cache");
        stmt.executeUpdate("create table Cache" +
                "(" +
                "    url    text," +
                "    html text" +
                ");");
        stmt.close();

        Scrapper scrapper = new ScrapperProxy(new URLScrapper());
        String firstResult = scrapper.parseHtmlByUrl("https://time.is/", "");
        TimeUnit.SECONDS.sleep(2);
        String secondResult = scrapper.parseHtmlByUrl("https://time.is/", "");
        Assertions.assertEquals(firstResult, secondResult);
    }
}
