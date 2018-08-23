package com.score8;

public class ScoreDTO {// Data Transfer Object - 데이터를 옮겨주는 전달자

	private String hak;
	private String name;
	private int kor;
	private int eng;
	private int mat;

	private int tot;
	private int avg;
	private int rank;

	public String getHak() {
		return hak;
	}

	public void setHak(String hak) {
		this.hak = hak;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;

		// -------------------------------------
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public int getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	// 출력 // Object와 상관관계를 알고 있으면 toString
	@Override
	public String toString() {

		String str;
		str = String.format("%5s %6s %4d %4d %4d %4d %4d %4d", hak, name, kor,
				eng, mat, tot, avg, rank);
		return str;

	}

}
