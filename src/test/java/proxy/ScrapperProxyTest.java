package proxy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class ScrapperProxyTest {
    @Test
    public void testProxy() throws SQLException, IOException, InterruptedException {
        Scrapper scrapper = new ScrapperProxy(new URLScrapper());
        String firstResult = scrapper.parseHtmlByUrl("https://time.is/", "");
        TimeUnit.SECONDS.sleep(2);
        String secondResult = scrapper.parseHtmlByUrl("https://time.is/", "");
        Assertions.assertEquals(firstResult, secondResult);
    }
}
