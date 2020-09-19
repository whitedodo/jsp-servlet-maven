package mavenWeb.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mavenWeb.db.AddressDto;
import mavenWeb.db.AddressImpl;

/**
 * Servlet implementation class BoardDeleteServlet
 */
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		out.print("<br/>");
		
		AddressImpl address = new AddressImpl();
		
		int result = address.deleteAddress(5);
		AddressDto addressDto = address.getAddress(3);
		
		out.println("<html><head><title>CRUD - Delete(Maven)</title></head>");
		out.println("<body><h2>MyBatis - Delete(Maven)</h2>");
		
		out.print("<br/>");
		out.print("삭제여부:" + result + "</br>");
		out.print("<br/>");
		
		if ( addressDto != null ) {
			out.print(addressDto.getNum() + "/" + addressDto.getName() + "/");
			out.print(addressDto.getAddress() + "/" + addressDto.getBirthdate());
		}
		
		out.println("</body></html>");
		
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
