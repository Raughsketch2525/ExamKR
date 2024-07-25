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


public class SubjectCreateExeciteAction extends Action{

	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession();//セッション開始
		Teacher teacher = (Teacher)session.getAttribute("user");

		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメーターの取得
		String cd = request.getParameter("cd");//科目コード
		String name = request.getParameter("name");//科目名

		//Subjectへ登録するデータをセット
		Subject subject = new Subject();

		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());

		SubjectDao sDao = new SubjectDao();
		List<Subject> subjects = sDao.filter(teacher.getSchool());
		for(Subject scd:subjects){
			if(scd.getCd().equals(cd)){
				errors.put("f1", "科目コードが重複しています");
				request.setAttribute("errors", errors);
				request.setAttribute("cd", cd);
				request.setAttribute("name", name);

				request.getRequestDispatcher("subject_create.jsp").forward(request,response);
			}
		}


		boolean flag = sDao.save(subject);


		//JSPへフォワード(登録完了)
		request.getRequestDispatcher("subject_create_done.jsp").forward(request,response);
	}
}



