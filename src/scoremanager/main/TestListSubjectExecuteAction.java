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
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{
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


		//リクエストパラメーターの取得
				String entYearStr = request.getParameter("f1");
				String classNum = request.getParameter("f2");
				String subject = request.getParameter("f3");


				if (entYearStr != null && classNum !=null && subject !=null){

					if (!entYearStr.equals("0") && !classNum.equals("0") && !subject.equals("0")){
						System.out.println("全パラメータが指定されています");


						Subject subject_set = sDao.get(subject, teacher.getSchool());

						int entYear = Integer.parseInt(entYearStr);
						TestListSubjectDao tlsDao = new TestListSubjectDao();
						List<TestListSubject> tests = tlsDao.filter(entYear, classNum, subject_set,teacher.getSchool());


						request.setAttribute("subject", subject_set);
						request.setAttribute("tests", tests);

					}else{
						Map<String, String>errors = new HashMap<>();
						errors.put("filter", "入学年度とクラスと科目と回数を選択してください");
						request.setAttribute("errors", errors);
					}

				}


				//JSPへフォワード
				request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
	}






}













