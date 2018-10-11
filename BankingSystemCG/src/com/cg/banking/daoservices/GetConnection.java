package com.cg.banking.daoservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnection {
	static Connection conn;
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		if((getConnection())!=null)
			System.out.println("Yes");
		else
			System.out.println("no");
	}
	public static Connection getConnection(){
		try{
					Properties property=new Properties();
					property.load(new FileInputStream(new File(".//resources//banking.properties")));
					String driver=property.getProperty("driver");
					String url=property.getProperty("url");
					String user=property.getProperty("user");
					String password=property.getProperty("password");
					Class.forName(driver);
					conn=DriverManager.getConnection(url, user, password);
				}
				catch(SQLException e){
					e.printStackTrace();
				}
		catch(ClassNotFoundException c){
			System.out.println("Class is Not found in Connection");
		}
		catch(FileNotFoundException f){
			System.out.println("File Not found in Connection");
		}
		catch(IOException io){
			System.out.println("IO Exception in Connection");
		}
		return conn;
	}
}
