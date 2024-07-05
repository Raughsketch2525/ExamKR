package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExeciteAction extends Action{

	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response)throws Exception{

		//処理内容（シーケンス図）

		//変更内容の保存

		int ent_year = Integer.parseInt(request.getParameter("ent_year"));//入学年度の取得
		String no = request.getParameter("no");//学生番号の取得
		String name = request.getParameter("name");//氏名の取得
		String class_num = request.getParameter("class_num");//クラスの取得
		String si_attend = request.getParameter("si_attend");//在学フラグ(在学:on,在学していない:null)
		boolean attend = true;//在学フラグ初期化(true)

		//在学フラグがonの場合
		if(si_attend == "null"){
			attend = false;
		}

		//保存するデータをStudentにセット
		Student stu = new Student();
		stu.setEntYear(ent_year);
		stu.setNo(no);
		stu.setName(name);
		stu.setClassNum(class_num);
		stu.setAttend(attend);

		StudentDao sDao = new StudentDao();
		sDao.save(stu);

		request.getRequestDispatcher("student_update_done.jsp").forward(request,response);
	}

}
