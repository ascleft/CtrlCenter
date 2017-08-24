<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>Seed7</title>

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
		<link href="../../img/CodeMartrix/main/weisuomeng.jpg" rel="shortcut icon" />

		<link href="../../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="../../js/materialize.js"></script>
		<script src="../../js/init.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/global/logo/icon_title_1.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

	</head>

	<body>

		<nav class="white" role="navigation">
			<div class="nav-wrapper container">
				<a id="logo-container" href="#" class="brand-logo">鲁泰接口测试中心</a>
				<ul class="right hide-on-med-and-down">
					<li>
						<a href="#">Navbar Link 1</a>
					</li>
				</ul>
				<ul id="nav-mobile" class="side-nav">
					<li>
						<a href="#">Navbar Link 2</a>
					</li>
				</ul>
				<a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i> </a>
			</div>
		</nav>

		<div class="container">

			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="card">
							<div class="card-image">
								<img src="../../img/global/main/title_breast_1.jpg">
								<span class="card-title ">测试数据</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col s12 m12 l12">
						<ul class="tabs tab-demo z-depth-1">
							<li class="tab">
								<a href="#tab1">测试1</a>
							</li>
							<li class="tab">
								<a href="#tab2">库存查询</a>
							</li>
							<li class="tab">
								<a href="#tab3">出库通知单</a>
							</li>
						</ul>
					</div>
					<iframe id="tab1" src="tab_1.html" scrolling="no" frameborder=0 width="100%"></iframe>
					<iframe id="tab2" src="tab_2.html" scrolling="no" frameborder=0 width="100%"></iframe>
					<iframe id="tab3" src="tab_3.html" scrolling="no" frameborder=0 width="100%"></iframe>

				</div>
			</div>

		</div>

		<footer class="page-footer teal ">
			<div class="container" style="text-align: right;">
				<div class="row ">
					<div class="col s12 m12 ">
						<h5 class="white-text ">山东鲁泰集团 K3接口</h5>
					</div>
				</div>
			</div>
			<div class="footer-copyright ">
				<div class="container ">
					Made by ZhangChi 2017 with Framework
					<a class="brown-text text-lighten-3 " href="http://materializecss.com ">Materialize</a>
				</div>
			</div>
		</footer>

	</body>

</html>