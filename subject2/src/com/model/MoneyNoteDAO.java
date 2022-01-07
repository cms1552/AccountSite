package com.model;

import java.sql.*;
import java.util.ArrayList;
import com.util.DBconnection;

public class MoneyNoteDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public MoneyNoteDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			System.out.println("DB ERROR : " + e);
		}
	}

	/* 초기화면 그리고 검색 없는 상황 에 사용 */
	public ArrayList<MoneyNoteDTO> getMoneyNoteAll(String id){
		ArrayList<MoneyNoteDTO> list = new ArrayList<MoneyNoteDTO>();
		//String url = "jdbc:mysql://localhost:3307/moneyweb";
		//String user = "root";
		//String password = "1234";
		try {
			//conn = DriverManager.getConnection(url, user, password);
			if(conn==null) {
				conn = new DBconnection().getConnection();
			}
			String sql = "select * from moneynote where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MoneyNoteDTO dto = new MoneyNoteDTO();
				dto.setNo(rs.getInt("No"));
				dto.setId(rs.getString("id"));
				dto.setEmail(rs.getString("email"));
				dto.setMoney(rs.getInt("money"));
				dto.setIno(rs.getString("ino"));
				dto.setNote(rs.getString("note"));
				dto.setIodate(rs.getString("iodate"));
				dto.setMemo(rs.getString("memo"));
				dto.setConfirm(rs.getString("confirm"));
				list.add(dto);
			}
		}catch (Exception e) {
			System.out.println("getMoneyNoteAll ERROR : " + e);
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

	/* 조건 검색 시 사용됨 */
	public ArrayList<MoneyNoteDTO> getMoneyNote(String id, String date, String condition, String innout, String ynn, String conORmemo){
		ArrayList<MoneyNoteDTO> list = new ArrayList<MoneyNoteDTO>();
		String sql = "select * from moneynote where id = "+"'"+id+"'";
		String date1 = "";
		String date2 = "";
		if(!date.equals("")) {
			date1 = date.substring(0, 10);
			date2 = date.substring(14);
		}
		try {
			if(conn==null) {
				conn = new DBconnection().getConnection();
			}
			if(!date.equals("")) sql = sql + " and iodate between "+"'"+date1+"'"+" and "+"'"+date2+"'";
			if(!conORmemo.equals("")) sql = sql + " and "+condition+" like "+ "'%"+conORmemo+"%'";
			if(innout!=null) sql = sql + " and ino = "+"'"+innout+"'";
			if(ynn!=null) sql = sql + " and confirm = "+"'"+ynn+"'";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MoneyNoteDTO dto = new MoneyNoteDTO();
				dto.setNo(rs.getInt("No"));
				dto.setId(rs.getString("id"));
				dto.setEmail(rs.getString("email"));
				dto.setMoney(rs.getInt("money"));
				dto.setIno(rs.getString("ino"));
				dto.setNote(rs.getString("note"));
				dto.setIodate(rs.getString("iodate"));
				dto.setMemo(rs.getString("memo"));
				dto.setConfirm(rs.getString("confirm"));
				list.add(dto);
			}
		}catch (Exception e) {
			System.out.println("MoneyNoteDAO getMoneyNote ERROR : " + e);
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
}
