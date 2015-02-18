<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/admin/adminpage.jsp" ></jsp:include>
<form action="admin?action=notify" method="post">
  <table>
  <tr><td colspan="5" class="title">邮件通知</td></tr>
  <tr><td>
<textarea rows="10" cols="50" name="content"></textarea></td></tr>
<tr><td>
<input type="submit" value="确认" /></td></tr>
</table>
</form>

</div>
<jsp:include page="/jsp/template/foot.jsp" ></jsp:include>