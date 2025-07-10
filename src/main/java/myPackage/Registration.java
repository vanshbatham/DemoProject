package myPackage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String mobile = request.getParameter("contact");
		RequestDispatcher rd = null;
		Connection conn = null;

		try {
			// Updated driver for MySQL 8+
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://mysql:3306/signup", "root", "Vansh@sql16");

			String query = "insert into users(uname, password, email, mobileno) values(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, uname);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, mobile);

			int rowCount = ps.executeUpdate();

			rd = request.getRequestDispatcher("registration.jsp");

			if (rowCount > 0) {
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("status", "failed");
			}

			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
