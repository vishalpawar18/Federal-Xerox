
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;
import org.sqlite.SQLiteConfig;
public class JavaConnect1 {
    Connection conn=null;
    public static Connection ConnectDB()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config= new SQLiteConfig();
            config.enforceForeignKeys(true); 
            Connection conn=DriverManager.getConnection("jdbc:sqlite:Federal.sqlite",config.toProperties());
           //JOptionPane.showMessageDialog(null,"connection established");
           /*
            SQLiteConfig config = new SQLiteConfig();
       config.enforceForeignKeys(true);
       Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:", config.toProperties());

           */
                    
          return conn;  
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e)       
        {
            JOptionPane.showMessageDialog(null, e+"\nConnection Error\nplease check..");
            return null;
        }
        
    }
//    public static void main(String... arvs){
//        ConnectDB();
//    }
    
}

