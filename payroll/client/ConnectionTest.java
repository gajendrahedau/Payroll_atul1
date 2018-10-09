package com.cg.payroll.client;
import com.cg.payroll.util.ConnectionProvider;
public class ConnectionTest {
	public static void main(String[] args) {
		if(ConnectionProvider.getDBConnection()!=null)
			System.out.println("Connection open");
	}
}
