package com.score8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.db.DBConn;

public class ScoreDAO {// Data Access Object(SQL)

	// 1.입력 DB에 access해야하니까
	// 값을 get / set 에 넣어야하니까 객체 생성
	// DTO가 받은 값을 통채로 DAO에 던져준다
	// DTO안에 데이터 넣어놨어. 그걸 풀어
	// set 에 넣어 놨으니까 get 받아

	// 1.입력 DB에 access해야하니까
	public int insertData(ScoreDTO dto) {

		int result = 0; // 나중에 void로 한다고함 기억해

		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "insert into score (hak, name, kor, eng, mat) ";
			sql += "values (?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql); // pstmt는 sql을 먼저 검사, query가
												// pstmt 안에있다.

			pstmt.setString(1, dto.getHak());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMat());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	// 수정
	public int updateData(ScoreDTO dto) {

		int result = 0;

		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update score set kor=?, eng=?, mat=? ";
			sql += "where hak=?";

			pstmt = conn.prepareStatement(sql); // sql 검사

			pstmt.setInt(1, dto.getKor());
			pstmt.setInt(2, dto.getEng());
			pstmt.setInt(3, dto.getMat());
			pstmt.setString(4, dto.getHak());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	// 3. 삭제
	public int deleteData(String hak) {

		int result = 0;

		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "delete score where hak =?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, hak);

			result = pstmt.executeUpdate();

			pstmt.close();

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
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select hak, name, kor, eng, mat,";
			sql += "(kor+eng+mat) tot, (kor+eng+mat)/3 avg,";
			sql += "rank() over (order by (kor+eng+mat) desc) rank ";
			sql += "from score";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			// ---------------------------------------------
			// 꺼내려면 그릇이 있어야하는데 ScoreDTO가 그릇
			while (rs.next()) {
				// while이 돌때마다 그릇이필요해서 안에다가 생성\
				// dto 안에다가 넣어야해
				ScoreDTO dto = new ScoreDTO();

				dto.setHak(rs.getString("hak"));
				dto.setName(rs.getString("name"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAvg(rs.getInt("avg"));
				dto.setRank(rs.getInt("rank"));
				// DTO에 넣어놓은걸 담아 놔야하는데 그걸 List에 넣어준다.
				// Lists 안에는 주소가 들어가는거다.
				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}

	// 5. 학번검색
	// 학번을 검색해서 어디다담아? DTO에 담겠지?
	public ScoreDTO getList(String hak) {

		Connection conn = DBConn.getConnection();
		ScoreDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select hak, name, kor, eng, mat,";
			sql += "(kor+eng+mat) tot, (kor+eng+mat)/3 avg ";
			sql += "from score where hak=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, hak);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new ScoreDTO();

				dto.setHak(rs.getString("hak"));
				dto.setName(rs.getString("name"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAvg(rs.getInt("avg"));
				dto.setRank(rs.getInt("rank"));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;

	}

}
