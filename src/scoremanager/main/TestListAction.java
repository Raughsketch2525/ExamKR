package scoremanager.main;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String entYearStr="";//入力された入学年度
		String classNum="";//入力されたクラス番号
		int entYear = 0;//入学年度

		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメーターの取得
		//entYearStr = request.getParameter("f1");
		//classNum = request.getParameter("f2");
		//isAttendStr = request.getParameter("f3");

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

		request.getRequestDispatcher("test_list.jsp").forward(request, response);
	}






}

