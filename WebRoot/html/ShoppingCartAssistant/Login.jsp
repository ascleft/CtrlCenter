<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<!--
		
		作者：ascleft@163.com
		时间：2017-11-20
		描述：
		购物车添加工具 SCA 2.0
		
		登录
		
	-->

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>优纤下单工具</title>

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

		<script src="../../js/init_sca.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/global/logo/icon_title_1.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

		<script src="<%=path %>/js/vue.min.js"></script>

		<script src="<%=path %>/js/init_sca.js"></script>

		<script type="application/javascript">
			var url_login = "/CtrlCenter/LTYX/SCA/Login.action";

			function checkFormInput() {
				if($('#name').val().length == 0) {
					Materialize.toast('请输入账户', 1000);
				} else if($('#pwd').val().length == 0) {
					Materialize.toast('请输入密码', 1000);
				} else {
					submit();
				}
			}

			function submit() {
				$.ajax({
					cache: true,
					type: "POST",
					url: url_login,
					data: $('#userform').serialize(),
					async: true,
					error: function(request) {
						Materialize.toast('网络异常，无法连接到服务器', 1000);
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE) {
							if("succ" == resp.ERRDESC) {
								Materialize.toast("登录成功", 1000);
								setTimeout("javascript:location.href='" + resp.data + "'", 1000);
							} else {
								Materialize.toast(resp.data, 1000);
							}
						} else {
							Materialize.toast("EC服务器通讯异常", 1000);
						}
					}
				});
			}
		</script>

	</head>

	<body>

		<header>
			<nav class="top-nav teal">
				<div class="container">
					<div class="nav-wrapper">
						<a class="page-title">登录优纤下单工具</a>
					</div>
				</div>
			</nav>
		</header>

		<main>
			<div class="container">
				<div class="section">
					<div class="card-panel">
						<div class="row">
							<div class=" col s12 m12 l12">
								<form method="post" id="userform">
									<div class="row">
										<div class="col s12 m4 l4">
											<div class="input-field">
												<input id="name" type="text" class="validate" name="name" value="">
												<label>账户</label>
											</div>
										</div>
										<div class="col s12 m4 l4">
											<div class="input-field">
												<input id="pwd" type="password" class="validate" name="pwd" value="">
												<label>密码</label>
											</div>
										</div>
										<div class="input-field col s12 m4 l4">
											<select name="role">
												<option value="2">客户经理</option>
												<option value="1">定制顾问</option>
												<option value="3">定制店</option>
											</select>
											<label>角色</label>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="row">
							<div class="col s12 m12 l12 center">
								<a class="col s12 m8 l6 offset-l3 offset-m2 btn" onclick="checkFormInput()">登录</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>

	</body>

</html>