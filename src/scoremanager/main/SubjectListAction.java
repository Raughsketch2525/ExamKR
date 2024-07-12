package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action{
	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

//		School school = teacher.getSchool();
//		List<Subject> subjects = null;//科目リスト

		SubjectDao sDao = new SubjectDao();//科目Dao
		List<Subject> subjects = sDao.filter(teacher.getSchool());


		Map<String, String> errors = new HashMap<>();//エラーメッセージ


		//DBからデータ取得
		//ログインユーザーの学校コードをもとに科目番号の一覧を取得


		//リクエストにセット
		request.setAttribute("subjects",subjects);//科目一覧


		request.getRequestDispatcher("subject_list.jsp").forward(request, response);
	}
}
