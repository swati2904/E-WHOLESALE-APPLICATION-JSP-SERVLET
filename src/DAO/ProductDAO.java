package DAO;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import model.Product;
import model.User;
import utitlity.ConnectionManager;


public class ProductDAO {

        private ConnectionManager cm = new ConnectionManager();
        private Connection con;
        private  List<Product> productList = new ArrayList<Product>();

// --------------------------------EXECUTE THE STATEMENT TO UPADTE THE DATABASE --------------------------------
        public void updateDB(String update) throws Exception {
    		con = cm.getConnection();
    		PreparedStatement ps = con.prepareStatement(update);
    		int x = ps.executeUpdate();
    		if(x==1) {
    		    System.out.println("Updated Successfully.");
    		}
        }
     
//--------------------------------- GETTING THE RESULT FROM THE DATABASE -----------------------------------
        public ResultSet  getDB(String sql) throws Exception {
         	con = cm.getConnection();
         	PreparedStatement ps = con.prepareStatement(sql);
         	ResultSet rs = ps.executeQuery();
         	return rs;
        }
     
//------------------------------------DISPLAY THE PRODUCT LIST DETAILS---------------------------------------- 
        public List<Product> displayProductlist() throws Exception {
            	Product prod; 
            	String id;
            	String name;
            	int price;
            	String desc;
            	
	    		String sql = "select * from product  ORDER BY id ASC";
	    		ResultSet rs = getDB(sql);
	    		while(rs.next()) {
	    		    id = rs.getString(1);
	    		    name = rs.getString(2);
	    		    price = rs.getInt(3);
	    		    desc = rs.getString(4);
	    	   
	    		    prod = new Product();
	    		    prod.setProductId(id);
	    		    prod.setProductName(name);
	    		    prod.setPrice(price);
	    		    prod.setDescription(desc);
	    	  
	    		    productList.add(prod);
    		}
    		return productList;
        }

// --------------------------------------------ADD THE ITEM INTO THE CART --------------------------------------
        public ArrayList<Product> addTocart( ArrayList<Product> cartList, String prodid, int num) throws Exception {
    	     	String sql = "select product.id,product.name,product.price,product.description,stock.quantity from product inner join stock on product.id = stock.productid where id='"+prodid+"'";
    		ResultSet rs = getDB( sql);
    		while(rs.next()) {
    		   String id = rs.getString(1);
    		   String name = rs.getString(2);
    		   int price = rs.getInt(3);
    		   String desc = rs.getString(4);
    		   int qaunt = rs.getInt(5);
    		   if(qaunt>=num) {
    		   cartList.add(new Product(id,name,price*num, desc)); 
    		   }else {
    		       System.out.println("Stock not available");
    		   }
    		}
    		return cartList;
        }
   
//----------------------------------------REMOVE ITEM INTO THE CART --------------------------------------------
        public ArrayList<Product> removeProduct(String ProductId, ArrayList<Product> cartlist) {
            try {
        	   for(int i = 0; i<cartlist.size();++i) {
               	if(cartlist.get(i).getProductId().equals(ProductId)) {
               	    cartlist.remove(i);
               	}
                   }
            }catch(Exception e) {
        	
            }
         
            return cartlist;
        }
        
        //--------------------TOTAL CART VALUE ------------------------------
        public double totalCartValue(ArrayList<Product> cartlist) {
            double totalAmount = 0;
            try {
        	   for(int i = 0; i<cartlist.size();++i) {
               	totalAmount+= cartlist.get(i).getPrice();
                   }
            }catch(Exception e){
            	e.printStackTrace();
            }
            return totalAmount;
        }
    	
//-----------------INSERT THE DATA INTO TABLE FROM THE ORDERS AND PERFORM THE PLACE ORDER FUNCTION -------------
        public String placeOrder(ArrayList<Product> cartlist,String custid) throws Exception {
    	    LocalDate date = LocalDate.now();
    	    String orderNo = generateOrderNo();
    	    String sql = "insert into orders(orderno,custId,orderdate) values(?,?,?)";
    		 con = cm.getConnection();
    		 PreparedStatement ps = con.prepareStatement(sql);
    		 ps.setString(1, orderNo);
    		 ps.setString(2, custid);
    		 ps.setDate(3,Date.valueOf(date));
    		 int y =  ps.executeUpdate();
    		 
    		 if(y==1) {
    		    System.out.println("Order placed Successfuly.");
    		}
    		return orderNo;
    	} 	
    	
// --------------------------------INSERT ORDER DETAILS INTO THE DATABSE --------------------------------------
        public void InsertOrderDetails(String prodid, String orderNo, int no) throws SQLException {
    	    String insertOrderDetails = "insert into orderdetails(productid,orderid,quantity) values(?,?,?)";
        	    PreparedStatement ps2 = con.prepareStatement(insertOrderDetails);
        	    ps2.setString(1, prodid);
        	    ps2.setString(2, orderNo);
        	    ps2.setInt(3, no);
        	    int y = ps2.executeUpdate();
        	    if(y==1) {
        		System.out.println("update orderdetails.");
        	    }
    	}
        // -----------------GENERATE THE UNIQUE ORDER NUMBER TO LINK THE PLACE ORDER DETAILS
        public String generateOrderNo() throws Exception {
    		 int id = 0;
    		 String sql = "select count(orderno)+1 from orders";
    		     ResultSet rs = getDB(sql);
    		     if(rs.next()) {
    			 id = Integer.parseInt(rs.getString(1));
    		     }
    		     String ordNo = "order"+id;
    		     return ordNo ;
    	    }
    	
        //-----------------------UPDATE THE SHIPMENT DEAILS INTO THE DATABASE ----------------------------------
        public void shippingDetails(User user, String orderid, String shipid) throws SQLException {
    	    LocalDate shipdate = LocalDate.now();
    	    String shippingAddress = user.getAddress();
    	    String contactNumber = user.getContact();
    	    String sql = "insert into shipment(id,address,shipdate,contact,ordrid) values(?,?,?,?,?)";
    	    PreparedStatement ps = con.prepareStatement(sql);
    	    ps.setString(1, shipid);
    	    ps.setString(2, shippingAddress);
    	    ps.setDate(3, Date.valueOf(shipdate));
    	    ps.setString(4,contactNumber);
    	    ps.setString(5, orderid);
    	    ps.executeUpdate();
    	}
    	// -----------------GENERATE UNIQUE SHIPPING ID FOR THE ORDER SHIPMENT--------------------------
        public String generateShipId() throws Exception {
    		 int id = 0;
    		 String sql = "select count(orderno)+1 from orders";
    		     ResultSet rs = getDB(sql);
    		     if(rs.next()) {
    			 id = Integer.parseInt(rs.getString(1));
    		     }
    		     String ShipNo = "ShipNo"+id;
    		     return ShipNo ;
    	    }
    	
        // ------------------------UPDATE THE PAYMENT DETAILS INTO THE DATABASE---------------------------
        public void payment(String type,String inv,String orderid,int cartTotal) throws Exception {
    	    LocalDate pdate = LocalDate.now();
    	    String sql = "insert into payment(payno,type,paydate,amount,orderid) values(?,?,?,?,?)";
    	    PreparedStatement ps = con.prepareStatement(sql);
    	    ps.setString(1, inv);
    	    ps.setString(2, type);
    	    ps.setDate(3, Date.valueOf(pdate));
    	    ps.setInt(4, cartTotal);
    	    ps.setString(5,orderid);
    	    ps.executeUpdate();
    	}
    	
        // ----------------------------INVOICE FOR THE PAYMENT -------------------------------------
        public String generateInvoice() throws Exception {
    	    String sql = "select count(payno)+1 from payment";
    	    int shipid = 0;
    	     ResultSet rs = getDB(sql);
    	     if(rs.next()) {
    		 shipid = Integer.parseInt(rs.getString(1));
    	     }
    	    String inv = "INVO"+shipid;
    	    return inv;
    	}
   }
    
    

