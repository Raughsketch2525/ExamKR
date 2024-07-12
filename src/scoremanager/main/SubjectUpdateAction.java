package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;


public class SubjectUpdateAction extends Action{
	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{


		//処理内容(シーケンス図から)
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();

		//学生の詳細データを取得
		String cd = request.getParameter("cd");//変更対象の学生番号の取得

		SubjectDao sDao = new SubjectDao();
		Subject sub = sDao.get(cd,teacher.getSchool());//変更対象の学生詳細データを取得

		//クラスの一覧を取得



		List<Subject> subject_list = sDao.filter(teacher.getSchool());

		//画面表示
		request.setAttribute("sub_date", sub);
		request.setAttribute("subject_list", subject_list);

		//jspへフォワード
		request.getRequestDispatcher("subject_update.jsp").forward(request, response);


	}
}
