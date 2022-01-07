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
 * Servlet implementation class AccountInsertController
 */
@WebServlet("/AccountInsert")
public class AccountInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInsertController() {
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
		//int no = Integer.parseInt(request.getParameter("No"));
		HttpSession session = request.getSession();

		
		String email = request.getParameter("email");
		int money = Integer.parseInt(request.getParameter("money"));
		String ino = request.getParameter("Ino");
		String note = request.getParameter("Note");
		String iodate = request.getParameter("IoDate");
		String memo = request.getParameter("Memo");

		String id = (String) session.getAttribute("id");
		System.out.println("id = " + id);
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String sql = "insert into moneynote(id, email, money, ino, note, iodate, memo) values(?,?,?,?,?,?,?)";
		try {
			conn = new DBconnection().getConnection();
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, no);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.setInt(3, money);
			pstmt.setString(4, ino);
			pstmt.setString(5, note);
			pstmt.setString(6, iodate);
			pstmt.setString(7, memo);
			rs = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("insert into moneynote error : "+ e);
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		response.sendRedirect("shownote.jsp");
	}

}
