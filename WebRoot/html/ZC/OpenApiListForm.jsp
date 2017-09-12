<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
	
	String ec_user_id=(String) session.getAttribute("ec_user_id");
	String ec_user_name=(String) session.getAttribute("ec_user_name");
	String ec_user_rank=(String) session.getAttribute("ec_user_rank");
	
	String menulist=(String) session.getAttribute("menulist");
	
	
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
		<title>OpenAPI列表</title>

		<!-- CDN  -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery  -->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->

		<!-- local html  -->
		<link href="../../img/global/logo/icon_title_1.jpg" rel="shortcut icon" />

		<link href="../../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="../../js/materialize.js"></script>
		<script src="../../js/init.js"></script>

		<script src="../../js/vue.min.js"></script>

		<script src="../../js/init_tailorinfo.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/global/logo/icon_title_1.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

		<script src="<%=path %>/js/vue.min.js"></script>

		<script src="<%=path %>/js/init_tailorinfo.js"></script>

		<script type="application/javascript">
		</script>

	</head>

	<body>
		<!--宽屏模式下拉框菜单-->
		<ul id="dropdown1" class="dropdown-content">
			<%= menulist %>
		</ul>
		<nav class="teal" role="navigation">
			<div class="nav-wrapper container">
				<a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons white-text">menu</i></a>
				<a id="logo-container" href="#" class="brand-logo white-text">OpenAPI列表</a>
				<ul class="right hide-on-med-and-down">
					<li>
						<a class="white-text" href="#">您好:
							<%=ec_user_name%>
						</a>
					</li>
					<li>
						<a class="white-text" onclick="logout()">注销 </a>
					</li>
					<li>
						<a class="white-text dropdown-button" data-activates="dropdown1">操作选择</a>
					</li>
				</ul>
				<ul id="nav-mobile" class="side-nav">
					<li class="teal">
						<a class="white-text" href="#">您好:
							<%=ec_user_name%>
						</a>
					</li>
					<li class="teal lighten-1">
						<a class="white-text" onclick="logout()">注销 </a>
					</li>
					<%= menulist %>
				</ul>
			</div>
		</nav>

		<div class="container">
			<a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons white-text">menu</i></a>
			<div class="section">
				<div class="row">

					<div class="col s12 m12 l12">
						<div class="card horizontal">
							<div class="card-image">
								<img src="">
							</div>
							<div class="card-stacked">
								<div class="card-content">
									<p>衬衫下单平台设置</p>
									<a href="/CtrlCenter/LTYX/OpenAPI/ShowParam.action">进入</a>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12">
						<div class="card horizontal">
							<div class="card-image">
								<img src="">
							</div>
							<div class="card-stacked">
								<div class="card-content">
									<p>OpenAPI上行参数测试</p>
									<a href="/CtrlCenter/LTYX/OpenAPI/ShowParam.action">进入</a>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12">
						<div class="card horizontal">
							<div class="card-image">
								<img src="">
							</div>
							<div class="card-stacked">
								<div class="card-content">
									<p>OpenAPI接口连通性测试</p>
									<a href="/CtrlCenter/LTYX/OpenAPI/GetInterfaceTestPage.action">进入</a>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12">
						<div class="card horizontal">
							<div class="card-image">
								<img src="">
							</div>
							<div class="card-stacked">
								<div class="card-content">
									<p>聊天机器人</p>
									<a href="/CtrlCenter/Talk/GetTalkPage.action">进入</a>
								</div>
							</div>
						</div>
					</div>


					<div class="col s12 m12 l12">
						<div class="card horizontal">
							<div class="card-image">
								<img src="">
							</div>
							<div class="card-stacked">
								<div class="card-content">
									<p>随机串获取</p>
									<a href="/CtrlCenter/Talk/GetRandomWordPage.action">进入</a>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>

	

		<footer class="page-footer teal">
			<div class="container">
				<div class="row" style="display: none;">
					<h5 class="center-align">					
						<img class="hoverable"
						src="http://pan.baidu.com/share/qrcode?w=150&h=150&url=http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action"/>
					</h5>
				</div>
			</div>
			<div class="footer-copyright">
				<div class="container">Made By ZhangChi 2017</div>
			</div>
		</footer>
	</body>

</html>