package proxy;

import java.io.IOException;
import java.sql.*;

public class ScrapperProxy implements Scrapper {
    private final URLScrapper urlScrapper;

    public ScrapperProxy(URLScrapper urlScrapper) {
        this.urlScrapper = urlScrapper;
    }

    @Override
    public String parseHtmlByUrl(String urlString, String modifier) throws SQLException, IOException {
        String html = "";
        String query = urlString + modifier;
        Connection connection = DriverManager.getConnection("jdbc:sqlite:HtmlCache");
        try (PreparedStatement select = connection.prepareStatement(
                "SELECT url, html FROM Cache WHERE url = ?")) {
            select.setString(1, query);
            try (ResultSet rs = select.executeQuery()) {
                if (rs.getString("html") != null) {
                    html = rs.getString("html");
                }
                else {
                    throw new SQLException();
                }
            }
        }
        catch (SQLException exception){
            html = urlScrapper.parseHtmlByUrl(urlString, modifier);
            PreparedStatement select = connection.prepareStatement("insert into Cache (url, html) values (?, ?)");
            select.setString(1, query);
            select.setString(2, html);
            select.executeUpdate();
            select.close();
        }

        return html;
    }
}
