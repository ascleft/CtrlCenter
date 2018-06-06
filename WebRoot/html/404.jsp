<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<!--
                            	作者：ascleft@163.com
                            	时间：2017-04-29
                            	描述：
                            -->

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>404</title>

		<!-- CDN  -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery  -->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<!--  Vue.js-->
		<!--<script src="https://unpkg.com/vue/dist/vue.js"></script>-->
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->

		<!-- local html  -->
		<link href="../img/CodeMartrix/main/weisuomeng.jpg" rel="shortcut icon" />

		<link href="../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="../js/materialize.js"></script>
		<script src="../js/init.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/global/logo/icon_title_1.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

	</head>

	<body>
		<nav class="top-nav teal">
			<div class="container">
				<div class="nav-wrapper">
					<a class="page-title">404</a>
				</div>
			</div>
		</nav>
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m8 l8 offset-m2 offset-l2">
						<div class="card hoverable">
							<div class="card-image">
								<img src="<%=path %>/img/global/logo/err_404.jpg">
								<span class="card-title "></span>
							</div>
							<div class="card-content">
								<h5>别自己胡搞，链接地址必须要合法，服务器已经对非法请求做过处理了。</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>