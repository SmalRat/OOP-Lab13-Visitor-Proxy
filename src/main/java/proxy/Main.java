package proxy;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Scrapper scrapper = new ScrapperProxy(new URLScrapper());
        System.out.println(scrapper.parseHtmlByUrl("https://www.5.ua/", ""));
    }
}
