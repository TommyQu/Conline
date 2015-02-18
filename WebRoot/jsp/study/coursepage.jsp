<%@page import="domain.CoFileInfo"%>
<%@page import="domain.CoCourseChapter"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/head.jsp">
	<jsp:param name="application-title" value="Course" />
	<jsp:param name="application-left-jsp" value="/jsp/study/menu.jsp" />
</jsp:include>
<link href="jplayer/skin/blue.monday/jplayer.blue.monday.css"
	rel="stylesheet" type="text/css" />
	<link href="css/study/coursepage.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="jplayer/js/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="js/study/coursepage.js"></script>

<div id="mediaplayer">
	<div id="jp_container_1" class="jp-video jp-video-360p">
		<div class="jp-type-single">
			<div id="jquery_jplayer_1" class="jp-jplayer"></div>
			<div class="jp-gui">
				<div class="jp-video-play">
					<a href="javascript:;" class="jp-video-play-icon" tabindex="1">play</a>
				</div>
				<div class="jp-interface">
					<div class="jp-progress">
						<div class="jp-seek-bar">
							<div class="jp-play-bar"></div>
						</div>
					</div>
					<div class="jp-current-time"></div>
					<div class="jp-duration"></div>
					<div class="jp-controls-holder">
						<ul class="jp-controls">
							<li><a href="javascript:;" class="jp-play" tabindex="1">play</a>
							</li>
							<li><a href="javascript:;" class="jp-pause" tabindex="1">pause</a>
							</li>
							<li><a href="javascript:;" class="jp-stop" tabindex="1">stop</a>
							</li>
							<li><a href="javascript:;" class="jp-mute" tabindex="1"
								title="mute">mute</a>
							</li>
							<li><a href="javascript:;" class="jp-unmute" tabindex="1"
								title="unmute">unmute</a>
							</li>
							<li><a href="javascript:;" class="jp-volume-max"
								tabindex="1" title="max volume">max volume</a>
							</li>
						</ul>
						<div class="jp-volume-bar">
							<div class="jp-volume-bar-value"></div>
						</div>
						<ul class="jp-toggles">
							<li><a href="javascript:;" class="jp-full-screen"
								tabindex="1" title="full screen">full screen</a>
							</li>
							<li><a href="javascript:;" class="jp-restore-screen"
								tabindex="1" title="restore screen">restore screen</a>
							</li>
							<li><a href="javascript:;" class="jp-repeat" tabindex="1"
								title="repeat">repeat</a>
							</li>
							<li><a href="javascript:;" class="jp-repeat-off"
								tabindex="1" title="repeat off">repeat off</a>
							</li>
						</ul>
					</div>
					<div class="jp-title">
						<ul>
							<li>C Online Study</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%
  List<CoFileInfo> lcfi=(List<CoFileInfo>)request.getAttribute("lcfi");
  int i=0;
 %>
<%
 for(i=0;i<lcfi.size();i++)
 {
%>
<div class="outside">
<div class="course">
<ul>
  <li><a href="#1">第一单元</a></li>
  <li href="study?action=getonecourse&id" width="1000px";>文件名:<%=lcfi.get(i).getFileName() %></li>
  <li>下载量:<%=lcfi.get(i).getDownCount() %></li>
  <li>创建时间 :<%=lcfi.get(i).getCreateTime() %></li>
</ul>
</div>
</div>
  <%
    }
   %>
   </div>
   <div class="pagenumber"><br />
<a>当前第<%=request.getAttribute("page") %>页</a>
<br /><br />
<a href="study?action=tocoursepage&flag=up&page=<%=request.getAttribute("page") %>">上一页</a>
<a href="study?action=tocoursepage&flag=down&page=<%=request.getAttribute("page") %>">下一页</a>
</div>
<jsp:include page="/jsp/template/foot.jsp"></jsp:include>