<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/admin/adminpage.jsp" ></jsp:include>
  <form action="admin?action=uploadfile" method="post" enctype="multipart/form-data">
    <table>
  <tr><td colspan="5" class="title">上传资料</td></tr>
  <tr>
  	<td></td>
  	     <td></td>
     <td><input type="file" name="attach"></td>
     <td><input type="submit" value="确认"></td>
     <td></td>
  </tr>
  </table>
  </form>
</div>
<jsp:include page="/jsp/template/foot.jsp" ></jsp:include>