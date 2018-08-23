package com.score8;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Score {

	Scanner sc = new Scanner(System.in);
	ScoreDAO dao = new ScoreDAO(); // �̰Ŵ� �ѹ��� �����ϸ�ȴ�.

	// 1.�߰� from users putting into DTO >>>>>> DAO
	public void insert() {// insert()�� ȣ���Ϸ��� Main!

		ScoreDTO dto = new ScoreDTO(); // dto�� �Է��Ҷ����� ��ü����

		System.out.println("�й�?");
		dto.setHak(sc.next());

		System.out.println("�̸�?");
		dto.setName(sc.next());

		System.out.println("����?");
		dto.setKor(sc.nextInt());

		System.out.println("����?");
		dto.setEng(sc.nextInt());

		System.out.println("����?");
		dto.setMat(sc.nextInt());

		int result = dao.insertData(dto);

		if (result != 0)
			System.out.println("�߰�����!!");
		else
			System.out.println("�߰�����!!");

	}

	public void update() {

		ScoreDTO dto = new ScoreDTO();

		System.out.println("������ �й�?");
		dto.setHak(sc.next());

		System.out.println("����?");
		dto.setKor(sc.nextInt());

		System.out.println("����?");
		dto.setEng(sc.nextInt());

		System.out.println("����?");
		dto.setMat(sc.nextInt());

		int result = dao.updateData(dto);

		if (result != 0)
			System.out.println("���� ����!!");
		else
			System.out.println("���� ����!!");

	}

	public void delete() {

		System.out.println("������ �й�?");
		String searchHak = sc.next();

		int result = dao.deleteData(searchHak);

		if (result != 0)
			System.out.println("���� ����!!");
		else
			System.out.println("���� ����!!");

	}

	// ��ȯ�� dto�� ��� Lists
	public void selectAll() {

		List<ScoreDTO> lists = dao.getLists();

		Iterator<ScoreDTO> it = lists.iterator();

		// �ϳ� ������ dto
		while (it.hasNext()) {

			ScoreDTO dto = it.next();

			System.out.println(dto.toString());

		}

	}

	// 5.�й� �˻�
	public void searchHak() {

		System.out.println("�˻��� �й�?");

		String searchHak = sc.next();

		ScoreDTO dto = dao.getList(searchHak);

		if (dto != null) {

			System.out.println(dto.toString());
		}

	}

}
