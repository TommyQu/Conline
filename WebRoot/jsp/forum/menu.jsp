<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/user/menu.css" />
		<ul id="nav">
		<li><a>讨论区</a>
		<ul>
		<li><a href="user?action=todiscussionarea&page=0">帖子列表</a></li>
		<li><a href="post?action=tonewpostpage&keyword=discussion">发布帖子</a></li>
		<li><a href="user?action=tosearchpostpage&keyword=discussion&page=0">搜索帖子</a></li>
		</ul>
		</li>
		<li><a>灌水区</a>
		<ul>
		<li><a href="user?action=tofreearea&page=0">帖子列表</a></li>
		<li><a href="post?action=tonewpostpage&keyword=free">发布帖子</a></li>
		<li><a href="user?action=tosearchpostpage&keyword=free&page=0">搜索帖子</a></li>
		</ul>
		</li>
		<li><a>下载区</a>
		<ul>
		<li><a href="user?action=todownloadarea&page=0">资源列表</a></li>
		<li><a href="user?action=tosearchpostpage&keyword=download&page=0">搜索资源</a></li>
		</ul>
	</ul>