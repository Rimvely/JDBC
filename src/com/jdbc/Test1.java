package com.jdbc;

import java.sql.Connection;

import com.db.DBConn;

public class Test1 {

	public static void main(String[] args) {

		// com.db���� conn ȣ��
		Connection conn = DBConn.getConnection();

		if (conn == null) {

			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);

		}

		System.out.println("�����ͺ��̽� ���� ����!!");
		// com.db����  close() ȣ��
		DBConn.close();
		
		
		//System.out.println(conn);
		
		//hashcode
		//oracle.jdbc.driver.T4CConnection@27f8302d
		
		

	}

}
//�����ͺ��̽� ���� ����!!�� �ȶߴ»���� dbConn�ȿ��� ���� �߻��� ŭ

//���� ���������� �����غ��ڽ��ϴ�
//Test2�� ����
