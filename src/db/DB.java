package db;

import java.io.IOException;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {

        if (conn == null) {

            try{
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
            }
        }
    }

}
