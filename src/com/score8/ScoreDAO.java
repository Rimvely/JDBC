package com.score8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.db.DBConn;

public class ScoreDAO {// Data Access Object(SQL)

	// 1.�Է� DB�� access�ؾ��ϴϱ�
	// ���� get / set �� �־���ϴϱ� ��ü ����
	// DTO�� ���� ���� ��ä�� DAO�� �����ش�
	// DTO�ȿ� ������ �־����. �װ� Ǯ��
	// set �� �־� �����ϱ� get �޾�

	// 1.�Է� DB�� access�ؾ��ϴϱ�
	public int insertData(ScoreDTO dto) {

		int result = 0; // ���߿� void�� �Ѵٰ��� �����

		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "insert into score (hak, name, kor, eng, mat) ";
			sql += "values (?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql); // pstmt�� sql�� ���� �˻�, query��
												// pstmt �ȿ��ִ�.

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

	// ����
	public int updateData(ScoreDTO dto) {

		int result = 0;

		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update score set kor=?, eng=?, mat=? ";
			sql += "where hak=?";

			pstmt = conn.prepareStatement(sql); // sql �˻�

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

	// 3. ����
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

	// 4. ��ü���� 1���� ������(1��(row))�� ���� �׸� = DTO
	// 2��° DTO 3��° DTO //Therefore, �����Ͱ�����ŭ DTO �ʿ�
	// List �ȿ� �� �ִ� dto�� ���ϴ� ��ġ�� �ѷ��ش�.
	// ��ȯ�� List
	// ������ ū������� �����ϰ� ������ ���ְ� ����� �ű�� ��
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
			// �������� �׸��� �־���ϴµ� ScoreDTO�� �׸�
			while (rs.next()) {
				// while�� �������� �׸����ʿ��ؼ� �ȿ��ٰ� ����\
				// dto �ȿ��ٰ� �־����
				ScoreDTO dto = new ScoreDTO();

				dto.setHak(rs.getString("hak"));
				dto.setName(rs.getString("name"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAvg(rs.getInt("avg"));
				dto.setRank(rs.getInt("rank"));
				// DTO�� �־������ ��� �����ϴµ� �װ� List�� �־��ش�.
				// Lists �ȿ��� �ּҰ� ���°Ŵ�.
				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;

	}

	// 5. �й��˻�
	// �й��� �˻��ؼ� ���ٴ��? DTO�� �����?
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
