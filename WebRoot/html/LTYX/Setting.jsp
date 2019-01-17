<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
		<title>服务器设置</title>

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
		<script src="../../js/jquery-3.2.1.min.js"></script>
		<script src="<%=path %>/js/jquery-3.2.1.min.js"></script>

		<!-- local html  -->
		<link href="../../css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<script src="../../js/materialize.js"></script>
		<script src="../../js/init.js"></script>
		
		<script src="../../js/init_sca.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>
		
		<script src="<%=path %>/js/init_sca.js"></script>

		<script type="application/javascript">
			var url_getstate = "/CtrlCenter/LTYX/Core/GetSettingState.action";
			var url_setting =  "/CtrlCenter/LTYX/Core/Setting.action";

			var p_systeminfo;
			var systeminfo;

			function submitsetting() {
				$.ajax({
					cache: true,
					type: "POST",
					url: url_setting,
					data: $('#settingform').serialize(),
					async: true,
					error: function(request) {
						Materialize.toast('无法连接到服务器', 1000);
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE && "succ" == resp.ERRDESC) {
							Materialize.toast(resp.data, 1000);
							getstate();
						} else {
							Materialize.toast(resp.data, 1000);
						}
					}
				});
			}

			function getstate() {
				$.ajax({
					cache: true,
					type: "POST",
					url: url_getstate,
					data: $('#settingform').serialize(),
					async: true,
					error: function(request) {
						Materialize.toast('无法连接到服务器', 1000);
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE && "succ" == resp.ERRDESC) {
							systeminfo = resp.data;
							update();
						} else {
							Materialize.toast(resp.data, 1000);
						}
					}
				});
			}

			function update() {
				p_systeminfo.innerHTML = systeminfo;
			}

			$().ready(function() {
				p_systeminfo = document.getElementById("p_systeminfo");
				getstate();
			})
		</script>

	</head>

	<body>
		<nav class="top-nav teal">
			<div class="container">
				<div class="nav-wrapper">
					<a class="page-title">服务器设置</a>
				</div>
			</div>
		</nav>
		<div class="container">
			<div class="section">
				<div class="card-panel">
					<div class="row">
						<div class=" col s12 m12 l12">
							<p id="p_systeminfo">
							</p>
						</div>
					</div>
				</div>
				<div class="card-panel">
					<div class="row">
						<div class=" col s12 m12 l12">
							<form method="post" id="settingform">
								<div class="row">
									<div class="input-field col s12 m6 l6">
										<select name="url">
											<option value="http://www.utailor.com.cn">正式服务器 http://www.utailor.com.cn</option>
											<option value="http://tool.uskin.net.cn/">测试服务器 http://tool.uskin.net.cn/</option>
										</select>
										<label>目标服务器</label>
									</div>
									<div class="input-field col s12 m6 l6">
										<select name="dbpwd">
											<option value="jycsFactal150428!">正式库</option>
											<option value="junyi000726">测试库</option>
										</select>
										<label>数据库密码</label>
									</div>
									<div class="col s12 m12 l12">
										<div class="input-field">
											<input id="pwd" type="password" class="validate" name="pwd" value="">
											<label>密码</label>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col s12 m12 l12 center">
							<a id="submit" class="col s12 m8 l6 offset-l3 offset-m2 waves-effect waves-light btn" onclick="submitsetting()">确认修改</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>