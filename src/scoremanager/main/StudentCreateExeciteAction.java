package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExeciteAction extends Action{

	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		//リクエストパラメーターの取得
		int ent_year = Integer.parseInt(request.getParameter("ent_year"));//入学年度
		String no = request.getParameter("no");//学生番号
		String name = request.getParameter("name");//氏名
		String class_num = request.getParameter("class_num");//クラス番号

		//入学年度の未入力チェック
		if(ent_year == 0){
			request.setAttribute("no", no);
			request.setAttribute("name", name);
			request.setAttribute("class_num", class_num);

			//JSPへフォワード(入学年度未入力)
			request.getRequestDispatcher("StudentCreate.action").forward(request, response);

		}

		//Studentへ登録するデータをセット
		Student stu = new Student();
		HttpSession session = request.getSession();
		stu.setEntYear(ent_year);
		stu.setNo(no);
		stu.setName(name);
		stu.setClassNum(class_num);
		stu.setAttend(true);
		stu.setSchool(((Teacher)session.getAttribute("user")).getSchool());
		StudentDao sdao = new StudentDao();
		boolean flag = sdao.save(stu);

		//JSPへフォワード(登録完了)
		request.getRequestDispatcher("student_create_done.jsp").forward(request,response);

	}


}
