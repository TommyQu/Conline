<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/admin/adminpage.jsp"></jsp:include>
<html>
<head>
<title>My JSP 'UploadProblem.jsp' starting page</title>
</head>
<body>
	<form action="admin?action=uploadproblem" method="post">
		<table>
			<tr>
				<td colspan="5" class="title">上传题目</td>
			</tr>
			<tr>
				<td></td>
				<td>题目:</td>
				<td><textarea rows="15" cols="80" name="content"></textarea></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>关键字:</td>
				<td><input type="text" style="height:40px;width:380px;"name="keyword" /></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td><td></td>
				<td><input type="submit" style="height:40px;width:80px;" value="上传" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
</div>
<jsp:include page="/jsp/template/foot.jsp"></jsp:include>