<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧(学生)</h2>
			<div class="my-2 text-end px-4">

			</div>

			<form action="TestListSubjectExecute.action" method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					科目情報
					<div class="col-2">

						<label class="form-label" for="student-f1-select">入学年度 </label>
						<select class="form-select " id="student-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select " id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label" for="student-f3-select">科目</label>
						<select class="form-select " id="student-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subjects}">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					</div>
				</form>

			<form action="TestListStudentExecute.action" method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					学生番号
					<div class="col-2">
						<input type="text" name="f4" value="${student.no}" maxlength="10" placeholder="学生番号を入力してください" required>
					</div>
					<div class="col-4 text-center">
								<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
				</div>

				<div class="mt-2 text-warning">${errors.get("filter")}</div>
			</form>
<!-- 学生別成績表示処理 -->
			<c:choose>
				<c:when test="${tests.size()>0}">
					<div>氏名:${student.name} (${student.no})</div>
					<table class="table table-hover">
						<tr>
							<th>科目名</th>
							<th>科目コード</th>
							<th>回数</th>
							<th>点数</th>

						</tr>
						<c:forEach var="test" items="${tests}">
							<tr>
								<td>${test.subjectName}</td>
								<td>${test.subjectCd}</td>
								<td>${test.num}</td>
								<td>${test.point}</td>

							</tr>
						</c:forEach>
						</table>

				</c:when>
				<c:otherwise>
				<p>対象の成績データがありません。</p>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>