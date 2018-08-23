package com.score8;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.db.DBConn;

//CallableStatement    (Procedure) ����Ҷ�

public class ScoreDAO1 {

	// 1. �Է�

	public int insertData(ScoreDTO dto) {

		int result = 0;

		Connection conn = DBConn.getConnection();
		CallableStatement cstmt = null;
		String sql;

		try {
			//���������� ���� ? ����
			//procedure �̸� Ȯ��
			sql = "{call insertScore(?,?,?,?,?)}"; 

			cstmt = conn.prepareCall(sql); // pstmt�� sql�� ���� �˻�, query��
											// pstmt �ȿ��ִ�.

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

	// ����
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

	// 3. ����
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

	// 4. ��ü���� 1���� ������(1��(row))�� ���� �׸� = DTO
	// 2��° DTO 3��° DTO //Therefore, �����Ͱ�����ŭ DTO �ʿ�
	// List �ȿ� �� �ִ� dto�� ���ϴ� ��ġ�� �ѷ��ش�.
	// ��ȯ�� List
	// ������ ū������� �����ϰ� ������ ���ְ� ����� �ű�� ��
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

			// out�Ķ���� �ڷ��� ����
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			// ���ν��� ����
			cstmt.executeUpdate();

			// out�Ķ������ ���� ��������
			// Object�� �޾��ִ°ǵ� �װ� downcasting�Ѵ� ResultSet����
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

	// 5. �й��˻�
	public ScoreDTO getList(String hak) {

		Connection conn = DBConn.getConnection();
		ScoreDTO dto = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			// �Ű����� 2��
			sql = "{call selectHakScore(?,?)}";

			cstmt = conn.prepareCall(sql);

			// out�Ķ���� �ڷ��� ����
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			// in�Ķ���� �ڷ��� ����
			cstmt.setString(2, hak);

			// ���ν��� ����
			cstmt.executeUpdate();

			// out�Ķ������ ���� ��������
			// Object�� �޾��ִ°ǵ� �װ� downcasting�Ѵ� ResultSet����
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
