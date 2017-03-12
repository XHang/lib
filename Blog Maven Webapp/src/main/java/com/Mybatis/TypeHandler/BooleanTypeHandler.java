package com.Mybatis.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
public class BooleanTypeHandler implements TypeHandler<Boolean>{

	@Override
	public Boolean getResult(ResultSet arg0, String arg1) throws SQLException {
		System.out.println("getResult");
		return null;
	}

	@Override
	public Boolean getResult(ResultSet arg0, int arg1) throws SQLException {
		System.out.println("getResult");
		return null;
	}

	@Override
	public Boolean getResult(CallableStatement arg0, int arg1)
			throws SQLException {
		System.out.println("getResult");
		return null;
	}

	@Override
	public void setParameter(PreparedStatement arg0, int arg1, Boolean arg2,
			JdbcType arg3) throws SQLException {
		  	Boolean b = arg2;  
	        int value = (Boolean) b == true ? 1 : 0;
	        arg0.setInt(1, value);
		
	}
	
}
