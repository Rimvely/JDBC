package com.score8;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Score {

	Scanner sc = new Scanner(System.in);
	ScoreDAO dao = new ScoreDAO(); // 이거는 한번만 생성하면된다.

	// 1.추가 from users putting into DTO >>>>>> DAO
	public void insert() {// insert()를 호출하려면 Main!

		ScoreDTO dto = new ScoreDTO(); // dto는 입력할때마다 객체생성

		System.out.println("학번?");
		dto.setHak(sc.next());

		System.out.println("이름?");
		dto.setName(sc.next());

		System.out.println("국어?");
		dto.setKor(sc.nextInt());

		System.out.println("영어?");
		dto.setEng(sc.nextInt());

		System.out.println("수학?");
		dto.setMat(sc.nextInt());

		int result = dao.insertData(dto);

		if (result != 0)
			System.out.println("추가성공!!");
		else
			System.out.println("추가실패!!");

	}

	public void update() {

		ScoreDTO dto = new ScoreDTO();

		System.out.println("수정할 학번?");
		dto.setHak(sc.next());

		System.out.println("국어?");
		dto.setKor(sc.nextInt());

		System.out.println("영어?");
		dto.setEng(sc.nextInt());

		System.out.println("수학?");
		dto.setMat(sc.nextInt());

		int result = dao.updateData(dto);

		if (result != 0)
			System.out.println("수정 성공!!");
		else
			System.out.println("수정 실패!!");

	}

	public void delete() {

		System.out.println("삭제할 학번?");
		String searchHak = sc.next();

		int result = dao.deleteData(searchHak);

		if (result != 0)
			System.out.println("삭제 성공!!");
		else
			System.out.println("삭제 실패!!");

	}

	// 반환값 dto가 담긴 Lists
	public void selectAll() {

		List<ScoreDTO> lists = dao.getLists();

		Iterator<ScoreDTO> it = lists.iterator();

		// 하나 꺼낸게 dto
		while (it.hasNext()) {

			ScoreDTO dto = it.next();

			System.out.println(dto.toString());

		}

	}

	// 5.학번 검색
	public void searchHak() {

		System.out.println("검색할 학번?");

		String searchHak = sc.next();

		ScoreDTO dto = dao.getList(searchHak);

		if (dto != null) {

			System.out.println(dto.toString());
		}

	}

}
