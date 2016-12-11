


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class connectionString {
  
    
   public Connection conn = null;  
 public ResultSet rs=null;
 public PreparedStatement stmt;
  public void connectDB(){
     try{
        Class.forName("com.mysql.jdbc.Driver");   
        conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/sql6148706?","root","1234");
     
     } 
     catch (Exception e){
         System.out.print(e);
   
     }
    
    
    }
}
