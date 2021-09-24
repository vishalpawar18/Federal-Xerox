
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class JavaConnect {
    
     Connection conn=null;
    public static Connection ConnectDB()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn=DriverManager.getConnection("jdbc:sqlite:Federal.sqlite");
            //JOptionPane.showMessageDialog(null,"connection Successfully Done !!!");
                     
          return conn;  
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e+"\nConnection Error\nplease check..");
            return null;
        }
        
    }
     /*public static void main(String[] args) 
    {
        JavaConnect.ConnectDB();
    }*/
    
}
