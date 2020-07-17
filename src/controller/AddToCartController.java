package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDAO;
import model.Product;

@WebServlet("/addToCart")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Product> cart = new ArrayList<Product>();
	ProductDAO prodDao = new ProductDAO();
       
    public AddToCartController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String id  = request.getParameter("id");
		    System.out.println("Product added");
		    System.out.println(id);
		     try {
			cart = prodDao.addTocart(cart,id, 1);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		     System.out.println("cart size : "+cart.size());
		     
		       
//---------------------------      ACCESSING PART CART LIST FOR CART.JSP------------------------------------
		     request.getSession().setAttribute("cartList",cart);

		     
//---------------------------------------- FOR REDIRECTING TO THE HOME-----------------------------------------
		     response.sendRedirect("home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
