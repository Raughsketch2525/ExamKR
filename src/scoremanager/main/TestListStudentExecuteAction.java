package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

	//クラス番号一覧取得

		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		List<String> class_list = cNumDao.filter(teacher.getSchool());

	//入学年度のリストデータ作成
		LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		List<Integer>entYearSet = new ArrayList<>();
		//10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 10; i++) {
			entYearSet.add(i);
		}


	//科目リストデータ作成
		SubjectDao sDao = new SubjectDao();
		List<Subject> subjects = sDao.filter(teacher.getSchool());//科目の一覧を取得

	//リクエストにデータをセット
		request.setAttribute("class_num_set", class_list);//クラス一覧セット
		request.setAttribute("ent_year_set", entYearSet);//入学年度リストセット
		request.setAttribute("subjects", subjects);//科目一覧セット

		String no = request.getParameter("f4");//学生番号の取得

		if (no != null){

			if(!no.equals("0")){
				System.out.println("入力されています。");
			}

			StudentDao stDao = new StudentDao();
			Student student = stDao.get(no);
			TestListStudentDao tlstDao = new TestListStudentDao();
			List<TestListStudent> tests = tlstDao.filter(student);

			request.setAttribute("student", student);
			request.setAttribute("tests", tests);

		}
		//JSPへフォワード
		request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
	}

}




















