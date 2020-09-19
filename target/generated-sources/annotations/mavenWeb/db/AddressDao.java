package mavenWeb.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddressDao {
	
	private AddressDao() {}
    private static AddressDao dao;
    private static SqlMapSessionFactory session; 

    public static AddressDao getInstance(){

        if(dao == null){
            dao = new AddressDao();
            session = SqlMapSessionFactory.getInstance();
        }

        return dao;
    }
    

    public AddressDto selectAddress(Integer num) {

    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	AddressDto node = new AddressDto();
    	
    	String sql = "select NUM, NAME, ADDRESS, BIRTHDATE " +
    					  " from addressbook" + 
    					  " where num=?";
		
    	System.out.println(sql);
    	
    	// 달력 날짜 출력 버그 개선
    	
    	try {
    		conn = session.connect();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, num);
    		
    		rs = pstmt.executeQuery();
    		
    		if ( rs.next() ) {
    			node.setNum(rs.getInt(1));
    			node.setName(rs.getNString(2));
    			node.setAddress(rs.getNString(3));
    			node.setBirthdate(rs.getDate(4));
    		}
    		
    		
    	}catch(Exception ex) {
    		System.out.println("오류 발생: " + ex);
    	}
    	finally {
    		session.close(conn, pstmt, rs);
    	}
    	
        return node;

    }
    
    public int updateAddress(AddressDto addressDTO) {

    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	int result = -1;
    	
    	String sql = "update addressbook set NAME = ?, ADDRESS = ?, BIRTHDATE = ? " + 
    					  " where num = ?";
		
    	try {
    		
    		System.out.println(addressDTO.getBirthdate());
    		
    		conn = session.connect();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, addressDTO.getName());
    		pstmt.setString(2, addressDTO.getAddress());
    		pstmt.setDate(3, addressDTO.getBirthdate());
    		pstmt.setInt(4,  addressDTO.getNum());
    		
    		result = pstmt.executeUpdate();
    		
    		
    	}catch(Exception ex) {
    		System.out.println("오류 발생: " + ex);
    	}
    	finally {
    		session.close(conn, pstmt);
    	}
    	
    	return result;
    	
    }

    public int insertAddress(AddressDto addressDTO) {
    	
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	int result = -1;
    	
    	String sql = "insert into addressbook (NAME, ADDRESS, BIRTHDATE) " + 
    					  " values(?,?,?)";
		
    	try {
    		
    		System.out.println(addressDTO.getBirthdate());
    		
    		conn = session.connect();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, addressDTO.getName());
    		pstmt.setString(2, addressDTO.getAddress());
    		pstmt.setDate(3, addressDTO.getBirthdate());
    		
    		result = pstmt.executeUpdate();
    		
    		
    	}catch(Exception ex) {
    		System.out.println("오류 발생: " + ex);
    	}
    	finally {
    		session.close(conn, pstmt);
    	}
    	
    	return result;
    }
    
    public int deleteAddress(Integer num) {
    	
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	int result = -1;
    	
    	String sql = "delete from addressbook " + 
    					  " where num = ?";
		
    	try {
    		
    		conn = session.connect();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, num);
    		
    		result = pstmt.executeUpdate();
    		
    		
    	}catch(Exception ex) {
    		System.out.println("오류 발생: " + ex);
    	}
    	finally {
    		session.close(conn, pstmt);
    	}
    	
    	return result;
    }

    
}
