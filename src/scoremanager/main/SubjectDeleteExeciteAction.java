package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExeciteAction extends Action{

	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response)throws Exception{

		//処理内容（シーケンス図）
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		//変更内容の保存


		String cd = request.getParameter("cd");//科目コードの取得
		String name = request.getParameter("name");//科目名の取得


		//保存するデータをSubjectにセット
		Subject sub  = new Subject();
		sub.setCd(cd);
		sub.setName(name);
		sub.setSchool(school);

		SubjectDao sDao = new SubjectDao();
		sDao.delete(sub);

		request.getRequestDispatcher("subject_delete_done.jsp").forward(request,response);
	}

}
