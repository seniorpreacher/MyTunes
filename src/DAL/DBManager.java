package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * An abstract database manager class that has the connection builder in it's
 * constructor.
 *
 * @author Anthony
 */
public abstract class DBManager {

    protected SQLServerDataSource dataSource = new SQLServerDataSource();
    public String PATH;
    
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
        
        PATH = conf.getProperty("music.path");

        Connection conn;
        dataSource.setUser(conf.getProperty("jdbc.username"));
        dataSource.setPassword(conf.getProperty("jdbc.password"));
        dataSource.setServerName(conf.getProperty("jdbc.servername"));
        dataSource.setDatabaseName(conf.getProperty("jdbc.databasename"));
        dataSource.setInstanceName(conf.getProperty("jdbc.dbinstancename"));
        conn = dataSource.getConnection();
        conn.close();
    }
}
