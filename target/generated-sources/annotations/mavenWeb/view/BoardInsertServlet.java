package mavenWeb.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mavenWeb.db.AddressDto;
import mavenWeb.db.AddressImpl;

/**
 * Servlet implementation class BoardInsertServlet
 */
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
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
		AddressDto dbNode = new AddressDto(); 
		dbNode.setName("도도" + serialVersionUID);
		dbNode.setAddress("행복시 행복동");
		
		// 버그1: new Date() 사용안됨. (2020을 3920으로 인식함.) 
		// 버그2: new Timestamp() 사용안됨. (2020을 3920으로 인식함.) 
		String userDate = "2020-02-01";
		java.sql.Date sqlDate = java.sql.Date.valueOf(userDate);
		dbNode.setBirthdate(sqlDate);
		
		int result = address.insertAddress(dbNode);
		AddressDto addressDto = address.getAddress(1);
		
		out.println("<html><head><title>CRUD - Insert(Maven)</title></head>");
		out.println("<body><h2>MyBatis - Insert</h2>");

		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd" );
		String birthdate = format1.format(addressDto.getBirthdate());
		
		out.print("<br/>");
		out.print("등록여부:" + result + "</br>");
		out.print("<br/>");
		out.print(addressDto.getNum() + "/" + addressDto.getName() + "/");
		out.print(addressDto.getAddress() + "/" + birthdate);
		
		out.println("</body></html>");
		
		out.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
