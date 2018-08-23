package com.score7;

import java.util.Scanner;

import com.db.DBConn;

public class ScoreMain {

	public static void main(String[] args) throws Exception {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Score ob = new Score();
		int ch;

		while (true) {

			do {
				System.out.print("1.ȸ������ 2.ȸ���������� 3.ȸ������"
						+ "4.ȸ����ü��� 5.ȸ���˻� 6.����:");

				ch = sc.nextInt();

			} while (ch < 1 || ch > 6);

			switch (ch) {

			case 1:
				ob.insertData();
				break;
				
			case 2:
				ob.updateData();
				break;
				
			case 3:
				ob.deleteData();
				break;
				
			case 4:
				ob.selectAll();
				break;
				
			case 5:
				ob.searchId();
				break;
				
			case 6:
				DBConn.close();
				System.exit(0);

			}

		}
	}
}
