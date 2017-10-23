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
			function OpenMenu() {
				$('#nav_menu').sideNav('show');
			}
		</script>

	</head>

	<body>

		<header>
			<nav class="teal" role="navigation">
				<div class="nav-wrapper container">
					<!-- 页面标题  -->
					<a id="logo-container " href="#" class="brand-logo white-text ">OpenAPI列表</a>
					<!-- 导航菜单键（运动移动设备） -->
					<a href="#" data-activates="nav_menu_list " class="button-collapse ">
						<i class="material-icons white-text">menu</i>
					</a>
					<!-- 页面右上角菜单  （桌面设备）-->
					<ul id="nav_menu_mobile" class="right hide-on-med-and-down white-text">
						<li>
							<a class="white-text">当前用户:
								<%=ec_user_name%>
							</a>
						</li>
						<li>
							<a class="white-text" onclick="OpenMenu()">操作</a>
						</li>
					</ul>
					<!-- 菜单触发-->
					<a href="#" class="button-collapse" data-activates="nav_menu_list" id="nav_menu"></a>
					<!-- 菜单列表-->
					<ul id="nav_menu_list" class="side-nav teal-text">
						<div class="teal ">
							<li>
								<a class=" white-text">您好:
									<%=ec_user_name%>
								</a>
							</li>
							<li>
								<a class=" white-text" onclick="logout() ">注销 </a>
							</li>
						</div>
						<li>
							<a class="subheader">功能</a>
						</li>
						<li>
							<a class="waves-effect" href="/CtrlCenter/Core/Main.action">首页</a>
						</li>
						<li>
							<a class="waves-effect" href="/CtrlCenter/Core/OpenApiList.action">OpenAPI列表</a>
						</li>
					</ul>
				</div>
			</nav>
		</header>

		<div class="container ">
			<!--<a href="# " data-activates="slide-out " class="button-collapse "><i class="material-icons white-text ">menu</i></a>-->
			<div class="section ">
				<div class="row ">
					<div class="col s12 m12 l12 ">
						<div class="card horizontal ">
							<div class="card-stacked ">
								<div class="card-content ">
									<h5>衬衫下单平台设置</h5>
									<div class="row ">
										<div class="col s6 m6 l6 teal-text ">
											<p>进入页面</p>
											<a href="/CtrlCenter/LTYX/Tailor/TailorForm/GetSettingPage.action ">进入</a>
										</div>
										<div class="col s6 m6 l6 orange-text ">
											<p>数据接口</p>
											<a href="/CtrlCenter/LTYX/Tailor/TailorForm/Setting.action ">提交设置</a>
											<br>
											<a href="/CtrlCenter/LTYX/Tailor/TailorForm/GetState.action ">获取当前设置</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12 ">
						<div class="card horizontal ">
							<div class="card-stacked ">
								<div class="card-content ">
									<h5>衬衫下单工具 平台商品</h5>
									<div class="row ">
										<div class="col s6 m6 l6 teal-text ">
											<p>进入页面</p>
											<a href="/CtrlCenter/LTYX/Tailor/TailorForm/GetPlatformTailorPage.action ">进入</a>
										</div>
										<div class="col s6 m6 l6 orange-text ">
											<p>数据接口</p>
											<a href="/CtrlCenter/LTYX/Tailor/TailorForm/UpdatePlatformTailorPage.action ">获取列表（基于关键词）</a>
											<br>
											<a href="/CtrlCenter/LTYX/Tailor/TailorForm/GetPlatformTailorRTInventory.action ">获取即时库存</a>
											<br>
											<a href=" ">提交订单</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12 ">
						<div class="card horizontal ">
							<div class="card-stacked ">
								<div class="card-content ">
									<h5>OpenAPI接口连通性测试</h5>
									<div class="row ">
										<div class="col s6 m6 l6 teal-text ">
											<p>进入页面</p>
											<a href="/CtrlCenter/LTYX/OpenAPI/GetInterfaceTestPage.action ">进入</a>
										</div>
										<div class="col s6 m6 l6 orange-text ">
											<p>数据接口</p>
											<a href="/CtrlCenter/LTYX/OpenAPI/InterfaceTest.action ">提交参数</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12 ">
						<div class="card horizontal ">
							<div class="card-stacked ">
								<div class="card-content ">
									<h5>OpenAPI上行参数测试</h5>
									<div class="row ">
										<div class="col s6 m6 l6 orange-text ">
											<p>数据接口</p>
											<a href="/CtrlCenter/LTYX/OpenAPI/ShowParam.action ">提交参数</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12 ">
						<div class="card horizontal ">
							<div class="card-stacked ">
								<div class="card-content ">
									<h5>聊天机器人</h5>
									<div class="row ">
										<div class="col s6 m6 l6 teal-text ">
											<p>进入页面</p>
											<a href="/CtrlCenter/Talk/GetTalkPage.action ">进入</a>
										</div>
										<div class="col s6 m6 l6 orange-text ">
											<p>数据接口</p>
											<a href="/CtrlCenter/Talk/Talk.action ">提交信息</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col s12 m12 l12 ">
						<div class="card horizontal ">
							<div class="card-stacked ">
								<div class="card-content ">
									<h5>随机串获取</h5>
									<div class="row ">
										<div class="col s6 m6 l6 teal-text ">
											<p>进入页面</p>
											<a href="/CtrlCenter/Talk/GetRandomWordPage.action ">进入</a>
										</div>
										<div class="col s6 m6 l6 orange-text ">
											<p>数据接口</p>
											<a href="/CtrlCenter/Talk/GetRandomWord.action ">提交信息</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<footer class="page-footer teal ">
			<div class="container ">
				<div class="row " style="display: none; ">
					<h5 class="center-align ">					
						<img class="hoverable "
						src="http://pan.baidu.com/share/qrcode?w=150&h=150&url=http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action "/>
					</h5>
				</div>
			</div>
			<div class="footer-copyright ">
				<div class="container ">Made By ZhangChi 2017</div>
			</div>
		</footer>
	</body>

</html>