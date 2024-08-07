package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class SubjectCreateAction extends Action{

	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response)throws Exception{

		//処理内容(シーケンス図から)
		//セッションデータを取得
		HttpSession session = request.getSession();//セッション開始
		Teacher teacher = (Teacher)session.getAttribute("user");

		//セレクトボックス用のクラス番号一覧の取得
		ClassNumDao class_num = new ClassNumDao();
		List<String> class_list = class_num.filter(teacher.getSchool());

		//student_create.jspの入学年度リストの取得
		LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		//リストの初期化
		List<Integer>entYearSet = new ArrayList<>();
		//10年前から10年後まで年をリストに追加
		for(int i = year - 10;i < year + 11; i++){
			entYearSet.add(i);
		}
		//レスポンス値をセット
		//リクエストに入学年度をセット
		request.setAttribute("ent_year_set",entYearSet);

		//リクエストにデータをセット
		request.setAttribute("class_select",class_list);

		//JSPへフォワード
		request.getRequestDispatcher("subject_create.jsp").forward(request,response);
	}
}