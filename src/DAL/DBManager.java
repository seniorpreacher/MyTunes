package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBManager class.
 *
 * Manages the whole database.
 *
 * @author Anthony
 */
public abstract class DBManager {

    protected SQLServerDataSource dataSource = new SQLServerDataSource();

    /**
     * Constructor.
     *
     * Makes the connection and catches the exception in case there is a
     * connection problem.
     */
    public DBManager() throws SQLException, IOException {
        Properties conf = new Properties();
        FileInputStream file = new FileInputStream("config.properties");
        conf.load(file);

        Connection conn;
//        dataSource.setUser(conf.getProperty("jdbc.username"));
//        dataSource.setPassword(conf.getProperty("jdbc.password"));
//        dataSource.setServerName(conf.getProperty("jdbc.servername"));
//        dataSource.setDatabaseName(conf.getProperty("jdbc.databasename"));
//        dataSource.setInstanceName(conf.getProperty("jdbc.dbinstancename"));
        dataSource.setUser(conf.getProperty("java"));
        dataSource.setPassword(conf.getProperty("java"));
        dataSource.setServerName(conf.getProperty("localhost"));
        dataSource.setDatabaseName(conf.getProperty("MyTunes"));
        dataSource.setInstanceName(conf.getProperty("SQLEXPRESS"));
        conn = dataSource.getConnection();
        conn.close();
    }
}
