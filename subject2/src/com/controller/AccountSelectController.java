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
import javax.servlet.http.HttpSession;
import com.util.DBconnection;

/**
 * Servlet implementation class AccountSelectController
 */
@WebServlet("/AccountSelect")
public class AccountSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountSelectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인되있지 않으면 바로 index로 이동
		HttpSession session = request.getSession();
		if(session==null) {
			response.sendRedirect("index.jsp");
		}
		else
			response.sendRedirect("shownote.jsp");
		
		String no = request.getParameter("no");
		System.out.println("no : "+ no);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String sql = "update moneynote set confirm='y' where No=?";
		try {
			conn = new DBconnection().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeUpdate();
		}catch (SQLException e) {
			System.out.println("update moneynote error : " + e);
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
