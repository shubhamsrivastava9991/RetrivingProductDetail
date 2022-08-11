package product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet implementation class ProductServlet

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 System.out.println("driver loaded successfully");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_world","root","root");
		     System.out.println("Successfully connected to database............");
		     Statement stmt = con.createStatement();
		     PrintWriter pw=response.getWriter();
		     
		     String pid = request.getParameter("pid");
		     
		     if(pid.isEmpty()) {
		    	 pw.print("<h3>You not given any id to search the product!<br>Try again!</h3>");
		    	 pw.println("<a href=index.html><Button><strong>Search Product<strong></Button></a>");
		     }
		     else {
		    	 String sql ="select * from product where Product_id ="+pid;
		    	 ResultSet res = stmt.executeQuery(sql);
		    	 if(res.next()) {
		    		 pw.println("<h1>Product Detail</h1>");
		    		 pw.println("<h3>Id is "+res.getInt(1)+ "</h3>");
		    		 pw.println("<h3>Product Name is "+res.getString(2)+"</h3>");
		    		 pw.println("<h3>Price is "+res.getFloat(3)+"</h3>");
		    		 pw.println("<a href=index.html><Button><strong>Search another Product<strong></Button></a>");
		    	 }
		    	 else {
		    		 pw.print("<h3>There is no product with Product id: "+pid+ "<br>Try again!</h3>");
		    		 pw.println("<a href=index.html><Button><strong>Search Product<strong></Button></a>");
		    	 } 
		     }  
		     
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}