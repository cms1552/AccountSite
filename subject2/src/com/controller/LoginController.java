package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import com.model.UserDAO;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println("id : "+id+", password : " + password);
		PrintWriter out = response.getWriter();
		UserDAO DAO = new UserDAO();
		boolean x = DAO.duplicateIdCheck(id);
		if(x) {
			//ȸ�������� id�� ������, ����̶� �´��� ȭ���ؾ���
			out.println(DAO.idPasswordCheck(id, password));
		}else {
			//�α��� �Է� id�� �������� �������
			out.println("0");
		}
		
		//response.sendRedirect("loginsuccese.jsp");
		
		//�α����� ajax �� �ϱ�� ���� �Ա� ���� �ۼ��س��� �ڵ�
		/*
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String url = "jdbc:mysql://localhost:3307/moneyDB";
		String user ="root";
		String pw ="1234";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pw);
			pstmt = conn.prepareStatement("select password from user where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs==null)
			while(rs.next()) {
			System.out.println(rs.getString("password"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
			
		}*/
	}

}
