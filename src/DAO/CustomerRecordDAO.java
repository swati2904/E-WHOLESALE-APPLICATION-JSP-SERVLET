package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import utitlity.ConnectionManager;


public class CustomerRecordDAO {

	ConnectionManager cm = new ConnectionManager();
	Connection con;

// --------------------------------------FETCH THE USER DETAILS FROM THE DATABSE------------------------------
public void fetchUserDetails(String custid) throws Exception {
	String username = null;
	String pass = null;
	String firstname = null;
	String lastname = null;
	String email = null;
	int age =0;
	String gender = null;
	String add = null;
	String contact = null;

	String userDetailsFetch = "select userdetails1.username, userdetails1.password,\r\n" +
	"customer.firstname,customer.lastname,customer.email,customer.age,customer.gender,customer.address,customer.contact\r\n"
	+"from userdetails1\r\n" +"inner join customer on userdetails1.customerid = customer.custid\r\n" +"where custid = "+custid;
	ResultSet rs = getDb(userDetailsFetch);
	while(rs.next()) {
		username = rs.getString(1);
		pass = rs.getString(2);
		firstname = rs.getString(3);
		lastname = rs.getString(4);
		email = rs.getString(5);
		age = rs.getInt(6);
		gender = rs.getString(7);
		add = rs.getString(8);
		contact = rs.getString(9);
}

// ------------------------------------ SHOW ALL THE DETAILS OF THE USER -----------------------------------
	System.out.println("*___________________________________User details_____________________________________________*");
	System.out.println();
	System.out.println("User Name : "+username+"\t First Name : "+firstname+"\t Last Name : "+lastname);
	System.out.println("Email Address : "+email);
	System.out.println("Age : "+age+"\t Gender : "+gender);
	System.out.println("Contact Number : "+contact);
	System.out.println("Address : "+add);
	System.out.println("Password : "+pass);
	System.out.println();
	System.out.println("|=============================================================================|");
	System.out.println();
}

//----------------------------------- FETCH THE ORDER HISTORY FROM THE DATABASE -----------------------------

public void fetchOrderHistory(String custid) throws Exception {
	String ordid = null;
	
	String prodid = null;
	String pname = null;
	int prdprice =0;
	int quant = 0;
	int totalamount = 0;
	String invono = null;
	String ptype = null;
	Date paydate = null ;
	Date shipdate = null;
	String contact = null;
	String add = null;

	String getOrderhistory = "select orders.orderno,orderdate from orders where customerid = "+custid;
	ResultSet rs = getDb(getOrderhistory);
	while(rs.next()) {
	ordid = rs.getString(1);
	
	String getProductDetails = "select orderdetails.productid,product.name,product.price,orderdetails.quantity\r\n" +
	"from orderdetails\r\n" +
	"inner join product on orderdetails.productid = product.id \r\n" +
	"where orderdetails.orderid = '"+ordid+"'";
	ResultSet rs1 = getDb(getProductDetails);
	while(rs1.next()) {
			prodid = rs1.getString(1);
			pname = rs1.getString(2);
			prdprice = rs1.getInt(3);
			quant = rs1.getInt(4);
			System.out.println();
		System.out.println("Order Id : "+ordid+"\t\t Product Id : "+prodid+"\t\t Product name : "+pname+"\t\t Price : "+prdprice+"\t Quantity : "+quant+" pieces");


		String getPayDetails = "select payment.amount,payment.payno,payment.type,payment.paydate,\r\n" +
		"shipment.contact,shipment.address,shipment.shipdate\r\n" +
		"from shipment inner join payment on payment.ordid = shipment.ordrid\r\n" +
		"where ordrid = '"+ordid+"'";
		
		ResultSet rs2 = getDb(getPayDetails);
		while(rs2.next()) {
			totalamount = rs2.getInt(1);
			invono = rs2.getString(2);
			ptype = rs2.getString(3);
			paydate = rs2.getDate(4);
			contact = rs2.getString(5);
			add = rs2.getString(6);
			shipdate = rs2.getDate(7);
	}
}
	System.out.println();
	System.out.println("--------------------------------- Total amount : "+totalamount+"\t\t Invoice Number : "+invono+"---------------------------------");
	System.out.println("");
	
	System.out.println("\t\t Payment Date : "+paydate+"\t\t Type of Payment : "+ptype+"\t\t Shiping Date : "+shipdate);
	System.out.println();
	System.out.println("\t\t Delivery Address : "+add+"\t\t Mobile Number : "+contact);
	System.out.println();
	System.out.println("|=============================================================================|");
}
}

//--------------------------------FETCHING THE DETAILS FROM THE CONNECTION DATABASE----------------------------
public ResultSet getDb(String sql) throws Exception {
	con = cm.getConnection();
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery(sql);
	return rs;
  }
}