<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp" >
	<jsp:param name="application-title" value="ddd" />
	<jsp:param name="application-left-jsp" value="/jsp/ladder/menu.jsp"/>
</jsp:include>
<link type="text/css" href="css/forum/discussionarea.css" rel="stylesheet" />
<table>
  <tr>
    <td width="250" align="center">章节名</td>
    <td width="300" align="center">章节描述</td>
    <td width="200" align="center">学习人数</td>
    <td width="50" align="center">难度</td>
  </tr>
  <tr>
    <td></td>
  </tr>
</table>
<jsp:include page="/jsp/template/foot.jsp"></jsp:include>