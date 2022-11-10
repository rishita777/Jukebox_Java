package sql_connection_jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class sql_Connect
{
    public Connection  sql_get_Connection()
    {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // load the driver   register the driver type
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/jukebox_project", "root", "root");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return con;
    }
}