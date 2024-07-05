package bean;

import java.io.Serializable;

public class Student extends User implements Serializable {

	private String no;//学生番号
	private String name;//氏名
	private int entYear;//入学年度
	private String classNum;//クラス番号
	private boolean isAttend;//在籍フラグ
	private School school;//school→beanの学校名と学校コード


	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String class_num) {
		this.classNum = class_num;
	}

	public boolean isAttend() {
		return isAttend;
	}

	public void setAttend(boolean isAttend) {
		this.isAttend = isAttend;
	}


	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public int getSchoolYear() {
		return 0;
	}
}
