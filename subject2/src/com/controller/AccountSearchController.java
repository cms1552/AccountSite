package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DBconnection;

/**
 * Servlet implementation class AccountSearchController
 */
@WebServlet("/AccountSearch")
public class AccountSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("dateSelect");
		String condition = request.getParameter("condition");
		String innout = request.getParameter("innout");
		String ynn = request.getParameter("ynn");
		String conORmemo = request.getParameter("conORmemo");
		
		System.out.println("date : " + date);
		System.out.println("condition : " + request.getParameter("condition"));
		System.out.println("innout : " + innout);
		
		response.setHeader("date", date);
		response.setHeader("innout", innout);
		response.setHeader("ynn", ynn);
		response.setHeader("condition", condition);
		response.setHeader("conORmemo", conORmemo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("shownote.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
