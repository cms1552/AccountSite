package com.model;

import java.sql.*;
import java.util.ArrayList;

import com.util.DBconnection;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public UserDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			System.out.println("DB ERROR : " + e);
		}
	}
	
	public ArrayList<UserDTO> getUserAll(){
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		
		//String url = "jdbc:mysql://localhost:3307/moneyweb";
		//String user = "root";
		//String password = "1234";
		try {
			//conn = DriverManager.getConnection(url, user, password);
			if(conn==null) {
				conn = new DBconnection().getConnection();
			}
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				list.add(dto);
			}
		}catch (Exception e) {
			System.out.println("getUserAll ERROR : " + e);
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch (Exception e){
				
			}
		}
		
		return list;
	}
	
	public boolean duplicateIdCheck(String id) {
		//conn = null;
		//pstmt = null;
		//rs = null;
		boolean x = false;
		
		try {
			//StringBuffer query = new StringBuffer();
			//query.append("select id from member where id=?");
			String query = "select id from member where id = ?";
			if(conn==null) {
				conn = new DBconnection().getConnection();
			}
			System.out.println("conn = "+ conn);
			//pstmt = conn.prepareStatement(query.toString());
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) x =true;
			return x;
		}catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		}finally {
			try {
				if(pstmt!=null) {pstmt.close(); pstmt=null;}
				if(conn!=null) {conn.close(); conn=null;}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}
	public String idPasswordCheck(String id, String password) {
		//conn = null;
		//pstmt = null;
		//rs = null;

		try {
			String sql = "select password from member where id=?";
			String pw = "";
			if(conn==null) {
				conn = new DBconnection().getConnection();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pw = rs.getString("password");
			}
			//비밀번호가 일치할 경우
			if(pw.equals(password)) {
				return "2";
			}else
				return "1";
			
		}catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		}finally {
			try {
				if(pstmt!=null) {pstmt.close(); pstmt=null;}
				if(conn!=null) {conn.close(); conn=null;}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		
	}
}
}