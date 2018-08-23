package com.score8;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.db.DBConn;

//CallableStatement    (Procedure) 사용할때

public class ScoreDAO1 {

	// 1. 입력

	public int insertData(ScoreDTO dto) {

		int result = 0;

		Connection conn = DBConn.getConnection();
		CallableStatement cstmt = null;
		String sql;

		try {
			//변수갯수에 따라서 ? 갯수
			//procedure 이름 확인
			sql = "{call insertScore(?,?,?,?,?)}"; 

			cstmt = conn.prepareCall(sql); // pstmt는 sql을 먼저 검사, query가
											// pstmt 안에있다.

			cstmt.setString(1, dto.getHak());
			cstmt.setString(2, dto.getName());
			cstmt.setInt(3, dto.getKor());
			cstmt.setInt(4, dto.getEng());
			cstmt.setInt(5, dto.getMat());

			result = cstmt.executeUpdate();

			cstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	// 수정
	public int updateData(ScoreDTO dto) {

		int result = 0;

		Connection conn = DBConn.getConnection();
		CallableStatement cstmt = null;
		String sql;

		try {

			sql = "{call updateScore(?,?,?,?)}";

			cstmt = conn.prepareCall(sql);

			cstmt.setString(1, dto.getHak());
			cstmt.setInt(2, dto.getKor());
			cstmt.setInt(3, dto.getEng());
			cstmt.setInt(4, dto.getMat());

			result = cstmt.executeUpdate();

			cstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	// 3. 삭제
	public int deleteData(String hak) {

		int result = 0;

		Connection conn = DBConn.getConnection();
		CallableStatement cstmt = null;
		String sql;

		try {

			sql = "{call deleteScore(?)}";

			cstmt = conn.prepareCall(sql);

			cstmt.setString(1, hak);

			result = cstmt.executeUpdate();

			cstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	// 4. 전체선택 1개의 데이터(1행(row))을 담을 그릇 = DTO
	// 2번째 DTO 3번째 DTO //Therefore, 데이터갯수만큼 DTO 필요
	// List 안에 들어가 있는 dto를 원하는 위치에 뿌려준다.
	// 반환값 List
	// 총점을 큰순서대로 나열하고 점수를 없애고 등수를 매기는 식
	// sql += "rank() over (order dy (kor+eng+mat) desc) rank ";
	public List<ScoreDTO> getLists() {

		List<ScoreDTO> lists = new ArrayList<ScoreDTO>();

		Connection conn = DBConn.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "{call selectAllScore(?)}";

			cstmt = conn.prepareCall(sql);

			// out파라미터 자료형 설정
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			// 프로시져 실행
			cstmt.executeUpdate();

			// out파라미터의 값을 돌려받음
			// Object로 받아주는건데 그걸 downcasting한다 ResultSet으로
			rs = (ResultSet) cstmt.getObject(1);

			while (rs.next()) {

				ScoreDTO dto = new ScoreDTO();

				dto.setHak(rs.getString("hak"));
				dto.setName(rs.getString("name"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAvg(rs.getInt("avg"));
				dto.setRank(rs.getInt("rank"));

				lists.add(dto);
			}

			rs.close();
			cstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}

	// 5. 학번검색
	public ScoreDTO getList(String hak) {

		Connection conn = DBConn.getConnection();
		ScoreDTO dto = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			// 매개변수 2개
			sql = "{call selectHakScore(?,?)}";

			cstmt = conn.prepareCall(sql);

			// out파라미터 자료형 설정
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			// in파라미터 자료형 설정
			cstmt.setString(2, hak);

			// 프로시져 실행
			cstmt.executeUpdate();

			// out파라미터의 값을 돌려받음
			// Object로 받아주는건데 그걸 downcasting한다 ResultSet으로
			rs = (ResultSet) cstmt.getObject(1);

			if (rs.next()) {

				dto = new ScoreDTO();

				dto.setHak(rs.getString("hak"));
				dto.setName(rs.getString("name"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAvg(rs.getInt("avg"));

			}

			rs.close();
			cstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;

	}

}
