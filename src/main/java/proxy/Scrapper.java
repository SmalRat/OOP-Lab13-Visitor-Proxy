package proxy;

import java.io.IOException;
import java.sql.SQLException;

public interface Scrapper {
    String parseHtmlByUrl(String urlString, String modifier) throws SQLException, IOException;
}
