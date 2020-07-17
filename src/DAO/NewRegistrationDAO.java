package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.User;
import utitlity.ConnectionManager;

public class NewRegistrationDAO {

    ConnectionManager cm = new ConnectionManager();
    Connection con;

// ------------------------- UPDATE TEH USER AND CUSTOMER DETAILS AFTER THE NEW REGISTRATION -----------------
     public void insertUserDetails(User user) throws Exception {
         con = cm.getConnection();
         String insertToUserdetails = "insert into userdetails1 (userId,custId,username,password) values(?,?,?,?)";
         String insertToCustomer = "insert into customer (custId,firstname,lastname,email,address,gender,age,contact) values(?,?,?,?,?,?,?,?)";
         
         //---------------------INSERTING THE DETAILS INTO THE CUSTOMER TABLE ----------------------
         PreparedStatement ps1 = con.prepareStatement(insertToCustomer);
         ps1.setString(1,user.getCustomerId());
         ps1.setString(2,user.getFirstName());
         ps1.setString(3,user.getLastName());
         ps1.setString(4,user.getEmailadd());
         ps1.setString(5,user.getAddress());
         ps1.setString(6, user.getGender());
         ps1.setInt(7, user.getAge());
         ps1.setString(8,user.getContact());
    	     
         //---------------------INSERTING THE DETAILS INTO THE USERDETAILS TABLE ----------------------
         PreparedStatement ps =  con.prepareStatement(insertToUserdetails);
         ps.setInt(1, user.getUserId());
         ps.setString(2,user.getCustomerId());
         ps.setString(3,user.getUserName());
         ps.setString(4,user.getPassword());
         ps1.executeUpdate();
         ps.executeUpdate();  
     }  
     
//---------------------------------GENERATE THE UNIQUE USER ID FOR THE EACH USER -------------------------------
     public int generateUserId() throws Exception {
         int userid = 0;
         String sql = "select count(userid)+1 from userdetails1";
         ResultSet rs = getDb(sql);
         if(rs.next()) {
    	 userid = Integer.parseInt(rs.getString(1));
         }
         return userid;
     }
     
//----------------------------GENERATE THE UNIQUE CUSTOMER ID FOR THE EACH CUSTOMER -----------------------------
     public String generateCustomerId() throws Exception {
         String custid = null;
         String sql = "select count(custid)+1 from customer";
         ResultSet rs = getDb(sql);
         if(rs.next()) {
    	custid = rs.getString(1);
         }
         return custid;
     }
     
// -----------------------------------FETCH THE RESULT FROM THE DRIVER -------------------------------------
     public ResultSet getDb(String sql) throws Exception {
         con = cm.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql);
         return rs; 
     }
    
}
