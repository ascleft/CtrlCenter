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
		<!--<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>-->
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->
		<!-- Vue.js  -->
		<!--<script src="../../js/vue.min.js"></script>-->

		<!-- YXN  -->
		<script src="../js/jquery-3.2.1.min.js"></script>
		<script src="<%=path %>/js/jquery-3.2.1.min.js"></script>

		<!-- local html  -->
		<link href="../css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<script src="../js/materialize.js"></script>
		<script src="../js/init.js"></script>

		<script src="../js/init_sca.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

		<script src="<%=path %>/js/init_sca.js"></script>

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
								<h5>如果你是客户经理，那你应该先退出客户的账户，才能进到客户经理的页面，对不对，毕竟自己不能同时既是顾客，又是经理。</h5>
								<h5>大家都知道我这么体贴，肯定是替你准备了便捷操作的。</h5>
								<h5><a class="red-text bi" href="http://www.uskin.net.cn:8080/CtrlCenter/LTYX/SCA/Logout.action">点这里，可以快速注销并登录系统</a></h5>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>