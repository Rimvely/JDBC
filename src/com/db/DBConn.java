package com.db;

import java.sql.Connection;
import java.sql.DriverManager;


//������ ��������
public class DBConn {

	private static Connection dbConn;

	public static Connection getConnection() {

		if (dbConn == null) {

			try {
				// ����Ŭ�� �⺻��Ʈ��ȣ 1521
				String url = "jdbc:oracle:thin:@192.168.16.27:1521:TestDB";
				String user = "suzi";
				String pwd = "a123";

				// Class �� class�� �д� class�̴�.
				// oracle �ȿ� jdbc �ȿ� driver�ȿ� �ִ� OracleDriver�� �о�Ͷ�
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// dbConn���� �ҷ�������Ҳ���
				dbConn = DriverManager.getConnection(url, user, pwd);

			} catch (Exception e) {
				System.out.println(e.toString());

			}

		}

		return dbConn;
	}

	public static void close() {

		if (dbConn != null) {

			try {

				if (!dbConn.isClosed()) {
					dbConn.close();
				}

			} catch (Exception e) {
				System.out.println(e.toString());
			}

		}
		//DB�� �ݾ����� dbConn = null �� û�Ҹ� ������Ѵ�. 
		//�ȱ׷��� �ι�° DB�� �����Ҷ� ������ �����.
		dbConn = null;
	}

}
