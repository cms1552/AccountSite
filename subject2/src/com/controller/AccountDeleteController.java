package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DBconnection;

/**
 * Servlet implementation class AccountDeleteController
 */
@WebServlet("/AccountDelete")
public class AccountDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeleteController() {
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
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("id")==null) {
			System.out.println("db 삭제 실패 : 로그인 하세요." + "id : " + session.getAttribute("id"));
			response.sendRedirect("index.jsp");
		}else {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		int no = Integer.parseInt(request.getParameter("id"));
		System.out.println(no);
		String sql = "delete from moneynote where No=?";
		
		try {
			conn = new DBconnection().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeUpdate();
			System.out.println("데이터 삭제 성공!");
		}catch(Exception e) {
			System.out.println("moneynote delete error : "+e);
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
	}

}
