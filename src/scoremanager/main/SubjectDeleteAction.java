package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;


public class SubjectDeleteAction extends Action{
	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{


		//処理内容(シーケンス図から)
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();

		//科目の詳細データを取得
		String cd = request.getParameter("cd");//削除対象の科目コードの取得

		SubjectDao sDao = new SubjectDao();
//		Subject sub = sDao.get(cd,teacher.getSchool());//変更対象の科目コードデータを取得
		Subject sub = sDao.get(cd,school);


		//画面表示
		request.setAttribute("sub_date",sub);


		//jspへフォワード
		request.getRequestDispatcher("subject_delete.jsp").forward(request, response);


	}
}
