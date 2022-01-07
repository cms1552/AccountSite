package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DBconnection;

/**
 * Servlet implementation class ModifyController
 */
@WebServlet("/Modify")
public class ModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String sqls[] = {"update member set name=? where id=?", "update member set password=? where id=?", "update member set email=? where id=?", "update member set phone=? where id=?"};
		String id = (String) request.getSession().getAttribute("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("password");
		String email = request.getParameter("email");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone = phone1+"-"+phone2+"-"+phone3;
		String vals[] = {name, pw, email, phone};
		
		try {
			for(int i=0; i<sqls.length; i++) {
			conn = new DBconnection().getConnection();
			pstmt = conn.prepareStatement(sqls[i]);
			pstmt.setString(1, vals[i]);
			pstmt.setString(2, id);
			rs = pstmt.executeUpdate();
			}
			System.out.println("modify success");
		}catch (Exception e) {
			System.out.println("member modify error : "+e);
		}finally {
			//if(rs!=null)
			//	try {
				//	rs.close();
				//} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		response.sendRedirect("loginsuccess.jsp");
	}

}
